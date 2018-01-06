//package nju.java;


import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.swing.ImageIcon;
enum personality{GOOD,BAD};
enum STATE{DIE,LIVE};
public class  Player extends Thing2D implements Runnable {
    private Field field;
    private String name;
    public  int level;
    public personality pr;
    public STATE st;
    public ArrayList enemys;
    public ArrayList friends;
    public Player(int x, int y,String name, Field field) {
        super(x, y);
        this.field = field;
        this.name=name;
        this.st=STATE.LIVE;
        this.counttime=0;
    }
    public void printInf(FileWriter writer)
    {
        try
        {
            writer.write(this.name+" "+this.x()+" "+this.y()+" "+this.st+" "+this.pr+"\r\n");
        }catch (IOException e)
        {e.printStackTrace();}
    }
    public void setenemys(ArrayList m)
    {
        this.enemys=m;
    }
    public void setFriends(ArrayList m){this.friends=m;}
    public boolean testPlace(int x,int y)
    {
        boolean test=true;
        for(int i=0;i<this.friends.size();i++)
        {
            if(this.name!=((Player)this.friends.get(i)).name&&this.x()+x==((Player)this.friends.get(i)).x()&&this.y()+y==((Player)this.friends.get(i)).y())
            {test=false;break;
            }}
        for(int i=0;i<this.enemys.size();i++)
        {
            if(this.x()+x==((Player)this.enemys.get(i)).x()&&this.y()+y==((Player)this.enemys.get(i)).y())
            {  test=false;break;
            }}
            return test;
    }
    public String getName()
    {return this.name;}
    public void MovePlace(int x,int y)
    {
        if(this.pr==personality.GOOD) {
            int nx = this.x() + x;
            int ny = this.y() + y;
            if(nx+100>this.field.getBoardWidth())
            {
                nx=this.x()-x;
                this.pr=personality.BAD;
            }
            if(ny+100>this.field.getBoardHeight()||ny<100)
                ny=this.y()-y;
            this.setX(nx);
            this.setY(ny);
        }
        else
        {
            int nx = this.x() - x;
            int ny = this.y() + y;
            if(nx<100)
            {
                nx=this.x()+x;
                counttime++;
                this.pr=personality.GOOD;
            }
            if(ny+100>this.field.getBoardHeight()||ny<100)
                ny=this.y()-y;
            this.setX(nx);
            this.setY(ny);
        }
    }
    public void move(int x, int y) {
        if(testPlace(x,y)==true)
        {
            MovePlace(x,y);
        }
        else
        {
            this.setX(this.x()+x);
            this.setY(this.y());
        }
    }
    public void setXY(int x,int y)
    {
    super.setX(x);
    super.setY(y);
}
    public boolean testEnd()
    {
        int count=0;
        for(int i=0;i<enemys.size();i++)
        {
            if(((Player)enemys.get(i)).st==STATE.LIVE)
                count++;
        }
        if(count==0)
        {
            try
            {this.field.writer.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            Thread.interrupted();
            return false;
        }
        return true;
    }
    public void killEnemy(Player m,Random rand)
    {
        m.st=STATE.DIE;
        m.printInf(this.field.writer);
        this.level=this.level-1;
        this.move(40, (rand.nextInt(40)-20));
        this.printInf(this.field.writer);
    }
    public void killMyself(Player m)
    {
        this.st=STATE.DIE;
        Thread.interrupted();
        this.printInf(this.field.writer);
        m.level-=1;
    }
    public void dieTog(Player m)
    {
        this.st=STATE.DIE;
        m.st=STATE.DIE;
        m.printInf(this.field.writer);
        this.printInf(this.field.writer);
        Thread.interrupted();
    }
    public synchronized void run() {
        while (!Thread.interrupted()&&this.st==STATE.LIVE&&this.field.completed!=true) {
            Random rand = new Random();
                boolean test=true;
                if(testEnd()==false)
                {
                    this.field.completed=true;
                    return;
                }
                if(testEnd()==true);
               {
                for(int i=0;i<enemys.size();i++)
                {
                    if(((Player)enemys.get(i)).st==STATE.LIVE&&this.testdelete((Player) enemys.get(i))==true)
                    {
                        if(this.level>((Player)enemys.get(i)).level)
                        {
                            test=false;
                            killEnemy( (Player)enemys.get(i),rand);
                            break;
                        }
                        else if(this.level<((Player)enemys.get(i)).level)
                        {
                            test=false;
                            killMyself((Player)enemys.get(i));
                            break;
                        }
                        else
                        {
                            test=false;
                            dieTog((Player)enemys.get(i));
                            break;
                        }
                    }
                }
                if(test==true)
                {
                    this.move(40, rand.nextInt(40)-20);
                    this.printInf(this.field.writer);
                }
               }
            try {
                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();
            } catch (InterruptedException e) {
            }finally {

            }
        }
    }
}