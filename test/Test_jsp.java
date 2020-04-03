import DAO.UserDAO;
import com.mchange.v2.collection.MapEntry;

import java.sql.SQLException;
import java.util.*;

public class Test_jsp {
    public static void main(String[] args) throws SQLException {
        List<Map<String, Object>> TotalList = UserDAO.UserArticle("all");
        Map<String, Map<String, Object>> finalMap = new HashMap<>();

        List<String> list = new ArrayList<>();
        for (Map<String, Object> m : TotalList){
            list.add( m.get("sort").toString());
        }
        Collections.reverse(list);



        for(Map<String, Object> m : TotalList){
            
        }

        list.forEach(System.out::println);
        for (String i : list){
            Map<String,Object> map;
        }
        /*List<Map<String, Object>> list = UserDAO.UserArticle();
        Map<String, Integer> sortMap = new HashMap<>();

        for(Map m : list){
            sortMap.merge(m.get("sort").toString(),1,Integer::sum);
        }

        for (Map.Entry entry : sortMap.entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue());
        }*/


    }
}
