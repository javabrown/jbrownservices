package com.jbrown.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class SwingUtil {
	
	public static void texttoImage(String text, int width, int height,
			HttpServletResponse res) {
       try  
        {
    	     
            //Create an image 200 x 200  
            BufferedImage bufferedImage = new BufferedImage(width, height,   
                         BufferedImage.TYPE_INT_RGB);  
  
            //Draw an oval  
            Graphics g = bufferedImage.getGraphics();  
       
            g.setFont(new Font("Serif", Font.ITALIC, 35));  
            g.setColor(Color.red);  
            
            int imgW = 20;
            int imgh = 20;
            if(width > 0 && height > 0){
              imgW =  width/10;
              imgh = height / 2;
            }
            
            g.drawString(text, imgW, imgh);  
               
  
            //Free graphic resources  
            g.dispose();  
            //Write the image as a jpg  
            //ImageIO.write(bufferedImage, "jpg", res.getOutputStream());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            ImageIO.write(bufferedImage, "jpg", res.getOutputStream());
            byte[] bytesOut = baos.toByteArray();
            
            //return bytesOut;
        }  
        catch (Exception ioe)  
        {  
        	ioe.printStackTrace();
        }
        //return null;
	}
}
