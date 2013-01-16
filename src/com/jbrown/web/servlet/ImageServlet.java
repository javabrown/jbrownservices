package com.jbrown.web.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
    {
        res.setContentType("image/jpeg");  
  
        try  
        {
            //Create an image 200 x 200  
            BufferedImage bufferedImage = new BufferedImage(200, 200,   
                         BufferedImage.TYPE_INT_RGB);  
  
            //Draw an oval  
            Graphics g = bufferedImage.getGraphics();  
            g.setColor(Color.blue);  
            g.fillOval(0, 0, 199,199);  
  
            //Free graphic resources  
            g.dispose();  
  
            //Write the image as a jpg  
            ImageIO.write(bufferedImage, "jpg", res.getOutputStream());  
        }  
        catch (IOException ioe)  
        {  
        	ioe.printStackTrace();
        }
    }
}
