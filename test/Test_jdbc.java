import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_jdbc {

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        String sql = "Select * from t_article;";
        preparedStatement = JDBCUtils.GetConnection().prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
        }
        JDBCUtils.Close(connection,preparedStatement,null);

        /*JDBCUtils jdbcUtils = new JDBCUtils();
        User user = new User();
        UserDAO userDAO = new UserDAO();
        List<Map<String,Object>> list = userDAO.UserArticle();
        System.out.println(list);*/

        /*ResultSet rs;
        PreparedStatement pstmt = null;
        User user = new User();
        String sql = "Select * from t_article;";
        try {
            rs = pstmt.executeQuery("sql");
            user.setArticle_title(rs.getString(1));
            user.setArticle_sort(rs.getString(2));
            user.setArticle_time(rs.getString(3));
            user.setArticle_visit(rs.getString(4));
            user.setArticle_content(rs.getString(5));

            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        }*/
    }
}
