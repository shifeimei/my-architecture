package com.stx.core.bigfile;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class BigFileTest {
    private static final String filePathPrefix = "/Users/tongxiang.stx/tmp/big/";
    private static final String filePath = filePathPrefix + "bigfile.txt";
    private static final Integer batch = 500 * 1024 * 1024;

    public static void main(String[] args) throws Exception {
        //generateBigFile(2000000);

        splitFile();

        //sortBigFile();
    }

    private static void sortBigFile() {

    }

    private static void splitFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = 0;
        int fileIndex = 0;
        try {
            String line = null;
            int lineCnt = 0;
            List<Integer> subList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lineCnt++;
                String[] cols = line.split(",");
                if (cols == null || cols.length <= 0) {
                    continue;
                }
                for (String col : cols) {
                    if (!Objects.isNull(col)) {
                        subList.add(Integer.parseInt(col));
                    }
                }

                if (lineCnt >= batch) {
                    saveSubFile(fileIndex, subList);
                    lineCnt = 0;
                }
            }

            saveSubFile(fileIndex, subList);
            lineCnt = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void saveSubFile(int fileIndex, List<Integer> subList) throws Exception {
        if (subList == null || subList.size() <=0){
            return;
        }

        Collections.sort(subList);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filePathPrefix + "/split/" + fileIndex));
            StringBuilder sb = new StringBuilder();
            int inncnt = 0;
            for (Integer col : subList) {
                sb.append(col).append(",");
                if (inncnt >= 100) {
                    inncnt = 0;
                    sb.append("\n");
                }
            }
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        fileIndex++;
    }

    private static void generateBigFile(Integer size) throws Exception {
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));

        Random random = new Random();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        int batch = 100000;
        while (i++ < size) {
            for (int j = 0; j < 100; j++) {
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
