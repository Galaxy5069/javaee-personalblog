import util.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Test_AxisServlet {
    public static void main(String[] args) throws SQLException {
        String sql = "Select time from t_article;";
        Statement stmt = JDBCUtils.GetConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    }
}
