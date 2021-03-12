
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CodespaceRunner extends JFrame implements WindowListener
{
  private static int DEFAULT_FPS = 40; 

  private CodespacePanel bp;        // where the game is drawn

  public CodespaceRunner(long period)
  { super("CodespaceRunner");

    Container c = getContentPane();    // default BorderLayout used
    bp = new CodespacePanel(this, period);
    c.add(bp, "Center");
    
    addWindowListener( this );
    pack();
    setResizable(false);
    setVisible(true);
  }  // end of CodespaceRunner() constructor


  // ----------------- window listener methods -------------

  public void windowActivated(WindowEvent e) 
  { bp.resumeGame();  }

  public void windowDeactivated(WindowEvent e) 
  { bp.pauseGame();  }


  public void windowDeiconified(WindowEvent e) 
  {  bp.resumeGame();  }

  public void windowIconified(WindowEvent e) 
  {  bp.pauseGame(); }


  public void windowClosing(WindowEvent e)
  {  bp.stopGame();  }


  public void windowClosed(WindowEvent e) {}
  public void windowOpened(WindowEvent e) {}

  // ----------------------------------------------------

  public static void main(String args[])
  { 
    long period = (long) 1000.0/DEFAULT_FPS;
    // System.out.println("fps: " + DEFAULT_FPS + "; period: " + period + " ms");
    new CodespaceRunner(period*1000000L);    // ms --> nanosecs 
  }

} // end of CodespaceRunner class


