package cn.anyoufang.controller;

import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.service.CommentService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.util.FastDFSClient;
import cn.anyoufang.util.SecurityQuestionConstant;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author daiping
 */

@RestController
@RequestMapping("/api")
public class CommonController  extends AbstractController{


    @Autowired
    private CommentService commentService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/questions")
    public List<String> getQuestions() {
        return SecurityQuestionConstant.getQuestions();
    }

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    private Logger logger = LoggerFactory.getLogger(CommonController.class);


    @RequestMapping("/pic/upload")
    public AnyoujiaResult fileUpload(HttpServletRequest request) {
        try {

            MultipartHttpServletRequest mulRequest = request instanceof MultipartHttpServletRequest ? (MultipartHttpServletRequest) request : null;
            if(mulRequest==null){
                return AnyoujiaResult.build(400,"上传失败");
            }
            CommonsMultipartFile uploadFile = null;
            Map<String, Object> map = new HashMap<String, Object>();
            MultipartFile file = null;

            Iterator<String> fileNames = mulRequest.getFileNames();
            if (fileNames.hasNext()) {
                String inputName = fileNames.next();

                uploadFile = (CommonsMultipartFile) mulRequest.getFile(inputName);
                //1、取文件的扩展名
                String originalFilename = uploadFile.getOriginalFilename();
                String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                //2、创建一个FastDFS的客户端
                FastDFSClient fastDFSClient = FastDFSClient.getInstance("classpath:client.conf");
                //3、执行上传处理
                String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
                //4、拼接返回的url和ip地址，拼装成完整的url
                String url = IMAGE_SERVER_URL + path;
                //5、返回map
                Map result = new HashMap<>();
                result.put("error", 0);
                result.put("url", url);
                return AnyoujiaResult.ok(result);
            }
            return AnyoujiaResult.build(500,"上传图片失败");

        } catch (Exception e) {
            logger.info("上传图片失败: "+e.getMessage());
            //5、返回map
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return AnyoujiaResult.build(500, "图片上传失败");
        }
    }

    @RequestMapping("/sendcomment")
    public AnyoujiaResult comment(@RequestParam String comment,
                                  @RequestParam String url,
                                  HttpServletRequest request) {
        System.out.println(comment+"," + url);
        if(StringUtil.isEmpty(comment)&&StringUtil.isEmpty(url)) {
            return AnyoujiaResult.build(400,"请输入有效评论或者上传正确的图片");
        }
        try{
          SpMember user =  getUser(request,loginService);
            if(user != null) {
                 String phone = user.getPhone();
               boolean commentOk =  commentService.saveComments(comment,url,phone);
               if(commentOk) {
                   return AnyoujiaResult.ok();
               }
            }

        }catch (Exception e) {
            return AnyoujiaResult.build(500,"评论失败，系统错误");
        }
        return AnyoujiaResult.build(401,"请登录");
    }

    @PostMapping("/pic/del")
    @CrossOrigin(value = "*")
    @ResponseBody
    public AnyoujiaResult fileDelete(@RequestBody String url) {

        Map<String,Object> ul = JsonUtils.jsonToMap(url);
        String url1 =(String)ul.get("url");
        try{
            if(url1 !=null && !url1.equals("")) {
                String[] s =  url1.split("\\/");
                StringBuilder sb = new StringBuilder();
                for(int i = 1;i<s.length;i++) {
                    sb.append(s[i]);
                    if(i<s.length-1) {
                        sb.append("/");
                    }
                }
                String fileId = sb.toString();
                //2、创建一个FastDFS的客户端
                FastDFSClient fastDFSClient = FastDFSClient.getInstance("classpath:client.conf");
                //执行删除
                //0 for success, none zero for fail (error code)
                int i = fastDFSClient.deletFile(fileId);
                Map result = new HashMap<>();
                if(i == 0) {
                    result.put("error", 0);
                    result.put("message", "图片删除成功");
                    return AnyoujiaResult.ok(result);
                }
            }
        }catch (Exception e) {
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片删除失败");
            return AnyoujiaResult.ok(result);
        }
        return AnyoujiaResult.build(500,"删除失败");
    }

    /**
     * 检查安全密码
     * @param request
     * @param password
     * @return
     */
    @RequestMapping("/common/checkse")
    public AnyoujiaResult checkSecurityPassword(HttpServletRequest request,
                                                @RequestParam(value = "pd") String password) {

        if(StringUtil.isEmpty(password)) {
            return AnyoujiaResult.build(400,"密码不能为空");
        }
        SpMember user = getUser(request,loginService);
        if(user == null) {
            return AnyoujiaResult.build(401,"登录超时");
        }
        boolean ok = commentService.checkSecurityPassword(user.getSecuritypwd(),password);
        if(ok) {
            return AnyoujiaResult.ok();
        }
        return AnyoujiaResult.build(400,"密码错误");
    }

}
