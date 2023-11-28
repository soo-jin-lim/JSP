package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static String uploadFile(HttpServletRequest req, String sDirectory)
        throws ServletException, IOException{
        Part part=req.getPart("ofile");
        String partHeader=part.getHeader("content-disposition");
        String[] phArr=partHeader.split("filename=");
        String originalFileName=phArr[1].trim().replace("\"","");
        if(!originalFileName.isEmpty()){
            part.write(sDirectory+ File.separator+originalFileName);
        }
        return originalFileName;
    }
    public static String renameFile(String sDirectory, String fileName){
        String ext=fileName.substring(fileName.lastIndexOf("."));
        String sname=fileName.substring(0, fileName.lastIndexOf("."));
        String now=new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName=sname+"_"+now+ext;
        File oldFile=new File(sDirectory+File.separator+fileName);
        File newFile=new File(sDirectory+File.separator+newFileName);
        oldFile.renameTo(newFile);
        return newFileName;
    }

    public static void deleteFile(HttpServletRequest req, String directory,
                                  String saveFielName) {
        String sDirectory=req.getServletContext().getRealPath(directory);
        File file=new File(sDirectory+File.separator+saveFielName);
        if(file.exists()){
            file.delete();
        }
    }

    public static void download(HttpServletRequest req, HttpServletResponse resp,
                                String savedDirectory,
                                String sfileName, String ofileName) {
        String sDirectory=req.getServletContext().getRealPath(savedDirectory);
        try{
            File file=new File(sDirectory, sfileName);
            InputStream inputStream=new FileInputStream(file);

            //한글파일명 깨짐 방지
            String client=req.getHeader("User-Agent");
            if(client.indexOf("WOW64")==-1){
                ofileName=new String(ofileName.getBytes("UTF-8"),"ISO-8859-1");
            }else{
                ofileName=new String(ofileName.getBytes("KSC5601"),"ISO-8859-1");
            }

            //파일 다운로드형 응답헤더 설정
            resp.reset();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition","attachment; filename=\""+ofileName+"\"");
            resp.setHeader("Content-Length",""+file.length());

            //response 객체로부터 출력스트림 생성 후 다운로드
            OutputStream outputStream=resp.getOutputStream();
            byte[] b=new byte[(int)file.length()];
            int readBuffer=0;
            while((readBuffer=inputStream.read(b))>0){
                outputStream.write(b,0, readBuffer);
            }
            outputStream.close();
            inputStream.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
