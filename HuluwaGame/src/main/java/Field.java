//package nju.java;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import javax.swing.JFileChooser;

import static java.lang.Thread.sleep;

public class Field extends JPanel {

    private final int OFFSET = 50;
    private final int SPACE = 50;
    private Background background;
    private snake shejing;
    private oldman yeye;
    private xiezijing xiezijing1;
    private ArrayList luoluos=new ArrayList();
    private ArrayList hulvwas=new ArrayList();
    private ArrayList badmans=new ArrayList();
    private ArrayList goodmans=new ArrayList();
    private int w = 0;
    private int h = 0;
    public boolean completed = false;
    FileWriter writer;
    private String level =
            ".................\n" +
                    "1.............l..\n"+
                    ".............l...\n" +
                    "2...........l....\n" +
                    "3..........l.....\n" +
                    "...o......s......\n" +
                    "4.........x......\n" +
                    "5..........l.....\n" +
                    "............l....\n"+
                    "6............l...\n"+
                    "7.............l..\n"+
                    ".................\n";
    private Image iBuffer;
    private  Graphics gBuffer;

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public ArrayList getBadmans() {
        return badmans;
    }

    public ArrayList getGoodmans() {
        return goodmans;
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {
        luoluos.clear();
        hulvwas.clear();
        badmans.clear();
        goodmans.clear();
        int x = OFFSET;
        int y = OFFSET;
        hulvwa i1;
        luoluo l;
        int count=0;
        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);
            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            }
            else if (item == '.') {
                x += SPACE;
            }
            else if(item=='s')
            {
                shejing=new snake(x,y,this,"shejing");
                badmans.add(shejing);
                x+=SPACE;
            }
            else if(item=='o')
            {
                yeye=new oldman(x,y,this,"yeye");
                goodmans.add(yeye);
                x+=SPACE;
            }
            else if(item=='x')
            {
                xiezijing1=new xiezijing(x,y,this,"xiezijing");
                badmans.add(xiezijing1);
                x+=SPACE;
            }
            else if(item=='l')
            {
                count++;
                l=new luoluo(x,y,this,("luoluo"+count).toString());
                badmans.add(l);
                luoluos.add(l);
                x+=SPACE;
            }
            else if (item == '1')
            {
                i1=new hulvwa(x,y,this,1,6,"red");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '2')
            {
                i1=new hulvwa(x,y,this,2,6,"orange");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '3')
            {
                i1=new hulvwa(x,y,this,3,7,"yellow");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '4')
            {
                i1=new hulvwa(x,y,this,4,7,"green");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '5')
            {
                i1=new hulvwa(x,y,this,5,6,"blue");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '6')
            {
                i1=new hulvwa(x,y,this,6,6,"dian");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == '7')
            {
                i1=new hulvwa(x,y,this,7,5,"purple");
                hulvwas.add(i1);
                goodmans.add(i1);
                x += SPACE;
            }
            else if (item == ' ')
            {
                x += SPACE;
            }

