package gametank.block;

import java.awt.Point;

public abstract class Block {
    public enum Layer{first,second,third};
    public final boolean DESTROYABLE;
    public final boolean PASSABLE;
    public final Point POSITION;
    public final Point RESOURSE_POINT;
    public final Layer layer; 
    Block(Point pos,boolean destroy,boolean pass,Layer l,Point rp){
        POSITION = pos;
        DESTROYABLE = destroy;
        PASSABLE = pass;
        RESOURSE_POINT = rp;
        layer = l;
        
    }
}
