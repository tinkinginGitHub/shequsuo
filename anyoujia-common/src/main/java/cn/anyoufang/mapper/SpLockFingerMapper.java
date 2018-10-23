package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpLockFinger;
import cn.anyoufang.entity.SpLockFingerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpLockFingerMapper {
    int countByExample(SpLockFingerExample example);

    int deleteByExample(SpLockFingerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpLockFinger record);

    int insertSelective(SpLockFinger record);

    List<SpLockFinger> selectByExample(SpLockFingerExample example);

    SpLockFinger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpLockFinger record, @Param("example") SpLockFingerExample example);

    int updateByExample(@Param("record") SpLockFinger record, @Param("example") SpLockFingerExample example);

    int updateByPrimaryKeySelective(SpLockFinger record);

    int updateByPrimaryKey(SpLockFinger record);
}