package cn.anyoufang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.anyoufang.entity.TbBespeak;
import cn.anyoufang.entity.TbBespeakExample;

public interface TbBespeakMapper {
    int countByExample(TbBespeakExample example);

    int deleteByExample(TbBespeakExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbBespeak record);

    int insertSelective(TbBespeak record);

    List<TbBespeak> selectByExample(TbBespeakExample example);

    TbBespeak selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbBespeak record, @Param("example") TbBespeakExample example);

    int updateByExample(@Param("record") TbBespeak record, @Param("example") TbBespeakExample example);

    int updateByPrimaryKeySelective(TbBespeak record);

    int updateByPrimaryKey(TbBespeak record);

    int isBespeak(@Param("id") String id, @Param("rentId") String rentId);

    int seenHouse(@Param("besid") String besid);
}