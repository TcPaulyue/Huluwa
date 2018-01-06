//package nju.java;


import javax.swing.JFrame;

public final class Ground extends JFrame {

   // private final int OFFSET = 50;


    public Ground() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth(),
                field.getBoardHeight());
        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}