package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbTrade;
import cn.anyoufang.entity.TbTradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbTradeMapper {
    int countByExample(TbTradeExample example);

    int deleteByExample(TbTradeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbTrade record);

    int insertSelective(TbTrade record);

    List<TbTrade> selectByExample(TbTradeExample example);

    TbTrade selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbTrade record, @Param("example") TbTradeExample example);

    int updateByExample(@Param("record") TbTrade record, @Param("example") TbTradeExample example);

    int updateByPrimaryKeySelective(TbTrade record);

    int updateByPrimaryKey(TbTrade record);
}