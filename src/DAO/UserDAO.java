package DAO;

import bean.User;
import util.JDBCUtils;

import java.sql.*;
import java.util.*;

/**
 * 操作DB中的用户表
 */
public class UserDAO {
    private static boolean isSuccess;

    public static List<Map<String, Object>> UserArticle(String flag) throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql;
        if (flag.equals("Newest"))
            sql = "Select * from t_article order by time desc ;";
        else if (flag.equals("Visits"))
            sql = "Select * from t_article order by visit desc ;";
        else
            sql = "";
        Statement stmt;
        ResultSet rs;
        ResultSetMetaData rsmd;
        stmt = connection.createStatement();
        rs = stmt.executeQuery(sql);
        rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < columns; i++) {
                map.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
            }
            list.add(map);
        }
            /*UserArticle.setArticle_title(rs.getString(1));
            UserArticle.setArticle_sort(rs.getString(2));
            UserArticle.setArticle_time(rs.getString(3));
            UserArticle.setArticle_visit(rs.getString(4));
            UserArticle.setArticle_content(rs.getString(5));*/
        JDBCUtils.Close(connection, stmt, rs);

        return list;
    }

    public static int UserTagCount() throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql = "select count(distinct tag) as Tag from t_tag;";
        int tagCount = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            tagCount = rs.getInt(1);
            JDBCUtils.Close(connection, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tagCount;

        }
    }

    public static int UserSortCount() throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql = "select count(distinct sort) from t_article;";
        int sortCount = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            sortCount = rs.getInt(1);
            JDBCUtils.Close(connection, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return sortCount;
        }
    }

    public static List<String> UserTagList() throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql = "select * from t_tag;";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(2));
            }
            JDBCUtils.Close(connection, null, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public static Map<String, List<Map<String, String>>> SortedServlet(String param) throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql_String;
        if (!param.equals("all")) {
            sql_String = "select distinct (sort) from t_article where sort = " + " '" + param + "' ";
        } else
            sql_String = "select sort, count(*) from t_article group by sort order by count(*) desc;";
        String sql_ListMap = "select id,title,time,visit from t_article where sort = ? ;";
        Map<String, List<Map<String, String>>> finalMap = new LinkedHashMap<>();

        try {
            Statement stmt = connection.createStatement();
            PreparedStatement preStmt = connection.prepareStatement(sql_ListMap); /**两条stmt和preStmt语句*/

            ResultSet rs_String = stmt.executeQuery(sql_String);
            List<String> sortList = new LinkedList<>();
            while (rs_String.next()) {
                sortList.add(rs_String.getString(1));
                /**以上初始化sortList，将t_article表中的sort列数据添加进去,同时此list将元素出现次数从多到少排列*/
            }


            for (int i = 0; i < sortList.size(); i++) {
                preStmt.setString(1, sortList.get(i));
                ResultSet rs_ListMap = preStmt.executeQuery();
                ResultSetMetaData rs_ListMap_md = rs_ListMap.getMetaData();
                List<Map<String, String>> tempList = new ArrayList<>();
                while (rs_ListMap.next()) {
                    Map<String, String> tempMap = new HashMap<>();/**这里初始化大Map里value中的ListMap,
                     如查询"java"字符串数据的rs，且该rs可能有多行数据，层层嵌套*/

                    for (int j = 1; j <= rs_ListMap_md.getColumnCount(); j++) {
                        tempMap.put(rs_ListMap_md.getColumnName(j), rs_ListMap.getString(j));
                        /**上面用了ResultSetMetada来动态获取列名和列数据，故稍显复杂*/
                    }
                    tempList.add(tempMap);/**此处：List<Map<String, String>中的每一个元素相当于一个map对象即
                     *每次都会add一个 new map 对象进去*/
                    finalMap.put(sortList.get(i), tempList);
                }
            }
            JDBCUtils.Close(connection, stmt, rs_String);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return finalMap;
        }
    }

    public static Map<String, List<Map<String, String>>> TagsServlet(String param) throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql_String;
        if (!param.equals("all")) {
            sql_String = "select id, tag from t_tag where tag = " + "'" + param + "'";
        } else
            sql_String = "Select id, tag from t_tag;";
        String sql_ListMap = "select id,title,time,visit from t_article where id = ? ;";
        Map<String, List<Map<String, String>>> finalMap = new LinkedHashMap<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs_String = stmt.executeQuery(sql_String);
            List<String> idList = new LinkedList<>();
            List<String> titleList = new LinkedList<>();
            while (rs_String.next()) {
                idList.add(rs_String.getString(1));
                titleList.add(rs_String.getString(2));
            }
//            Collections.sort(idList, String::compareTo);

            for (int i = 0; i < titleList.size(); i++) {
                PreparedStatement preStmt = connection.prepareStatement(sql_ListMap);
                preStmt.setString(1, idList.get(i));
                ResultSet rs_ListMap = preStmt.executeQuery();
                ResultSetMetaData rsmd = rs_ListMap.getMetaData();
                List<Map<String, String>> tempList = new ArrayList<>();
                while (rs_ListMap.next()) {
                    Map<String, String> tempMap = new HashMap<>();
                    for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                        tempMap.put(rsmd.getColumnName(j), rs_ListMap.getString(j));
                        /**上面用了ResultSetMetada来动态获取列名和列数据，故稍显复杂*/
                    }
                    tempList.add(tempMap);/**此处：List<Map<String, String>中的每一个元素相当于一个map对象即
                     *每次都会add一个 new map 对象进去*/
                    finalMap.put(titleList.get(i), tempList);
                }
            }

            JDBCUtils.Close(connection, stmt, rs_String);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return finalMap;
        }
    }

    /**
     * 登录方法
     *
     * @param LoginUser (用户名&密码)
     * @return true if success or false if fail
     */
    public Boolean Login(User LoginUser) {
        String sql = "Select * from t_user where user_name = ? and user_password = ?;";
        try {
            ResultSet rs;
            PreparedStatement pstmt;
            pstmt = JDBCUtils.GetConnection().prepareStatement(sql);
            pstmt.setString(1, LoginUser.getUsername());
            pstmt.setString(2, LoginUser.getPassword());
            rs = pstmt.executeQuery();
            if (rs.first())
                isSuccess = true;
            else
                isSuccess = false;

            if (rs != null && pstmt != null) {
                rs.close();
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return isSuccess;
        }
    }

    public User UserInstance(User LoginUser) throws SQLException {
        Connection connection =JDBCUtils.GetConnection();
        String sql = "Select * from t_user where user_name = ? and user_password = ?;";
        User successUser = new User();
        try {
            ResultSet rs;
            PreparedStatement pstmt;
            pstmt = JDBCUtils.GetConnection().prepareStatement(sql);
            pstmt.setString(1, LoginUser.getUsername());
            pstmt.setString(2, LoginUser.getPassword());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                successUser.setUsername(rs.getString(2));
            }

            JDBCUtils.Close(connection,pstmt,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (successUser != null)
                return successUser;
            else
                return null;
        }
    }


}
