package cn.anyoufang.test;

import cn.anyoufang.util.SimulateGetAndPostUtil;
import cn.anyoufang.utils.Md5Utils;
import org.junit.Test;

/**
 * @author daiping
 */
public class TestLockInterface {

    private static final String url = "http://114.215.71.205:81/sp/index.html";
    private static final String s = "ctlockv.0.1";

    /**
     * 测试注册锁接口
     */

    @Test
    public void testRegister() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append("5CCF7F1BA457").append(timestamp).append(1).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=register.lock.info&userid=1&locksn=5CCF7F1BA456&temptime="+timestamp+"&sign="+sss;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
        System.out.println(timestamp);
        System.out.println(sss);
    }

    /**
     * 测试锁详情接口
     */

    @Test
    public void testLockDetails() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append("5CCF7F1BA457").append(timestamp).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.info&locksn=5CCF7F1BA456&temptime="+timestamp+"&sign="+sss;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }

    /**
     * 测试获取注册门锁列表
     */
    @Test
    public void testRegistedLockList() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(timestamp).append(1).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.list&userid=1&temptime="+timestamp+"&sign="+sss;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }

    /**
     * 测试添加密码用户接口
     *ptype,密码类型1.永久 2:一次 3：限时
     * seqid，密码用户id最大值：4294967295
     */
    @Test
    public void testAddPwdUser() {
        long timestamp = System.currentTimeMillis()/1000;
        int ptype = 1;
        long seqid = 8l;
        String locksn = "5CCF7F1BA456";
        long endtime = System.currentTimeMillis()/1000 + 600;
        String pwds = "12345678";
        String nickname = "张三";
        StringBuilder sb = new StringBuilder();
        String ss = sb.append(endtime).append(locksn).append(nickname).append(ptype).append(pwds).append(timestamp).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=set.lock.pwd&ptype=1&temptime="+timestamp+"&sign="+sss+"&seqid="+seqid +
                "&locksn="+locksn+"&endtime="+endtime + "&pwds="+pwds + "&nickname="+nickname;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }

    /**
     * 测试删除密码用户接口
     *
     */

    @Test
    public void testDelPwdUser() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String ss = sb.append("5CCF7F1BA456").append(2).append(timestamp).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=del.lock.pwd&seqid=3&temptime="+timestamp+"&sign="+sss+"&locksn=5CCF7F1BA456";
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }

}
