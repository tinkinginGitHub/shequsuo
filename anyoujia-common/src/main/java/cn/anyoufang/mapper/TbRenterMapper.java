package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbRenter;
import cn.anyoufang.entity.TbRenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRenterMapper {
    int countByExample(TbRenterExample example);

    int deleteByExample(TbRenterExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbRenter record);

    int insertSelective(TbRenter record);

    List<TbRenter> selectByExample(TbRenterExample example);

    TbRenter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbRenter record, @Param("example") TbRenterExample example);

    int updateByExample(@Param("record") TbRenter record, @Param("example") TbRenterExample example);

    int updateByPrimaryKeySelective(TbRenter record);

    int updateByPrimaryKey(TbRenter record);
}