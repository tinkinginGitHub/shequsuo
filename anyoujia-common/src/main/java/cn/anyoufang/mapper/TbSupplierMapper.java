package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbSupplier;
import cn.anyoufang.entity.TbSupplierExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbSupplierMapper {
    int countByExample(TbSupplierExample example);

    int deleteByExample(TbSupplierExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbSupplier record);

    int insertSelective(TbSupplier record);

    List<TbSupplier> selectByExample(TbSupplierExample example);

    TbSupplier selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbSupplier record, @Param("example") TbSupplierExample example);

    int updateByExample(@Param("record") TbSupplier record, @Param("example") TbSupplierExample example);

    int updateByPrimaryKeySelective(TbSupplier record);

    int updateByPrimaryKey(TbSupplier record);
}