package gametank.block;

import java.awt.Point;


public class BrickBlock extends Block{
        public BrickBlock(Point pos){
         super(pos, true, false, Layer.second,new Point(32, 0));
    }
}
