package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.stream.events.EndDocument;
import java.io.*;

@WebServlet("/upload/download.do")
public class FileDownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String saveDirectory = req.getServletContext().getRealPath("/uploads");
        String originalFileName = req.getParameter("oName");
        String saveFileName = req.getParameter("sName");
        FileUtile.download(req, resp, saveFileName, originalFileName);

    }
}