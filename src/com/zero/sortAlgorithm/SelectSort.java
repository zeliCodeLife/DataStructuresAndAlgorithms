package com.zero.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zero on 2020/6/10.
 * desc:选择排序
 * 选择排序(select sorting)也是一种简单的排序方法。它的基本思想是:第一次从 arr[0]~arr[n-1]中选取最小值，
 * 与 arr[0]交换，第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2] 交换，
 * ...，第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，...,
 * 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值， 与 arr[n-2]交换，
 * 总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列。
 */
public class SelectSort {
    public static void main(String[] args) {
        //创建要给 80000 个的随机的数组， 在我的机器是 2-3 秒，比冒泡快.
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数 }

        }
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    private static void selectSort(int[] arr) {
        //在推导的过程，我们发现了规律，因此，可以使用 for 来解决
        //选择排序时间复杂度是 O(n^2)
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            int min = arr[i];
            //下面的内容是找出最小值和下标
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]) { //说明假定的最小值，并不是最小
                    min = arr[j];//重置 min
                    minIndex = j;
                }

            }
            // 将最小值，放在 arr[0]（游标所在的位置）, 即交换。
            if (minIndex!=i) {
                  arr[minIndex] = arr[i];
                  arr[i] = min;
            }

        }
    }
}