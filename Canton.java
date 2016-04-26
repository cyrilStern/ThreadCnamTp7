package fr.canm.cyrilstern1.threadcnamtp6;

/**
 * Created by cyrilstern1 on 26/04/2016.
 */
public class Canton {

    private String name;
    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Canton(String name, String article) {
        this.name =name;
        this.article=article;
    }
}