            h = y;
        }
        background=new Background(this.w,this.h);
        for(int i=0;i<hulvwas.size();i++)
        { ((Player)hulvwas.get(i)).setenemys(badmans);
            ((Player)hulvwas.get(i)).setFriends(goodmans);
        }
        for(int i=0;i<luoluos.size();i++) {
            ((Player) luoluos.get(i)).setenemys(goodmans);
            ((Player) luoluos.get(i)).setFriends(badmans);
        }
        shejing.setenemys(goodmans);
        shejing.setFriends(badmans);

        xiezijing1.setenemys(goodmans);
        xiezijing1.setFriends(badmans);

        yeye.setenemys(badmans);
        yeye.setFriends(goodmans);
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();

        world.add(background);
        world.add(shejing);
        world.addAll(luoluos);
        world.add(yeye);
        world.add(xiezijing1);
        world.addAll(hulvwas);
        g.drawImage(background.getImage(), 0, 0, this);
        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                if(((Player) item).st==STATE.LIVE)
                {g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);}
            }
            if (completed) {
                Font font=new Font("Serif",Font.PLAIN,56);
                g.setFont(font);
                g.setColor(new Color(100, 255, 0));
                //g.drawString("Completed", 25, 20);
                this.testOver(g);
            }
        }
    }

    public Player GetName(String name)
    {
        for(int i=0;i<this.yeye.enemys.size();i++)
        {
            if(((Player)yeye.enemys.get(i)).getName().equals(name))
                return ((Player)yeye.enemys.get(i));
        }
        for(int i=0;i<this.yeye.friends.size();i++)
        {
            if(((Player)yeye.friends.get(i)).getName().equals(name))
                return ((Player)yeye.friends.get(i));
        }
        return yeye;
    }
    @Override
    public void paint(Graphics g) {
        if(iBuffer==null)
        {
            iBuffer=createImage(this.w,this.h);
            gBuffer=iBuffer.getGraphics();
        }
        super.paint(gBuffer);
        buildWorld(gBuffer);
        g.drawImage(iBuffer,0,0,this);
    }
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_L) {
                initWorld();
                try {
                    JFileChooser fd=new JFileChooser(".");
                    fd.showOpenDialog(null);
                    File file=fd.getSelectedFile();
                    if(file == null)
                        return;
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String read;
                    while(null!=(read=reader.readLine()))
                    {
                        String name=read.substring(0,read.indexOf(' '));
                        read=read.substring(read.indexOf(' ')+1);
                        String x=read.substring(0,read.indexOf(' '));
                        read=read.substring(read.indexOf(' ')+1);
                        String y=read.substring(0,read.indexOf(' '));
                        read=read.substring(read.indexOf(' ')+1);
                        String state=read.substring(0,read.indexOf(' '));
                        if(state.equals("LIVE")) {
                            GetName(name).setXY(Integer.parseInt(x), Integer.parseInt(y));
                            try {
                                sleep(100);
                            }catch (Exception e4)
                            {
                                e4.printStackTrace();
                            }
                            paint(getGraphics());
                        }
                        else
                        {
                            GetName(name).st=STATE.DIE;
                        }
                }}catch (IOException e3)
                {
                    e3.printStackTrace();
                }
            }
            else if (key == KeyEvent.VK_SPACE) {
                try {
                    File file = new File("1.txt");
                    writer = new FileWriter(file);
                }catch(IOException e2)
                {
                    e2.printStackTrace();
                }
                try {
                    ExecutorService exec = Executors.newCachedThreadPool();
                    for (int i = 0; i < hulvwas.size(); i++) {
                        exec.execute((hulvwa) hulvwas.get(i));
                    }
                    for (int i = 0; i < luoluos.size(); i++) {
                        exec.execute((luoluo) luoluos.get(i));
                    }
                    exec.execute(yeye);
                    exec.execute(xiezijing1);
                    exec.execute(shejing);
                    exec.shutdown();
                }catch(RuntimeException ue)
                {
                    System.out.println(ue);
                }
            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }

    public void restartLevel() {

        //a.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }

    public void testOver(Graphics g)
    {
        int count=0;
        int count1=0;
        for(int i=0;i<yeye.friends.size();i++)
        {
            if(((Player)yeye.friends.get(i)).st==STATE.LIVE)
                count++;
        }
        for(int i=0;i<yeye.enemys.size();i++)
        {
            if(((Player)yeye.enemys.get(i)).st==STATE.LIVE)
                count1++;
        }
       if(count==0&&count1==0)
       {
           g.drawString("两败俱伤",200,300);
       }
       else if(count==0)
       {
           g.drawString("妖精获胜!!!",200,300);
       }
       else if(count1==0)
       {
           g.drawString("葫芦娃获胜！！！",200,300);
       }
       else
       {
           g.drawString("两败俱伤",200,300);
       }
    }
}