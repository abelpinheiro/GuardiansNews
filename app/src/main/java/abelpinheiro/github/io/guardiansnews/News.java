package abelpinheiro.github.io.guardiansnews;

import java.util.Date;

public class News {

    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mDate;
    private String mUrl;


    public News(String title, String section, String author, String date, String url){
        this.mTitle = title;
        this.mSection = section;
        this.mAuthor = author;
        this.mDate = date;
        this.mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}