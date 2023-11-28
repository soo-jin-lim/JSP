package model2;

import common.MySQConPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MVCBoardDAO extends MySQConPool {
    public MVCBoardDAO(){
        super();
    }

    public int selectCount(Map<String, Object> map){
        int totalCount=0;
        String sql="select count(*) from mvcboard";
        if(map.get("searchWord")!=null && !map.get("searchWord").equals("")){
            sql += " where "+map.get("searchField")
                    +" like '%"+map.get("searchWord")+"%'";
        }

        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                totalCount=rs.getInt(1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return totalCount;
    }

    public List<MVCBoardDTO> selectList(Map<String, Object> map){
        List<MVCBoardDTO> boards=new ArrayList<MVCBoardDTO>();
        String sql="select * from mvcboard";
        if(map.get("searchWord")!=null && !map.get("searchWord").equals("")){
            sql += " where "+map.get("searchField")
                    +" like '%"+map.get("searchWord")+"%'";
        }
        sql +=" order by idx desc limit ?,?";

        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(map.get("start").toString()));
            pstmt.setInt(2, Integer.parseInt(map.get("pageSize").toString()));
            rs=pstmt.executeQuery();
            while(rs.next()){
                MVCBoardDTO dto=new MVCBoardDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setName(rs.getString("name"));
                dto.setTitle(rs.getString("title"));
                dto.setPostdate(rs.getDate("postdate"));
                dto.setDowncount(rs.getInt("downcount"));
                dto.setOfile(rs.getString("ofile"));
                dto.setSfile(rs.getString("sfile"));
                dto.setVisitcount(rs.getInt("visitcount"));
                dto.setReplycount(rs.getInt("replycount"));
                boards.add(dto);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return boards;
    }

    public int insertBoard(MVCBoardDTO dto){
        int result=0;
        String sql="insert into mvcboard(title, content, name, pass, ofile, sfile) " +
                "values(?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getPass());
            pstmt.setString(5, dto.getOfile());
            pstmt.setString(6, dto.getSfile());
            result=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    public MVCBoardDTO selectView(int idx){
        MVCBoardDTO board=null;
        String sql="select * from mvcboard where idx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            rs=pstmt.executeQuery();
            if(rs.next()){
                board=new MVCBoardDTO();
                board.setIdx(idx);
                board.setName(rs.getString("name"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setOfile(rs.getString("ofile"));
                board.setSfile(rs.getString("sfile"));
                board.setPostdate(rs.getDate("postdate"));
                board.setPass(rs.getString("pass"));
                board.setDowncount(rs.getInt("downcount"));
                board.setVisitcount(rs.getInt("visitcount"));
                board.setReplycount(rs.getInt("replycount"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  board;
    }
    public void updateVisitCount(int idx){
        String sql="update mvcboard set visitcount=visitcount+1 where idx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public boolean confirmPassword(String pass, int idx, String mode){
        boolean isCorr=false;
        String sql="";
        if(mode.equals("reply_del")){
            sql="select count(*) from reply where pass=? and ridx=?";
        }else{
             sql="select count(*) from mvcboard where pass=? and idx=?";
        }
        sql="select count(*) from mvcboard where pass=? and idx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, pass);
            pstmt.setInt(2, idx);
            rs=pstmt.executeQuery();
            if(rs.next()){
                isCorr=true;
            }
        }catch (Exception ex){
            isCorr=false;
        }
        return isCorr;
    }
    public int update(MVCBoardDTO dto){
        int result=0;
        String sql="update mvcboard set title=?, name=?, content=?, ofile=?, sfile=? where idx=? and pass=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getContent());
            pstmt.setString(4, dto.getOfile());
            pstmt.setString(5, dto.getSfile());
            pstmt.setInt(6, dto.getIdx());
            pstmt.setString(7, dto.getPass());
            result=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    public int delete(int idx){
        int result=0;
        String sql="delete from mvcboard where idx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,idx);
            result=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public void downCountPlus(int idx) {
        String sql="update mvcboard set downcount=downcount+1 where idx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
        }catch (Exception ex){}
    }

    public int insertReply(ReplyDTO dto) {
        int result=0;
        String sql="insert reply(bno,name,content,pass) values(?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getBno());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getContent());
            pstmt.setString(4, dto.getPass());
            result=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public List<ReplyDTO> selectListReply(int bno) {
        List<ReplyDTO> replyDTOList=new ArrayList<>();
        String sql="select * from reply where bno=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, bno);
            rs=pstmt.executeQuery();
            while (rs.next()){
                ReplyDTO dto=new ReplyDTO();
                dto.setRidx(rs.getInt("ridx"));
                dto.setBno(rs.getInt("bno"));
                dto.setName(rs.getString("name"));
                dto.setContent(rs.getString("content"));
                dto.setPass(rs.getString("pass"));
                dto.setPostdate(rs.getDate("postdate"));
                replyDTOList.add(dto);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return replyDTOList;
    }

    public int replyDelete(int ridx) {
        int result=0;
        String sql="delete from reply where ridx=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,ridx);
            result=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
