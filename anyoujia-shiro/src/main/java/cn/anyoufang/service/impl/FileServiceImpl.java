package cn.anyoufang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import cn.anyoufang.dao.FileDao;
import cn.anyoufang.entity.File;
import cn.anyoufang.service.FileService;




@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao fileDao;
	
	@Override
	public File get(String id){
		return fileDao.get(id);
	}

	@Override
	public List<File> getList(Map<String, Object> map){
		return fileDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fileDao.getCount(map);
	}

	@Override
	public void save(File file){
		fileDao.save(file);
	}

	@Override
	public void update(File file){
		fileDao.update(file);
	}

	@Override
	public void delete(String id){
		fileDao.delete(id);
	}

	@Override
	public void deleteBatch(String[] ids){
		fileDao.deleteBatch(ids);
	}

    @Override
    public void updateState(String[] ids,String stateValue) {
        for (String id:ids){
			File file=get(id);
			//file.setState(stateValue);
            update(file);
        }
    }

	@Override
	public List<File> getByRelationId(String relationId) {
		return fileDao.getByRelationId(relationId);
	}

	@Override
	public List<File> getFileList(String imgUUID) {
		return fileDao.getFileList(imgUUID);
	}

	@Override
	public List<File> getFileListByUUID(List<String> uuidList) {
		return fileDao.getFileListByUUID(uuidList);
	}

	@Override
	public void deleteByRelationId(String relationId) {
		fileDao.deleteByRelationId(relationId);
	}

}
