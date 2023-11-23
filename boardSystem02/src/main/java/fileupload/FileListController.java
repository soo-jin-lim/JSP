package fileupload;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/upload/fileList.do")
public class FileListController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MyfileDAO dao=new MyfileDAO();
        List<MyFileDTO> fileList=dao.selectFileList();
        req.setAttribute("fileList",fileList);
        req.getRequestDispatcher("/ch13/fileList.jsp")
                .forward(req, resp);
    }
}
