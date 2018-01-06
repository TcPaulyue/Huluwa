//package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class hulvwa extends goodman {
    private int no;
    //String s;
    public hulvwa(int x,int y,Field field,int no1,int level1,String name)
    {
        super(x,y,name,field);
        level=level1;
        pr=personality.GOOD;
        this.no=no1;
        URL loc;
        switch(no)
        {
            case 1:{loc = this.getClass().getClassLoader().getResource("红娃.png");break;}
            case 2:{loc = this.getClass().getClassLoader().getResource("橙娃.png");break;}
            case 3:{loc = this.getClass().getClassLoader().getResource("黄娃.png");break;}
            case 4:{loc = this.getClass().getClassLoader().getResource("绿娃.png");break;}
            case 5:{loc = this.getClass().getClassLoader().getResource("蓝娃.png");break;}
            case 6:{loc = this.getClass().getClassLoader().getResource("靛娃.png");break;}
            case 7:{loc = this.getClass().getClassLoader().getResource("紫娃.png");break;}
            default:{loc = this.getClass().getClassLoader().getResource("红娃.png");break;}
        }
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
