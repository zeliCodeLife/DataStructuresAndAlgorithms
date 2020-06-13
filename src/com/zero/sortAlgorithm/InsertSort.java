package com.zero.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zero on 2020/6/10.
 * desc:插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。
 * 插入排序(Insertion Sorting)的基本思想是:把 n 个待排序的元素看成为一个有序表和一个无序表，
 * 开始时有序表中只包含一个元素，无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，
 * 把它的排 序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 */
public class InsertSort {
    public static void main(String[] args) {
        //创建要给 80000 个的随机的数组，
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数 }

        }
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    /*
     * @author  李泽
     * @date  2020/6/10 10:39 PM
     * @desc 插入排序
     */
    private static void insertSort(int[] arr) {
        int insertVal = 0;
        int inserIndex = 0;
        //使用 for 循环来把代码简化
        for (int i = 0; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            inserIndex = i - 1;// 即 arr[1]的前面这个数的下标
            // 给 insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置，第一次循环时arr[insertIndex]是待插入数待前面那个数
            // 3. 如果待插入待数比较小，就需要将 arr[insertIndex] 后移，arr[insertIndex]是前面有序队列的从最后一个往前的一个游标便利的过程。
            while (inserIndex >= 0 && insertVal < arr[inserIndex]) {
                arr[inserIndex + 1] = arr[inserIndex];
                inserIndex--;
            }

            // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1，如果等于i，说明它原来的位置就是正确的位置，就不用换了
            // 这里我们判断是否需要赋值
            if (inserIndex + 1 != i) {
                arr[inserIndex + 1] = insertVal;
            }
        }
    }
}
