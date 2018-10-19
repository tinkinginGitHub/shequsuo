package cn.anyoufang.mapper;

import cn.anyoufang.entity.selfdefined.ResultWx;
import cn.anyoufang.entity.SpMemberWx;
import cn.anyoufang.entity.SpMemberWxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpMemberWxMapper {
    int countByExample(SpMemberWxExample example);

    int deleteByExample(SpMemberWxExample example);

    int deleteByPrimaryKey(Integer wx);

    int insert(SpMemberWx record);

    int insertSelective(SpMemberWx record);

    List<SpMemberWx> selectByExample(SpMemberWxExample example);

    SpMemberWx selectByPrimaryKey(Integer wx);

    int updateByExampleSelective(@Param("record") SpMemberWx record, @Param("example") SpMemberWxExample example);

    int updateByExample(@Param("record") SpMemberWx record, @Param("example") SpMemberWxExample example);

    int updateByPrimaryKeySelective(SpMemberWx record);

    int updateByPrimaryKey(SpMemberWx record);

    List<ResultWx> selectByOpenid(String openid);
}