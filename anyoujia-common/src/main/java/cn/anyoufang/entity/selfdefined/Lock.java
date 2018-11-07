package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * @author daiping
 */
public class Lock extends LockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isAdmin;
    private boolean lockpwdAuth;
    private boolean lockfingerAuth;
    private String locksn;
    /**
     * 管理员昵称，如果没有，将会使用手机号代替
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isLockpwdAuth() {
        return lockpwdAuth;
    }

    public void setLockpwdAuth(boolean lockpwdAuth) {
        this.lockpwdAuth = lockpwdAuth;
    }

    public boolean isLockfingerAuth() {
        return lockfingerAuth;
    }

    public void setLockfingerAuth(boolean lockfingerAuth) {
        this.lockfingerAuth = lockfingerAuth;
    }
}
