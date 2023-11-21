package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet/test.do") //애너테이션 매핑
public class TestServlet extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("msg","doGet()");
        req.getRequestDispatcher("/ch11/servletTest.jsp")
                .forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.setAttribute("msg","doPost()");
        req.getRequestDispatcher("/ch11/servletTest.jsp")
                .forward(req,resp);

    }
}
