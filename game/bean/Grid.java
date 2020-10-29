package com.game.bean;

import java.awt.Point;

/**
 * Created by Xu Bowen on 2020/10/28 11:49
 */
public class Grid {
    char content;
    boolean state;
    Point point;
    public char getContent(){
        return content;
    }
    public void setContent(char content){
        this.content = content;
    }
    public void setState(boolean state){
        this.state = state;
    }
    public boolean isState(){
        return state;
    }
    public Point getPoint(){
        return point;
    }
    public void setPoint(Point point){
        this.point = point;
    }
}
