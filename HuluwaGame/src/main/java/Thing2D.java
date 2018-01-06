//package nju.java;

import java.awt.Image;

public class Thing2D {

    private final int SPACE = 50;

    private int x;
    private int y;
    public static int counttime=0;
    private Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;
        //counttime=0;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean testdelete(Player m1)
    {
        int t1=m1.x()-this.x();
        int t2=m1.y()-this.y();
        if(t1<0)
            t1=-t1;
        if(t2<0)
            t2=-t2;
        if(t1<30*(1+this.counttime)&&t2<30*(1+this.counttime))
            return true;
        else
            return false;
    }
} 