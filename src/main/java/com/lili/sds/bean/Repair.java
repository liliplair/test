package com.lili.sds.bean;


public class Repair {
    private String repairId;
    private String repairName;
    private String repairDate;
    private String repairResult;
    private String repairOverview;
    private String repairDom;

    public Repair() {
    }

    public Repair(String repairId, String repairName, String repairDate, String repairResult, String repairOverview, String repairDom) {
        this.repairId = repairId;
        this.repairName = repairName;
        this.repairDate = repairDate;
        this.repairResult = repairResult;
        this.repairOverview = repairOverview;
        this.repairDom = repairDom;
    }

    public String getRepairId() {
        return repairId;
    }

    public String getRepairName() {
        return repairName;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public String getRepairOverview() {
        return repairOverview;
    }

    public String getRepairDom() {
        return repairDom;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public void setRepairOverview(String repairOverview) {
        this.repairOverview = repairOverview;
    }

    public void setRepairDom(String repairDom) {
        this.repairDom = repairDom;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId='" + repairId + '\'' +
                ", repairName='" + repairName + '\'' +
                ", repairDate='" + repairDate + '\'' +
                ", repairResult='" + repairResult + '\'' +
                ", repairOverview='" + repairOverview + '\'' +
                ", repairDom='" + repairDom + '\'' +
                '}';
    }

}
