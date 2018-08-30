package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbAd;
import cn.anyoufang.entity.TbAdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAdMapper {
    int countByExample(TbAdExample example);

    int deleteByExample(TbAdExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbAd record);

    int insertSelective(TbAd record);

    List<TbAd> selectByExample(TbAdExample example);

    TbAd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbAd record, @Param("example") TbAdExample example);

    int updateByExample(@Param("record") TbAd record, @Param("example") TbAdExample example);

    int updateByPrimaryKeySelective(TbAd record);

    int updateByPrimaryKey(TbAd record);
}