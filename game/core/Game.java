package com.game.core;

/**
 * Created by Xu Bowen on 2020/10/28 11:47
 */

import java.awt.Point;
import java.util.Random;
import com.game.bean.Grid;
import com.game.player.score;

public class Game {
    Grid[][] grid = new Grid[9][9];
    int count = 10;
    score play = new score();
    Random r = new Random();
    int[] death_count = new int[]{0};

    public static void change(int[] a){
        a[0]++;
    }
//    int sum = 0;

//    public void death_init(sum){
//        play.setDeath_count(sum);
//    }
//
//    public int death_count(){
//        return play.getDeath_count();
//    }

    public void addGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Grid();
                grid[i][j].setContent(' ');
                grid[i][j].setState(false);
                grid[i][j].setPoint(new Point(i, j));
            }
        }
    }

    public void paint(){
        for (Grid[] grids : grid) {
            for (Grid value : grids) {
                if (value.isState()) {
                    System.out.print(value.getContent() + " ");
                } else {
                    System.out.print("■ ");
                }
            }
            System.out.println();
        }
    }

    public void setMine(){
        int i = 0;
        do {
            int x = r.nextInt(9);
            int y = r.nextInt(9);
            if(grid[x][y].getContent() != '*'){
                grid[x][y].setContent('*');
                i++;
            }
        }while(i < count);
    }

    public Point[] getPoint(int x, int y){
        Point[] point = new Point[8];
        point[0] = new Point(x-1, y);
        point[1] = new Point(x-1, y-1);
        point[2] = new Point(x, y-1);
        point[3] = new Point(x+1, y-1);
        point[4] = new Point(x+1, y);
        point[5] = new Point(x+1, y+1);
        point[6] = new Point(x, y+1);
        point[7] = new Point(x-1, y+1);
        return point;
    }

    public void setNumber(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                int sum = 0;
                if(grid[i][j].getContent() == '*'){
                    continue;
                }
                else{
                    Point[] point = getPoint(i, j);
                    for(int k = 0; k < point.length; k++){
                        Point p = point[k];
                        if(p.x >= 0 && p.y >= 0 && p.x < 9 && p.y < 9){
                            if(grid[p.x][p.y].getContent() == '*'){
                                sum++;
                            }
                        }
                    }
                }
                if(sum > 0){
                    grid[i][j].setContent((char)(48 + sum));
                }
            }
        }
    }

    public void stamp(int x, int y){
        if(grid[x][y].getContent() == '*'){
            System.out.println("你死了!");
            change(death_count);
        }
        else{
            grid[x][y].setState(true);
            if(grid[x][y].getContent() == ' '){
                Point[] point = getPoint(x, y);
                for(int k = 0; k < point.length; k++){
                    Point p = point[k];
                    if(p.x >= 0 && p.y >= 0 && p.x < 9 && p.y < 9){
                        if(grid[p.x][p.y].getContent() == ' ' && grid[p.x][p.y].isState() == false){
                            stamp(p.x, p.y);
                        }else if(grid[p.x][p.y].getContent() != ' '){
                            grid[p.x][p.y].setState(true);
                        }
                    }
                }
            }
        }
    }

    public boolean clear(){
        int sum = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(!grid[i][j].isState()){
                    sum++;
                }else{
                    continue;
                }
            }
        }
        if(sum == count){
            System.out.println("游戏胜利!");
            System.out.println("你的死亡次数：" + death_count[0]);
            return true;
        }else{
            return false;
        }
    }
}
