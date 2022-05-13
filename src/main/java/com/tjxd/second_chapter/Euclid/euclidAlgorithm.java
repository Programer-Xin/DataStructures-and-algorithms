package com.tjxd.second_chapter.Euclid;

/**
 * Github作者:小李不会写代码
 *  * https://github.com/Programer-Xin/DataStructures-and-algorithms
 *  * 《数据结构与算法Java分析》第二章算法分析 欧几里得算法 page 32
 */

/*分析 欧几里得算法是求两个数的最大公因数 同时除以两者的最大整数
* 算法连续计算余数知道余数是0位置 最后的非0余数就是最大公因数
* */

    //定理:对于gcd(M,N) 如果M>N 则M mod N < M/2
public class euclidAlgorithm {
    public static void main(String[] args) {
        int a = 100;
        int b = 3;
        System.out.println(gcd(a, b));
    }

    public static long gcd(long m,long n){
        while (n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
