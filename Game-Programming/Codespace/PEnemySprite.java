
// PEnemySprite.java
import java.awt.*;


public class PEnemySprite extends Sprite
{
  // the PEnemy's x- and y- step values are STEP +/- STEP_OFFSET
  private static final int STEP = 10;
  private static final int STEP_OFFSET = 2;

  private static double DURATION = 1;  // secs
  // total time to cycle through all the images
  private int period;
  
  private ClipsLoader clipsLoader;   

  private CodespacePanel cp;
  private ShipSprite ship;
  public Projectile[] projs;
  
  public PEnemySprite(int w, int h, ImagesLoader imsLd, ClipsLoader cl,
                             CodespacePanel cp, ShipSprite s, int p, Projectile[] pr) 
  { 
    super( w/2, 0, w, h, imsLd, "penemy");  
           // the enemy is positioned in the middle at the top of the panel
    projs = pr;
    clipsLoader = cl;
    this.cp = cp;
    ship = s;
    period = p;
    setImage("penemy");
    initPosition();
    
  }

  private void initPosition()
  // initialize the enemy's image, position and step values (speed)
  { 
    setImage("penemy");
    setPosition( (int)(getPWidth() * Math.random()), 0);   
                                  // somewhere along the top
    int step = STEP + getRandRange(STEP_OFFSET);
    int xStep = ((Math.random() < 0.5) ? -step : step); // move left or right
    setStep(xStep, STEP + getRandRange(STEP_OFFSET));   // move down
    setAngle(180 * Math.random());
    turn();
    loopImage(period, DURATION);
    startLooping();
  }  // end of initPosition()

  private int getRandRange(int x) 
  // random number generator between -x and x
  {   return ((int)(2 * x * Math.random())) - x;  }

  public void updateSprite() 
  {
    hasHitShip();
    hasHitProj();
    hasHitWall();
    
    super.updateSprite();
  }  // end of updateSprite()


  private void hasHitShip()
  /* If the enemy hits the ship, lose health or game.
     
  */
  {
    Rectangle rect = getMyRectangle();
    if (rect.intersects( ship.getMyRectangle() )) {     // ship collision?
      clipsLoader.play("hitShip", false);
      Rectangle interRect = rect.intersection(ship.getMyRectangle());
      cp.hitShip();
      setPosition(0,0);
      initPosition();
    }
  } // end of hasHitShip()
  
  private void hasHitProj()
  // If the enemy hits the bullet, reset it and add 1 to score
  {
    Rectangle rect = getMyRectangle();
    for(int i = 0; i <= 29; i++){
    	if (rect.intersects(projs[i].getMyRectangle() )) {     
    		clipsLoader.play("hitProj", false);
    		Rectangle interRect = rect.intersection(projs[i].getMyRectangle());
    		cp.addScore();
    		setPosition(0,0);
    		initPosition();
    }
    }
  } // end of hasHitShip()

  private void hasHitWall()
  /* Respond when the enemy hits a wall.
     Only change the enemy's direction if the present direction 
     (dx/dy) is heading over the edge.
  */
  {
    if ((locx <= 0) && (dx < 0)) {  // touching lhs and moving left
      dx = -dx;   // move right
    }
    else if ((locx+getWidth() >= getPWidth()) && (dx > 0)) {   
		                           // touching rhs and moving right
      dx = -dx;   // move left
    }
    if ((locy <= 0) && (dy < 0)) {  // touching top and moving up
        dy = -dy;   // move down
      }
      else if ((locy+getHeight() >= getPHeight()) && (dy > 0)) {   
  		                           // touching bottom and moving down
        dy = -dy;   // move up
      }
    turn();
  } // end of hasHitWall()

  private void turn()
  //turn enemy in the direction of movement
  {
	  double rotationAngle = Math.toDegrees(Math.atan2((double)getYStep(),(double)getXStep()));
	  setAngle(rotationAngle);
  }
}  // end of PEnemySprite class
