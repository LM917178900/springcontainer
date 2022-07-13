package com.only.huahua.portal.model.report.controller;

/**
 * @author :leiming5
 * @version :1.0
 * @createTime :2022/7/1 15:27
 */

import java.util.Scanner;

class Test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {

            // TODO: 请在此编写代码
            // 1. n%2==1,n/2! + n/2;
            // 2. n%2=0, n/2!;
            int n = Integer.parseInt(scan.next());
            int result = getResult(n);
            System.out.println(result);
        }
        scan.close();
    }

    private static int getResult(int number) {

        int total = 0;
        for (int m = 0; m < number / 2; m++) {
            int n = number - m;
            total += getConcreate(m, n);
        }

        boolean odd = number % 2 == 1;
        int lastStage = odd ? (number / 2 + 1) : 1;
        return total + lastStage;
    }

    private static int getConcreate(int m, int n) {
        if (n == m) {
            return 1;
        }
        return getStage(n) / (getStage(m) * getStage(n - m));
    }

    private static int getStage(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * getStage(n - 1);
    }
}
