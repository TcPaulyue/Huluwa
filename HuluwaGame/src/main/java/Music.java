//package nju.java;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.*;

public class Music extends JFrame{
   public File f;
   public   URI uri;
   public   URL url;
      Music(){
          try {
                /*  f = new File("嗨嗨人生.wav");
                  uri = f.toURI();
                  url = uri.toURL();  //解析地址
                   AudioClip aau;
                   aau = Applet.newAudioClip(url);
                   aau.loop();  //循环播放*/
            } catch (Exception e)
           { e.printStackTrace();
           }
    }}