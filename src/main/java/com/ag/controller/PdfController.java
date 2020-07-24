package com.ag.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/pdf")
public class PdfController {
    private static final String font = "SimSun";

    @RequestMapping("/createPdf")
    public String createPdf() {
        HashMap<String, String> hashMap = new HashMap<>();
        //将数据放入map集合中 从键中取值
        hashMap.put("data", "aaaaa");
        hashMap.put("font", font);

        return null;
    }

    /**
     * 通过注解获取配置文件里面的内容
     */
    @Value("${init.app}")
    private String number;

    @RequestMapping("/test1")
    public void test1() {
        System.out.println(number);
    }
}
