
package gametank.tank;


import gametank.Field;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Tank {
    enum Course{TOP,LEFT,DOWN,RIGHT};
    private ArrayList[] SpriteA = new ArrayList [4];
    private Iterator<Point> SpriteIterator;
    public Point Sp;// self sprite point
    public Point position;
    public double speed;
    public Course course;
    public Bullet bullet;//пуля
    
    public Field myField;
    
    private final void initSprite(int str){
        int iter = 1;
        for (int i = 0; i<4;i++){
            SpriteA[i] = new ArrayList<Point>();
            SpriteA[i].add(new Point(iter, str));
            SpriteA[i].add(new Point(iter+1, str));
            iter+=2;
        }
    }
    
    Tank(int str,Course c,Field f){
        initSprite(str);
        course = c;
        myField = f;
        updateSpriteIterator();
        NextSprite();
    }
    
    protected final void NextSprite(){
        if (SpriteIterator.hasNext())Sp = SpriteIterator.next();
        else{
            SpriteIterator = SpriteA[course.ordinal()].iterator();
            if (SpriteIterator.hasNext())Sp = SpriteIterator.next();
        }
    }
    
    protected final void updateSpriteIterator(){SpriteIterator = SpriteA[course.ordinal()].iterator();}
    
    public void move(){
        if (bullet!=null){
            bullet.move();
        }
        
        Point bufPos  = new Point(position);
        Point bufClone;
            
        switch (course){
            case DOWN: {
                 bufPos.y +=speed;
                 if (bufPos.y>=(512-32)){position.y = 480; NextSprite();return;}
                 bufClone = new Point(bufPos);
                 bufClone.y+=31;
                 if (!myField.isBarrier(myField.pointInCell(bufClone))){
                     bufClone.x+=31;
                     if (!myField.isBarrier(myField.pointInCell(bufClone)))position = new Point(bufPos);
                 }
                 break;
            }
            case LEFT:{ 
               bufPos.x -=speed; 
               if (bufPos.x<0){position.x = 0;NextSprite();return;}
               bufClone = new Point(bufPos);
               if (!myField.isBarrier(myField.pointInCell(bufClone))){
                    bufClone.y+=31;
                    if (!myField.isBarrier(myField.pointInCell(bufClone)))position = new Point(bufPos);
                 }                   
                break;
            }
            case RIGHT:{
                bufPos.x +=speed;
                if (bufPos.x>=(512-32)){position.x = 480;NextSprite();return;}
                bufClone = new Point(bufPos);
                 bufClone.x+=31;
                 if (!myField.isBarrier(myField.pointInCell(bufClone))){
                     bufClone.y+=31;
                     if (!myField.isBarrier(myField.pointInCell(bufClone)))position = new Point(bufPos);
                 }                
                break;
            }
            case TOP:{
                 bufPos.y -=speed;
                 if (bufPos.y<0){position.y = 0;NextSprite();return;}
                 bufClone = new Point(bufPos);
                 if (!myField.isBarrier(myField.pointInCell(bufClone))){
                     bufClone.x+=31;
                     if (!myField.isBarrier(myField.pointInCell(bufClone)))position = new Point(bufPos);
                 }                   
                 break;
            }
        }
        NextSprite();
        
    } 
    public boolean isMove(){
        if (speed>0) return true; else return false; 
    }
    
}
