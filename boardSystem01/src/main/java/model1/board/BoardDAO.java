package model1.board;

import common.JDBConnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO extends JDBConnect {
    public BoardDAO(){
        super();
    }

    public int selectCount(Map<String, Object> map){
        int totalCount=0;
        //"select count(*) from board where title like '%jsp%'";
        String sql="select count(*) from board";
        if(map.get("searchWord")!=null){
            sql+=" where "+map.get("searchField")+" ";
            sql+=" like '%"+map.get("searchWord")+"%'";
        }

        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                totalCount=rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return totalCount;
    }

    public List<BoardDTO> selectPagingList(Map<String, Object>map){
        List<BoardDTO> bbs=new ArrayList<BoardDTO>();
        String query="select * from board";
        if(map.get("searchWord")!=null){
            query+="where "+map.get("SearchFiled")+"like '%"+map.get("searchWord")+"%'";
        }
        query+="order by num desc limit ?, ?";//페이지의 시작 번호 한 페이지당 몇개 있다는 것을 보내줌 .

        try{
            pstmt=conn.prepareStatement(query);
            pstmt.setInt(1,Integer.parseInt(map.get("start").toString()));
            pstmt.setInt(2,Integer.parseInt(map.get("pageSize").toString()));
            rs=pstmt.executeQuery();
            while (rs.next()){
                BoardDTO dto=new BoardDTO();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getInt("visitcount"));
                bbs.add(dto);
            }
        }catch (Exception ex){

        }
        return bbs;
    }

    public List<BoardDTO> selectList(Map<String, Object> map){
        List<BoardDTO> bbs=new ArrayList<BoardDTO>();
        String sql="select * from board ";
        if(map.get("searchWord") !=null){
            sql+=" where "+ map.get("searchField")+" ";
            sql+=" like '%"+map.get("searchWord")+"%'";
        }
        sql+=" order by num DESC";
        try{
            stmt= conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                BoardDTO dto=new BoardDTO();
                dto.setNum(rs.getInt("num"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setId(rs.getString("id"));
                dto.setVisitcount(rs.getInt("visitcount"));
                bbs.add(dto);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return bbs;
    }
    public int insertWrite(BoardDTO dto){
        int iResult=-1;
        String sql="insert into board(id, title, content) values(?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getTitle());
            pstmt.setString(3, dto.getContent());
            iResult=pstmt.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return iResult;
    }
    public void updateVisitCount(int num){
        String sql="update board set visitcount=visitcount+1 where num=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public BoardDTO selectView(int num){
        BoardDTO dto=null;
        String sql="select * from board where num=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs=pstmt.executeQuery();
            if(rs.next()){
                dto=new BoardDTO();
                dto.setNum(num);
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setId(rs.getString("id"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setVisitcount(rs.getInt("visitcount"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return dto;
        }

        public int updateEdit(BoardDTO dto) {
            int iResult =-1;
            String sql = "update board set title=?, content=? where num=?";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, dto.getTitle());
                pstmt.setString(2, dto.getContent());
                pstmt.setInt(3, dto.getNum());
                iResult = pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return iResult;
        }
        public int deletePost(int num) {
            int iResult=-1;
            String sql = "delete from board where num=?";
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, num);
                iResult = pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return iResult;
        }
}
