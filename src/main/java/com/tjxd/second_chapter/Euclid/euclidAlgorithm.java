package com.tjxd.second_chapter.Euclid;

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
