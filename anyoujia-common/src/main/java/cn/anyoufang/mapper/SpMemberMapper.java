package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberMapper {
    int countByExample(SpMemberExample example);

    int deleteByExample(SpMemberExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(SpMember record);

    int insertSelective(SpMember record);

    List<SpMember> selectByExampleWithBLOBs(SpMemberExample example);

    List<SpMember> selectByExample(SpMemberExample example);

    SpMember selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") SpMember record, @Param("example") SpMemberExample example);

    int updateByExampleWithBLOBs(@Param("record") SpMember record, @Param("example") SpMemberExample example);

    int updateByExample(@Param("record") SpMember record, @Param("example") SpMemberExample example);

    int updateByPrimaryKeySelective(SpMember record);

    int updateByPrimaryKeyWithBLOBs(SpMember record);

    int updateByPrimaryKey(SpMember record);
}