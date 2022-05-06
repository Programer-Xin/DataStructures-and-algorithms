package com.tjxd.sixth_chapter.sort;

import java.util.Arrays;

/**
 * 作者:小李不会写代码
 * https://github.com/Programer-Xin/DataStructures-and-algorithms
 * 《数据结构与算法分析Java》page:271 插入排序
 */

/*
插入排序算法---升序排序

此算法保证从位置0到位置p上的元素为已排序状态
在第p躺 我们将位置p上的元素向左移动 直到它在前p+1个元素中的正确位置被找到
 */
public class insertSort {
    public static void main(String[] args) {
        int arr[] = {34,8,64,51,32,21};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    由于嵌套循环的每一个都要花费N次迭代 所以插入排序的最坏时间复杂度为O(N^2) 最好是O(N)
    此外如果输入数据已预先排序 那么时间复杂度为O(N) 因为第二层for循环的检测总是立即判定不成立就终止
     */
    public static void insertionSort(int[] arr) {
        //定义j 作为指向后一个数的位置的辅助变量
        int j;
        for (int p = 1; p < arr.length; p++) {
            //把数组第p位置上的元素赋值给临时变量tmp
            int tmp = arr[p];
            for (j = p;j > 0 && tmp < arr[j-1];j--){
                //j--的目的是让j不断的和前面的所有数字进行比较
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }
}

/**
 * 小结
 * 逆序数就是具有性质i<j 但是a[i]>a[j]的序偶(a[i],a[j])
 * 定理:1.N个互异的数组的平均逆序数是N(N-1)/4
 *     2.通过交换相邻元素进行排序的任何算法平均都需要O(N^2)时间
 */
