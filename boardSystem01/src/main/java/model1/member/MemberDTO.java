package model1.member;

import java.util.Date;

public class MemberDTO {
    private int mno;
    private String id;
    private String pass;
    private String name;
    private Date regidate;

    public MemberDTO(){}
    public MemberDTO(int mno, String id, String pass, String name, Date regidate){
        this.mno = mno;
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.regidate = regidate;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegidate() {
        return regidate;
    }

    public void setRegidate(Date regidate) {
        this.regidate = regidate;
    }
}
