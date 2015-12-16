package simplegame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
   private Robot robot; 
   private Image image, currentSprite, character, characterDown, characterJumped, background;
   private URL base; //url allows us to use addresses (such as C:\\Users\\Desktop\\image1.jpg)
   private Graphics second;
   private static Background bg1, bg2;
   
	public static Background getBg1() {
	return bg1;
}

public static void setBg1(Background bg1) {
	StartingClass.bg1 = bg1;
}

public static Background getBg2() {
	return bg2;
}

public static void setBg2(Background bg2) {
	StartingClass.bg2 = bg2;
}

	public void init(){
	   setSize(800, 400);
	   setBackground(Color.BLACK);
	   setFocusable(true);
	   Frame frame = (Frame) this.getParent().getParent();
	   frame.setTitle("Bot Alpha");
	   addKeyListener(this);
	   try{
		   base = getDocumentBase();
	   }catch(Exception e){
		   //handle exception
	   }
	   
	   //Image setups
	   character = getImage(base, "data/character.png");
	   characterDown = getImage(base, "data/down.png");
	   characterJumped = getImage(base, "data/jumped.png");
	   currentSprite = character;
	   
	   background = getImage(base, "data/background.png");
   }
   
   public void start(){
	   bg1 = new Background(0, 0);
	   bg2 = new Background(2160, 0);
	   robot = new Robot();
	   
	   Thread thread = new Thread(this);
	   thread.start();
   }
   
   public void stop(){
	 
   }
   
   public void destroy(){
	  
   }
   @Override
	public void run() {
		// TODO Auto-generated method stub
	   while(true){
		   
		   robot.update();
		   if (robot.isJumped()){
				currentSprite = characterJumped;
			}else if (robot.isJumped() == false && robot.isDucked() == false){
				currentSprite = character;
			}
		   bg1.update();
		   bg2.update();
		   repaint();
		   try{
			   Thread.sleep(17);
		   }catch (InterruptedException e){
			   e.printStackTrace();
		   }
		   
	   }
		
	}
   
   //the update method is used for double buffering. 
   @Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (image == null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0,  0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0 , this);
	}
   
   @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	    g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
	    g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
	 
   }



   @Override
   public void keyPressed(KeyEvent e) {

       switch (e.getKeyCode()) {
       case KeyEvent.VK_UP:
           System.out.println("Move up");
           break;

       case KeyEvent.VK_DOWN:
           currentSprite = characterDown;
           if (robot.isJumped() == false){
               robot.setDucked(true);
               robot.setSpeedX(0);
           }
           break;

       case KeyEvent.VK_LEFT:
           robot.moveLeft();
           robot.setMovingLeft(true);
           break;

       case KeyEvent.VK_RIGHT:
           robot.moveRight();
           robot.setMovingRight(true);
           break;

       case KeyEvent.VK_SPACE:
           robot.jump();
           break;

       }

   }

   @Override
   public void keyReleased(KeyEvent e) {
       switch (e.getKeyCode()) {
       case KeyEvent.VK_UP:
           System.out.println("Stop moving up");
           break;

       case KeyEvent.VK_DOWN:
           currentSprite = character;
           robot.setDucked(false);
           break;

       case KeyEvent.VK_LEFT:
           robot.stopLeft();
           break;

       case KeyEvent.VK_RIGHT:
           robot.stopRight();
           break;

       case KeyEvent.VK_SPACE:
           break;

       }

   }



@Override
public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

}
   
   
   
}
