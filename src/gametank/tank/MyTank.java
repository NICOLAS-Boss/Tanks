package gametank.tank;

import gametank.Field;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MyTank extends Tank implements KeyListener{
    private boolean KeyboardIsLock = false;
    private int curentKey = 0;
    
    public MyTank(Field f) {
        super(1,Course.RIGHT,f);
        position = new Point(32,400);
    }
    @Override
    public void keyTyped(KeyEvent e) {//КОСТЫЛЬ
              if (e.getKeyChar() == 'w'){
                    if (bullet  == null)bullet = new Bullet(position,course,myField);
                    if (speed!=0)speed = 4;
            }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        
        if ((!KeyboardIsLock)||(curentKey==e.getKeyCode())){
        KeyboardIsLock = true;
        curentKey= e.getKeyCode();
        switch(e.getKeyCode()){
            case KeyEvent.VK_DOWN:{
                if (course != Course.DOWN){ 
                    course = Course.DOWN;
                    updateSpriteIterator();
                }
                speed = 4;
                break;
            }
            case KeyEvent.VK_UP:{
                if (course != Course.TOP){ 
                    course = Course.TOP;
                    updateSpriteIterator();
                }
                speed = 4;
                break;
            }        
            case KeyEvent.VK_LEFT:{
                if (course != Course.LEFT){ 
                    course = Course.LEFT;
                    updateSpriteIterator();
                }
                speed = 4;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                if (course != Course.RIGHT){ 
                    course = Course.RIGHT;
                    updateSpriteIterator();
                }
                speed = 4;
                break;
            }

            
       }
        
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()!=e.VK_W){
        while ((position.x % 16 !=0)||(position.y % 16 !=0)){
            myField.actionPerformed(null);
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyTank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        speed = 0;
        KeyboardIsLock = false;
        }
    }
    
}
