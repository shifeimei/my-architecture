package com.stx.core.bigfile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class BigFileTest {
    private static final String filePath = "/Users/tongxiang.stx/tmp/bigfile.txt";

    public static void main(String[] args) throws Exception {
        //generateBigFile(2000000);

        sortBigFile();
        }

    private static void sortBigFile() {

    }

    private static void generateBigFile(Integer size) throws Exception {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));

        Random random = new Random();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        int batch = 100000;
        while (i++ < size) {
            for (int j = 0; j < 100; j ++) {
                sb.append(random.nextInt()).append(",");
            }
            sb.append("\n");
            if (i % batch == 0) {
                System.out.println("big file generated part" + i / batch);
                outputStream.write((sb.toString().getBytes()));
                outputStream.flush();
                sb = new StringBuffer();
            }
        }
        if (sb != null && sb.length() > 0) {
            outputStream.write((sb.toString().getBytes()));
            outputStream.flush();
        }
        System.out.println("big file generated");
    }
}
