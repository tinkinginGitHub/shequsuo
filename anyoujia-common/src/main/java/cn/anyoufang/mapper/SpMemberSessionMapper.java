package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberSession;
import cn.anyoufang.entity.SpMemberSessionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberSessionMapper {
    int countByExample(SpMemberSessionExample example);

    int deleteByExample(SpMemberSessionExample example);

    int deleteByPrimaryKey(Integer lid);

    int insert(SpMemberSession record);

    int insertSelective(SpMemberSession record);

    List<SpMemberSession> selectByExample(SpMemberSessionExample example);

    SpMemberSession selectByPrimaryKey(Integer lid);

    int updateByExampleSelective(@Param("record") SpMemberSession record, @Param("example") SpMemberSessionExample example);

    int updateByExample(@Param("record") SpMemberSession record, @Param("example") SpMemberSessionExample example);

    int updateByPrimaryKeySelective(SpMemberSession record);

    int updateByPrimaryKey(SpMemberSession record);
}