import sun.applet.Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Time;


public class Chronometer extends JFrame implements ActionListener{
    private int seconds, minutes, hours;
    private Timer chronometer;
    private JButton runStop, restart;
    private JLabel time;

    public Chronometer(){
        this.setTitle("Chronometer");
        this.setSize(250, 80);
        this.setLocation(0,0);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        imagee5 im = new imagee5();
        this.add(im);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        runStop = new JButton("run");
        runStop.addActionListener(this);

        restart = new JButton("restart");
        restart.addActionListener(this);

        this.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("Screenshot_20230330_002532.png");
        this.setIconImage(icon);

        new Chronometer();




        seconds = 0;
        minutes = 0;
        hours = 0;

        time = new JLabel(hours+" : "+minutes+" : "+seconds);

        this.add(runStop);
        this.add(restart);
        this.add(time);

        chronometer = new Timer(1000, this);

    }
    public static void main(String[] args){
        Chronometer chronometer = new Chronometer();
        chronometer.setResizable(false);
        chronometer.setVisible(true);
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
        }else if(e.getSource() instanceof JButton){
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

