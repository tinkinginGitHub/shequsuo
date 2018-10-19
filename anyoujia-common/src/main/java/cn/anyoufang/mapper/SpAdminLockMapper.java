package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpAdminLock;
import cn.anyoufang.entity.SpAdminLockExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpAdminLockMapper {
    int countByExample(SpAdminLockExample example);

    int deleteByExample(SpAdminLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpAdminLock record);

    int insertSelective(SpAdminLock record);

    List<SpAdminLock> selectByExample(SpAdminLockExample example);

    SpAdminLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpAdminLock record, @Param("example") SpAdminLockExample example);

    int updateByExample(@Param("record") SpAdminLock record, @Param("example") SpAdminLockExample example);

    int updateByPrimaryKeySelective(SpAdminLock record);

    int updateByPrimaryKey(SpAdminLock record);
}