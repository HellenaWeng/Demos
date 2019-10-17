package org.na.service;

import org.apache.poi.EmptyFileException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.na.dto.ImportExcelParamDto;
import org.na.dto.ImportExcelResultDto;
import org.na.entity.WordImport;
import org.na.entity.Student;
import org.na.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {
    //    public void imp(ImportExcelParamDto dto){
//    public List<Student> imp(ImportExcelParamDto dto){
    public ImportExcelResultDto imp(ImportExcelParamDto dto){
//        List<Student> result = new ArrayList<>();

        ImportExcelResultDto result = new ImportExcelResultDto();
        result.setTitle(dto.getTitle());
//        List<Student> studentList = new ArrayList<>();
//        result.setStudentList(studentList);

        List<WordImport> wordImportList = new ArrayList<>();
        result.setPrescriptionList(wordImportList);

//        dto.getExcel();
        Workbook workbook = null;
        try {
//                Workbook workbook = WorkbookFactory.create(new File("d:/upload/import.xlsx"));
            workbook = WorkbookFactory.create(dto.getExcel().getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            for(int i = 1; i <= rowNum; i++){
                Row row = sheet.getRow(i);
//                    Student student = new Student();
//                    result.add(student);
//                    studentList.add(student);
//                    student.setName(row.getCell(0).getStringCellValue());
//                    student.setAge((int) row.getCell(1).getNumericCellValue());
//                    student.setTime(row.getCell(2).getDateCellValue());
                WordImport wordImport = new WordImport();
                wordImportList.add(wordImport);
                wordImport.setTitle(row.getCell(0).getStringCellValue());
                wordImport.setContent(row.getCell(1).getStringCellValue());
//                    System.out.println("姓名：" + row.getCell(0).getStringCellValue());
//                    System.out.println("年龄：" + row.getCell(1).getNumericCellValue());
//                    System.out.println("时间：" + row.getCell(2).getDateCellValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("解析Excel失败！");
        } finally {
            if(workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    public Workbook export(boolean isXlsx){
        Workbook workbook ;
//        boolean isXlsx = false;//判断是03版的excel文件还是07版的
        if(isXlsx){
            workbook = new XSSFWorkbook();
        }else {
            workbook = new HSSFWorkbook();
        }
        Sheet my_sheet = workbook.createSheet("My Sheet");
        List<List<String>> content = this.getContent();
        for(int i = 0; i < content.size(); i++ ){
            Row row = my_sheet.createRow(i);
            List<String> rowData = content.get(i);
            for(int j = 0; j < rowData.size(); j++){
                row.createCell(j).setCellValue(rowData.get(j));
            }
        }
        return workbook;
    }
    private List<List<String>> getContent(){
        List<List<String>> result = new ArrayList<>();
        List<String> row = new ArrayList<>();
        result.add(row);
        row.add("序号");
        row.add("姓名");
        row.add("年龄");
        row.add("时间");

        row = new ArrayList<>();
        result.add(row);
        row.add("1");
        row.add("路人甲");
        row.add("18");
        row.add("2010-01-01");

        row = new ArrayList<>();
        result.add(row);
        row.add("2");
        row.add("路人乙");
        row.add("20");
        row.add("2008-01-01");

        row = new ArrayList<>();
        result.add(row);
        row.add("3");
        row.add("路人丙");
        row.add("10");
        row.add("2018-01-01");

        return result;
    }
}