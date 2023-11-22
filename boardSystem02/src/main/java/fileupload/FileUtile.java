package fileupload;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class FileUtile {
    public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException{
        Part part=req.getPart("ofile");
        String partHeader=part.getHeader("content-disposition");
        System.out.println("partHeader="+partHeader);
        String[] phArr=partHeader.split("filename=");
        String orignalFileName=phArr[1].trim().replace("\"", "");
        if(!orignalFileName.isEmpty()){
            part.write(sDirectory+ File.separator+orignalFileName);
        }
        return orignalFileName;
    }
    public static String renameFile(String sDirectory, String filename){
        String ext=filename.substring(filename.lastIndexOf("."));
        String now=new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName=now+ext;
        File oldFile=new File(sDirectory+File.separator+filename);
        File newFile=new File(sDirectory+File.separator+newFileName);
        oldFile.renameTo(newFile);
        return  newFileName;
    }
}
