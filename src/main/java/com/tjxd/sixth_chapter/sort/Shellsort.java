package com.tjxd.sixth_chapter.sort;


import java.util.Arrays;

/**
 * 作者:小李不会写代码
 * https://github.com/Programer-Xin/DataStructures-and-algorithms
 * 《数据结构与算法分析Java》page:274 希尔排序
 */

/*
希尔排序是通过比较相距一定间隔的元素来工作 各躺比较所用的距离随着算法的进行而减小
直到只比较相邻元素的最后一趟排序为止 由于这个原因希尔排序也叫缩减增量排序

增强序列默认是每次除以2 直到1为止进行插入排序 但是并不是固定的 依据实际情况优化算法
 */
public class Shellsort {
    public static void main(String[] args) {
        int arr[] = {81,94,11,96,12,35,17,95,28,58,41,75,15};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int arr[]){
        //定义变量j作为指向后一个数的位置的辅助变量
        int j;
        //对于任意一个正整数除2取整 最后一定会变成1之后取整变为0 当变成0我们的for循环结束
        for (int gap = arr.length / 2;gap > 0;gap /= 2){
            for (int i = gap;i < arr.length;i++){
                int tmp = arr[i];
                for (j = i;j >= gap && tmp < arr[j-1];j -=gap){
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }
}

/**
 * 小结
 *  定理:1.使用希尔增量时希尔排序的最坏运行时间复杂度为O(N^2)
 *  Hibbard提出一个稍微不同的增量序列 增量形如1、3、7...2^k-1
 *  虽然这些增量几乎是相同的 但关键区别是相邻的增量没有公因子
 *  定义2.使用Hibbard增量的希尔排序的醉花时间复杂度为O(N^3/2)
 */
