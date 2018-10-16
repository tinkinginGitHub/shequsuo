package cn.anyoufang.service;

import java.util.Map;

/**
 * @author daiping
 */
public interface HandlePhpService {


    String handlePostRequestPhp (String mod,String fun,Map<String,String> data) throws Exception;
}
