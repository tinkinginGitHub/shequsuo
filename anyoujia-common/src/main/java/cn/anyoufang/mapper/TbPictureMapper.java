package cn.anyoufang.mapper;

import cn.anyoufang.entity.TbPicture;
import cn.anyoufang.entity.TbPictureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPictureMapper {
    int countByExample(TbPictureExample example);

    int deleteByExample(TbPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbPicture record);

    int insertSelective(TbPicture record);

    List<TbPicture> selectByExample(TbPictureExample example);

    TbPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbPicture record, @Param("example") TbPictureExample example);

    int updateByExample(@Param("record") TbPicture record, @Param("example") TbPictureExample example);

    int updateByPrimaryKeySelective(TbPicture record);

    int updateByPrimaryKey(TbPicture record);
}