package cn.anyoufang.service;

import java.util.List;
import java.util.Map;

import cn.anyoufang.entity.TbCode;

/**
 * 获取单值代码
 * CodeService
 * @author wantianwu
 * @version 1.0
 *
 */
public interface CodeService {
    public String getCodeName(String code);

    public List<Map<String, Object>> getSubCode(String pcode);

    public List<Map<String, Object>> getCodes(String[] codes);

    public List<Map<String, Object>> getAreas();

    public List<Map<String, Object>> getSubway();

    List<TbCode> getCodes(String subcode);
}
