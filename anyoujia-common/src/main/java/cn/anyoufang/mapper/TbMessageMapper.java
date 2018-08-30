package cn.anyoufang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.anyoufang.entity.TbMessage;
import cn.anyoufang.entity.TbMessageExample;

public interface TbMessageMapper {
    int countByExample(TbMessageExample example);

    int deleteByExample(TbMessageExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbMessage record);

    int insertSelective(TbMessage record);

    List<TbMessage> selectByExample(TbMessageExample example);

    TbMessage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

    int updateByExample(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

    int updateByPrimaryKeySelective(TbMessage record);

    int updateByPrimaryKey(TbMessage record);

    List<TbMessage> queryMyMsg(@Param("id") String id);

    void readMsg(@Param("id") String id);

    int msgFlag(@Param("id") String id);
}