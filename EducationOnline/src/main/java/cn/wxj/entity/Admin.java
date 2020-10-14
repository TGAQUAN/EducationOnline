package cn.wxj.entity;


public class Admin {

    //登录名
    private String id;
    //密码
    private String pwd;

    public String getUsername() {
        return id;
    }

    public void setUsername(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
