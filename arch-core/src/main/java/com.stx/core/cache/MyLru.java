package com.stx.core.cache;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author tongxiang.stx
 * @date 2019/07/15
 */
public class MyLru {

    public static void main(String[]args){
        LinkedHashMap map = new LinkedHashMap(10, 0.75f, true);
        map.put("c","3");
        map.put("a", "1");
        map.put("b","2");

        System.out.println(map);

        String i = (String)map.get("a");

        System.out.println(map);

        List<String> a= new LinkedList<>();
        a.add("a");a.add("b");a.add("c");
        a.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
