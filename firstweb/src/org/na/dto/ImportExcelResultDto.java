package org.na.dto;


import org.na.entity.Prescription;
import org.na.entity.WordImport;

import java.util.List;

public class ImportExcelResultDto {
    private String title;
//    private List<Student> StudentList;
    private String msg;
    private List<WordImport> WordImportList;

    public List<WordImport> getPrescriptionList() {
        return WordImportList;
    }

    public void setPrescriptionList(List<WordImport> WordImportList) {
        WordImportList = WordImportList;
    }

//    public List<Student> getStudentList() {
//        return StudentList;
//    }
//
//    public void setStudentList(List<Student> studentList) {
//        StudentList = studentList;
//    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
