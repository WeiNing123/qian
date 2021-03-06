package com.wn.qian.modules.common.service.impl;

import com.wn.qian.modules.common.service.IAuthImgService;
import com.wn.utils.RandomCount;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class AuthImgServiceImpl implements IAuthImgService {

    /**
     * 产生默认验证码，4位中等难度
     * @return
     */
    @Override
    public String getSecurityCode() {
        int code = RandomCount.random4();
        return code + "";
    }

    /**
     * 生成验证码图片
     * @param securityCode
     * @return
     */
    @Override
    public BufferedImage createImage(String securityCode){
        int codeLength = securityCode.length();//验证码长度
        int fontSize = 18;//字体大小
        int fontWidth = fontSize+1;
        //图片宽高
        int width = codeLength*fontWidth+6;
        int height = fontSize*2+1;
        //图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);//设置背景色
        g.fillRect(0, 0, width, height);//填充背景
        g.setColor(Color.LIGHT_GRAY);//设置边框颜色
        g.setFont(new Font("Arial", Font.BOLD, height-2));//边框字体样式
        g.drawRect(0, 0, width-1, height-1);//绘制边框
        //绘制噪点
        Random rand = new Random();
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < codeLength*6; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            g.drawRect(x, y, 1, 1);//绘制1*1大小的矩形
        }
        //绘制验证码
        int codeY = height-10;
        g.setColor(new Color(19,148,246));
        //Georgia  是个字体，如果想用中文要指定中文字体；eg:"宋体";
        //g.setFont(new Font("Georgia", Font.BOLD, fontSize));
        g.setFont(new Font("宋体", Font.BOLD, fontSize));
        for(int i=0;i<codeLength;i++){
            double deg=new Random().nextDouble()*20;
            g.rotate(Math.toRadians(deg), i*16+13,codeY-7.5);
            g.drawString(String.valueOf(securityCode.charAt(i)), i*16+5, codeY);
            g.rotate(Math.toRadians(-deg), i*16+13,codeY-7.5);
        }
        g.dispose();//关闭资源
        return image;
    }
}
