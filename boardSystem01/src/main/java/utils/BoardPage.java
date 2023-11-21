package utils;

/**
 * 페이징 처리를 위한 유틸리티 클래스입니다.
 */
public class BoardPage {

    /**
     * 페이징 처리를 위한 HTML 문자열을 생성하는 메서드입니다.
     *
     * @param totalCount 총 게시물 수
     * @param pageSize 페이지당 표시할 게시물 수
     * @param blockPage 블록당 페이지 수
     * @param pageNum 현재 페이지 번호
     * @param reqUrl 요청 URL
     * @return 페이징을 나타내는 HTML 문자열
     */
    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
        // 생성된 HTML 문자열을 저장할 변수
        String pagingStr = "";

        // 전체 페이지 수 계산
        int totalPages = (int) (Math.ceil((double) totalCount / pageSize));

        // 이전 페이지 블록 바로가기 출력
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;  // 어떤 블록의 첫 페이지
        if (pageTemp != 1) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전 블록]</a>";
        }

        // 페이지 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // 현재 페이지는 링크를 걸지 않음
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // 다음 블록 바로가기 마지막 페이지 출력
        if (pageTemp <= totalPages) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>[다음 블록]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
        }

        return pagingStr;
    }
}