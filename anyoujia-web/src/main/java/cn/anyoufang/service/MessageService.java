package cn.anyoufang.service;

import java.util.Map;

/**
 * @author daiping
 * @time 2018-9-5
 */
public interface MessageService {

    String handleTypesOfMessageFromWx(Map<String, String> requestMap ) throws Exception;
}
