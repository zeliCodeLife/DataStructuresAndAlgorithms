package com.zero.sparsearray;

/**
 * Created by zero on 2020/5/29.
 * 场景：
 * 1) 二维数组的很多值是默认值 0, 因此会记录很多没有意义的数据，此时可以想到稀疏数组。
 * 2) 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * 稀疏数组的处理方法是:
 * 1) 记录数组一共有几行几列，有多少个不同的值
 * 2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        // 输出原始的二维数组,加强for循环，格式化输出
        System.out.println("原始的二维数组");
        for(int[] row : chessArray){
            for(int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组转换为细数数组
        //1.便利二维数组得到非0数据的个数，知识点：二维数组未知length的情况下便利
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j]!=0) {
                      sum++;
                }

            }
        }
        //2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        //给稀疏数组第一行赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //便利二维数组将非0数据存到sparseArr中
        int count = 0; //记录这是第几个非0数据，也就是第几行
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j]!=0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }

            }
        }
        //3.输出稀疏数组验证
        System.out.println();
        System.out.println("得到稀疏数组为");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();

        //将稀疏数组还原成二维数组
        //1.先创建出二维数组的行+列
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        //2.恢复具体的二维数组中的值
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //3.输出验证
        // 输出原始的二维数组,加强for循环，格式化输出
        System.out.println("还原后的二维数组");
        for(int[] row : chessArr2){
            for(int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
