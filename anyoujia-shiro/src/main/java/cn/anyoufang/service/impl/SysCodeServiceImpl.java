package cn.anyoufang.service.impl;

import cn.anyoufang.dao.SysCodeDao;
import cn.anyoufang.entity.SysCode;
import cn.anyoufang.service.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysCodeService")
public class SysCodeServiceImpl implements SysCodeService {
	@Autowired
	private SysCodeDao sysCodeDao;
	
	@Override
	public SysCode queryObject(Long id){
		return sysCodeDao.queryObject(id);
	}
	
	@Override
	public List<SysCode> queryList(Map<String, Object> map){
		return sysCodeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysCodeDao.queryTotal(map);
	}
	
	@Override
	public void save(SysCode sysCode){
		sysCodeDao.save(sysCode);
	}
	
	@Override
	public void update(SysCode sysCode){
		sysCodeDao.update(sysCode);
	}
	
	@Override
	public void delete(Long id){
		sysCodeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysCodeDao.deleteBatch(ids);
	}

	@Override
	public List<SysCode> getCodeValues(Map<String, Object> params) {
		return sysCodeDao.getCodeValues(params);
	}

	@Override
	public List<SysCode> findByVerify(SysCode sysCode) {
		return sysCodeDao.findByVerify(sysCode);
	}

}
