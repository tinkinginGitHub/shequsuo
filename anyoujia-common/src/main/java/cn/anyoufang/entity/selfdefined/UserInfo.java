package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * @author daiping
 */
public class UserInfo implements Serializable {


    private static final long serialVersionUID = -8564280903931128045L;

    private int memberId;
    private int gender;
    private String headurl;
    private String relation;

    
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
