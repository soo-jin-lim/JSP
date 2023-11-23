package fileupload;
import common.MySQLConnectPool;

import java.util.ArrayList;
import java.util.List;

public class MyfileDAO  extends MySQLConnectPool{
    public MyfileDAO(){
        super();
    }
    public int insertFile(MyFileDTO dto){
        int result=0;
        String sql="insert into myfile(title, cate, ofile, sfile) values(?,?,?,?)";
        try{
            psmt=conn.prepareStatement(sql);
            psmt.setString(1,dto.getTitle());
            psmt.setString(2,dto.getCate());
            psmt.setString(3,dto.getOfile());
            psmt.setString(4,dto.getsfile());
            result=psmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public List<MyFileDTO> selectFileList() {
        List<MyFileDTO> fileList=new ArrayList<MyFileDTO>();
        String sql="select * from myfile";
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                MyFileDTO dto=new MyFileDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setCate(rs.getString("cate"));
                dto.setSfile(rs.getString("sfile"));
                dto.setOfile(rs.getString("ofile"));
                dto.setPostDate(rs.getDate("postdate"));
                fileList.add(dto);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return fileList;
    }
}
