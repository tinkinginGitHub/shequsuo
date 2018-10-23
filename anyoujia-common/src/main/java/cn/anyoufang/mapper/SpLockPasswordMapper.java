package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpLockPassword;
import cn.anyoufang.entity.SpLockPasswordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpLockPasswordMapper {
    int countByExample(SpLockPasswordExample example);

    int deleteByExample(SpLockPasswordExample example);

    int deleteByPrimaryKey(Integer pwdid);

    int insert(SpLockPassword record);

    int insertSelective(SpLockPassword record);

    List<SpLockPassword> selectByExample(SpLockPasswordExample example);

    SpLockPassword selectByPrimaryKey(Integer pwdid);

    int updateByExampleSelective(@Param("record") SpLockPassword record, @Param("example") SpLockPasswordExample example);

    int updateByExample(@Param("record") SpLockPassword record, @Param("example") SpLockPasswordExample example);

    int updateByPrimaryKeySelective(SpLockPassword record);

    int updateByPrimaryKey(SpLockPassword record);
}