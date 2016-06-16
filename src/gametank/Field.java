package gametank;


import gametank.block.Block;
import gametank.block.BrickBlock;
import gametank.block.GreenBlock;
import gametank.block.SteelBlock;
import gametank.tank.Enemy;
import gametank.tank.MyTank;
import gametank.tank.Tank;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;





public class Field extends Canvas implements ActionListener{
    protected Timer time = new Timer(50, this );
    private final int S = 512;
    public final int SPRITE_FACTOR = 16;
    private ArrayList l1 = new ArrayList<Point>();
    private ArrayList l3 = new ArrayList<Point>();
    private ArrayList l2 = new ArrayList<Point>();
    

    Block[][] map;
    MyTank t = new MyTank(this);
    Enemy bt = new Enemy(6, this);
    
    private Image resources;
    private BufferedImage bufferimg = new BufferedImage(S, S, BufferedImage.TYPE_INT_RGB);
    private ArrayList<Tank> players;
    
    Field(){
        
        setSize(S, S);
        setPreferredSize(new Dimension(S, S));
        initResources();
        initMap(StringAr());
//        initMap();
        addKeyListener(t);

    }
    private void initResources(){
        resources = new ImageIcon("sprite.png").getImage();
      
    }
    private String[] StringAr(){
        String [] s = new String[16];
        s[0] = "SS---SSSSS---SSS";
        s[1] = "-------G-G------";
        s[2] = "----------------";
        s[3] = "--------G---S---";
        s[4] = "----------------";
        s[5] = "----S----BB-----";
        s[6] = "----------------";
        s[7] = "--BB----G-------";
        s[8] = "----------------";
        s[9] = "-------G----B-B-";
        s[10] = "----------------";
        s[11] = "--------BG------";
        s[12] = "----BB----GG----";
        s[13] = "------SB--------";
        s[14] = "--------S-------";
        s[15] = "----------------";
        return s;
        
    }
    private void initMap(){
        map = new Block[getWidth()/SPRITE_FACTOR][getHeight()/SPRITE_FACTOR];
        for (int i = 0;i<32;i++){
            for(int j = 0;j<32;j++){
               
                if (i>8)map[i][j] = new GreenBlock(new Point(i, j)); else map[i][j] = new BrickBlock(new Point(i, j));
                 if (i <32 && j<2)map[i][j] = null;
                 if (j <32 && i<2)map[i][j] = null;
            }
        }
        initLayer();
    }
    private void initMap(String[] s){
        map = new Block[getWidth()/SPRITE_FACTOR][getHeight()/SPRITE_FACTOR];
        for(int i = 0;i<16;i++){
            char [] c = s[i].toCharArray();
            for (int j = 0;j<16;j++){
                switch (c[j]){
                    case 'S':{
                        map[i*2][j*2] = new SteelBlock(new Point(i*2, j*2));
                        map[i*2+1][j*2] = new SteelBlock(new Point(i*2+1, j*2));
                        map[i*2][j*2+1] = new SteelBlock(new Point(i*2, j*2+1));
                        map[i*2+1][j*2+1] = new SteelBlock(new Point(i*2+1, j*2+1));
                        break;
                    }
                    case 'B':{
                        map[i*2][j*2] = new BrickBlock(new Point(i*2, j*2));
                        map[i*2+1][j*2] = new BrickBlock(new Point(i*2+1, j*2));
                        map[i*2][j*2+1] = new BrickBlock(new Point(i*2, j*2+1));
                        map[i*2+1][j*2+1] = new BrickBlock(new Point(i*2+1, j*2+1));
                        break;
                    }
                    case 'G':{
                        map[i*2][j*2] = new GreenBlock(new Point(i*2, j*2));
                        map[i*2+1][j*2] = new GreenBlock(new Point(i*2+1, j*2));
                        map[i*2][j*2+1] = new GreenBlock(new Point(i*2, j*2+1));
                        map[i*2+1][j*2+1] = new GreenBlock(new Point(i*2+1, j*2+1));
                        break;
                    }
                    default:{}
                }
            }
        }
         initLayer();
    }
    private void initLayer(){
        for (int i = 0; i <getWidth()/SPRITE_FACTOR; i++) {
            for (int j = 0; j < getHeight()/SPRITE_FACTOR; j++) {
                if (map[i][j] !=null ){
                    if (map[i][j].layer == Block.Layer.first)l1.add(new Point(i, j));
                    if (map[i][j].layer == Block.Layer.second)l2.add(new Point(i, j));
                    if (map[i][j].layer == Block.Layer.third)l3.add(new Point(i, j));
                }
            }
        }
    
    }
    private void  drawLayer(Graphics g,ArrayList<Point> a){
        g = (Graphics2D) g;
        for (Point a1 : a) {
            if (map[a1.x][a1.y] !=null)
            g.drawImage(resources,
                    map[a1.x][a1.y].POSITION.x*SPRITE_FACTOR,
                    map[a1.x][a1.y].POSITION.y*SPRITE_FACTOR,
                    map[a1.x][a1.y].POSITION.x*SPRITE_FACTOR+16,
                    map[a1.x][a1.y].POSITION.y*SPRITE_FACTOR+16,
                    SPRITE_FACTOR/2*map[a1.x][a1.y].RESOURSE_POINT.x,SPRITE_FACTOR/2*map[a1.x][a1.y].RESOURSE_POINT.y,
                    SPRITE_FACTOR/2*(map[a1.x][a1.y].RESOURSE_POINT.x+1),SPRITE_FACTOR/2*(map[a1.x][a1.y].RESOURSE_POINT.y+1),
                    null);           
        }   
    }
    public void DestroyBlock(Point p){
        map[p.x][p.y] = null;
    }
    public Point pointInCell(Point p ){
        Point self = new Point();
        self.x =(int) Math.floor(p.x/16);
        self.y =(int) Math.floor(p.y/16);
        return self;
    }
    public boolean isBarrier(Point p){
        if ((map[p.x][p.y]==null))
        {
            return false;
        }else{
            if (map[p.x][p.y].PASSABLE)return false;else return true;
               
        }
    }
    public Block getBlockAtField(Point p){
    return map[p.x][p.y];
    }
    private void render(){
        Graphics2D g = bufferimg.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            drawLayer(g,l1);
            drawLayer(g,l2);
            
            g.drawImage(resources,
                t.position.x, t.position.y,t.position.x+32,t.position.y+32,
                SPRITE_FACTOR*(t.Sp.x-1),SPRITE_FACTOR*(t.Sp.y-1),
                SPRITE_FACTOR*(t.Sp.x),SPRITE_FACTOR*(t.Sp.y), null);
            if (t.bullet!=null){
                g.drawImage(resources,
                t.bullet.position.x, t.bullet.position.y,t.bullet.position.x+16,t.bullet.position.y+16,
                SPRITE_FACTOR/2*(t.bullet.resoursPoint.x),SPRITE_FACTOR/2*(t.bullet.resoursPoint.y),
                SPRITE_FACTOR/2*(t.bullet.resoursPoint.x+1),SPRITE_FACTOR/2*(t.bullet.resoursPoint.y+1), null);
            }
                        g.drawImage(resources,
                bt.position.x, bt.position.y,bt.position.x+32,bt.position.y+32,
                SPRITE_FACTOR*(bt.Sp.x-1),SPRITE_FACTOR*(bt.Sp.y-1),
                SPRITE_FACTOR*(bt.Sp.x),SPRITE_FACTOR*(bt.Sp.y), null);
            if (bt.bullet!=null){
                g.drawImage(resources,
                bt.bullet.position.x, bt.bullet.position.y,bt.bullet.position.x+16,bt.bullet.position.y+16,
                SPRITE_FACTOR/2*(bt.bullet.resoursPoint.x),SPRITE_FACTOR/2*(bt.bullet.resoursPoint.y),
                SPRITE_FACTOR/2*(bt.bullet.resoursPoint.x+1),SPRITE_FACTOR/2*(bt.bullet.resoursPoint.y+1), null);
            }
            drawLayer(g, l3);
    }
    @Override
    public void update(Graphics g) {
        g = (Graphics2D)g;
        g.drawImage(bufferimg,0,0,getWidth(),getHeight(), this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (t.isMove())t.move();
        if (t.bullet!=null){
        if (t.bullet.isDead)t.bullet = null;else t.bullet.move();
        }
        if (bt.isMove())bt.move();
        if (bt.bullet!=null){
        if (bt.bullet.isDead)bt.bullet = null;else bt.bullet.move();
        }
        render();
        update(getGraphics());
        
    }
    
}
