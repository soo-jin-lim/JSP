// 하나의 controller가 여러 요청을 받도록 함.
package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.one")
public class FrontController extends HttpServlet{
    @Override
    public void init() throws ServletException {
        System.out.println("init()호출");
    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String uri = req.getRequestURI();
//        System.out.println("uri :" + uri);
//        int lastSlash = uri.lastIndexOf("/");//마지막으로 /가 나오는 위치를 찾아줍니다.
//        String commandStr = uri.substring(lastSlash);//서브 스트링을 사용하여 마지막 /부터 끝까지 즉 commadStr이 됨.
//
//        if (commandStr.equals("/regist.com")) {//"commandStr이 /regist.com 입니까?
//            registFunc(req);//function에다가 req를 전달
//        }else if(commandStr.equals("/login.one")){
//            loginFunc(req);
//        } else if (commandStr.equals("/freeboard.one")) {
//            freeboardFuc(req);
//        }
//        req.setAttribute("uri", uri);
//        req.setAttribute("commandStr", commandStr);
//        req.getRequestDispatcher("/ch11/frontController.jsp").forward(req,resp);
//    }

    @Override//서비스 함수
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String uri = req.getRequestURI();
            System.out.println("Service() 호출");
            int lastSlash = uri.lastIndexOf("/");//마지막으로 /가 나오는 위치를 찾아줍니다.
            String commandStr = uri.substring(lastSlash);//서브 스트링을 사용하여 마지막 /부터 끝까지 즉 commadStr이 됨.

            if (commandStr.equals("/regist.one")) {//"commandStr이 /regist.com 입니까?
                registFunc(req);//function에다가 req를 전달
            }else if(commandStr.equals("/login.one")){
                loginFunc(req);
            } else if (commandStr.equals("/freeboard.one")) {
                freeboardFuc(req);
            }
            req.setAttribute("uri", uri);
            req.setAttribute("commandStr", commandStr);
            req.getRequestDispatcher("/ch11/frontController.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy() 호출");//전부 컨솔에서 호출할 때 init이 몇번만들어지는지 확인
    }

    void registFunc(HttpServletRequest req){//함수생성
        req.setAttribute("resultValue","<h4>회원가입</h4>");//resultValue로 이름 설정
    }
    void loginFunc(HttpServletRequest req){//함수생성
        req.setAttribute("resultValue","<h4>로그인</h4>");//resultValue로 이름 설정
    }
    void freeboardFuc(HttpServletRequest req){//함수생성
        req.setAttribute("resultValue","<h4>자유게시판</h4>");//resultValue로 이름 설정
    }
}



