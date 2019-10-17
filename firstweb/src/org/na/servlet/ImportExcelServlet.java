package org.na.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.na.dto.ImportExcelParamDto;
import org.na.dto.ImportExcelResultDto;
import org.na.dto.ParamDto;
import org.na.service.ExcelService;
import org.na.util.RequestUtil;

public class ImportExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/jsp/importExcelResult.jsp").forward(req, resp);
        if(ServletFileUpload.isMultipartContent(req)){
            //            //是上传文件表单
            ParamDto dto = RequestUtil.parseParam(req);

            ImportExcelParamDto paramDto = new ImportExcelParamDto();
            paramDto.setTitle(dto.getParamMap().get("title"));
            paramDto.setExcel(dto.getFileMap().get("excel"));

            ExcelService service = new ExcelService();
//            service.imp(paramDto);
            ImportExcelResultDto resultDto = service.imp(paramDto);//执行导入
            req.setAttribute("result", resultDto);

        }
        else{
            //不是上传文件表单
        }
        req.getRequestDispatcher("/WEB-INF/jsp/importExcelResult.jsp").forward(req, resp);
    }
}
