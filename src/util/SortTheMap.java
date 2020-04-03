package util;

import java.util.*;

public class SortTheMap {
    /**
     *
     * @param map
     * @return Map<String, Integer>
     *
     * 返回一个Map<String, Integer>类型的Map，其中的Value按从大到小的顺序排列
     */
    public static Map<String, Integer> SortedMapByBiggerValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());/**idea牛*/
        //Collections.reverse(list);

        Map<String, Integer> tempMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list){
            tempMap.put(entry.getKey(), entry.getValue());
        }
        return tempMap;
    }

}
