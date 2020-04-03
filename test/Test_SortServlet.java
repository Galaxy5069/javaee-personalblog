public class Test_SortServlet {
    public static void main(String[] args) {

        /*String sql_String = "select sort, count(*) from t_article group by sort order by count(*) desc;";
        String sql_ListMap = "select id,title,time,visit from t_article where sort = ? ;";
        Map<String, List<Map<String, String>>> finalMap = new LinkedHashMap<>();

        try {
            Statement stmt = JDBCUtils.GetConnection().createStatement();

            PreparedStatement preStmt =JDBCUtils.conn.prepareStatement(sql_ListMap); *//**两条stmt和preStmt语句*//*

            ResultSet rs_String = stmt.executeQuery(sql_String);
            List<String> sortList = new LinkedList<>();
            while (rs_String.next()){
                sortList.add(rs_String.getString(1));
                *//**以上初始化sortList，将t_article表中的sort列数据添加进去,同时此list将元素出现次数从多到少排列*//*
            }

            *//**这里初始化大Map里value中的ListMap, 如查询java的rs放到一个数组索引位置，且该rs可能有多行数据，层层嵌套*//*
            ResultSet[] rsArray_ListMap = new ResultSet[1024];*//**！1024最好不要写死！*//*
            for (int i = 0; i < sortList.size(); i++) {
                preStmt.setString(1, sortList.get(i));
                ResultSet rs_ListMap = preStmt.executeQuery();
                ResultSetMetaData rs_ListMap_md = rs_ListMap.getMetaData();
                List<Map<String,String>> tempList = new ArrayList<>();
                while (rs_ListMap.next()){
                    Map<String,String> tempMap = new HashMap<>();
                    for (int j = 1; j <= rs_ListMap_md.getColumnCount(); j++) {
                        tempMap.put(rs_ListMap_md.getColumnName(j), rs_ListMap.getString(j));
                    }
                    tempList.add(tempMap);
                    finalMap.put(sortList.get(i), tempList);
                }
            }
            finalMap.forEach((k,v) -> System.out.println(k+v));



            *//*for (int i = 0; i < rsArray_ListMap.length; i++) {
                finalMap.put(sortList.get(i), finalMap.get(sortList.get(i)).add(i,);)
            }*//*

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        }*/



    }
}
