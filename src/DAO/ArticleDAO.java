package DAO;

import bean.Article;
import util.JDBCUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ArticleDAO {
    /**Never Use*/
    /*public static List<Article> GetAxis(){
        try {
            Statement stmt = JDBCUtils.GetConnection().createStatement();
            String sql = "Select * from t_article;";

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }*/
}
