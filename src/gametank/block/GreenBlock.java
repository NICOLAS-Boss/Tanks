package gametank.block;

import java.awt.Point;

public class GreenBlock extends Block{
        public GreenBlock(Point pos){
        super(pos, false, true,Block.Layer.third, new Point(36, 6));
    }
}
