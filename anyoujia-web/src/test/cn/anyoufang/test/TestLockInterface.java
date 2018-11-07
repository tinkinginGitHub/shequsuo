package cn.anyoufang.test;

import cn.anyoufang.entity.selfdefined.Data;
import cn.anyoufang.entity.selfdefined.InitParam;
import cn.anyoufang.utils.AesCBC;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import cn.anyoufang.utils.SortJsonAesc;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        String ss = sb.append("235435A159").append(timestamp).append(2).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=register.lock.info&userid=2&locksn=5CCF7F1BA450&temptime="+timestamp+"&sign="+sss;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
        System.out.println(timestamp);
        System.out.println(sss);
    }

    /**
     * pwdtype [可选] 密码类型 99:全部 0: APP用户 1: 永久 2：一次 3：限时
     * usertype 用户类型 99:全部 0: APP 1:密码 2: 指纹 3:IC卡
     */
    @Test
    public void testGetLockTempPwdList() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param=sb.append("555444").append(2).append(timestamp).append(1).append(s).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String combineParam = "method=get.lock.userlist&locksn="+"555444"+"&temptime="+timestamp+"&sign="+sign + "&usertype="+1+"&pwdtype="+2;
        String result =  SimulateGetAndPostUtil.sendPost(url,combineParam);
        System.out.println(result);
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
        long seqid = 81l;
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


    /**
     * 测试管理用户接口
     *
     */

    @Test
    public void testManageUser() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();//locksn seqid state temptime
        String ss = sb.append("5CCF7F1BA456").append(36474).append(2).append(timestamp).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=edit.lock.user&seqid=3&temptime="+timestamp+"&seqid="+36474+"&state="+2+"&sign="+sss+"&locksn=5CCF7F1BA456";
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }


    /**
     * 测试获取开锁详情
     */
    @Test
    public void testGetOpenLockRecords() {
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String locksn = "5CCF7F1BA456";
        int isalarm = 0; //0表示开锁记录，1表示报警记录
        int page = 1;
        int pagesize = 10;
        String ss = sb.append(isalarm).append(locksn).append(page).append(pagesize).append(timestamp).append(s).toString();
        String sss = Md5Utils.md5(ss,"UTF-8");
        String param = "method=get.lock.openlist&page=1"+"&pagesize=20"+"&locksn="+locksn+"&temptime="+timestamp+"&sign="+sss + "&isalarm="+0;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);

    }


    @Test
    public void edit() {
        long timestamp = System.currentTimeMillis()/1000;
        //locksn seqid state temptime
        StringBuilder sb = new StringBuilder();
        String ss = sb.append("555444").append(7483659).append(2).append(timestamp).append(s).toString();
        String sign = Md5Utils.md5(ss,"UTF-8");
        String param = "method=edit.lock.user&seqid="+7483659+"&temptime="+timestamp+"&state="+2+"&sign="+sign+"&locksn="+555444;
        String result = SimulateGetAndPostUtil.sendPost(url,param);
        System.out.println(result);
    }

    @Test
    public void alarm() throws Exception {
        Data data1 = new Data();
        Map<String, String> map = new HashMap<>();
        map.put("sn", "1123");
        map.put("userid", "36479");
        map.put("user_type", "15");
        map.put("opentime", "1540384417");
        map.put("status", "1");
        map.put("type", "40");
        map.put("uid", "147258");
        map.put("vid", "001");
        map.put("isalarm", "1");
        map.put("nickname", "jack");
        map.put("pwd_type", "0");
        map.put("pwds","124356");
        data1.setData(map);
        Map<String, String> tempMap = map;
        Map<String, String> toJson =  data1.getData();

        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(toJson));
        System.out.println(json);
        InitParam p = new InitParam();
        p.setMod("Alarm");
        p.setFun("receive");
       String sign =  genarateSign("Alarm", "receive", json);
        p.setSign(sign);
        Map<String, String> data = new HashMap<>(tempMap);
        p.setData(data);
       //Map<String,Object> res =  parseResponse();
        System.out.println(doPhpRequest(p));
    }

    @Test
    public void testActiveLock()throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("sn", "1123");
        map.put("uid", "36479");
        String json = JsonUtils.objectToJson(SortJsonAesc.sortMap(map));
        InitParam p = new InitParam();
        p.setMod("Com");
        p.setFun("register");
        String sign =  genarateSign("Com", "register", json);
        p.setSign(sign);
        p.setData(map);
        System.out.println(doPhpRequest(p));
    }

    public Map<String, Object> parseResponse(String response) {
        Map<String, Object> map = JsonUtils.jsonToMap(response);
        Integer status = (Integer) map.get("status");
        Object object = map.get("data");
        JSONObject json;
        Map<String, Object> data = new HashMap<>();
        if (object != null) {
            if (object instanceof String) {
                String msg = (String) object;
                data.put("msg", msg);
            } else {
                json = (JSONObject) map.get("data");
                data.putAll(JsonUtils.jsonToMap(json));
            }

        }
        data.put("status", status);
        return data;
    }

    private  String genarateSign(String mod, String fun, String jsonData) {
        StringBuilder sb = new StringBuilder();
        String result = sb.append(mod).append(fun).append(jsonData).append("575gh5rr556Dfhr67Ohrt8").toString().replaceAll("\t|\n|\r", "");
        return Md5Utils.md5(result, "utf-8");
    }
    private  String doPhpRequest(InitParam initParam) throws Exception {
        String ss = JsonUtils.objectToJson(initParam);
        return SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Project/Api/index/", genarateParamForPhpRequest(ss));
    }

    private  String genarateParamForPhpRequest(String s) throws Exception {
        return "sp=" + AesCBC.getInstance().encrypt(s).
                replaceAll("\r|\n", "").trim().replaceAll("\\+", "%2B");
    }

    public static void main(String[] args) {
        String[] s = new String[]{"page","pagesize"};
        Arrays.sort(s);
        System.out.println(s[0]);
    }
}
