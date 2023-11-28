package model2;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ofile=req.getParameter("ofile");
        String sfile=req.getParameter("sfile");
        int idx=Integer.parseInt(req.getParameter("idx"));
        FileUtil.download(req, resp,"/uploads", sfile, ofile);
        MVCBoardDAO dao=new MVCBoardDAO();
        dao.downCountPlus(idx);
        dao.close();
    }
}
