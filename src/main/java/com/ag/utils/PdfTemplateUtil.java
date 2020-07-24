package com.ag.utils;

/**
 * @Auther: admin
 * @Date: 2019/8/10 12:59
 * @Description:
 */

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

public class PdfTemplateUtil {


    //构造器私有，防止别人通过new对象调用
    private PdfTemplateUtil() {
    }

    /**
     * @param data             模板数据
     * @param templateFileName freemarker模板文件名
     * @return : java.io.ByteArrayOutputStream
     * @auther : $Mr. Liu$
     * @date : 2019/8/9 14:45
     * @description : 通过模板导出pdf文件(有返回值)
     **/

    public static ByteArrayOutputStream createPDF(Map<String, Object> data, String templateFileName) throws Exception {
        // 创建一个FreeMarker实例, 负责管理FreeMarker模板的Configuration实例
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        configuration.setClassForTemplateLoading(PdfTemplateUtil.class, "/templates");
        ITextRenderer renderer = new ITextRenderer();
        OutputStream out = new ByteArrayOutputStream();
        StringWriter writer = new StringWriter();

        try {
            // 设置 css中 的字体样式（暂时仅支持宋体和黑体） 必须，不然中文不显示
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("C:/Windows/Fonts/simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            // 设置模板的编码格式
            configuration.setEncoding(Locale.CHINA, "UTF-8");
            // 获取模板文件
            Template template = configuration.getTemplate(templateFileName, "UTF-8");
            // 将数据输出到html中
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            // 把html代码传入渲染器中
            renderer.setDocumentFromString(html);

            // 设置模板中的图片路径 （这里的images在resources目录下） 模板中img标签src路径需要相对路径加图片名 如<img src="images/xh.jpg"/>
//            String url = PdfTemplateUtil.class.getClassLoader().getResource("static/images").toURI().toString();
//            renderer.getSharedContext().setBaseURL(url);
            renderer.layout();
            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
            return (ByteArrayOutputStream) out;
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @param data             模板数据
     * @param templateFileName freemarker模板文件名
     * @auther : $Mr. Liu$
     * @date : 2019/8/9 14:45
     * @description : 通过模板导出pdf文件(改进后无返回值)
     **/

    public static void createPDF(Map<String, Object> data, String templateFileName, String fileName, HttpServletResponse response) throws Exception {
        // 创建一个FreeMarker实例, 负责管理FreeMarker模板的Configuration实例
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        configuration.setClassForTemplateLoading(PdfTemplateUtil.class, "/templates");
        //设置字体
        ITextRenderer renderer = new ITextRenderer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StringWriter writer = new StringWriter();

        try {
            // 设置 css中 的字体样式（暂时仅支持宋体和黑体） 必须，不然中文不显示
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("C:/Windows/Fonts/simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont("C:/Windows/Fonts/simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            // 设置模板的编码格式
            configuration.setEncoding(Locale.CHINA, "UTF-8");
            // 获取模板文件
            Template template = configuration.getTemplate(templateFileName, "UTF-8");
            // 将数据输出到html中
            template.process(data, writer);
            writer.flush();
            String html = writer.toString();
            // 把html代码传入渲染器中
            renderer.setDocumentFromString(html);

            // 设置模板中的图片路径 （这里的images在resources目录下） 模板中img标签src路径需要相对路径加图片名 如<img src="images/xh.jpg"/>
            String url = PdfTemplateUtil.class.getClassLoader().getResource("static/images").toURI().toString();
            renderer.getSharedContext().setBaseURL(url);
            renderer.layout();
            renderer.createPDF(out, false);
            out.flush();
            renderer.finishPDF();
            response.setContentType("application/x-msdownload");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out.writeTo(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
    /**
     * @param inputFile 你的PDF文件地址
     * @param outputFile 添加水印后生成PDF存放的地址
     * @param waterMarkName 你的水印
     * @return
     */
    public static boolean waterMark(String inputFile,
                                    String outputFile, String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));
            //这里的字体设置比较关键，这个设置是支持中文的写法
            BaseFont base = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 使用系统字体
            int total = reader.getNumberOfPages() + 1;

            PdfContentByte under;
            Rectangle pageRect = null;
            for (int i = 1; i < total; i++) {
                pageRect = stamper.getReader().
                        getPageSizeWithRotation(i);
                // 计算水印X,Y坐标
                float x = pageRect.getWidth()/10;
                float y = pageRect.getHeight()/10-10;
                // 获得PDF最顶层
                under = stamper.getOverContent(i);
                under.saveState();
                // set Transparency
                PdfGState gs = new PdfGState();
                // 设置透明度为0.2
                gs.setFillOpacity(1.f);
                under.setGState(gs);
                under.restoreState();
                under.beginText();
                under.setFontAndSize(base, 60);
//                under.setColorFill(BaseColor.ORANGE);

                // 水印文字成45度角倾斜
                under.showTextAligned(Element.ALIGN_CENTER
                        , waterMarkName, x,
                        y, 55);
                // 添加水印文字
                under.endText();
                under.setLineWidth(1f);
                under.stroke();
            }
            stamper.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}