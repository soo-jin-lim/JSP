package model2;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

import java.io.IOException;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mode",req.getParameter("mode"));
        req.getRequestDispatcher("/board/pass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx=Integer.parseInt(req.getParameter("idx"));
        String mode=req.getParameter("mode");
        String pass=req.getParameter("pass");
        MVCBoardDAO dao=new MVCBoardDAO();
        boolean confirmed=dao.confirmPassword(pass, idx, mode);
        dao.close();

        if(confirmed){//confirm이 true이면
            if(mode.equals("edit")){
                HttpSession session=req.getSession();
                session.setAttribute("pass",pass);
                resp.sendRedirect("/mvcboard/edit.do?idx="+idx);
            }else if(mode.equals("delete")){
                dao=new MVCBoardDAO();
                MVCBoardDTO dto=dao.selectView(idx);
                int result=dao.delete(idx);
                dao.close();
                if(result==1){
                    String saveFielName=dto.getSfile();
                    FileUtil.deleteFile(req, "/uploads", saveFielName);
                }
                JSFunction.alertLocation(resp, "삭제되었습니다","/mvcboard/list.do");
            } else if (mode.equals("reply_del")) {
                resp.sendRedirect("/mvcboard/reply.do?idx="+idx);
            }
        }else{
            JSFunction.alertBack(resp,"비밀번호 검증실패");
        }

    }
}
