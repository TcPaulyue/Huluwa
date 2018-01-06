//package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class xiezijing extends badguys {
    public xiezijing(int x,int y,Field field,String name)
    {
        super(x,y,field,name);
        level=7;
        pr=personality.BAD;
        URL loc = this.getClass().getClassLoader().getResource("蝎子精.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
