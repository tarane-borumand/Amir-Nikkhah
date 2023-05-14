import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;

public class GameMap extends JPanel {
    boolean draw_map = true;
    boolean hide_treasure = true;
    boolean hide_loot = true;
    boolean hide_wall = false;
    boolean hide_trap = true;
    int mmx;
    int mmy;
    int a, a1, a2, a3, a4 = 0;
    int b, b1, b2, b3, b4 = 0;
    int c, c1, c2, c3, c4 = 0;
    int d, d1, d2, d3, d4 = 0;
    int e, e1, e2, e3, e4 = 0;
    int f, f1, f2, f3, f4 = 0;
    int h, h1, h2, h3, h4 = 0;
    int i, i1, i2, i3, i4 = 0;
    int[][] game_map = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
    };

    int[][] game_map2 = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
    };

    int[][] game_map3 = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
    };


    int x1, y1;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //map
        if (draw_map) {
            //block 1
            for (int x = 0; x < 5; x++) {
                x1 = (int) Math.floor(Math.random() * (4 - 0 + 1) + 0);
                for (int y = 0; y < 4; y++) {
                    y1 = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0);
                    g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                    g.setColor(white);
                    g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                    g.setColor(gray);
                    if (game_map[x1][y1] == -1) {
                        //loot - blue
                        if (a1 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            a1++;
                        }
                        //wall - black
                        if (a3 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 4;
                            a3++;
                        }
                    }
                }
            }

            //block 2
            for (int x = 0; x < 5; x++) {
                x1 = (int) Math.floor(Math.random() * (4 - 0 + 1) + 0);
                for (int y = 4; y < 6; y++) {
                    y1 = (int) Math.floor(Math.random() * (5 - 4 + 1) + 4);
                    if (game_map[x1][y1] == -1) {
                        //treasure - green
                        if (b <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 1;
                            b++;
                        }
                        //loot - blue
                        if (b1 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            b1++;
                        }
//                        //market - orange
                        if (b4 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 5;
                            b4++;
                        }
                    }
                }
            }
            //block 3
            for (int x = 0; x < 5; x++) {
                x1 = (int) Math.floor(Math.random() * (4 - 0 + 1) + 0);
                for (int y = 6; y < 8; y++) {
                    y1 = (int) Math.floor(Math.random() * (7 - 6 + 1) + 6);
                    if (game_map[x1][y1] == -1) {
                        //treasure - green
                        if (c <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 1;
                            c++;
                        }
                        //loot - blue
                        if (c1 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            c1++;
                        }
                        //trap - red
                        if (c2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            c2++;
                        }
                        //wall - black
                        if (c3 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 4;
                            c3++;
                        }
                        //market - orange
                        if (c4 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 5;
                            c4++;
                        }
                    }
                }
            }
            //block 4
            for (int x = 0; x < 5; x++) {
                x1 = (int) Math.floor(Math.random() * (4 - 0 + 1) + 0);
                for (int y = 8; y < 10; y++) {
                    y1 = (int) Math.floor(Math.random() * (9 - 8 + 1) + 8);
                    if (game_map[x1][y1] == -1) {
                        //loot - blue
                        if (d1 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            d1++;
                        }
                        //trap - red
                        if (d2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            d2++;
                        }
                    }
                }
            }
            //block 5
            for (int x = 5; x < 10; x++) {
                x1 = (int) Math.floor(Math.random() * (9 - 5 + 1) + 5);
                for (int y = 0; y < 4; y++) {
                    y1 = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0);
                    if (game_map[x1][y1] == -1) {
                        //treasure - green
                        if (e <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 1;
                            e++;
                        }
                        //market - orange
                        if (e4 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 5;
                            e4++;
                        }
                        //loot - blue
                        if (e1 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            e1++;
                        }
                        //trap - red
                        if (e2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            e2++;
                        }
                        //wall - black
                        if (e3 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 4;
                            e3++;
                        }
                    }
                }
            }
            //block 6
            for (int x = 5; x < 10; x++) {
                x1 = (int) Math.floor(Math.random() * (9 - 5 + 1) + 5);
                for (int y = 4; y < 6; y++) {
                    y1 = (int) Math.floor(Math.random() * (5 - 4 + 1) + 4);
                    if (game_map[x1][y1] == -1) {
                        //treasure - green
                        if (f <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 1;
                            f++;
                        }
                        //market - orange
                        if (f4 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 5;
                            f4++;
                        }
                        //loot - blue
                        if (f1 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            f1++;
                        }
                        //trap - red
                        if (f2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            f2++;
                        }
                        //wall - black
                        if (f3 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 4;
                            f3++;
                        }
                    }
                }
            }
            //block 7
            for (int x = 5; x < 10; x++) {
                x1 = (int) Math.floor(Math.random() * (9 - 5 + 1) + 5);
                for (int y = 6; y < 8; y++) {
                    y1 = (int) Math.floor(Math.random() * (7 - 6 + 1) + 6);
                    if (game_map[x1][y1] == -1) {
                        //treasure - green
                        if (h <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 1;
                            h++;
                        }
                        //market - orange
                        if (h4 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 5;
                            h4++;
                        }
                        //loot - blue
                        if (h1 <= 1 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            h1++;
                        }
                        //trap - red
                        if (h2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            h2++;
                        }
                        //wall - black
                        if (h3 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 4;
                            h3++;
                        }
                    }
                }
            }
            //block 8
            for (int x = 5; x < 10; x++) {
                x1 = (int) Math.floor(Math.random() * (9 - 5 + 1) + 5);
                for (int y = 8; y < 10; y++) {
                    y1 = (int) Math.floor(Math.random() * (9 - 8 + 1) + 8);
                    if (game_map[x1][y1] == -1) {
                        //loot - abi
                        if (i1 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 2;
                            i1++;
                        }
                        //trap - red
                        if (i2 <= 0 && game_map[x1][y1] == -1) {
                            game_map[x1][y1] = 3;
                            i2++;
                        }
                    }
                }
            }
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    //castle - yellow
                    if (x == 5 && y == 3) {
                        game_map[x][y] = 6;
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(yellow);
                    }
                    //treasure - green
                    if (game_map[x][y] == 1 && hide_treasure == false) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(new Color(0, 220, 100));
                        setVisible(true);
                    }
                    //hide the treasure
                    if (game_map[x][y] == 1 && hide_treasure == true) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(gray);
                    }
                    //loot - blue
                    if (game_map[x][y] == 2 && hide_loot == false) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(new Color(0, 140, 225));
                        setVisible(true);
                    }
                    //hide the loot
                    if (game_map[x][y] == 2 && hide_loot == true) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(gray);
                    }
                    //trap - red
                    if (game_map[x][y] == 3 && hide_trap == false) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.red);
                        setVisible(true);
                    }
                    //hide the trap
                    if (game_map[x][y] == 3 && hide_trap == true) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(gray);
                    }
                    //wall - black
                    if (game_map[x][y] == 4 && hide_wall == false) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.black);
                        setVisible(true);
                    }
                    //hide the wall
                    if (game_map[x][y] == 4 && hide_wall == true) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(gray);
                    }
                    //market - orange
                    if (game_map[x][y] == 5) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(Color.white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(new Color(250, 140, 20));
                        setVisible(true);
                    }
                    //empty block
                    if (game_map[x][y] == -1) {
                        g.fillRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(white);
                        g.drawRect(x * 70, y * 70, (x + 1) * 70, (y + 1) * 70);
                        g.setColor(gray);
                    }
                }
            }
            draw_map = false;
        }
        // distinguishing the treasure
        int[] rands_treasure = new int[8];
        int u = 0;

        while (u < 8) {
            int rand =  (int) Math.floor(Math.random() * (17 - 10 + 1) + 10);
            boolean s = false;

            for (int k = 0 ; k < u ; k++){
                if (rand == rands_treasure[k]){
                    s = true;
                    break;
                }
            }
            if (s == false){
                rands_treasure[u] = rand;
                u++;
            }
        }
        // distinguishing the loot
        int[] rands_loot = new int[2];
        int p = 0;

        while (p < 2) {
            int rand =  (int) Math.floor(Math.random() * (19 - 18 + 1) + 18);
            boolean s = false;

            for (int j = 0 ; j < p ; j++){
                if (rand == rands_loot[j]){
                    s = true;
                    break;
                }
            }
            if (s == false){
                rands_loot[p] = rand;
                p++;
            }
        }

        // distinguishing the trap
        int[] rands_trap = new int[2];
        int w = 0;

        while (w < 2) {
            int rand =  (int) Math.floor(Math.random() * (21 - 20 + 1) + 20);
            boolean s = false;

            for (int j = 0 ; j < w ; j++){
                if (rand == rands_trap[j]){
                    s = true;
                    break;
                }
            }
            if (s == false){
                rands_trap[w] = rand;
                w++;
            }
        }

        int c = 0;
        int l = 0;
        int t = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                // distinguishing the treasure
                if(game_map[i][j] == 1 && c <= 8 ){
                    game_map[i][j] = rands_treasure[c];
                    c++;
                }
                // distinguishing the loot 1
                if (game_map[i][j] == 2 && l <= 7){
                    game_map[i][j] = rands_loot[0];
                    l++;
                }
                // distinguishing the loot 2
                if (game_map[i][j] == 2 && l <= 13){
                    game_map[i][j] = rands_loot[1];
                    l++;
                }
                // distinguishing the trap 1
                if (game_map[i][j] == 3 && t <= 3){
                    game_map[i][j] = rands_trap[0];
                    t++;
                }
                // distinguishing the trap 2
                if (game_map[i][j] == 3 && t <= 6){
                    game_map[i][j] = rands_trap[1];
                    t++;
                }
            }
        }

        for (int i = 0 ; i < 10 ; i++){
            for (int j = 0 ; j < 10 ; j++){
                if (game_map[i][j] == 1){
                    game_map2[i][j] = 1;
                    game_map3[i][j] = 1;
                }
                if (game_map[i][j] == 2){
                    game_map2[i][j] = 2;
                    game_map3[i][j] = 2;
                }
                if (game_map[i][j] == 3){
                    game_map2[i][j] = 3;
                    game_map3[i][j] = 3;
                }
                if (game_map[i][j] == 4){
                    game_map2[i][j] = 4;
                    game_map3[i][j] = 4;
                }
                if (game_map[i][j] == 5){
                    game_map2[i][j] = 5;
                    game_map3[i][j] = 5;
                }
                if (game_map[i][j] == 6){
                    game_map2[i][j] = 6;
                    game_map3[i][j] = 6;
                }
                if (game_map[i][j] == 7){
                    game_map2[i][j] = 7;
                    game_map3[i][j] = 7;
                }
                if (game_map[i][j] == 8){
                    game_map2[i][j] = 8;
                    game_map3[i][j] = 8;
                }
                if (game_map[i][j] == 9){
                    game_map2[i][j] = 9;
                    game_map3[i][j] = 9;
                }
                if (game_map[i][j] == 10){
                    game_map2[i][j] = 10;
                    game_map3[i][j] = 10;
                }
                if (game_map[i][j] == 11){
                    game_map2[i][j] = 11;
                    game_map3[i][j] = 11;
                }
                if (game_map[i][j] == 12){
                    game_map2[i][j] = 12;
                    game_map3[i][j] = 12;
                }
                if (game_map[i][j] == 13){
                    game_map2[i][j] = 13;
                    game_map3[i][j] = 13;
                }
                if (game_map[i][j] == 14){
                    game_map2[i][j] = 14;
                    game_map3[i][j] = 14;
                }
                if (game_map[i][j] == 15){
                    game_map2[i][j] = 15;
                    game_map3[i][j] = 15;
                }
                if (game_map[i][j] == 16){
                    game_map2[i][j] = 16;
                    game_map3[i][j] = 16;
                }
                if (game_map[i][j] == 17){
                    game_map2[i][j] = 17;
                    game_map3[i][j] = 17;
                }
                if (game_map[i][j] == 18){
                    game_map2[i][j] = 18;
                    game_map3[i][j] = 18;
                }
                if (game_map[i][j] == 19){
                    game_map2[i][j] = 19;
                    game_map3[i][j] = 19;
                }
                if (game_map[i][j] == 20){
                    game_map2[i][j] = 20;
                    game_map3[i][j] = 20;
                }
                if (game_map[i][j] == 21){
                    game_map2[i][j] = 21;
                    game_map3[i][j] = 21;
                }
//                System.out.println(game_map2[i][j]);
            }
        }
    }


    public GameMap() {
        JFrame f = new JFrame();
        setSize(700, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        setLayout(null);
        setVisible(true);
    }

}








