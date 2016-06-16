package gametank.tank;

import gametank.Field;
import gametank.tank.Tank.Course;
import java.awt.Point;

public class Bullet {
    public Course course;
    public int speed = 8;
    public Point position;
    private Field field;
    private Point [] resourseImgA = new Point[4];
    public Point resoursPoint;
    public boolean isDead = false;
    public Bullet(Point p,Course c,Field f){
        initSprite();
        course = c;
        position = new Point(p);
        position.y+=8;
        position.x+=8;
        field = f;
        resoursPoint = resourseImgA[course.ordinal()];
        
    }
    private void initSprite(){
         resourseImgA[0] = new Point(36, 5);
          resourseImgA[1] = new Point(37, 5);
          resourseImgA[2] = new Point(37, 4);
          resourseImgA[3] = new Point(36, 4);

    }
     public void move(){
          Point bufPos  = new Point(position);
          Point bufClone;
              switch (course){
            case RIGHT: {
                
                 bufPos.x +=speed;
                 if (bufPos.x>=(512-16)){isDead = true;return;}
                 bufClone = new Point(bufPos);
                 bufClone.x+=16;
                 if (field.isBarrier(field.pointInCell(bufClone))){
                     if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                            field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                 }
                  bufClone.y+=16;   
                 if (field.isBarrier(field.pointInCell(bufClone))){
                     if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                 }
            
                 
               
                 break;
                }
                case LEFT:{
                 bufPos.x -=speed; 
                   if (bufPos.x<0){isDead = true;return;}
                    bufClone = new Point(bufPos);
                    if (field.isBarrier(field.pointInCell(bufClone))){
                        if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                     }
                    bufClone.y+=16;  
                    if (field.isBarrier(field.pointInCell(bufClone))){
                        if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                 }
                   break;
                }
                case TOP:{
                 bufPos.y -=speed; 
                 bufClone = new Point(bufPos);
                   if (bufPos.y<0){isDead = true;return;}
                     if (field.isBarrier(field.pointInCell(bufClone))){
                         if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                     }
                    bufClone.x+=16;  
                    if (field.isBarrier(field.pointInCell(bufClone))){
                        if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                 }
                   break;
                }
                case DOWN: {
                 bufPos.y +=speed;
                 if (bufPos.y>=(512-16)){isDead = true;return;}
                 
                      bufClone = new Point(bufPos);
                  bufClone.y+=16;
                     if (field.isBarrier(field.pointInCell(bufClone))){
                         if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                     }
                    bufClone.x+=16;  
                    if (field.isBarrier(field.pointInCell(bufClone))){
                        if (field.getBlockAtField(field.pointInCell(bufClone)).DESTROYABLE)
                     field.DestroyBlock(field.pointInCell(bufClone));
                     isDead = true;
                 }
                }
 
   }
             
        position = new Point(bufPos);
     }

     
    
}
