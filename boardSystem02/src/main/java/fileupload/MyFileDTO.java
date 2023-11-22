package fileupload;

import java.util.Date;

public class MyFileDTO {
    private int idx;
    private String cate;
    private String title;
    private String ofile;

    @Override
    public String toString() {
        return "MyFileDTO{" +
                "idx=" + idx +
                ", cate='" + cate + '\'' +
                ", title='" + title + '\'' +
                ", ofile='" + ofile + '\'' +
                ", sfile='" + sfile + '\'' +
                ", postDate=" + postDate +
                '}';
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfile() {
        return ofile;
    }

    public void setOfile(String ofile) {
        this.ofile = ofile;
    }

    public String getsfile() {
        return sfile;
    }

    public void setSfile(String sfile) {
        this.sfile = sfile;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    private String sfile;
    private Date postDate;
}

