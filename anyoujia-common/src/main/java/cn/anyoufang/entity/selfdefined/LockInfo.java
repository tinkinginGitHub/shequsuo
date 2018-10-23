package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 *
 * 单个门锁详情实体类
 * @author daiping
 */
public class LockInfo implements Serializable {

    private int lockCreatetime;
    private int power1;
    private int power2;
    private int online;
    private int lockStatus;

    public int getLockCreatetime() {
        return lockCreatetime;
    }

    public void setLockCreatetime(int lockCreatetime) {
        this.lockCreatetime = lockCreatetime;
    }

    public int getPower1() {
        return power1;
    }

    public void setPower1(int power1) {
        this.power1 = power1;
    }

    public int getPower2() {
        return power2;
    }

    public void setPower2(int power2) {
        this.power2 = power2;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }
}
