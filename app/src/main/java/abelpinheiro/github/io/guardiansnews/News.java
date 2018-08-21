package abelpinheiro.github.io.guardiansnews;

public class News {

    //atributos do objeto News
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mDate;
    private String mUrl;

    /**
     * construtor do objeto
     * cria um novo objeto {@link News}
     *
     * @param title titulo de cada noticia
     * @param section seção de cada noticia
     * @param author autor de cada noticia
     * @param date data de cada noticia
     * @param url link de cada noticia
     */
    News(String title, String section, String author, String date, String url){
        this.mTitle = title;
        this.mSection = section;
        this.mAuthor = author;
        this.mDate = date;
        this.mUrl = url;
    }

    //getter do titulo
    public String getTitle() {
        return mTitle;
    }

    //getter da seção
    public String getSection() {
        return mSection;
    }

    //getter de autor
    public String getAuthor() {
        return mAuthor;
    }

    //getter da data
    public String getDate() {
        return mDate;
    }

    //getter da Url
    public String getUrl() {
        return mUrl;
    }
}