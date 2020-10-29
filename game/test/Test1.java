package com.game.test;

import java.util.Scanner;
import com.game.core.Game;

/**
 * Created by Xu Bowen on 2020/10/28 11:46
 */


public class Test1 {
    public static void main(String[] args){
        Game g = new Game();
//        g.death_init();
        g.addGrid();
        g.setMine();
        g.setNumber();

        Scanner s = new Scanner(System.in);
        g.paint();
        while(true){
            System.out.println("请输入x坐标：");
            int x= s.nextInt();
            System.out.println("请输入y坐标：");
            int y = s.nextInt();
            g.stamp(x, y);
            if(g.clear()){
//                System.out.println(g.death_count());
                break;
            }
            g.paint();
        }
    }
}
