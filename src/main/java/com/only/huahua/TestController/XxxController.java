package com.only.huahua.TestController;

import com.only.huahua.model.Division;
import com.only.huahua.util.ExcelTest2;
import com.only.huahua.util.LtfReportExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: XxxController
 * @author: leiming5
 * @date: 2022/2/22 20:21
 */
@RestController
public class XxxController {

    @Resource
    private LtfReportExcel ltfReportExcel;
    @Resource
    private ExcelTest2 excelTest2;

    @GetMapping("test")
    public String xxx() {
        System.out.println("hello world!");
        return "Hello World!";
    }

    @GetMapping("excel/test")
    public String excelTest(HttpServletResponse response) {
        excelTest2.export(response);
        System.out.println("hello world!");
        return "Hello World!";
    }

    @GetMapping("division")
    public double division(double numerator, double denominator) {
        Division division = new Division();
        System.out.println("hello world!");
        return division.quotient();
    }
}
