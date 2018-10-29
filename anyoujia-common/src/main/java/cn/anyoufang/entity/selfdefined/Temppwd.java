package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * 临时密码列表所需信息封装实体类
 * @author daiping
 */
public class Temppwd implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 创建时间
     */
    private int createtime;
    /**
     * 目的
     */
    private String motive;
    /**
     * 临时密码
     */
    private int pwd;
    /**
     * 有效期
     */
    private int expire;
    /**
     * 密码类型（一次，限时）
     */
    private int ptype;
    /**
     * 状态
     */
    private int status;
    /**
     * 密码id
     */
    private int seqid;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeqid() {
        return seqid;
    }

    public void setSeqid(int seqid) {
        this.seqid = seqid;
    }
}
