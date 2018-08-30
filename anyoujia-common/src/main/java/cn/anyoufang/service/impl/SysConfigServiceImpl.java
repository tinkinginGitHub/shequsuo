package cn.anyoufang.service.impl;

import com.alibaba.fastjson.JSON;
import cn.anyoufang.entity.SysConfig;
import cn.anyoufang.dao.SysConfigDao;
import cn.anyoufang.service.SysConfigService;
import cn.anyoufang.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;
	
	@Override
	public void save(SysConfig config) {
		sysConfigDao.save(config);
	}

	@Override
	public void update(SysConfig config) {
		sysConfigDao.update(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}

	@Override
	public List<SysConfig> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	@Override
	public SysConfig queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if(StringUtils.isBlank(value)){
			return defaultValue;
		}
		return value;
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}

	@Override
	public List<SysConfig> findRule(Map<String, Object> params) {
		return sysConfigDao.findRule(params);
	}

	@Override
	public void setRule(SysConfig config) {
		sysConfigDao.setRule(config);
	}

	@Override
	public List<SysConfig> findByCode(String code) {
		return sysConfigDao.findByCode(code);
	}


}
