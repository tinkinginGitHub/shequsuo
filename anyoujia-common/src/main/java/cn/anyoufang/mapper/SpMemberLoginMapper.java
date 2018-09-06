package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpMemberLogin;
import cn.anyoufang.entity.SpMemberLoginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberLoginMapper {
    int countByExample(SpMemberLoginExample example);

    int deleteByExample(SpMemberLoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpMemberLogin record);

    int insertSelective(SpMemberLogin record);

    List<SpMemberLogin> selectByExample(SpMemberLoginExample example);

    SpMemberLogin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpMemberLogin record, @Param("example") SpMemberLoginExample example);

    int updateByExample(@Param("record") SpMemberLogin record, @Param("example") SpMemberLoginExample example);

    int updateByPrimaryKeySelective(SpMemberLogin record);

    int updateByPrimaryKey(SpMemberLogin record);
}