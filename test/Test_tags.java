import util.JDBCUtils;

import java.sql.*;
import java.util.*;

public class Test_tags {
    public static void main(String[] args) {
        String sql_String;
        /*if(!param.equals("all")){
            sql_String="";
        }
        else*/
            sql_String = "Select id, tag from t_tag;";
        String sql_ListMap = "select id,title,time,visit from t_article where id = ? ;";
        Map<String, List<Map<String, String>>> finalMap = new LinkedHashMap<>();

        try {
            Statement stmt = JDBCUtils.GetConnection().createStatement();
            ResultSet rs_String = stmt.executeQuery(sql_String);
            List<String> idList = new LinkedList<>(); List<String> titleList = new LinkedList<>();
            while (rs_String.next()){
                idList.add(rs_String.getString(1));
                titleList.add(rs_String.getString(2));
            }
//            Collections.sort(idList, String::compareTo);

            for (int i = 0; i < titleList.size(); i++) {
                PreparedStatement preStmt = JDBCUtils.GetConnection().prepareStatement(sql_ListMap);
                preStmt.setString(1,idList.get(i));
                ResultSet rs_ListMap = preStmt.executeQuery();
                ResultSetMetaData rsmd = rs_ListMap.getMetaData();
                List<Map<String,String>> tempList = new ArrayList<>();
                while (rs_ListMap.next()){
                    Map<String,String> tempMap = new HashMap<>();
                    for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                        tempMap.put(rsmd.getColumnName(j), rs_ListMap.getString(j));
                        /**上面用了ResultSetMetada来动态获取列名和列数据，故稍显复杂*/
                    }
                    tempList.add(tempMap);/**此处：List<Map<String, String>中的每一个元素相当于一个map对象即
                     *每次都会add一个 new map 对象进去*/
                    finalMap.put(titleList.get(i), tempList);
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            finalMap.forEach((k,v) ->System.out.println(k+ " "+v));
        }
    }
}
