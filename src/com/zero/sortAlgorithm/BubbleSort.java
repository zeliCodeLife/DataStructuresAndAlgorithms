package com.zero.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by zero on 2020/6/10.
 * desc:冒泡排序(Bubble Sorting)的基本思想是:通过对待排序序列从前向后(从下标较小的元素开始),
 * 依次比较 相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
 * 优化: 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，
 * 因此要在 排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排 序写好后，在进行)
 */
public class BubbleSort {
    public static void main(String[] args) {
        //测试一下冒泡排序的速度 O(n^2), 给 80000 个数据，测试
        // 创建要给 80000 个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        //测试冒泡排序
        bubbleSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

    }
    public static void bubbleSort(int[] arr){
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int tmp = 0;// 临时变量
        boolean flag = false;// 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                // 如果前面的数比后面的数大，则交换'
                if (arr[j] > arr[j+1]) {
                      flag = true;
                      tmp = arr[j+1];
                      arr[j+1] = arr[j];
                      arr[j] = tmp;
                }

            }
            //System.out.println("第" + (i + 1) + "趟排序后的数组");
            // System.out.println(Arrays.toString(arr));
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false; // 重置 flag!!!, 进行下次判断
            }

        }
    }
}
