package org.na.servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.na.dto.ImportWordParamDto;
import org.na.dto.ImportWordResultDto;
import org.na.dto.ParamDto;
import org.na.service.WordService;
import org.na.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        if (ServletFileUpload.isMultipartContent(req)){
            ParamDto dto = RequestUtil.parseParam(req);
            ImportWordParamDto paramDto = new ImportWordParamDto();
            paramDto.setTitle(dto.getParamMap().get("title"));
            paramDto.setWord(dto.getFileMap().get("word"));

            WordService service = new WordService();
            ImportWordResultDto resultDto = service.imp(paramDto);
            req.setAttribute("result", resultDto);
        }else{

        }
        req.getRequestDispatcher("/WEB-INF/jsp/importWordResult.jsp").forward(req, resp);
    }
}
