import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import static java.awt.Color.*;

public class salesman {

    public static  class Player1{
        public static int wallet = 50;
        public static int point = 0;
        public static int power = 6;
        public static int shield = 1;
        public static int money = 1;
        public static int ran = 0;

    }
    public static  class Player2{
        public static int wallet = 50;
        public static int point = 0;
        public static int power = 6;
        public static int shield = 1;
        public static int money = 1;
        public static int ran = 0;
    }
    static Player1 player1 = new Player1();
    static Player2 player2 = new Player2();



    //general
    public static int number;
    public static boolean turn = true; // turn around the players turn with true false
    public static boolean change_quest;
    public static int ind = 0;
    public static int[] rands_quest = new int[8];
    public static int nn = 0;
    public static int eight = 8;
    public static int FoundQuest =0;
    public static String turnn = "Toss a Dice";

    //etc
    static boolean sw = true;
    static GameMap gameMap = new GameMap();
    public static int mouse_cor[]= {-1 , -1};
    public static int mouse_x = gameMap.getX();
    public static int mouse_y = gameMap.getY();
    public static int sw2;
    public static int castle = 1;




    public static class Chronometer extends JFrame implements ActionListener{
        private int seconds, minutes, hours;
        private Timer chronometer;
        private JButton runStop, restart;
        private JLabel time;

