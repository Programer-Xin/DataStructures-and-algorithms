package com.tjxd.second_chapter.subSum;



/**
 * Github作者:小李不会写代码
 * https://github.com/Programer-Xin/DataStructures-and-algorithms
 * 《数据结构与算法Java分析》第二章算法分析 最大子序列和问题
 */

public class maxSub {
    public static void main(String[] args) {
        int arr[] = {-2,11,-4,13,-5,-2};
        System.out.println(maxSubSumOne(arr));
        System.out.println(maxSubSumTwo(arr));
        System.out.println(maxSubSumThree(arr));
        System.out.println(maxSubFour(arr));

    }

    /*
    算法1:
    运行时间为O(N^3)
     */
    public static int maxSubSumOne(int[] arr){
        int maxSum = 0; //记录最大Σ
        /*假设arr.length的长度为n
        第一个for循环控制的是取数次数(元素下标) 执行次数是n+1 i代表当前序列的起点
        */
        for (int i = 0; i < arr.length; i++) {
            //第二个for循环控制比较次数 执行次数为n+1 j代表当前序列的终点
            for (int j = 0; j < arr.length; j++) {
                //先定义一个临时子序列集合存放值
                int thisSum = 0;
                //第三个for循环控制子序列增加元素的个数 执行次数为n-2
                for (int k = 1;k <= j;k++){
                    thisSum += arr[k];
                }
                //if循环的执行次数为常数n次
                if (thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        //总的时间复杂度T(n)=(n+1)(n+1)(n-2)+n=n^3-2n-4 --> O(n^3)
        return maxSum;//返回最大子序列
        //这种算法最笨 从第一个数字开始 逐个增加一位
        // 变成新的子序列与之前假设的"最大子序列和"进行比较
    }

    /**
     * 算法2:
     * 通过撤销一个for循环实现运行时间是O(N^2)
     */
    public static int maxSubSumTwo(int[] arr){
        //假定最大子序列和
        int maxSum = 0;
        //第一个for循环控制取数次数(元素下标) 执行次数n+1 i代表当前序列的起点
        for (int i = 0; i < arr.length; i++) {
            //设置临时变量存储当前子序列和
            int thisSum = 0;
            //第二个for循环控制子序列加元素次数
            //和假定的"最大子序列"maxSum和当前子序列thisSum的比较次数 循环执行次数为n+1 j代表当前序列的终点
            for (int j = 0; j < arr.length; j++) {
                thisSum += arr[j];
                //if循环执行次数为n 受最近的for循环控制
                if (thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
        //总的时间复杂度=T(n)=(n+1)(n+1)+n=n^2+3n+1 --> O(n)=n^2
    }

    /**
     * 算法3:
     * 对于算法2的时间复杂度为O(n^2) 我们应该将其降低为nlog^n的形式
     * 采用"分治"的策略 把问题分成两个大致相等的子问题 然后递归地对他们求解 这是"分"的部门
     * "治"的阶段将两个子问题修补在一起并尽可能做些少量的附加工作 得到整个问题的解
     *
     * 我们分析知道 最大子序列是数组一定范围内连续存储的数字最大和
     * 所以最大子序列和只有三种分布方式 分别是输入数据的左半部、右半部、或者输入数据的中部
     * 前两种左右半部的情况可以通过递归求解 第三种情况可以求出前半部分最大和与右半部分最大和 两者相加得到
     */
//    {-2,11,-4,13,-5,-2};
    private static int maxSumRec(int[] arr,int left,int right){
        /*递归终止条件 子列只有一个数字*/
        if (left == right){
            if (arr[left] > 0){
                return arr[left];
            }else {
                return 0;
            }
        }
        //定义中间元素的下标
        int center = (left + right) / 2;
        //前半部分最大和
        int maxLeftSum = maxSumRec(arr,left,center);
        //后半部分最大和
        int maxRightSum = maxSumRec(arr,center + 1,right);

        //定义左半部分最大和 以及 临时存储子序列和变量
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum){
                maxLeftBorderSum = leftBorderSum;
            }
        }
        //定义右半部分最大和 以及 临时存储子序列和变量
        int maxRightBorderSum = 0,rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum){
                maxRightBorderSum = maxRightBorderSum;
            }
        }
        return max(maxLeftSum,maxRightSum,maxLeftBorderSum + maxRightBorderSum);
    }

    //比较三者数字的最大值 返回最大值
    private static int max(int maxLeftSum, int maxRightSum, int i) {
       int max = 0;
       if (maxLeftSum > maxRightSum){
           max = maxLeftSum;
       }else {
           max = maxRightSum;
       }
       if (i > maxRightSum){
           max = i;
       }
       return max;
    }

    /**
     *
     */
    public static int maxSubSumThree(int[] arr){
        return maxSumRec(arr,0, arr .length - 1);
    }

    /**
     * 算法4:
     * 这个算法的优点是 它只对数据进行一次扫描 一旦a[i]被读入并处理 它就不需要被记忆
     * 因此 如果数组在磁盘或通过互联网传送 那么它可以被按顺序读入 在贮存中不必存储数组的任何部分
     * 不仅如此 在任意时刻算法都能对它已经读入的数据给出子序列问题的正确答案 具有这种特性的算法叫做联机算法、
     * 仅需要常量空间并以线性时间运行的联机算法几乎是完美的算法
     */

    public static int maxSubFour(int arr[]){
        int maxSum = 0,thisSum = 0;
        //j代表当前序列的终点
        for (int j = 0; j < arr.length; j++) {
            thisSum += arr[j];
            if (thisSum > maxSum){
                maxSum = thisSum;
            }else if (thisSum < 0){
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
