package cn.anyoufang.controller;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.service.CommonService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.util.FastDFSClient;
import cn.anyoufang.util.SecurityQuestionConstant;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    private CommonService commonService;

    @Autowired
    private LoginService loginService;

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;


    /**
     * 获取安全问题列表
     * @return
     */
    @RequestMapping("/questions")
    public List<String> getQuestions() {
        return SecurityQuestionConstant.getQuestions();
    }

    /**
     * 评论上传图片请求处理
     * @param request
     * @return
     */
    @RequestMapping("/pic/upload")
    public AnyoujiaResult fileUpload(HttpServletRequest request) {
        try {

            MultipartHttpServletRequest mulRequest = request instanceof MultipartHttpServletRequest ? (MultipartHttpServletRequest) request : null;
            if(mulRequest==null){
                return AnyoujiaResult.build(FOUR_H,"请求失败");
            }
            CommonsMultipartFile uploadFile;
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
            return AnyoujiaResult.build(FOUR_H,"上传图片失败");

        } catch (Exception e) {
            if(logger.isInfoEnabled()) {
                logger.info("上传图片失败: "+e.getMessage());
            }
            //5、返回map
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return AnyoujiaResult.build(FIVE_H, "系统错误",result);
        }
    }

    @RequestMapping("/sendcomment")
    public AnyoujiaResult comment(@RequestParam String comment,
                                  @RequestParam(required = false) String url,
                                  HttpServletRequest request) {
        if(StringUtil.isEmpty(comment)) {
            return AnyoujiaResult.build(FOUR_H,"请输入有效评论");
        }
        try{
          SpMember user =  getUser(request,loginService);
            if(user != null) {
                 String phone = user.getPhone();
                 boolean commentOk =  commonService.saveComments(comment,url,phone);
               if(commentOk) {
                   return AnyoujiaResult.ok();
               }
            }

        }catch (Exception e) {
            if(logger.isInfoEnabled()) {
                logger.info("评论失败: "+e.getMessage());
            }
            return AnyoujiaResult.build(FIVE_H,"评论失败，系统错误");
        }
        return AnyoujiaResult.build(FOUR_H_1,"请登录");
    }

    /**
     * 执行删除图片请求
     * @param url
     * @return
     */
    @RequestMapping("/pic/del")
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
            if(logger.isInfoEnabled()) {
                logger.info("删除图片失败: "+e.getMessage());
            }
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片删除失败");
            return AnyoujiaResult.ok(result);
        }
        return AnyoujiaResult.build(FIVE_H,"删除失败");
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
            return AnyoujiaResult.build(FOUR_H,"密码不能为空");
        }
        SpMember user = getUser(request,loginService);
        if(user == null) {
            return AnyoujiaResult.build(FOUR_H_1,"登录超时");
        }
        boolean ok = commonService.checkSecurityPassword(user.getSecuritypwd(),password);
        if(ok) {
            return AnyoujiaResult.ok();
        }
        return AnyoujiaResult.build(FOUR_H,"密码错误");
    }

    /**
     * 记录用户动作
     * @param locksn
     * @param action
     * @param request
     * @return
     */
    @RequestMapping("/recordaction")
    public AnyoujiaResult recordLockActions(@RequestParam String locksn,
                                            @RequestParam String action,
                                            HttpServletRequest request) {

        SpMember user = getUser(request,loginService);

        if(user == null) {
            return AnyoujiaResult.build(FOUR_H_1,"登录超时");
        }
        int when = DateUtil.generateTenTime();
        commonService.saveActionRecords(locksn,user.getUid(),action,when);
        return AnyoujiaResult.ok();
    }

}
