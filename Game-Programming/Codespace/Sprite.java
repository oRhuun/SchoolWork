
// Sprite.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* A Sprite has a position, velocity (in terms of steps),
   an image, and can be deactivated.

   The sprite's image is managed with an ImagesLoader object,
   and an ImagesPlayer object for looping.

   The images stored until the image 'name' can be looped
   through by calling loopImage(), which uses an
   ImagesPlayer object.

*/

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import java.awt.image.*;


public class Sprite 
{
  // default step sizes (how far to move in each update)
  private static final int XSTEP = 5; 
  private static final int YSTEP = 5;

  // default dimensions when there is no image
  private static final int SIZE = 12;   

  // image-related
  private ImagesLoader imsLoader;
  private String imageName;
  private BufferedImage image;
  private int width, height;     // image dimensions

  private ImagesPlayer player;  // for playing a loop of images
  private boolean isLooping;
  
  private int pWidth, pHeight;   // panel dimensions

  private boolean isActive = true;      
  // a sprite is updated and drawn only when it is active

  private GraphicsConfiguration gc;
  private int angle = 0;
  // variables for changing the sprites angle
  
  // protected vars
  protected int locx, locy;        // location of sprite
  protected int dx, dy;            // amount to move for each update

  public Sprite(int x, int y, int w, int h, ImagesLoader imsLd, String name) 
  { 
    locx = x; locy = y;
    pWidth = w; pHeight = h;
    dx = XSTEP; dy = YSTEP;

    imsLoader = imsLd;
    setImage(name);    // the sprite's default image is 'name'
  } // end of Sprite()


  public void setImage(String name)
  // assign the name image to the sprite
  {
    imageName = name;
    image = imsLoader.getImage(imageName);
    if (image == null) {    // no image of that name was found
      System.out.println("No sprite image for " + imageName);
      width = SIZE;
      height = SIZE;
    }
    else {
      width = image.getWidth();
      height = image.getHeight();
    }
    // no image loop playing 
    player = null;
    isLooping = false;
  }  // end of setImage()


  public void loopImage(int animPeriod, double seqDuration)
  /* Switch on loop playing. The total time for the loop is
     seqDuration secs. The update interval (from the enclosing
     panel) is animPeriod ms. */
  {
    if (imsLoader.numImages(imageName) > 1) {
      player = null;   // to encourage garbage collection of previous player
      player = new ImagesPlayer(imageName, animPeriod, seqDuration,
                                       true, imsLoader);
      isLooping = true;
    }
    else
      System.out.println(imageName + " is not a sequence of images");
  }  // end of loopImage()


  public void stopLooping()
  {
    if (isLooping) {
      player.stop();
      isLooping = false;
    }
  }  // end of stopLooping()
  
  public void startLooping()
  { isLooping = true; }


  public int getWidth()    // of the sprite's image
  {  return width;  }

  public int getHeight()   // of the sprite's image
  {  return height;  }

  public int getPWidth()   // of the enclosing panel
  {  return pWidth;  }

  public int getPHeight()  // of the enclosing panel
  {  return pHeight;  }
  
  public void setAngle(double a) // of the sprite
  {	 angle = (int)a;		}
  
  public double getAngle() // of the sprite
  {	 return angle;		}
  

  public boolean isActive() 
  {  return isActive;  }

  public void setActive(boolean a) 
  {  isActive = a;  }

  public void setPosition(int x, int y)
  {  locx = x; locy = y;  }

  public void translate(int xDist, int yDist)
  {  locx += xDist;  locy += yDist;  }
  
  public int getXPosn()
  {  return locx;  }

  public int getYPosn()
  {  return locy;  }

  public void setStep(int dx, int dy)
  {  this.dx = dx; this.dy = dy;}
  
  public void setXStep(int dx)
  {  this.dx = dx;}
  
  public void setYStep(int dy)
  {  this.dy = dy;}

  public int getXStep()
  {  return dx;  }

  public int getYStep()
  {  return dy;  }


  public Rectangle getMyRectangle()
  {  return  new Rectangle(locx, locy, width, height);  }


  public void updateSprite()
  // move the sprite
  {
    if (isActive()) {
      locx += dx;
      locy += dy;
      if (isLooping)
        player.updateTick();  // update the player
    }
  } // end of updateSprite()

  public BufferedImage getRotatedImage(BufferedImage src, double angle) {
		if (src == null) {
			System.out.println("getRotatedImage: input image is null");
			return null;
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment
				  .getLocalGraphicsEnvironment();
			gc = ge.getDefaultScreenDevice().getDefaultConfiguration();

		int transparency = src.getColorModel().getTransparency();
		BufferedImage dest = gc.createCompatibleImage(src.getWidth(), src
				.getHeight(), transparency);
		Graphics2D g2d = dest.createGraphics();

		AffineTransform origAT = g2d.getTransform(); // save original transform

		// rotate the coord. system of the dest. image around its center
		AffineTransform rot = new AffineTransform();
		rot.rotate(Math.toRadians(angle), src.getWidth() / 2,
				src.getHeight() / 2);
		g2d.transform(rot);

		g2d.drawImage(src, 0, 0, null); // copy in the image

		g2d.setTransform(origAT); // restore original transform
		g2d.dispose();

		return dest;
	} // end of getRotatedImage()

public void drawSprite(Graphics g, double angle) 
{
	  if (isActive()) {
    if (image == null) {   // the sprite has no image
      g.setColor(Color.yellow);   // draw a yellow circle instead
      g.fillOval(locx, locy, 10, 10);
      g.setColor(Color.black);
    }
    else {
      if (isLooping)
        image = player.getCurrentImage();
      BufferedImage foo = getRotatedImage(image, angle);
      g.drawImage(foo, locx, locy, null);
    }
  }
} // end of drawSprite()

  

  public void drawSprite(Graphics g) 
  {
    if (isActive()) {
      if (image == null) {   // the sprite has no image
        g.setColor(Color.yellow);   // draw a yellow circle instead
        g.fillOval(locx, locy, SIZE, SIZE);
        g.setColor(Color.black);
      }
      else {
        if (isLooping)
          image = player.getCurrentImage();
        g.drawImage(image, locx, locy, null);
      }
    }
  } // end of drawSprite()

}  // end of Sprite class
