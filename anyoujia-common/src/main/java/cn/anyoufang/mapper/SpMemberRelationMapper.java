package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.entity.SpMemberRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberRelationMapper {
    int countByExample(SpMemberRelationExample example);

    int deleteByExample(SpMemberRelationExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(SpMemberRelation record);

    int insertSelective(SpMemberRelation record);

    List<SpMemberRelation> selectByExample(SpMemberRelationExample example);

    SpMemberRelation selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") SpMemberRelation record, @Param("example") SpMemberRelationExample example);

    int updateByExample(@Param("record") SpMemberRelation record, @Param("example") SpMemberRelationExample example);

    int updateByPrimaryKeySelective(SpMemberRelation record);

    int updateByPrimaryKey(SpMemberRelation record);
}