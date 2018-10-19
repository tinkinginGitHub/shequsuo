package cn.anyoufang.service.impl;

import cn.anyoufang.entity.selfdefined.Data;
import cn.anyoufang.entity.selfdefined.InitParam;
import cn.anyoufang.service.HandlePhpService;
import cn.anyoufang.utils.AesCBC;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiping
 */
@Service
public class HandlePhpServiceImpl implements HandlePhpService {

    private static final String url = "http://144.anyoujia.com/Product/Api/index/";
    private static final String salt = "575gh5rr556Dfhr67Ohrt8";
    private static final String postParam = "sp=";

    @Override
    public String handlePostRequestPhp(String mod, String fun,Map<String,String> data) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("parentid","0");
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        String result =  sb.append(mod).append(fun).append(json).append(salt).toString().replaceAll("\t|\n\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod(mod);
        p.setFun(fun);
        p.setSign(sign);
//        Map<String,String> data = new HashMap<>();
//        data.put("parentid","0");
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = postParam + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Product/Api/index/",param);
        return response;
    }
}
