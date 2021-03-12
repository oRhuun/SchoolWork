import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ShipSprite extends Sprite
{
  private static double DURATION = .5;  // secs
    // total time to cycle through all the images

  private static final int FLOOR_DIST = 100;   
      // distance of ship's top from the floor
  private static final int XSTEP = 9;   
     // step distance for moving along x-axis
  private static final int YSTEP = 9;   
  // step distance for moving along y-axis

  private ClipsLoader clipsLoader;   
  
  private int period;
   /* in ms. The game's animation period used by the image
      cycling of the ship's left and right facing images. */

  public ShipSprite(int w, int h, ImagesLoader imsLd, ClipsLoader cl, int p) 
  { 
    super( w/2, h-FLOOR_DIST, w, h, imsLd, "ship"); 
            // positioned at the bottom of the panel, near the center
    period = p;
    setImage("ship");
    clipsLoader = cl;
    loopImage(period, DURATION);
    setStep(0,0);  // no movement
  } // end of Projectile()


  public void moveLeft()
  // start the ship moving left
  { 
    setXStep(-XSTEP);
    
  } // end of moveLeft()

  public void moveRight()
  // start the ship moving right
  { 
    setXStep(XSTEP); 
  } // end of moveRight()

  public void moveUp()
  // start the ship moving up
  { 
    setYStep(-YSTEP); 
  } // end of moveUp()
  
  public void moveDown()
  // start the ship moving down
  { 
    setYStep(YSTEP); 
  } // end of moveDown()
  
  public void stopX()
  // stop the ship in x direction
  { 
	setXStep(0);
  } // end of stopX()
  
  public void stopY()
  // stop the ship in y direction
  { 
	setYStep(0);
  } // end of stopY()
  
  public void dash()
  //move quickly
  {	
	 int x, y, newX, newY;
	 x = getXStep();
	 y = getYStep();
	 newX = x * 3;
	 newY = y * 3;
	 setStep(newX, newY);
	 try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		e.printStackTrace();
	} //honestly couldn't tell you why this is here but it helps
	 setStep(x, y);
  } // end of dash
  
  public void diagDash()
  //move quickly but in a diagonal direction
  {	
	 int x, y, newX, newY;
	 x = getXStep();
	 y = getYStep();
	 newX = x * (int)Math.sqrt(4.5);
	 newY = y * (int)Math.sqrt(4.5);
	 setStep(newX, newY);
	 try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		e.printStackTrace();
	} //honestly couldn't tell you why this is here but it helps
	 setStep(x, y);
  } // end of diagDash
  
  public void turn(int mouseX, int mouseY)
  //turn ship in direction of the mouse
  {
	  int shipX = getXPosn();
	  int shipY = getYPosn();
	  int xDistance = mouseX - shipX;
	  int yDistance = mouseY - shipY;
	  double rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance)) + 90;
	  setAngle(rotationAngle);
  }
  
  public void updateSprite() 
  // have the ship wrap-around at the walls
  {
    if (((locx) <= 0) && (dx < 0))   // almost gone off lhs
    {
    	locx = getPWidth()-1;      // make it just visible on the right
    	clipsLoader.play("warp", false);
    }
    else if ((locx+getWidth() >= getPWidth()-1) && (dx > 0))  // almost gone off rhs
    {
    	locx = 1 - getWidth();     // make it just visible on the left
    	clipsLoader.play("warp", false);
    }

    if (((locy) <= 0) && (dy < 0))   // almost gone off top
    {
        locy = getPHeight()-1;      // make it just visible on the bottom
        clipsLoader.play("warp", false);
    }
    else if ((locy+getHeight() >= getPHeight()-1) && (dy > 0))  // almost gone off bottom
    {
        locy = 1 - getHeight();     // make it just visible on the top
        clipsLoader.play("warp", false);
    }

    
    super.updateSprite();
  } // end of updateSprite()
}