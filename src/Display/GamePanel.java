package Display;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

 // GameFrame Objekt wird dem Panel als parameter übergeben
    private final GameFrame gameFrame;

 public GamePanel(GameFrame gameFrame){

  this.gameFrame = gameFrame;

  //gameframe fügt das panel hinzu
  gameFrame.add(this);
 }

 //Paint Methode zum rendern
 public void paint(Graphics g){

  Graphics2D g2 = (Graphics2D) g;
  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
  g2.setRenderingHint(RenderingHints.KEY_RENDERING , RenderingHints.VALUE_RENDER_QUALITY);

  gameFrame.render(g2);
 }






}
