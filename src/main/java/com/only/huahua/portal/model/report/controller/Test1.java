package com.only.huahua.portal.model.report.controller;

/**
 * @author :leiming5
 * @version :1.0
 * @createTime :2022/7/1 15:27
 */

class Test1 {

    public static void main(String[] args) {
        // a-z 97-122
        // A-Z 65-90
        String origin = "ab???????ba";
        String str = origin.replaceAll("\\?", "");
        int min = str.length() / 2;
        int max = origin.length() / 2;
        int readLength = max - min;
        boolean odd = origin.length() % 2 == 1;

        /**
         * 1.1 获取初始char，
         * 1.2 获取结束char;
         * 2. 获取第一段
         * 3. 获取第二段
         * 4. 获取中间值
         * 5. 拼接返回；
         */
        String startStr = str.substring(0, min);
        String endStr = str.substring(min);
        String firstStr = "";
        for (int i = 0; i < readLength; i++) {
            firstStr += (char) (97 + min + i);
        }
        char middleStr = (char) (97 + min + readLength);
        String secondStr = "";
        for (int i = firstStr.length() - 1; i >= 0; i--) {
            secondStr += (firstStr.charAt(i));
        }
        String result = "";
        if (odd) {
            result = startStr + firstStr + middleStr + secondStr + endStr;
        } else {
            result = startStr + firstStr + secondStr + endStr;
        }
        System.out.println(result);
    }

}
