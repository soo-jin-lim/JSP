package model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/mvcboard/list.do")
public class BoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        MVCBoardDAO dao=new MVCBoardDAO();
        Map<String, Object> map=new HashMap<>();

        String searchField=req.getParameter("searchField");
        String searchWord=req.getParameter("searchWord");

        if(searchWord != null){
            map.put("searchField", searchField);
            map.put("searchWord",searchWord);
        }else{
            searchField="";
            searchWord="";
        }

        int pageSize=3;
        int blockSize=3;
        int pageNum=1;

        String pageTemp=req.getParameter("pageNum");
        if(pageTemp !=null && !pageTemp.equals("")){
            pageNum=Integer.parseInt(pageTemp);
        }
        int start=(pageNum-1)*pageSize; //oracle : start=(pageNum-1)*pageSize+1
        //int end=pageNum*pageSize; oracle
        map.put("start",start);
        map.put("pageSize",pageSize);

        int totalcount=dao.selectCount(map);
        List<MVCBoardDTO> boardList=dao.selectList(map);
        dao.close();

        String pagingImg= BoardPage.pagingStr(totalcount, pageSize, blockSize,
                pageNum, searchField, searchWord,"/mvcboard/list.do");

        map.put("pageImg", pagingImg);
        map.put("pageSize",pageSize);
        map.put("pageNum",pageNum);
        req.setAttribute("map",map);
        req.setAttribute("totalCount", totalcount);
        req.setAttribute("boardList",boardList);
        req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
    }
}
