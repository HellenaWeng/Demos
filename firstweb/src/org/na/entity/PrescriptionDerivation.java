package org.na.entity;

public class PrescriptionDerivation {
    private Integer ID;
    private String name;
    private String content;
    private Integer prescriptionID;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(Integer prescriptionID) {
        this.prescriptionID = prescriptionID;
    }
}
