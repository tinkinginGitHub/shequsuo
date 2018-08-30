package cn.anyoufang.mapper;

import java.util.List;
import java.util.Map;

import cn.anyoufang.entity.*;
import org.apache.ibatis.annotations.Param;

public interface TbHousingMapper {
    int countByExample(TbHousingExample example);

    int deleteByExample(TbHousingExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbHousing record);

    int insertSelective(TbHousing record);

    List<TbHousing> selectByExample(TbHousingExample example);
    List<TbHousing> selectByDefinedExample(SupplyHousingManage example);

    TbHousing selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbHousing record, @Param("example") TbHousingExample example);

    int updateByExample(@Param("record") TbHousing record, @Param("example") TbHousingExample example);

    int updateByPrimaryKeySelective(TbHousing record);

    int updateByPrimaryKey(TbHousing record);

    Map<String, Object> getHousingbyId(@Param("id") String id);

    Map<String, Object> getMyAppointment(@Param("id") String id,
            @Param("rentId") String rentId);

    List<String> getMyCollect(@Param("id") String id,
            @Param("footmark") byte footmark);

    List<Map<String, Object>> getMyRoomRecord(
            @Param("recordstatus") String recordstatus, @Param("id") String id);

    List<Map<String, Object>> selectHousingSource(SearchParam searchParam);

    Map<String, Object> roomDetail(@Param("id") String id);

    List<Map<String, Object>> roomList();

    List<Map<String, Object>> roomListSupply(
            @Param("username") String username);

    List<Map<String, Object>> searchRoomListSupply(RoomParam roomParam);

    Map<String, Object> roomDetailSupply(@Param("id") String id);

    List<Map<String, Object>> searchRoomList(RoomParamTerrace roomParam);

    List<Map<String, Object>> getFootprint(@Param("lids") List<String> lids,
            @Param("b") byte b);

    EnterpriseInformation enterpriseInformation(@Param("id") String id);

    void updateHousekeeper(@Param("id") String id, @Param("jsid") String jsid);

    List<Map<String, Object>> getPicbyId(@Param("id") String id);

    List<Map<String, String>> fuzzySearch(@Param("key") String key);

    List<String> getStation(@Param("subway") String subway);

}