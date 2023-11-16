package utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl){
        String pagingStr="";//page num은 현재페이지 , pagestr은 빈 문서
        int totalPages=(int)(Math.ceil(((double) totalCount/pageSize)));//소숫점이 있으면 무조건 페이지 증가 때문에 방지하기 위해 ceil

        //이전 페이지 블록 바로가기 출력
        int pageTemp=(((pageNum-1)/blockPage)*blockPage)+1;// 어떤 블록의 첫페이지를 구하는 수식
        if(pageTemp !=1){
            pagingStr+="<a href='"+reqUrl+"?pageNum=1'>[첫 페이지]</a>";
            pagingStr+="&nbsp;";
            pagingStr+="<a href='"+reqUrl+"?pageNum"+(pageTemp-1)+"'>[이전블록]</a>";
        }
        //페이지 번호 출력
        int blockCount=1;
        while (blockCount<=blockPage && pageTemp <= totalPages){
            if(pageTemp==pageNum){
                
            }
        }
        return pagingStr;
    }
}
