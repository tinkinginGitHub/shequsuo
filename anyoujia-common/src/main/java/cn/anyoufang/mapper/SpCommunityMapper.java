package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpCommunity;
import cn.anyoufang.entity.SpCommunityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpCommunityMapper {
    int countByExample(SpCommunityExample example);

    int deleteByExample(SpCommunityExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(SpCommunity record);

    int insertSelective(SpCommunity record);

    List<SpCommunity> selectByExample(SpCommunityExample example);

    SpCommunity selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") SpCommunity record, @Param("example") SpCommunityExample example);

    int updateByExample(@Param("record") SpCommunity record, @Param("example") SpCommunityExample example);

    int updateByPrimaryKeySelective(SpCommunity record);

    int updateByPrimaryKey(SpCommunity record);
}