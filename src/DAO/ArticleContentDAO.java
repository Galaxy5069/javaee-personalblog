package DAO;

import bean.Article;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleContentDAO {
    public static Article GetContent(String id) throws SQLException {
        Connection connection =JDBCUtils.GetConnection();

        String sql = "Select * from t_article where id = ?";
        Article article = new Article();
        try {
            PreparedStatement preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, id);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                article.setId(rs.getInt(1));
                article.setTitle(rs.getString(2));
                article.setAuthor(rs.getString(3));
                article.setSort(rs.getString(4));
                article.setTime(rs.getTime(5));
                article.setStar(rs.getString(6));
                article.setComment(rs.getString(7));
                article.setVisit(rs.getString(8));
                article.setContent(rs.getString(9));
            }
            JDBCUtils.Close(connection, preStmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return article;
        }
    }

    public static Article GetNextArticle(Integer id) {
        Connection connection=null;
        String sql = "Select id, title from t_article where id = ?";
        Article article_next = new Article();
        try {
            connection=JDBCUtils.GetConnection();
            PreparedStatement preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, String.valueOf(id));
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                article_next.setId(rs.getInt(1));
                article_next.setTitle(rs.getString(2));
            }
            JDBCUtils.Close(connection, preStmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (article_next.getId() == 0)
                return null;
            else
                return article_next;
        }
    }

    public static Article GetPreArticle(Integer id) {
        Connection connection=null;

        String sql = "Select id, title from t_article where id = ? ;";
        Article article_next = new Article();
        try {
            connection = JDBCUtils.GetConnection();
            PreparedStatement preStmt = connection.prepareStatement(sql);
            preStmt.setString(1, String.valueOf(id));
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                article_next.setId(rs.getInt(1));
                article_next.setTitle(rs.getString(2));
            }
            JDBCUtils.Close(connection, preStmt, rs);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (article_next.getId() == 0)
                return null;
            else
                return article_next;
        }
    }
}
