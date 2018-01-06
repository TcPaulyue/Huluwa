//package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class oldman extends goodman {
    public oldman(int x,int y,Field field,String name)
    {
        super(x,y,name,field);
        level=6;
        pr=personality.GOOD;
        URL loc = this.getClass().getClassLoader().getResource("爷爷.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);

    }
}
