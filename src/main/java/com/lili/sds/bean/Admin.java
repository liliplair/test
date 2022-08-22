package com.lili.sds.bean;


public class Admin {
    private String adminId;
    private String adminName;
    private String adminPass;
    private String adminPhone;
    private String adminSex;
    private String aLevel;

    public Admin() {
    }

    public Admin(String adminId, String adminName, String adminPass,String adminPhone,String adminSex,String aLevel) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.adminPhone=adminPhone;
        this.adminSex=adminSex;
        this.aLevel=aLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public String getaLevel() {
        return aLevel;
    }

    public void setaLevel(String aLevel) {
        this.aLevel = aLevel;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminLevel='" + aLevel + '\'' +
                ", adminSex='" + adminSex + '\'' +
                '}';
    }
}
