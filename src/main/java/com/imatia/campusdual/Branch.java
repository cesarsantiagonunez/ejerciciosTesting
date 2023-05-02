package com.imatia.campusdual;

public class Branch {

    private String entityID;
    private String branchID;

    public Branch() {
        this.entityID = String.valueOf((long) Math.floor(Math.random() * 9_000L) + 1_000L);
        this.branchID = String.valueOf((long) Math.floor(Math.random() * 9_000L) + 1_000L);
    }

    public Branch(String entityID) {
        this.entityID = entityID;
        this.branchID = String.valueOf((long) Math.floor(Math.random() * 9_000L) + 1_000L);
    }

    public Branch(String entityID, String branchID) {
        this.entityID = entityID;
        this.branchID = branchID;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }
}
