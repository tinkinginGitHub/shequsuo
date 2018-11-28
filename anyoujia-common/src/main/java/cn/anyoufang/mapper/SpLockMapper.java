package cn.anyoufang.mapper;

import cn.anyoufang.entity.SpLock;
import cn.anyoufang.entity.SpLockExample;
import cn.anyoufang.entity.selfdefined.LockCombineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SpLockMapper {
    int countByExample(SpLockExample example);

    int deleteByExample(SpLockExample example);

    int deleteByPrimaryKey(String sn);

    int insert(SpLock record);

    int insertSelective(SpLock record);

    List<SpLock> selectByExample(SpLockExample example);

    SpLock selectByPrimaryKey(String sn);

    int updateByExampleSelective(@Param("record") SpLock record, @Param("example") SpLockExample example);

    int updateByExample(@Param("record") SpLock record, @Param("example") SpLockExample example);

    int updateByPrimaryKeySelective(SpLock record);

    int updateByPrimaryKey(SpLock record);
    /**
     * 自定义连表查询锁型号地址等信息
     * @param list
     * @return
     */
    List<LockCombineInfo> selectLockInfoByCombinetable(@Param("locksns") List<String> list);

    /**
     * 自定义关联查询获取单个锁信息
     * @param locksn
     * @return
     */
    LockCombineInfo selectLockInfoByLocksn(@Param("sn") String locksn);

    /**
     * 自定义关联查询获取锁状态和地址
     * @param params
     * @return
     */
    Map<String,Object> selectLockActiveByLocksnOrProkey(@Param("params") Map params);
}