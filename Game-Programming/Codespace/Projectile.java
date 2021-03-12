import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Projectile extends Sprite
{
  private static double DURATION = .75;  // secs
    // total time to cycle through all the images

  private static final int XSTEP = 15;   
     // step distance for moving along x-axis
  private static final int YSTEP = 15;   
  // step distance for moving along y-axis


  private int period;
   /* in ms. The game's animation period used by the image
      cycling of the ship's left and right facing images. */
  
  private ClipsLoader clipsLoader;   

  private CodespacePanel cp;
  private ShipSprite ship;
  
  public Projectile(int w, int h, ImagesLoader imsLd, CodespacePanel cp, ShipSprite s, int p) 
  {
    super( 0, 0, w, h, imsLd, "proj"); 
            // positioned at the bottom of the panel, near the center
    
    this.cp = cp;
    ship = s;
    period = p;
    setImage("proj");
    initPosition();
  } // end of Projectile()
  
  private void initPosition() {
	  double angle = Math.toRadians(ship.getAngle() - 90);
	  setPosition(ship.getXPosn() + (ship.getHeight()/2), 
			  			ship.getYPosn() + (ship.getWidth()/2));
	  setAngle(angle);
	  
	  int xVel = (int) ((XSTEP) * Math.cos(angle));
      int yVel = (int) ((YSTEP) * Math.sin(angle));
      setXStep(xVel);
      setYStep(yVel);
      loopImage(period, DURATION);
      startLooping();
  }
  
  public void updateSprite() 
  // 
  {
    super.updateSprite();
  } // end of updateSprite()
}