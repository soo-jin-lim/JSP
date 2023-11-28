package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/upload/multiUpload.do")
@MultipartConfig(
        maxFileSize = 1024*1204*1,
        maxRequestSize = 1024*1024*5
)

public class MultiUploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/ch13/multiUploadform.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String saveDirectory = getServletContext().getRealPath("/uploads");
            ArrayList<String> listFileName=FileUtile.multipleFile(req, saveDirectory);

            for(String originalFileName:listFileName){
                String saveFileName=FileUtile.renameFile(saveDirectory,originalFileName);
                insertMyFile(req,originalFileName,saveFileName);
            }
            resp.sendRedirect("/upload/fileList.do");

        }catch (Exception ex){
            ex.printStackTrace();
            req.setAttribute("errorMessage","파일업로드 오류");
            req.getRequestDispatcher("/upload/multiUpload.do").forward(req,resp);
        }
    }
    private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
        String title = req.getParameter("title");
        String[] cateArr = req.getParameterValues("cate");
        StringBuffer cateBuf = new StringBuffer();
        if (cateArr == null) {
            cateBuf.append("선택사항이 없음");
        } else {
            for(int i=0; i<cateArr.length;i++){
                cateBuf.append(cateArr[i]+" ");
            }
        }
        MyFileDTO dto=new MyFileDTO();
        dto.setTitle(title);
        dto.setCate(cateBuf.toString());
        dto.setOfile(oFileName);
        dto.setSfile(sFileName);
        MyfileDAO dao=new MyfileDAO();
        dao.insertFile(dto);
    }
}