package gametank.tank;

import gametank.Field;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Enemy extends Tank implements ActionListener{
    Timer t = new Timer(500, this);
    public Enemy(int str,  Field f) {
        super(str, Course.LEFT, f);
        speed = 4;
        position = new Point(32, 32);
        t.start();
    }
    private Course randomCourse(){
        int i =(int) Math.round(Math.random()*4);
        
        System.out.println(i);
        if (i==0)
            return Course.DOWN;
        else if(i==1)
            return Course.LEFT;
        else if(i==2)
            return Course.RIGHT;
        else 
            return Course.TOP;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if ((position.x%8==0)&&(position.y%8==0))course =  randomCourse();
        if (bullet==null)bullet = new Bullet(position, course, myField);
    }
    
}
