package com.stx.core.bigfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tongxiang.stx
 * @date 2019/07/16
 */
public class Sort<T extends Comparable> {
    public List<T> mergeS(List<T[]> lists) {
        if (lists == null || lists.size() <= 0) {
            return null;
        }

        int roadSize = lists.size();
        Object[] points = new Object[roadSize];
        int[] positions = new int[roadSize];
        boolean[] finishList = new boolean[roadSize];

        int index = 0;
        T[] road = null;
        for (index = 0; index < roadSize; index++) {
            road = lists.get(index);
            positions[index] = 0;
            finishList[index] = false;

            if (road != null && road.length > 0) {
                // 升序排.
                Arrays.sort(road);
                if (road[0] != null) {
                    points[index] = road[0];
                }
            } else {
                finishList[index] = true;
            }
        }

        List<T> finalList = new ArrayList<>();
        T min = null;
        int minPos = 0;
        while (true) {
            min = null;
            for (int i = 0; i < roadSize; i++) {
                if (finishList[i]) {
                    continue;
                }
                if (min == null) {
                    min = (lists.get(i))[positions[i]];
                    minPos = i;
                } else {
                    T d = (lists.get(i))[positions[i]];
                    if (min.compareTo(d) > 0) {
                        minPos = i;
                        min = d;
                    }
                }
            }

            if (min == null) {
                //结束
                return finalList;
            } else {
                positions[minPos]++;
                finalList.add(min);
                if (positions[minPos] >= lists.get(minPos).length) {
                    finishList[minPos] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Sort sorter = new Sort<Integer>();
        Integer[] arr1 = new Integer[2];
        arr1[0] = 3;
        arr1[1] = 1;

        Integer[] arr2 = new Integer[3];
        arr2[0] = 4;
        arr2[1] = 2;
        arr2[2] = 10;

        List<Integer[]> lists = new ArrayList<>();
        lists.add(arr1);
        lists.add(arr2);
        List<Integer> res = sorter.mergeS(lists);

        System.out.println(res);
    }
}
