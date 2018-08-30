package cn.anyoufang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.anyoufang.entity.TbCollect;
import cn.anyoufang.entity.TbCollectExample;

public interface TbCollectMapper {
    int countByExample(TbCollectExample example);

    int deleteByExample(TbCollectExample example);

    int deleteByPrimaryKey(String collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    List<TbCollect> selectByExample(TbCollectExample example);

    TbCollect selectByPrimaryKey(String collectId);

    int updateByExampleSelective(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByExample(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);

    TbCollect isCollect(@Param("id") String id, @Param("rentId") String rentId,
            @Param("footmark") byte footmark);

    int delCollect(@Param("id") String id, @Param("hsid") String hsid);
}