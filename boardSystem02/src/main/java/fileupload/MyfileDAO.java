package fileupload;
import common.MySQLConnectPool;

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
            psmt.setString(1,dto.getCate());
            psmt.setString(1,dto.getOfile());
            psmt.setString(1,dto.getsfile());
            result=psmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
