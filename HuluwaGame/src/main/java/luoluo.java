//package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class luoluo extends badguys {
    public luoluo(int x,int y,Field field,String name)
    {
        super(x,y,field,name);
        level=5;
        pr=personality.BAD;
        URL loc = this.getClass().getClassLoader().getResource("小喽啰2.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
