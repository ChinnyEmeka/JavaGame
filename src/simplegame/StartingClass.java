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
   private Image image, character;
   private URL base; //url allows us to use addresses (such as C:\\Users\\Desktop\\image1.jpg)
   private Graphics second;
   
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
   }
   
   public void start(){
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
		g.drawImage(character, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
	}



@Override
public void keyPressed(KeyEvent e) {

    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
    	System.out.println("Move up");
        break;

    case KeyEvent.VK_DOWN:
        System.out.println("Move down");
        break;

    case KeyEvent.VK_LEFT:
    	robot.moveLeft();
     
        break;

    case KeyEvent.VK_RIGHT:
    	robot.moveRight();
       
        break;

    case KeyEvent.VK_SPACE:
    	robot.jump();
        System.out.println("Jump");
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
        System.out.println("Stop moving down");
        break;

    case KeyEvent.VK_LEFT:
        robot.stop();
        break;

    case KeyEvent.VK_RIGHT:
    	robot.stop();
        break;

    case KeyEvent.VK_SPACE:
        System.out.println("Stop jumping");
        break;

    }

}

@Override
public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

}
   
   
   
}