        public Chronometer(){
            this.setTitle("Chronometer");
            this.setSize(300, 100);
            this.setLocationRelativeTo(null);
            this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

            runStop = new JButton("run");
            runStop.addActionListener(this);

            restart = new JButton("restart");
            restart.addActionListener(this);



            seconds = 0;
            minutes = 0;
            hours = 0;

            time = new JLabel(hours+" : "+minutes+" : "+seconds);
            this.add(runStop);
            this.add(restart);
            this.add(time);

            chronometer = new Timer(1000, this);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof Timer){
                seconds ++;
                if(seconds == 60){
                    minutes ++;
                    seconds = 0;
                    if(minutes == 60){
                        hours ++;
                        minutes = 0;
                    }
                }

                time.setText(hours+" : "+minutes+" : "+seconds);
                return;
            }
            else if(e.getSource() instanceof JButton){
                JButton button = (JButton)e.getSource();
                if(button.getText().equals("run")){
                    chronometer.start();
                    button.setText("stop");
                }else if(button.getText().equals("stop")){
                    chronometer.stop();
                    button.setText("run");

                }else if(button.getText().equals("restart")){
                    seconds = 0;
                    minutes = 0;
                    hours = 0;
                    chronometer.stop();
                    time.setText(hours+" : "+minutes+" : "+seconds);
                }
            }
        }
    }

    public static void playMusic(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "err");
        }
    }



    public static class MyFrame extends JPanel  implements MouseListener {
        JFrame frame = new JFrame();
        JButton dice = new JButton();
        JButton button = new JButton("start");

        int[][] map_falg = {  // check map to accept the move
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] map_p1 = {  //hide for the second player
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] map_p2 = {  //hide for the first player
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        MyFrame() {
            //start button
            button.setBounds(2, 700, 100, 60);
            JLabel label = new JLabel();
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            label.setBorder(border);
            label.setVisible(true);
            imagee back = new imagee();
            JButton scoreboard = new JButton();
            JButton time = new JButton();
            JButton quest = new JButton();
            gameMap.addMouseListener(this);
            frame.add(back);

            gameMap.setLayout(null);
            gameMap.setVisible(true);
            frame.add(gameMap);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setSize(1015,800);
            frame.setLocation(0,0);
            frame.setTitle("Traveling Salesman");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(label);

            Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
            frame.setIconImage(icon);

            ImageIcon im = new ImageIcon("514210-200.png");
            ImageIcon im1 = new ImageIcon("download.png");
            ImageIcon im2 = new ImageIcon("hat.png");
            ImageIcon dice2 = new ImageIcon("images1.png");
            ImageIcon im4 = new ImageIcon("game-icon-png-4480.png");

            //scoreboard
            scoreboard.setIcon(im);
            scoreboard.setBackground(Color.darkGray);
            scoreboard.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            scoreboard.setBounds(850, 20, 110, 118);
            scoreboard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame2 = new JFrame();
                    imagee3 im = new imagee3();
                    JLabel label = new JLabel();
                    label.setText(" " +player1.point );
                    JLabel label2 = new JLabel();
                    label2.setText( " " + player1.wallet + "$");
                    JLabel label3 = new JLabel();
                    label3.setText(" " + player1.power);
                    JLabel label4 = new JLabel();
                    label4.setText(" " + player1.shield);
                    JLabel label5 = new JLabel();
                    label5.setText(" " + player1.money);
                    JLabel label6 = new JLabel();
                    label6.setText(" " + player1.ran);
                    JLabel label7 = new JLabel();
                    label7.setText(" " + player2.point);
                    JLabel label8 = new JLabel();
                    label8.setText(" " + player2.wallet + "$");
                    JLabel label9 = new JLabel();
                    label9.setText(" " + player2.power);
                    JLabel label10 = new JLabel();
                    label10.setText(" " + player2.shield);
                    JLabel label11 = new JLabel();
                    label11.setText(" " + player2.money);
                    JLabel label12 = new JLabel();
                    label12.setText(" " + player2.ran);
                    JLabel label13 = new JLabel();
                    label13.setText("Point");
                    JLabel label14 = new JLabel();
                    label14.setText("Wallet");
                    JLabel label15 = new JLabel();
                    label15.setText("Power");
                    JLabel label16 = new JLabel();
                    label16.setText("Shield");
                    JLabel label17 = new JLabel();
                    label17.setText("Money");
                    JLabel label18 = new JLabel();
                    label18.setText("Ran");
                    JLabel label19 = new JLabel();
                    label19.setText("1");
                    JLabel label20 = new JLabel();
                    label20.setText("2");
                    //
                    label.setForeground(new ColorUIResource(0 , 0 , 90));
                    label2.setForeground(new ColorUIResource(0 , 0 , 90));
                    label3.setForeground(new ColorUIResource(0 , 0 , 90));
                    label4.setForeground(new ColorUIResource(0 , 0 , 90));
                    label5.setForeground(new ColorUIResource(0 , 0 , 90));
                    label6.setForeground(new ColorUIResource(0 , 0 , 90));
                    label7.setForeground(new ColorUIResource(90 , 0 , 0));
                    label8.setForeground(new ColorUIResource(90 , 0 , 0));
                    label9.setForeground(new ColorUIResource(90 , 0 , 0));
                    label10.setForeground(new ColorUIResource(90 , 0 , 0));
                    label11.setForeground(new ColorUIResource(90 , 0 , 0));
                    label12.setForeground(new ColorUIResource(90 , 0 , 0));
                    label13.setForeground(new ColorUIResource(211 , 211 , 211));
                    label14.setForeground(new ColorUIResource(211 , 211 , 211));
                    label15.setForeground(new ColorUIResource(211 , 211 , 211));
                    label16.setForeground(new ColorUIResource(211 , 211 , 211));
                    label17.setForeground(new ColorUIResource(211 , 211 , 211));
                    label18.setForeground(new ColorUIResource(211 , 211 , 211));
                    label19.setForeground(new ColorUIResource(211 , 211 , 211));
                    label20.setForeground(new ColorUIResource(211 , 211 , 211));
                    //
                    label.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label.setBounds(150,135,200,100);
                    label2.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label2.setBounds(150,175,250,100);
                    label3.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label3.setBounds(150,215,200,100);
                    label4.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label4.setBounds(150,258,200,100);
                    label5.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label5.setBounds(150,300,200,100);
                    label6.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label6.setBounds(150,340,200,100);

                    label7.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label7.setBounds(590,135,250,100);
                    label8.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label8.setBounds(590,175,250,100);
                    label9.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label9.setBounds(590,215,200,100);
                    label10.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label10.setBounds(590,258,250,100);
                    label11.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label11.setBounds(590,300,250,100);
                    label12.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label12.setBounds(590,340,250,100);

                    label13.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label13.setBounds(350,135,200,100);
                    label14.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label14.setBounds(350,175,200,100);
                    label15.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label15.setBounds(350,215,200,100);
                    label16.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label16.setBounds(350,258,200,100);
                    label17.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label17.setBounds(350,300,200,100);
                    label18.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label18.setBounds(350,340,200,100);

                    label19.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label19.setBounds(260,50,200,100);
                    label20.setFont(new Font("GearedSlab-Bold.ttf",Font.BOLD,33));
                    label20.setBounds(670,50,200,100);
                    //add label and image
                    frame2.add(label);
                    frame2.add(label2);
                    frame2.add(label3);
                    frame2.add(label4);
                    frame2.add(label5);
                    frame2.add(label6);
                    frame2.add(label7);
                    frame2.add(label8);
                    frame2.add(label9);
                    frame2.add(label10);
                    frame2.add(label11);
                    frame2.add(label12);
                    frame2.add(label13);
                    frame2.add(label14);
                    frame2.add(label15);
                    frame2.add(label16);
                    frame2.add(label17);
                    frame2.add(label18);
                    frame2.add(label19);
                    frame2.add(label20);
                    frame2.add(im);
                    Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                    frame2.setIconImage(icon);
                    frame2.setResizable(false);
                    frame2.setTitle("ScoreBoard");
                    frame2.setSize(800, 470);
                    frame2.setVisible(true);
                }
            });
            //time button
            time.setIcon(im1);
            time.setBackground(Color.darkGray);
            time.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            time.setBounds(850, 160, 110, 118);

            time.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                    Chronometer chronometer = new Chronometer();
                    chronometer.setIconImage(icon);
                    chronometer.setVisible(true);
                }
            });
            //quest button
            quest.setIcon(im2);
            quest.setBackground(Color.darkGray);
            quest.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            quest.setBounds(850, 300, 110, 118);
            while (nn < eight) {
                int rand =  (int) Math.floor(Math.random() * (7 - 0 + 1) + 0);
                boolean s = false;

                for (int j = 0 ; j < nn ; j++){
                    if (rand == rands_quest[j]){
                        s = true;
                        break;
                    }
                }
                if (s == false){
                    rands_quest[nn] = rand;
                    nn++;
                }
            }
            quest.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    castle = 1;
                    JFrame frame4 = new JFrame("Quest");
                    JLabel label111 = new JLabel();
                    Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                    frame4.setIconImage(icon);
                    if (change_quest){
                        ind++;
                        change_quest = false;
                        while (rands_quest[ind] == -1 && FoundQuest != 8){
                            ind++;
                            if(ind == 8){
                                ind = 0;
                            }
                        }
                    }
                    if (rands_quest[ind] == 0){
                        label111.setText("    Diamond Ring     ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 1){
                        label111.setText("    Jeweled Sword    ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 2){
                        label111.setText("   Golden Glass   ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 3){
                        label111.setText("     Glass Goblet   ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,40));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 4){
                        label111.setText("     Wooden Bow     ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 5){
                        label111.setText("     Steel Shield   ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 6){
                        label111.setText("     Golden Key    ");
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (rands_quest[ind]  == 7){
                        label111.setText("    Dragon Scroll   " );
                        label111.setForeground(new ColorUIResource(0x08A16E));
                        label111.setFont(new Font("GearedSlab-Bold.ttf",Font.PLAIN,35));
                        UIManager um=new UIManager();
                        um.put("OptionPane.background", darkGray);
                        um.put("Panel.background", black);
                        JOptionPane.showMessageDialog(frame4,label111,"Quest",JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            });
            //dice icon
            dice.setIcon(dice2);
            dice.setBounds(880,580,46,47);
            dice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //audio
                    playMusic("488502-organic-material-ui-dice-wooden-table-roll-rolling-long-02.wav");
                    //click
                    int diceNum;
                    JLabel l1 = new JLabel();
                    JTextArea alef = new JTextArea();
                    JFrame dicee = new JFrame("Random Dice");
                    dicee.setSize(270,100);
                    dicee.setLocation(740,700);
                    Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                    dicee.setIconImage(icon);
                    alef.setBounds(880,520,50,50);
                    l1.setBounds(80,15,200,30);
                    l1.setLocation(80,15);
                    alef.setBackground(Color.black);
                    int min = 1;
                    int max = 6;
                    diceNum = (int)(Math.random()*(max - min + 1) + min);
                    number = diceNum;
                    l1.setText("num is:" + diceNum);
                    Font f1 = new Font("GearedSlab-Bold.ttf",Font.BOLD,30);
                    l1.setForeground(new ColorUIResource(0x08A16E));
                    l1.setFont(f1);
                    dicee.add(l1);
                    dicee.add(alef);
                    dicee.setVisible(true);
                }
            });
            //game overview
            JButton go = new JButton();
            go.setBounds(850, 440, 110, 118);
            go.setBackground(Color.darkGray);
            go.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            go.setIcon(im4);
            go.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame game = new JFrame("Game Overview");
                    JLabel diceNumber = new JLabel("<html>The remainder of the dice: " + number + " " +
                            "<br/> Found Quest is: " + FoundQuest +
                            "<br/>" + "Turn: " + turnn);
                    diceNumber.setFont(f1);
                    diceNumber.setForeground(new Color(190, 4, 74));
                    if(sw){
                        turnn = "Player 1";
                    }
                    else{
                        turnn = "Player 2";
                    }
                    Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                    game.setIconImage(icon);
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(game,diceNumber,"Game Overview",JOptionPane.INFORMATION_MESSAGE);

                }
            });

            //add button
            frame.add(scoreboard);
            frame.add(dice);
            frame.add(time);
            frame.add(quest);
            frame.add(button);
            frame.add(go);
        }

        JPanel lab22 = new JPanel();
        JLabel lab3 = new JLabel();
        JFrame frame2 = new JFrame("Turn screen");
        JFrame frame22 = new JFrame("Turn screen");
        JFrame frame3 = new JFrame("Fight overview");
        JFrame f =new JFrame("Item");
        Font f1 = new Font("GearedSlab-Bold",Font.BOLD,30);
        JLabel  l = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();



        public void action(){
            JFrame fra = new JFrame();
            map_falg[mouse_cor[0]][mouse_cor[1]] = 1;
//            System.out.print("|" + mouse_cor[0] + " ");
//            System.out.print(mouse_cor[1] + " ");
//            System.out.print(gameMap.game_map[mouse_cor[0]][mouse_cor[1]]);
            number--;

            if (sw) {
                xm1 = mouse_cor[0];
                ym1 = mouse_cor[1];
            }
            if (sw == false) {
                xm2 = mouse_cor[0];
                ym2 = mouse_cor[1];
            }
            if (xm1 == xm2 && ym1 == ym2) {
                fight = true;
                //fight
                if(player1.ran > 0 || player2.ran > 0){
                    JOptionPane pp = new JOptionPane();

                    if(player1.ran > 0){
                        int a = pp.showConfirmDialog(fra,"DO you want to use your ran player1?","Ran item",JOptionPane.WARNING_MESSAGE);
                        if( a == JOptionPane.YES_OPTION){
                            player1.ran -= 1;
                            fight = false;
                        }
                        else if(a == JOptionPane.CANCEL_OPTION){
                            if(player2.ran > 0) {
                                int b = pp.showConfirmDialog(fra, "DO you want to use your ran player2?", "Ran item", JOptionPane.WARNING_MESSAGE);
                                if (b == JOptionPane.YES_OPTION) {
                                    player2.ran -= 1;
                                    fight = false;
                                } else {
                                    fight = true;
                                }
                            }
                            else{
                                fight = true;
                            }
                        }
                    }
                    else if(player2.ran > 0){
                        int a = pp.showConfirmDialog(fra,"DO you want to use your ran player2?","Ran item",JOptionPane.WARNING_MESSAGE);
                        if( a == JOptionPane.YES_OPTION){
                            player2.ran -= 1;
                            fight = false;
                        }
                        else if(a == JOptionPane.CANCEL_OPTION){
                            if(player1.ran > 0) {
                                int b = pp.showConfirmDialog(fra, "DO you want to use your ran player1?", "Ran item", JOptionPane.WARNING_MESSAGE);
                                if (b == JOptionPane.YES_OPTION) {
                                    player1.ran -= 1;
                                    fight = false;
                                } else {
                                    fight = true;
                                }
                            }
                            else {
                                fight = true;
                            }
                        }
                    }
                }
                if (fight) {
                    playMusic("fight.wav");
                    if (player1.power > player2.power) {
                        player1.wallet += ((player1.power - player2.power) * player2.wallet / (player1.power + player2.power));
                        player1.power = player1.power - player2.power;
                        if(player2.shield > 0){
                            int a = JOptionPane.showConfirmDialog(fra,"Do you want to use your shield player2?","Shield item",JOptionPane.WARNING_MESSAGE);
                            if(a == JOptionPane.YES_OPTION){
                                player2.shield -=1;
                                player2.power = player1.power / 2;
                            }
                            else{
                                player2.power = 0;
                            }
                        }
                        else {
                            player2.power = 0;
                        }
                        if(player2.money > 0){
                            int a = JOptionPane.showConfirmDialog(fra,"Do you want to use your moneyitem player2?","Shield item",JOptionPane.WARNING_MESSAGE);
                            if(a == JOptionPane.YES_OPTION){
                                player2.money -=1;
                                player2.wallet -= 5;
                            }
                            else{
                                player2.wallet -= 10;
                            }
                        }
                        else {
                            player2.wallet -= 10;
                        }
                        Font f1 = new Font("GearedSlab-Bold.ttf", Font.BOLD, 30);
                        lab3.setText("player1 win the fight");
                        lab3.setFont(f1);
                        lab22.setBackground(new ColorUIResource(0x070707));
                        lab22.setBounds(200, 300, 800, 100);
                        lab3.setForeground(new ColorUIResource(0x08A16E));
                        lab22.add(lab3);
                        frame3.setBounds(200, 300, 400, 100);
                        frame3.add(lab22);
                        frame3.setVisible(true);
                    } else if (player1.power < player2.power) {
                        player2.wallet += ((player2.power - player1.power) * player1.wallet / (player1.power + player2.power));
                        player2.power = player2.power - player1.power;
                        if(player1.shield > 0){
                            int a = JOptionPane.showConfirmDialog(fra,"Do you want to use your shield player1?","Shield item",JOptionPane.WARNING_MESSAGE);
                            if(a == JOptionPane.YES_OPTION){
                                player1.shield -=1;
                                player1.power = player2.power / 2;
                            }
                            else{
                                player1.power = 0;
                            }
                        }
                        else {
                            player1.power = 0;
                        }
                        if(player1.money > 0){
                            int a = JOptionPane.showConfirmDialog(fra,"Do you want to use your moneyItem player1?","Shield item",JOptionPane.WARNING_MESSAGE);
                            if(a == JOptionPane.YES_OPTION){
                                player1.money -=1;
                                player1.wallet -= 5;
                            }
                            else{
                                player1.wallet -= 10;
                            }
                        }
                        else {
                            player1.wallet -= 10;
                        }
                        Font f1 = new Font("GearedSlab-Bold.ttf", Font.BOLD, 30);
                        lab3.setText("player2 win the fight");
                        lab3.setFont(f1);
                        lab22.setBackground(new ColorUIResource(0x070707));
                        lab22.setBounds(200, 290, 800, 100);
                        lab3.setForeground(new ColorUIResource(0x08A16E));
                        lab22.add(lab3);
                        frame3.setBounds(200, 290, 400, 100);
                        frame3.add(lab22);
                        frame3.setVisible(true);
                    } else if (player1.power == player2.power) {
                        Font f1 = new Font("GearedSlab-Bold.ttf", Font.BOLD, 30);
                        lab3.setText("fight draw");
                        lab3.setFont(f1);
                        lab22.setBackground(new ColorUIResource(0x070707));
                        lab22.setBounds(200, 290, 800, 100);
                        lab3.setForeground(new ColorUIResource(0x08A16E));
                        lab22.add(lab3);
                        frame3.setBounds(200, 290, 400, 100);
                        frame3.add(lab22);
                        frame3.setVisible(true);

                    }
                    fight = false;
                }
            }
        }

        static int crx,cry,crx2,cry2;

        public void market() {

            //set up
            JLabel l = new JLabel();
            JLabel ll = new JLabel();
            JMenu menu,help,cost,weapon,spell,off,i12,weapon1,spell1,off1;
            JMenuItem i1, i2, i3, i4, i5,i6,i7,i8,i9,i10,i11,i13,i15,i99,i14,i18,i16,i17,i19,i20,i21,i22,i23,i24;
            JFrame ff= new JFrame("Market");
            Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
            ff.setIconImage(icon);
            JMenuBar mb=new JMenuBar();
            JSeparator sep = new JSeparator();
            //menu
            menu=new JMenu("Market");
            help = new JMenu("Help");
            weapon=new JMenu("Weapons");
            weapon1=new JMenu("Weapons");
            spell=new JMenu("Spells");
            spell1=new JMenu("Spells");
            off=new JMenu("Chef's Suggestion");
            off1=new JMenu("Chef's Suggestion");
            i12=new JMenu("Spells");
            cost=new JMenu("Cost");
            //menuItem
            i1=new JMenuItem("Big Brain");
            i19=new JMenuItem("Big Brain");
            i2=new JMenuItem("Show Random Treasure");
            i3=new JMenuItem("Change Random Treasure");
            i23=new JMenuItem("Show Random Treasure");
            i24=new JMenuItem("Change Random Treasure");
            i4=new JMenuItem("Strong Shield");
            i22=new JMenuItem("Strong Shield");
            i5=new JMenuItem("Royal Spear");
            i6=new JMenuItem("King's Knife");
            i7=new JMenuItem("Golden sword");
            i17=new JMenuItem("Royal Spear");
            i16=new JMenuItem("King's Knife");
            i18=new JMenuItem("Golden sword");
            i8=new JMenuItem("Oops I Ran");
            i20=new JMenuItem("Oops I Ran");
            i99=new JMenuItem("Rich Type");
            i21=new JMenuItem("Rich Type");
            //help
            i9=new JMenuItem("Weapon");
            i10=new JMenuItem("Show Random Treasure");
            i11=new JMenuItem("Change Random Treasure");
            i13=new JMenuItem("Big Brain");
            i14=new JMenuItem("Chef's Suggestion");
            i15=new JMenuItem("Oops I Ran");
            //design
            mb.setBorder(BorderFactory.createLineBorder(black));
            mb.setBackground(darkGray);
            cost.setBorder(BorderFactory.createLineBorder(new Color(159, 20, 7)));
            menu.setBorder(BorderFactory.createLineBorder(new Color(20,200,150)));
            spell.setBorder(BorderFactory.createLineBorder(new Color(200,200,0))); spell1.setBorder(BorderFactory.createLineBorder(new Color(200,200,0)));
            weapon.setBorder(BorderFactory.createLineBorder(new Color(200,80,0))); weapon1.setBorder(BorderFactory.createLineBorder(new Color(200,80,0)));
            help.setBorder(BorderFactory.createLineBorder(new Color(200,20,150)));
            weapon.setForeground(new Color(200,80,0)); weapon1.setForeground(new Color(200,80,0));
            menu.setForeground(new Color(20,200,150));
            help.setForeground(new Color(200,20,150));
            spell.setForeground(new Color(180,220,0)); spell1.setForeground(new Color(180,220,0)); off1.setForeground(new Color(180,220,0));
            cost.setForeground(new Color(159, 20, 7));
            i5.setForeground(new Color(200,80,0));i6.setForeground(new Color(200,80,0));i7.setForeground(new Color(200,80,0));
            i16.setForeground(new Color(200,80,0)); i17.setForeground(new Color(200,80,0)); i18.setForeground(new Color(200,80,0));
            i1.setForeground(new Color(180,220,0));off.setForeground(new Color(180,220,0));i8.setForeground(new Color(180,220,0));
            i99.setForeground(new Color(180,220,0));i4.setForeground(new Color(180,220,0));
            i20.setForeground(new Color(180,220,0)); i19.setForeground(new Color(180,220,0)); i21.setForeground(new Color(180,220,0));
            i9.setForeground(new Color(200,80,0)); i12.setForeground(new Color(180,220,0)); i22.setForeground(new Color(180,220,0));
            i13.setForeground(new Color(180,220,0)); i14.setForeground(new Color(180,220,0));
            i15.setForeground(new Color(180,220,0)); i19.setForeground(new Color(180,220,0)); i20.setForeground(new Color(180,220,0));
            //submenu
            weapon.add(i5); weapon.add(sep); weapon.add(i6); weapon.add(sep); weapon.add(i7);
            spell.add(i1); spell.add(sep);spell.add(off); spell.add(sep); spell.add(i8);
            off.add(i4); menu.add(sep); off.add(i99);
            menu.add(weapon);menu.add(sep); menu.add(i2); menu.add(sep);
            menu.add(i3); menu.add(sep); menu.add(spell);
            mb.add(menu); mb.add(cost); mb.add(help);
            help.add(i9); help.add(sep); help.add(i10); help.add(sep); help.add(i11); help.add(i12);
            i12.add(i13); i12.add(sep); i12.add(i14); i12.add(i15);
            //
            cost.add(weapon1); cost.add(sep); cost.add(i23); cost.add(sep); cost.add(i24); cost.add(sep); cost.add(spell1);
            weapon1.add(i17); weapon1.add(sep); weapon1.add(i16); weapon1.add(i18);
            spell1.add(i19); spell1.add(sep);spell1.add(off1); spell1.add(sep); spell1.add(i20);
            off1.add(i22); off1.add(sep); off1.add(i21);
            //help
            i9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>Weapons are so important.<br/>They can increase your power and then you can win the fights and collect money from loser.<br/>" +
                            "<br/>Royal Spear: Royal Spear is about 2000 years ago.<br/> it's good for long distance battle. it's increase 1 power and give you 10$.<br/>" +
                            "<br/>King's Knife: Sir Richard's Knife is one the most valuable things in the world.<br/> it's increase 2 power.<br/>" +
                            "<br/>Golden Sword: Arthur's sword is so famous.<br/> who don't want to get this legendary weapon?? it's increase 3 power.<br/>");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(200,80,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>Show a treasure randomly to play game easier.<br/> it is expensive but worth it.");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(101, 65, 65));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Show Random Treasure",JOptionPane.INFORMATION_MESSAGE);

                }
            });
            i11.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>If you are not pleasure with this treasure,<br/> You can change the treasure quest randomly." +
                            "<br/> may god with you and this quest is that what you want.");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(175, 142, 81));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Change Random Treasure",JOptionPane.INFORMATION_MESSAGE);

                }
            });
            i13.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>Big Brain based on your strategy.<br/> Sometimes you need money more than power.<br/> that was the BIg Brain come in. it's exchange 3 power to 30$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Spell",JOptionPane.INFORMATION_MESSAGE);

                }
            });
            i14.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>Chef's Suggestion is divided into two item.<br/><br/> Strong Shield: When you lose the fight, your power set to 0, so if you are get this item,<br/>" +
                            "your power is set to half of your enemy's power.<br/><br/>Rich Type: Rich guys never lose. if you have this item, decreasing of your money is 50% off.");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Spell",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i15.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("<html>Oops i can to take away from the fight.<br/> Sooo yes, I Ran!!!");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Spell",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            //market
            //weapon
            i5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet >= 15) {
                            player1.power += 1;
                            player1.wallet -= 5;
                            l.setText("player1 lost 5$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 15) {
                            player2.power += 1;
                            player2.wallet -= 5;
                            l.setText("player2 lost 5$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            i6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet >= 20) {
                            player1.power += 2;
                            player1.wallet -= 20;
                            l.setText("player1 lost 20$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 20) {
                            player2.power += 2;
                            player2.wallet -= 20;
                            l.setText("player2 lost 20$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            i7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet >= 25) {
                            player1.power += 3;
                            player1.wallet -= 25;
                            l.setText("player1 lost 25$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 25) {
                            player2.power += 3;
                            player2.wallet -= 25;
                            l.setText("player2 lost 25$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Weapon", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            //spell
            i1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.power >= 3) {
                            player1.power -= 3;
                            player1.wallet += 30;
                            l.setText("player1 lost 3 power");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.power >= 3) {
                            player2.power -= 3;
                            player2.wallet += 30;
                            l.setText("player2 lost 3 power");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            i4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet >= 10) {
                            player1.shield += 1;
                            player1.wallet -= 10;
                            l.setText("player1 lost 10$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if (player2.wallet >= 10) {
                            player2.shield += 1;
                            player2.wallet -= 10;
                            l.setText("player2 lost 10$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            i99.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)) {
                        if (player1.wallet >= 10) {
                            player1.money += 1;
                            player1.wallet -= 10;
                            l.setText("player1 lost 10$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 10) {
                            player2.money += 1;
                            player2.wallet -= 10;
                            l.setText("player2 lost 10$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            i8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)) {
                        if (player1.wallet >= 15) {
                            player1.ran += 1;
                            player1.wallet -= 15;
                            l.setText("player1 lost 15$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 15) {
                            player2.ran += 1;
                            player2.wallet -= 15;
                            l.setText("player2 lost 15$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Spell", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            //show
            i2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet  >= 90) {
                            player1.wallet -= 90;

                            int randx = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            int randy = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            while(gameMap.game_map[randx][randy] != 10 && gameMap.game_map[randx][randy] != 11 && gameMap.game_map[randx][randy] != 12 &&
                                    gameMap.game_map[randx][randy] != 13 && gameMap.game_map[randx][randy] != 14 && gameMap.game_map[randx][randy] != 15 &&
                                    gameMap.game_map[randx][randy] != 16 && gameMap.game_map[randx][randy] != 17){
                                randx = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                                randy = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            }
                            System.out.print("|" + gameMap.game_map[randx][randy]);

                            l.setText("player1 lost 90$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Show Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Show Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 90) {
                            player2.wallet -= 90;

                            int randx = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            int randy = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            while(gameMap.game_map[randx][randy] != 10 && gameMap.game_map[randx][randy] != 11 && gameMap.game_map[randx][randy] != 12 &&
                                    gameMap.game_map[randx][randy] != 13 && gameMap.game_map[randx][randy] != 14 && gameMap.game_map[randx][randy] != 15 &&
                                    gameMap.game_map[randx][randy] != 16 && gameMap.game_map[randx][randy] != 17){
                                randx = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                                randy = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
                            }
                            System.out.print("|" + gameMap.game_map[randx][randy]);

                            l.setText("player2 lost 90$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Show Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Show Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }); //show monde
            //change
            i3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((sw == true && number > 0) || (sw ==false && number == 0)){
                        if(player1.wallet >= 50) {
                            player1.wallet -= 50;
                            ind++;
                            while (rands_quest[ind] == -1 && FoundQuest != 8){
                                ind++;
                                if(ind == 8){
                                    ind = 0;
                                }
                            }
                            l.setText("player1 lost 50$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Change Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player1 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Change Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if((sw == false && number > 0) || (sw == true && number == 0)){
                        if(player2.wallet >= 50) {
                            player2.wallet -= 50;
                            ind++;
                            while (rands_quest[ind] == -1 && FoundQuest != 8){
                                ind++;
                                if(ind == 8){
                                    ind = 0;
                                }
                            }
                            l.setText("player2 lost 50$");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Change Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            l.setText("player2 cant buy this Item");
                            Font f2 = new Font("GearedSlab-Bold", Font.BOLD, 14);
                            l.setFont(f2);
                            l.setForeground(new Color(200, 0, 0));
                            UIManager um = new UIManager();
                            um.put("OptionPane.background", darkGray);
                            um.put("Panel.background", black);
                            JOptionPane.showMessageDialog(ff, l, "Change Random Treasure", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            //cost
            //weapon
            i16.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 20$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(200,80,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i17.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 15$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(200,80,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i18.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 25$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(200,80,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            // show
            i23.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 90$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(101, 65, 65));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            //chane
            i24.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 50$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(175, 142, 81));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            //spell
            i19.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 3 power");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i20.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 15$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i21.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 10$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            i22.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    l.setText("The cost of this is: 10$");
                    Font f2 = new Font("GearedSlab-Bold",Font.BOLD,14);
                    l.setFont(f2);
                    l.setForeground(new Color(180,220,0));
                    UIManager um=new UIManager();
                    um.put("OptionPane.background", darkGray);
                    um.put("Panel.background", black);
                    JOptionPane.showMessageDialog(ff,l,"Weapon",JOptionPane.INFORMATION_MESSAGE);
                }
            });
            //end
            ff.setJMenuBar(mb);ff.setSize(600,470);
            ff.add(sep);
            MarketBack m = new MarketBack();
            ff.add(m);
            ff.setResizable(false);
            ff.setVisible(true);
        }

        static int xm1 = -1; //player one's last move coordinate
        static int ym1 = -1;
        static int xm2 = -2; //player two's last move coordinate
        static int ym2 = -2;
        static int xx = 0 ; // variable to check correct click around cells pl 1
        static int yy = 8;
        static int xx2 = 0 ; // variable to check correct click around cells pl 2
        static int yy2 = 8;

        static boolean fight = false;


        @Override
        public void mouseClicked(MouseEvent e) {
            // show click
            Graphics ga = e.getComponent().getGraphics();
            //setup for click
            mouse_x = e.getX();
            mouse_y = e.getY();
            mouse_cor[0] = mouse_x / 70;
            mouse_cor[1] = (mouse_y / 70) - 1;
            if (mouse_cor[1] == -1) {
                mouse_cor[1] = 9;
                mouse_cor[0] -= 1;
                if (mouse_cor[0] == -1) {
                    mouse_cor[0] = 9;
                }
            }
            int cc = 0;
            int copy = -1;
            if(turn){
                JPanel lab = new JPanel();
                JLabel lab2 = new JLabel();
                Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
                frame2.setIconImage(icon);
                frame3.setIconImage(icon);
                if(number > 0 && map_falg[mouse_cor[0]][mouse_cor[1]] == 0 && gameMap.game_map[mouse_cor[0]][mouse_cor[1]] != 4) {
                    if(sw) {

                        for (int x = 0 ; x < 10 ; x++) {
                            for (int y = 0; y < 10; y++) {
                                if (map_p2[x][y] == -1 || map_p2[x][y] == -2 || map_p2[x][y] == -3){
                                    ga.setColor(gray);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                                if (map_p1[x][y] == -1){
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(new Color(0 , 220 , 100));
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                } else if (map_p1[x][y] == -2) {
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(new Color(0 , 140 , 225));
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                } else if (map_p1[x][y] == -3) {
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(red);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                                else if (map_p2[x][y] == -2){
                                    ga.setColor(gray);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                            }
                        }

                        ga.setColor(white);
                        ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                        if (xx2 == xx && yy2 == yy && (xx != 0 && yy != 8)) {
                            ga.setColor(white);
                            ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                            ga.setColor(new Color(120 , 0 , 0));
                            ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                            setLayout(null);
                        }
                        if ((mouse_cor[0] == xx && mouse_cor[1] == yy - 1) || (mouse_cor[1] == yy && mouse_cor[0] == xx + 1) ||
                                (mouse_cor[0] == xx && mouse_cor[1] == yy + 1) ||
                                (mouse_cor[1] == yy && mouse_cor[0] == xx - 1) || (mouse_cor[0] == 0 && mouse_cor[1] == 8)) {
                            xx = mouse_cor[0];
                            yy = mouse_cor[1];
                            copy = number;
                            action();
                            //rules
                            //treasure
                            if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 10){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Diamond Ring     ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 11){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Jeweled Sword    ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 12){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("  Golden Glass   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;

                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 13){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Glass Goblet   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 14){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Wooden Bow     ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 15){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Steel Shield   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;

                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 16){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Golden Key    ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 17){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("  Dragon Scroll   " );
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            //loot
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 18){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 140 , 225));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("positive event ding-dong.wav");
                                l2.setText("you eran 20$");
                                l2.setForeground(new Color(0 , 140 , 225));
                                l2.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l2,"Loot",JOptionPane.INFORMATION_MESSAGE);
                                player1.wallet += 20;
                                gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] = -1;
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -2;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 19){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 140 , 225));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("positive event ding-dong.wav");
                                l2.setText("you eran 15$");
                                l2.setForeground(new Color(0 , 140 , 225));
                                l2.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l2,"Loot",JOptionPane.INFORMATION_MESSAGE);
                                player1.wallet += 15;
                                gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] = -1;
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -2;
                            }
                            //trap
                            else if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 20){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(Color.red);
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("dsadsad.wav");
                                l3.setText("you lost 10$");
                                l3.setForeground(red);
                                l3.setFont(f1);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -3;

                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l3,"Trap",JOptionPane.INFORMATION_MESSAGE);
                                player1.wallet -= 10;
                            }
                            else if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 21){
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(Color.red);
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                playMusic("dsadsad.wav");
                                l3.setText("you lost 5$");
                                l3.setForeground(red);
                                l3.setFont(f1);
                                map_p1[mouse_cor[0]][mouse_cor[1]] = -3;

                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l3,"Trap",JOptionPane.INFORMATION_MESSAGE);
                                player1.wallet -= 5;
                            }
                            //market
                            else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] == 5){
                                playMusic("asd.wav");
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(new Color(250 , 140 , 20));
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                //
                                market();
                            }
                            //castle
                            else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] == 6) {
                                ga.setColor(white);
                                ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                                ga.setColor(yellow);
                                ga.fillRect(xx * 70, (yy + 1) * 70, 70, 70);
                                l.setText("Choose your idea Treasure");
                                l.setForeground(yellow);
                                l.setFont(f1);
                                UIManager um = new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                if (castle == 1) {
                                    gameMap.addMouseListener(new MouseListener() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            crx = e.getX() / 70;
                                            cry = (e.getY() / 70) - 1;
                                            if (cry == -1) {
                                                cry = 9;
                                                crx -= 1;
                                                if (crx == -1) {
                                                    crx = 9;
                                                }
                                            }


                                            if ((sw == false && number == 0 && castle == 1) || (sw == true && castle == 1)) {
                                                if (gameMap.game_map[crx][cry] == 10) {
//                                                    System.out.print("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 0) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 25;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 25$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;


                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 11) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 1) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 30;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 30$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 12) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 2) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 35;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 35$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;
                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 13) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 3) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 40;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 40$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 14) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 4) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 45;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 45$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 15) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 5) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 50;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 50$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;
                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 16) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 6) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 55;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 55$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 17) {
//                                                    System.out.print("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 7) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player1.point += 1;
                                                        player1.wallet += 60;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player1 got 1 point and 60$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;
                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;
                                                    }
                                                } else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] != 6) {
                                                    l.setText("<html>Sorry!<br/> Wrong answer.");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    castle = 0;


                                                }
                                            }

                                            //end game's condition
                                            if (FoundQuest == 8) {
                                                if (player1.point > player2.point) {
                                                    l.setText("<html>Congrats to PLAYER 1!!<br/> YOU win The game :))");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    playMusic("positive event strong-chord.wav");
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                } else if (player2.point > player1.point) {
                                                    l.setText("<html>Congrats to PLAYER 2!!<br/> YOU win The game :))");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    playMusic("positive event strong-chord.wav");
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                } else {
                                                    l.setText("<html>The Match is Draw!!<br/> Have fun :)");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                }
                                            }
                                        }


                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                        }

                                        @Override
                                        public void mouseReleased(MouseEvent e) {
                                        }

                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                        }

                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                        }
                                    });
                                }
                                else {
                                    l.setText("You cant  click");
                                    l.setForeground(new Color(0, 220, 100));
                                    l.setFont(f1);
                                    UIManager un = new UIManager();
                                    un.put("OptionPane.background", darkGray);
                                    un.put("Panel.background", black);
                                    JOptionPane.showMessageDialog(f, l, "Treasure", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            //show click
                            ga.setColor(new Color(0, 0, 150));
                            ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                            setLayout(null);

                        }
                        else{
                            ga.setColor(new Color(0 , 0 , 150));
                            ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                            setLayout(null);
                        }
                    }
                    else if(sw == false) {

                        for (int x = 0 ; x < 10 ; x++){
                            for (int y = 0 ; y < 10 ; y++) {
                                if (map_p1[x][y] == -1 || map_p1[x][y] == -2  || map_p1[x][y] == -3 ){
                                    ga.setColor(gray);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                                if (map_p2[x][y] == -1){
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(new Color(0 , 220 , 100));
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                } else if (map_p2[x][y] == -2) {
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(new Color(0 , 140 , 225));
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                } else if (map_p2[x][y] == -3) {
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(red);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                                else if (map_p1[x][y] == -2){
                                    ga.setColor(gray);
                                    ga.fillRect(x * 70, (y + 1) * 70, 70, 70);
                                    ga.setColor(white);
                                    ga.drawRect(x * 70, (y + 1) * 70, 70, 70);
                                }
                            }
                        }

                        if(xx2 == 0 && yy2 == 8){
                            ga.setColor(white);
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                            ga.setColor(new Color(0 , 0 , 150));
                            ga.drawRect(xx * 70, (yy + 1) * 70, 70, 70);
                            setLayout(null);
                        }
                        else {
                            ga.setColor(white);
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                        }
                        if (xx2 == xx && yy2 == yy && (xx != 0 && yy != 8)) {
                            ga.setColor(white);
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                            ga.setColor(new Color(0 , 0 , 150));
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                            setLayout(null);
                        }
                        if((mouse_cor[0] == xx2 && mouse_cor[1] == yy2 - 1) || (mouse_cor[1] == yy2 && mouse_cor[0] == xx2 + 1) ||
                                (mouse_cor[0] == xx2 && mouse_cor[1] == yy2 + 1) ||
                                (mouse_cor[1] == yy2 && mouse_cor[0] == xx2 - 1) || (mouse_cor[0] == 0 && mouse_cor[1] == 8)){
                            xx2 = mouse_cor[0];
                            yy2 = mouse_cor[1];
                            copy = number;
                            action();
                            //rules
                            //treasure
                            if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 10 ){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Diamond Ring     ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 11){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Jeweled Sword    ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 12){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("  Golden Glass   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 13){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Glass Goblet   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 14){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Wooden Bow     ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 15){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Steel Shield   ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 16){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("   Golden Key    ");
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            else  if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 17){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 220 , 100));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("ESE - QUANTUM - HIT - JC - Horror_.wav");
                                l.setText("  Dragon Scroll   " );
                                l.setForeground(new Color(0 , 220 , 100));
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Treasure",JOptionPane.INFORMATION_MESSAGE);
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -1;
                            }
                            //loot
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 18){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 140 , 225));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("positive event ding-dong.wav");
                                l2.setText("you eran 20$");
                                l2.setForeground(new Color(0 , 140 , 225));
                                l2.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l2,"Loot",JOptionPane.INFORMATION_MESSAGE);
                                player2.wallet += 20;

                                gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] = -1;
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -2;
                            }
                            else  if(gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] == 19){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(0 , 140 , 225));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("positive event ding-dong.wav");
                                l2.setText("you eran 15$");
                                l2.setForeground(new Color(0 , 140 , 225));
                                l2.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l2,"Loot",JOptionPane.INFORMATION_MESSAGE);
                                player2.wallet += 15;

                                gameMap.game_map2[mouse_cor[0]][mouse_cor[1]] = -1;
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -2;
                            }
                            //trap
                            else if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 20){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(Color.red);
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("dsadsad.wav");
                                l3.setText("you lost 10$");
                                l3.setForeground(red);
                                l3.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l3,"Trap",JOptionPane.INFORMATION_MESSAGE);
                                player2.wallet -= 10;
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -3;
                            }
                            else if(gameMap.game_map3[mouse_cor[0]][mouse_cor[1]] == 21){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(Color.red);
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                playMusic("dsadsad.wav");
                                l3.setText("you lost 5$");
                                l3.setForeground(red);
                                l3.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l3,"Trap",JOptionPane.INFORMATION_MESSAGE);
                                player2.wallet -= 5;
                                map_p2[mouse_cor[0]][mouse_cor[1]] = -3;
                            }
                            //market
                            else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] == 5){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(new Color(250 , 140 , 20));
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                //
                                market();
                                playMusic("asd.wav");
                            }
                            //castle
                            else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] == 6){
                                ga.setColor(white);
                                ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                ga.setColor(yellow);
                                ga.fillRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                                l.setText("Choose your idea Treasure");
                                l.setForeground(yellow);
                                l.setFont(f1);
                                UIManager um=new UIManager();
                                um.put("OptionPane.background", darkGray);
                                um.put("Panel.background", black);
                                JOptionPane.showMessageDialog(f,l,"Castle",JOptionPane.INFORMATION_MESSAGE);
                                if (castle == 1) {
                                    gameMap.addMouseListener(new MouseListener() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            crx = e.getX() / 70;
                                            cry = (e.getY() / 70) - 1;
                                            if (cry == -1) {
                                                cry = 9;
                                                crx -= 1;
                                                if (crx == -1) {
                                                    crx = 9;
                                                }
                                            }
                                            if ((sw == true && number == 0 && castle == 1) || sw == false && castle == 1) {
                                                if (gameMap.game_map[crx][cry] == 10) {
//                                                    System.out.print("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 0) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 25;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 25$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;


                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 11) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 1) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 30;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 30$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 12) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 2) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 35;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 35$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 13) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 3) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 40;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 40$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 14) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 4) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 45;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 45$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 15) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 5) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 50;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 50$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 16) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 6) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 55;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 55$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if (gameMap.game_map[crx][cry] == 17) {
//                                                    System.out.println("|" + gameMap.game_map[crx][cry] + " ");
                                                    if (rands_quest[ind] == 7) {
                                                        change_quest = true;
                                                        rands_quest[ind] = -1;
                                                        player2.point += 1;
                                                        player2.wallet += 60;
                                                        FoundQuest += 1;
                                                        gameMap.game_map[crx][cry] = -1;
                                                        l.setText("<html> Congrats!!<br/>player2 got 1 point and 60$");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    } else {
                                                        l.setText("<html>Sorry!<br/> Wrong answer.");
                                                        l.setForeground(yellow);
                                                        l.setFont(f1);
                                                        UIManager um = new UIManager();
                                                        um.put("OptionPane.background", new Color(0, 220, 100));
                                                        um.put("Panel.background", black);
                                                        JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                        castle = 0;

                                                    }
                                                } else if(gameMap.game_map[mouse_cor[0]][mouse_cor[1]] != 6) {
                                                    l.setText("<html>Sorry!<br/> Wrong answer.");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    JOptionPane.showMessageDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    castle = 0;

                                                }
                                            }


                                            //end game's condition
                                            if (FoundQuest == 8) {
                                                if (player1.point > player2.point) {
                                                    l.setText("<html>Congrats to PLAYER 1!!<br/> YOU win The game :))");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    playMusic("positive event strong-chord.wav");
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                } else if (player2.point > player1.point) {
                                                    l.setText("<html>Congrats to PLAYER 2!!<br/> YOU win The game :))");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    playMusic("positive event strong-chord.wav");
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                } else {
                                                    l.setText("<html>The Match is Draw!!<br/> Have fun :)");
                                                    l.setForeground(yellow);
                                                    l.setFont(f1);
                                                    UIManager um = new UIManager();
                                                    um.put("OptionPane.background", new Color(0, 220, 100));
                                                    um.put("Panel.background", black);
                                                    int end = JOptionPane.showConfirmDialog(f, l, "Castle", JOptionPane.INFORMATION_MESSAGE);
                                                    if (end == JOptionPane.YES_OPTION) {
                                                        frame.hide();
                                                    } else {
                                                        frame.hide();
                                                    }
                                                }


                                            }

                                        }

                                        @Override
                                        public void mousePressed(MouseEvent e) {

                                        }

                                        @Override
                                        public void mouseReleased(MouseEvent e) {
                                        }

                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                        }

                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                        }
                                    });


                                }
                                else{
                                    l.setText("You cant  click");
                                    l.setForeground(new Color(0, 220, 100));
                                    l.setFont(f1);
                                    UIManager un = new UIManager();
                                    un.put("OptionPane.background", darkGray);
                                    un.put("Panel.background", black);
                                    JOptionPane.showMessageDialog(f, l, "Treasure", JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                            //show click
                            if(yy2 == 9){
                                yy2 = 0;
                                xx2 += 1;
                                if(xx2 == 9){
                                    xx2 = 0;
                                }
                            }
                            ga.setColor(new Color(150, 0, 0));
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                            setLayout(null);

                        }
                        else{
                            ga.setColor(new Color(150 , 0 , 0));
                            ga.drawRect(xx2 * 70, (yy2 + 1) * 70, 70, 70);
                            setLayout(null);
                        }
                    }
                }

                cc++;
                if(cc == copy && sw == true){
                    Font f1 = new Font("GearedSlab-Bold.ttf",Font.BOLD,30);
                    lab2.setText(" Now player two's turn");
                    lab2.setFont(f1);
                    lab.setBackground(new ColorUIResource(0x070707));
                    lab.setBounds(200,200,800,100);
                    lab2.setForeground(new Color(120 , 0 , 0));
                    lab.add(lab2);
                    frame2.setBounds(200,200,400,100);
                    frame2.add(lab);
                    frame2.setVisible(true);
                    sw = false;
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            map_falg[i][j] = 0;
                        }
                    }
                }
                else if(cc == copy && sw == false){
                    Font f1 = new Font("GearedSlab-Bold.ttf",Font.BOLD,30);
                    lab2.setText(" Now player one's turn");
                    lab2.setFont(f1);
                    lab.setBackground(new ColorUIResource(0x070707));
                    lab.setBounds(200,200,800,100);
                    lab2.setForeground(new Color(0 , 50 , 240));
                    lab.add(lab2);
                    frame22.setBounds(200,200,400,100);
                    frame22.add(lab);
                    frame22.setVisible(true);
                    sw = true;
                    for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                            map_falg[i][j] = 0;
                        }
                    }
                }
                dice.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame2.hide();
                        frame22.hide();
                        frame3.hide();
                    }
                });
            }
        }
        @Override
        public void mousePressed (MouseEvent e){
        }
        @Override
        public void mouseReleased (MouseEvent e){
        }
        @Override
        public void mouseEntered (MouseEvent e){
        }
        @Override
        public void mouseExited (MouseEvent e){
        }

    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();

    }
}

