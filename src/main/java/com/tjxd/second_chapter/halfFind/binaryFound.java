package com.tjxd.second_chapter.halfFind;

/**
 * 作者:小李不会写代码
 * https://github.com/Programer-Xin/DataStructures-and-algorithms
 * 《数据结构与算法分析Java》page:31 折半查找
 */

public class binaryFound {
    /**
     * 运行时间中的对数
     * 对数最常出现的规律可概括下列一般法则:
     * 如果一个算法用常数时间O(1)将问题的大小削减为其一部分 那么该算法就是O(logN)
     * 另一方面如果使用常数时间只是把问题减少一个常数的数量 那么这种算法就是O(N)
     */

    public static void main(String[] args) {
        int arr[] = {2,10,16,19,23,44,56,58,69,80,99};
        System.out.println(binarySearch(arr,58));
    }

    public static int binarySearch(int[] arr,int x){
        int low = 0,high = arr.length - 1;
        while (low <= high){
            //取中间元素的下标
            int mid = (low + high) / 2;
            if (arr[mid] < x){
                low = mid + 1;
            }else if (arr[mid] > x){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -1; //没有找到
    }

}
