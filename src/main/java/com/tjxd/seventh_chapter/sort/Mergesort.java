package com.tjxd.seventh_chapter.sort;
import java.util.Arrays;

/**
 * 作者:小李不会写代码
 * https://github.com/Programer-Xin/DataStructures-and-algorithms
 * 《数据结构与算法分析Java》page:194 归并排序
 */

/**
 * 算法分析:
 * 归并排序以O(NlogN)最坏时间复杂度运行 而所使用的比较次数几乎是最优的
 * 这个算法的基本操作是合并两个已排序的表 因为两个表是排好序的 所以输出放到第三个表
 * 合并两个已排序的表的时间复杂是线性的 因为最多进行N-1次比较 其中N是元素总数
 *
 * 24,13,26,1,2,27,38,15排序
 * 递归地将前4个数字和后面4个数字分别排序再合并
 * 该算法是经典的分而治之策略 将问题分成一些小问题递归求解
 * 治的阶段将分的阶段得到的答案修补在一起 递归中常用到分而治之
 *
 * 声明小李写在注释写的"大数组"实际就是我们在main方法里输入的数组 即起始数组
 */

public class Mergesort {
    public static void main(String[] args) {
        int arr[] = {24,13,26,1,2,27,38,15};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int arr[],int tmpArray[],int left,int right){
        //left和right代表左右两边数组的下标 left < right 说明还没超过
        if (left < right){
            //取整个元素的中间下标
            int center = (left + right) / 2;
            //进入递归循环 不断的取中间下标 不断的把大数组拆解成两个元素的小数组 并进行归并
            mergeSort(arr,tmpArray,left,center);  //大数组的左半边
            mergeSort(arr,tmpArray,center + 1,right); //大数组的右半边
            //大数组左右两边 两部分内部已经排好序 现在对大数组的左右两部分进行归并排序
            merge(arr,tmpArray,left,center + 1,right);
        }
    }

    public static void mergeSort(int[] arr){
        //tmpArray是临时的数组也就是第三个数组 其长度和输入的原数组一致
        int tmpArray[] = new int[arr.length];
        //方法的重载
        //left为输入数组的左边起始下标 right为输入数组的最后元素下标
        mergeSort(arr,tmpArray,0, arr.length - 1);
    }

    /**
     * 归并排序函数
     * @param arr 大数组即原始数组
     * @param tmpArray 临时存储数组
     * @param leftPos (分)数组的左边起始下标
     * @param rightPos (分)数组的中间下标
     * @param rightEnd (分)数组的最后元素下标
     */
    public static void merge(int[] arr,int[] tmpArray,int leftPos,int rightPos,int rightEnd){
        //leftEnd代表(分)数组的左半边的终止下标
        int leftEnd = rightPos - 1;
        //把(分)数组的下标赋值为临时数组 让其依次填充元素
        int tmpPos = leftPos;
        //rightEnd即为(分)数组的右半边数组的终止下标 下面的式子计算(分)数组的元素数量
        int numElements = rightEnd - leftPos + 1;

        //满足左半边数组的下标没有越界到右半边数组
        while (leftPos <= leftEnd && rightPos <= rightEnd){
            //左半边数组的元素小 满足升序 小的元素放入临时数组
            if (arr[leftPos] <= arr[rightPos]){
                //leftPos和tmpPos同时自增 指向下一个索引
                //注意着是X++的形式 意味着先赋值再自增 千万不要迷糊了
                tmpArray[tmpPos++] = arr[leftPos++];
            }else {
                //右半边数组的元素小 放入临时数组
                tmpArray[tmpPos++] = arr[rightPos++];
            }
        }

        //左半边的数组下标没有越界 把剩余没有赋值的左半边元素赋给临时数组 直至越界到右半边数组
        while (leftPos <= leftEnd){
            tmpArray[tmpPos++] = arr[leftPos++];
        }

        //右半边数组没有越界 同上
        while (rightPos <= rightEnd){
            tmpArray[tmpPos++] = arr[rightPos++];
        }

        //左右数组交换结束后 把整个临时数组赋值给原数组 实现排序
        for (int i = 0; i < numElements; i++,rightEnd--) {
            arr[rightEnd] = tmpArray[rightEnd];
        }
    }
}
