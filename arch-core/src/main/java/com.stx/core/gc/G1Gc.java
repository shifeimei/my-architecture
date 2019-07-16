package com.stx.core.gc;

/**
 * g1 gc要求jdk>=1.9
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class G1Gc {
    public static void main(String[]args)throws Exception{
        System.out.println("full gc start");
        int i = 0;
        while ( i++ < 100) {
            byte[] a = new byte[1024 * 1024 * 2000];
            //System.gc();
            Thread.sleep(5000);
        }

        System.out.println("full gc finish");
    }
}
