package bean;

public class User {

    private String id;
    private String username;
    private String password;

    private String article_title;
    private String article_sort;
    private String article_time;
    private String article_visit;
    private String article_content;

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_sort() {
        return article_sort;
    }

    public void setArticle_sort(String article_sort) {
        this.article_sort = article_sort;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_visit() {
        return article_visit;
    }

    public void setArticle_visit(String article_visit) {
        this.article_visit = article_visit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", article_title='" + article_title + '\'' +
                ", article_sort='" + article_sort + '\'' +
                ", article_time='" + article_time + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_visit=" + article_visit +
                '}';
    }
}
