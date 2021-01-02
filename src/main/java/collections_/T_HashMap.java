package collections_;

import java.util.HashMap;
import java.util.Map;

public class T_HashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 200; i++){
            map.put(i, i);
        }
        map.put(1, 0);
    }
}
