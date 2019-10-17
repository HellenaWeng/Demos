package org.na.entity;

public class DerivationChange {
    private String plantName;
    private Integer changeFlag;
    private Integer derivationID;

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getDerivationID() {
        return derivationID;
    }

    public void setDerivationID(Integer derivationID) {
        this.derivationID = derivationID;
    }
}
