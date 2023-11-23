package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtile {
    // 파일 업로드 메서드
    public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
        // HttpServletRequest에서 파일 업로드를 위한 Part 객체 획득
        Part part = req.getPart("ofile");

        // Part의 헤더 정보 중 content_disposition을 획득
        String partHeader = part.getHeader("content-disposition");
        System.out.println("partHeader = " + partHeader);

        // 파일 이름 추출
        String[] phArr = partHeader.split("filename=");
        String originalFileName = phArr[1].trim().replace("\"","");

        // 원본 파일 이름이 비어있지 않은 경우 파일을 지정된 디렉토리에 저장
        if (!originalFileName.isEmpty()) {
            part.write(sDirectory + File.separator + originalFileName);
        }

        // 원본 파일 이름 반환
        return originalFileName;
    }

    // 파일 이름 변경 메서드
    public static String renameFile(String sDirectory, String filename){
        // 파일 확장자 추출
        String ext = filename.substring(filename.lastIndexOf("."));

        // 현재 날짜 및 시간을 이용하여 새로운 파일 이름 생성
        String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
        String newFileName = now + ext;

        // 기존 파일 및 새 파일 객체 생성
        File oldFile = new File(sDirectory + File.separator + filename);
        File newFile = new File(sDirectory + File.separator + newFileName);

        // 파일 이름 변경
        oldFile.renameTo(newFile);

        // 새로운 파일 이름 반환
        return newFileName;
    }

    public static void download(HttpServletRequest req, HttpServletResponse resp,
                                String sFileName, String oFileName){
        String sDirectory = req.getServletContext().getRealPath("/uploads");
        try{
            File file = new File(sDirectory, sFileName);
            InputStream inputStream = new FileInputStream(file);
            // 한글 파일명 깨짐 방지
            String client = req.getHeader("User-Agent");
            if(client.indexOf("WOW64")==-1){
                oFileName = new String(oFileName.getBytes("UTF-8"),"ISO-8859-1");
            }else {
                oFileName = new String(oFileName.getBytes("KSC5601"), "ISO-8859-1");
            }
            // 파일 다운로드용 응답 헤드 설정
            resp.reset();//응답 객체를 리셋을 시켜서 깨끗하게 만듬
            resp.setContentType("application/octet-stream");//문자스트림 형태로 만듬
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + oFileName + "\"");//헤더에 들어갈 내용 완성
            resp.setHeader("Content-Length", ""+file.length());

            OutputStream outputStream = resp.getOutputStream();//아웃풋스트림객체 만들기
            byte[] b = new byte[(int) file.length()];//byte형태로 받게하기위해 배열을 만듬
            int readBuffer = 0;//데이터를 여러개 받게 하기위해 buffer
            while ((readBuffer = inputStream.read(b)) > 0) {//파일 버퍼크기만큼 읽음
                outputStream.write(b, 0, readBuffer);//0부터 시작해서 readbuffer크기 만큼 읽음
            }
            inputStream.close();
            outputStream.close();

        }catch (FileNotFoundException fe){//예외임
            System.out.println("파일을 찾을 수 없음");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> multipleFile(HttpServletRequest req, String saveDirectory) {
    ArrayList<String> listFileName=new ArrayList<>();
    return listFileName;
    }
}