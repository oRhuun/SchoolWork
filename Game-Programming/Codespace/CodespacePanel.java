
// CodespacePanel.java
import java.awt.GraphicsConfiguration;
import java.awt.ImageCapabilities;
import java.awt.Window;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CodespacePanel extends JPanel implements Runnable
{
  private int Pwidth = 1200;  // size of panel
  private int Pheight = 800; 	
  
  private static final int NO_DELAYS_PER_YIELD = 16;
  /* Number of frames with a delay of 0 ms before the animation thread yields
     to other running threads. */
  private static int MAX_FRAME_SKIPS = 5;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

  // image and clip loader information files
  private static final String IMS_INFO = "imsInfo.txt";
  private static final String SNDS_FILE = "clipsInfo.txt";

  private Thread animator;           // the thread that performs the animation
  private volatile boolean running = false;   // used to stop the animation thread
  private volatile boolean isPaused = false;

  private long period;                // period between drawing in _nanosecs_

  private CodespaceRunner codeTop;
  private ClipsLoader clipsLoader;
  
  public ClipsLoader[] shootSound = new ClipsLoader[20];

  private VEnemySprite ve1;        // the sprites
  private VEnemySprite ve2;
  private VEnemySprite ve3;
  private PEnemySprite pe1;
  private PEnemySprite pe2;
  private PEnemySprite pe3;
  private ShipSprite ship;
  
  public Projectile[] projs = new Projectile[30];
  private int cycle; //for tracking multiple bullets
  private int soundCycle; //for tracking multiple bullets sounds
  
  private long gameStartTime;   // when the game started
  private long finalTime;
  private int timeSpentInGame;
  private long timeSpentPaused;
  private long startPauseTime;
  private long endPauseTime;

  // used at game termination
  private volatile boolean gameOver = false;
  private int score = 0;

  // for displaying messages
  private Font msgsFont;
  private Font msgsFontLarge;
  private FontMetrics metrics;

  // off-screen rendering
  private Graphics dbg; 
  private Image dbImage = null;

  // holds the background image
  private BufferedImage bgImage = null;

//to display the title/help screen
 private boolean showHelp = true;
 private BufferedImage helpIm = null;
 
 //to monitor health of player
 private int hitsTaken = 3;
 private BufferedImage healthIm = null;
 private static final String[] healthIms = 
	 {"health0", "health1", "health2", "health3"};
 
 private ImagesLoader imsLoader = new ImagesLoader(IMS_INFO); 
 
  public CodespacePanel(CodespaceRunner br, long period)
  {
    codeTop = br;
    this.period = period;
    
    setBackground(Color.white);
    setPreferredSize( new Dimension(Pwidth, Pheight));

    setFocusable(true);
    requestFocus();    // the JPanel now has focus, so receives key events

	addKeyListener( new KeyAdapter() {
       public void keyPressed(KeyEvent e)
       { processKeyPress(e);  }
     });
	
	addKeyListener( new KeyAdapter() {
	   public void keyReleased(KeyEvent e)
	   { processKeyRelease(e);  }
	});

    // load the background image 
    bgImage = imsLoader.getImage("back");

    // initialize the clips loader
    clipsLoader = new ClipsLoader(SNDS_FILE);
    
    ClipsLoader tempClip;
    for(int i = 0; i <= 19; i++)
    {
    	tempClip = new ClipsLoader(SNDS_FILE);
    	shootSound[i] = tempClip;
    }

    // create game sprites
    ship = new ShipSprite(Pwidth, Pheight, imsLoader, clipsLoader,
                                     (int)(period/1000000L) ); // in ms
    ve1 = new VEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship,
    								((int)(period/1000000L)), projs); 
    ve2 = new VEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship,
    								((int)(period/1000000L)), projs);
    ve3 = new VEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship,
    								((int)(period/1000000L)), projs);
    pe1 = new PEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship, 
    								((int)(period/1000000L)), projs ); // in ms 
    pe2 = new PEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship,
    								((int)(period/1000000L)), projs ); // in ms);
    pe3 = new PEnemySprite(Pwidth, Pheight, imsLoader, clipsLoader, this, ship,
    								((int)(period/1000000L)), projs ); // in ms);
    
    addMouseListener( new MouseAdapter() {
      public void mousePressed(MouseEvent e)
      { testPress(e.getX(),e.getY()); }  // handle mouse presses
    });

    // set up message font
    msgsFont = new Font("American Typewriter", Font.BOLD, 24);
    msgsFontLarge = new Font("American Typerwriter", Font.BOLD, 36);
    metrics = this.getFontMetrics(msgsFont);

    // prepare title/help screen
    helpIm = imsLoader.getImage("title");
    showHelp = true;    // show at start-up
    isPaused = true;
    
    healthIm = imsLoader.getImage("health0");
    hitsTaken = 0;
    
    cycle = 0;
    soundCycle = 0;
    for(int i = 0; i <= 29; i++) {
    	Projectile projTemp = new Projectile(Pwidth, Pheight, imsLoader, this, ship,
				(int)(period/1000000L)); //in ms
    	projs[i] = projTemp;
    }	//fill projectiles with blank projectiles
    	
    clipsLoader.play("background", true);
  }  // end of CodespacePanel()

  private void processKeyPress(KeyEvent e)
  // handles termination and game-play keys presses
  {
    int keyCode = e.getKeyCode();
    // termination keys
	// listen for esc, q, end, ctrl-c on the canvas to
	// allow a convenient exit from the full screen configuration
    if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
        (keyCode == KeyEvent.VK_END) ||
        ((keyCode == KeyEvent.VK_C) && e.isControlDown()) )
      running = false;
    
    // help controls
    if (keyCode == KeyEvent.VK_H || keyCode == KeyEvent.VK_P) {
      if (showHelp) {  // help being shown
        showHelp = false;  // switch off
        isPaused = false;
        endPauseTime = System.nanoTime();
        timeSpentPaused += (endPauseTime - startPauseTime); //to figure out how much time is spent paused
      }
      else {  // help not being shown
       showHelp = true;    // show it
       isPaused = true;    // isPaused may already be true
       startPauseTime = System.nanoTime();
      }
    }
    
    // game-play keys
    if (!isPaused && !gameOver) {
      if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
        ship.moveLeft(); 
      else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
        ship.moveRight();
      
      if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
        ship.moveDown();
      else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
    	ship.moveUp();
      
      if (keyCode == KeyEvent.VK_SHIFT) {
    	  clipsLoader.play("dash", false);
    	  //check for diag dash
    	  if (ship.getXStep() != 0 && ship.getYStep() != 0)
    		  ship.diagDash();
    	  else
    		  ship.dash();
    	  
      }
    }  // end of processKeyPress()
  }
  
  private void processKeyRelease(KeyEvent e)
  // handles game-play keys releases
  {
	int keyCode = e.getKeyCode();
	
	// game-play keys
	if (!isPaused && !gameOver) {
	  if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
	      ship.stopX(); 
	  else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
	      ship.stopX();
	  else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
	      ship.stopY();
	  else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
		  ship.stopY();
	    }
	  }  // end of processKeyRelease()
  
  public void hitShip(){
	  if(hitsTaken < 3)
		   hitsTaken++;
	  if(hitsTaken == 3){
		  gameOver();
	  }
  }
  
  public void gameOver()
  // called by hitShip (signalled by enemy) to signal that the game is over
  { 
    finalTime = System.nanoTime();  // ns --> secs
    //score = ;
    clipsLoader.play("gameOver", false);   // play a game over clip once
    gameOver = true;  
    
  } // end of gameOver()


  public void addNotify()
  // wait for the JPanel to be added to the JFrame before starting
  { super.addNotify();   // creates the peer
    startGame();         // start the thread
  }

  private void startGame()
  // initialize and start the thread 
  { 
    if (animator == null || !running) {
      animator = new Thread(this);
	  animator.start();
    }
  } // end of startGame()
    

  // ------------- game life cycle methods ------------
  // called by the JFrame's window listener methods


  public void resumeGame()
  // called when the JFrame is activated / deiconified
  {  if(!showHelp)
	  isPaused = false;  } 


  public void pauseGame()
  // called when the JFrame is deactivated / iconified
  { isPaused = true;   } 


  public void stopGame() 
  // called when the JFrame is closing
  {  running = false;   }

  // ----------------------------------------------


  private void testPress(int mouseX, int mouseY)
  // rotate the ship toward the mouse
  { if (!isPaused && !gameOver) {
      ship.turn(mouseX, mouseY);
  	  addProjectile();
  	  
  	  shootSound[soundCycle].play("shoot", false);
  	  soundCycle = (soundCycle+=1)%20;
  	}
  }  // end of testPress()
	
  protected void addProjectile() {
	  Projectile projTemp = new Projectile(Pwidth, Pheight, imsLoader, this, ship,
			  						(int)(period/1000000L)); //in ms
	  projs[cycle] = projTemp;
	  cycle = (cycle+=1)%30;
  }
  
  public void run()
  /* The frames of the animation are drawn inside the while loop. */
  {
    long beforeTime, afterTime, timeDiff, sleepTime;
    long overSleepTime = 0L;
    int noDelays = 0;
    long excess = 0L;

    gameStartTime = System.nanoTime();
    beforeTime = gameStartTime;
    startPauseTime = System.nanoTime();
	running = true;

	while(running) {
	  gameUpdate();
      gameRender();
      paintScreen();

      afterTime = System.nanoTime();
      timeDiff = afterTime - beforeTime;
      sleepTime = (period - timeDiff) - overSleepTime;  

      if (sleepTime > 0) {   // some time left in this cycle
        try {
          Thread.sleep(sleepTime/1000000L);  // nano -> ms
        }
        catch(InterruptedException ex){}
        overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
      }
      else {    // sleepTime <= 0; the frame took longer than the period
        excess -= sleepTime;  // store excess time value
        overSleepTime = 0L;

        if (++noDelays >= NO_DELAYS_PER_YIELD) {
          Thread.yield();   // give another thread a chance to run
          noDelays = 0;
        }
      }

      beforeTime = System.nanoTime();

      /* If frame animation is taking too long, update the game state
         without rendering it, to get the updates/sec nearer to
         the required FPS. */
      int skips = 0;
      while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
        excess -= period;
	    gameUpdate();    // update state but don't render
        skips++;
      }
	}
    System.exit(0);   // so window disappears
  } // end of run()


  private void gameUpdate() 
  { if (!isPaused && !gameOver) {
      ve1.updateSprite();
      ve2.updateSprite();
      ve3.updateSprite();
      pe1.updateSprite();
      pe2.updateSprite();
      pe3.updateSprite();
      ship.updateSprite();
      for(int i = 0; i <= 29; i++)
    	  projs[i].updateSprite();
    }
  }  // end of gameUpdate()


  private void gameRender()
  {
	
    if (dbImage == null){
      dbImage = createImage(Pwidth, Pheight);
      if (dbImage == null) {
        System.out.println("dbImage is null");
        return;
      }
      else
        dbg = dbImage.getGraphics();
    }

    // draw the background: use the image or a white color
    if (bgImage == null) {
      dbg.setColor(Color.white);
      dbg.fillRect (0, 0, Pwidth, Pheight);
    }
    else
      dbg.drawImage(bgImage, 0, 0, this);

    // draw game elements
    ve1.drawSprite(dbg, ve1.getAngle());
    ve2.drawSprite(dbg, ve2.getAngle());
    ve3.drawSprite(dbg, ve3.getAngle());
    pe1.drawSprite(dbg, pe1.getAngle());
    pe2.drawSprite(dbg, pe2.getAngle());
    pe3.drawSprite(dbg, pe3.getAngle());
    ship.drawSprite(dbg, ship.getAngle());
    for(int i = 0; i <= 29; i++)
    	projs[i].drawSprite(dbg, projs[i].getAngle());
    
    reportStats(dbg);

    if (gameOver)
      gameOverMessage(dbg);
    
    if (showHelp)    // draw the help at the very front (if switched on)
        dbg.drawImage(helpIm, (Pwidth-helpIm.getWidth())/2, 
                            (Pheight-helpIm.getHeight())/2, null);
    
    
    healthIm = imsLoader.getImage(healthIms[hitsTaken]);
	dbg.drawImage(healthIm, (Pwidth-healthIm.getWidth()), 
            (healthIm.getHeight()/8), null);
  }  // end of gameRender()

  public void addScore() //add score called by enemies getting hit
  {  score += 1;	}
  
  private void reportStats(Graphics g)
  // Report the number of returned balls, and time spent playing
  {
    if (!gameOver && !isPaused)    // stop incrementing the timer once the game is over
    	//pausing w/ time is hard
      timeSpentInGame = 
          (int) ((System.nanoTime() - gameStartTime - timeSpentPaused)/1000000000L);  // ns --> secs

	g.setColor(Color.black);
    g.setFont(msgsFont);

	g.drawString("Time: " + timeSpentInGame + " seconds", 15, 25);
   
	g.setColor(Color.black);
	g.drawString("Score: " + score, 15, 50);
  }  // end of reportStats()

   private void gameOverMessage(Graphics g)
  // center the game-over message in the panel
  {
	
	int scoreTime = 
       (int) ((finalTime - gameStartTime - timeSpentPaused)/1000000000L);  // ns --> secs
    String msgScore = "Game Over.\n Your score: " + score;
    String msgTime = "You survived for " + scoreTime + " seconds";
    
	int x = (Pwidth - metrics.stringWidth(msgScore))/2; 
	int y = (Pheight - metrics.getHeight())/2;
	g.setColor(Color.red);
    g.setFont(msgsFontLarge);
	g.drawString(msgScore, x, y);
	g.drawString(msgTime, x, y + 50);
  }  // end of gameOverMessage()


  private void paintScreen()
  // use active rendering to put the buffered image on-screen
  { 
	
    Graphics g;
    try {
      g = this.getGraphics();
      if ((g != null) && (dbImage != null))
        g.drawImage(dbImage, 0, 0, null);
      // Sync the display on some systems.
      // (on Linux, this fixes event queue problems)
      Toolkit.getDefaultToolkit().sync();

      g.dispose();
    }
    catch (Exception e)
    { System.out.println("Graphics context error: " + e);  }
    
  } // end of paintScreen()

  
}  // end of CodespacePanel class
