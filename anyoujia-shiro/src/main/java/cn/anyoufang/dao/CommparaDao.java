package cn.anyoufang.dao;

import cn.anyoufang.entity.Commpara;
import cn.anyoufang.entity.SysCode;

import java.util.List;
import java.util.Map;

/**
 * 字典管理
 * 
 * @author
 * @email
 * @date 2017-11-06 14:49:28
 */
public interface CommparaDao extends BaseDao<Commpara> {

    List<SysCode> getCodeValues(Map<String, Object> params);

    List<Commpara> findByVerify(Commpara commpara);
}
