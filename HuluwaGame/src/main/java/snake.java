//package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
public class snake extends badguys {
    public snake(int x,int y,Field field,String name)
    {
        super(x,y,field,name);
        level=8;
        pr=personality.BAD;
        URL loc = this.getClass().getClassLoader().getResource("蛇精.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

}
