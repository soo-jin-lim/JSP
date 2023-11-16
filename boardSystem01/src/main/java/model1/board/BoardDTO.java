package model1.board;

import java.util.Date;

public class BoardDTO {
    private int num;
    private String title;
    private String content;
    private String id;
    private Date postdate;
    private int visitcount;
    private String name;

    public BoardDTO(){}
    public BoardDTO(int num, String title, String content, String id, Date postdate, int visitcount, String name){
        this.num = num;
        this.title = title;
        this.content = content;
        this.id = id;
        this.postdate = postdate;
        this.visitcount = visitcount;
        this.name = name;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

