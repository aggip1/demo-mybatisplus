package com.ag.utils;

import com.ag.DemoMybatisplusApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
@SpringBootTest(classes = DemoMybatisplusApplication.class)
@RunWith(SpringRunner.class)
public class PdfTemplateUtilTest {

    @Test
    public void createPDF() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data","1" );
        try {
            ByteArrayOutputStream pdf = PdfTemplateUtil.createPDF(hashMap, "test.ftl");
            File file = new File("D://data//test.pdf");
            OutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = pdf.toByteArray();
            outputStream.write(bytes,0,bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testq(){
        ArrayList<Object> objects = new ArrayList<>();
//        objects.stream().flatMap()

    }
}