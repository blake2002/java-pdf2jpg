package com.leo.demo.src;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfImageType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class pdf2jpg {

    private int fontCounter = 1;

    @SuppressWarnings({"squid:S2068"})
    private static final String PASSWORD = "-password";
    private static final String PREFIX = "-prefix";
    private static final String ADDKEY = "-addkey";

@PostConstruct
    public void pdftojpg()
    {
        aa();
    }

    public void aa()
    {
        //创建一个PdfDocument对象
        PdfDocument doc = new PdfDocument();

//加载现有的PDF文件
        doc.loadFromFile("/home/leo/demo/java/pdf/123541.pdf");

//声明一个BufferedImage实例
        BufferedImage image;

//循环浏览PDF页面
        for (int i = 0; i < doc.getPages().getCount(); i++) {

//使用saveAsImage方法将每个页面保存为BufferedImage
            image = doc.saveAsImage(i,PdfImageType.Bitmap,300,300);
          //  image = doc.saveAsImage(i);//默认清晰度不好，上边得执行慢，但清晰度好
//将BufferedImage保存为PNG文件格式
           File file = new File( String.format("/home/leo/demo/java/img/ToImage-img-%d.jpg", i));
           try {
               // 转jpg
                BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
                newImage.createGraphics().drawImage(image,0,0,Color.WHITE,null);
                ImageIO.write(newImage, "jpg", file);
                //---
               // 直接保存是png 会比 再转jpg 快
              // ImageIO.write(newImage, "PNG", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
