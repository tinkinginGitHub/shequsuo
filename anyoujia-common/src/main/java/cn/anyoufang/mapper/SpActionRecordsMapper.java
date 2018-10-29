package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpActionRecords;
import cn.anyoufang.entity.SpActionRecordsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpActionRecordsMapper {
    int countByExample(SpActionRecordsExample example);

    int deleteByExample(SpActionRecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpActionRecords record);

    int insertSelective(SpActionRecords record);

    List<SpActionRecords> selectByExample(SpActionRecordsExample example);

    SpActionRecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpActionRecords record, @Param("example") SpActionRecordsExample example);

    int updateByExample(@Param("record") SpActionRecords record, @Param("example") SpActionRecordsExample example);

    int updateByPrimaryKeySelective(SpActionRecords record);

    int updateByPrimaryKey(SpActionRecords record);
}