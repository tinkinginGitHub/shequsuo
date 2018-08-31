package cn.anyoufang.service;

import cn.anyoufang.entity.Area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author
 * @email
 * @date 2017-08-10 16:00:04
 */
public interface AreaService {

	Area queryObject(String areaId);

	List<Area> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(Area area);

	void update(Area area);

	void delete(String areaId);

	void deleteBatch(String[] areaIds);

	List<Area> getAreaListByIsShow(HashMap<String, Object> paraMap);

	int getCount(Map<String, Object> params);

	void updateState(String[] ids, String stateValue);

	String getAreaNameStr(String area_id_str);

    List<Area> findByParentId(String parentAreaId);
}