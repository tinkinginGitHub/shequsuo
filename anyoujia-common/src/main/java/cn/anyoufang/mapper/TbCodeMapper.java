package cn.anyoufang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.anyoufang.entity.TbCode;
import cn.anyoufang.entity.TbCodeExample;

public interface TbCodeMapper {
    int countByExample(TbCodeExample example);

    int deleteByExample(TbCodeExample example);

    int insert(TbCode record);

    int insertSelective(TbCode record);

    List<TbCode> selectByExample(TbCodeExample example);

    int updateByExampleSelective(@Param("record") TbCode record, @Param("example") TbCodeExample example);

    int updateByExample(@Param("record") TbCode record, @Param("example") TbCodeExample example);

    String getCodeName(@Param("code") String code);

    List<Map<String, Object>> getSubCode(@Param("pcode") String pcode);

    List<String> getAreas();

    List<String> getStreets(@Param("area") String area);

    List<String> getSubway();

    List<String> getStations(@Param("subway") String subway);

    List<Map<String, Object>> getCodes(@Param("codes") String[] codes);
}