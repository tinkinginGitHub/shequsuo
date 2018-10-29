package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpLockAdmin;
import cn.anyoufang.entity.SpLockAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpLockAdminMapper {
    int countByExample(SpLockAdminExample example);

    int deleteByExample(SpLockAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpLockAdmin record);

    int insertSelective(SpLockAdmin record);

    List<SpLockAdmin> selectByExample(SpLockAdminExample example);

    SpLockAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpLockAdmin record, @Param("example") SpLockAdminExample example);

    int updateByExample(@Param("record") SpLockAdmin record, @Param("example") SpLockAdminExample example);

    int updateByPrimaryKeySelective(SpLockAdmin record);

    int updateByPrimaryKey(SpLockAdmin record);
}