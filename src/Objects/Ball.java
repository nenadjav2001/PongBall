package Objects;

import Display.GameFrame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Ball {

    //Ball variablen
private double x;
private double y;
private double witdh;
private double height;
private int speed;
private int xStep;
private int yStep;

//Ball wird als rects gezeichnet um die intersects Methode benutzen zu können
private Rectangle2D.Double ball;
private Random random = new Random();

    //Random variablen für die richtung des balls
    boolean randomXDirecttion = random.nextBoolean();
    boolean randomYDirection = random.nextBoolean();

    //In der Klasse wird das rect ebenfalls in ein rect2D gecastet

public Ball(double x , double y , double witdh , double height){
    this.x = x;
    this.y = y;
    this.witdh = witdh;
    this.height = height;
    ball = (Rectangle2D.Double) makeShape();
    speed = 5;
    xStep = 5;
    yStep = 5;


}
    //erstellt und gibt das rect2D zurück
    private Rectangle2D makeShape() {
    return new Rectangle2D.Double(x , y , witdh , height);
    }
    public void render(Graphics2D g){

    g.setColor(Color.GREEN);
    g.fillOval((int) x , (int) y , (int) witdh , (int)  height);

    g.setColor(Color.white);
    g.setStroke(new BasicStroke(2F));
    g.drawOval((int) x , (int) y , (int) witdh , (int)  height);

   }

   //Methoden für  die zufällige richtunges des balls (x , y)
   public void setRandomXDirecttion(){
    if (randomXDirecttion){
        x += xStep;
    } else {
        x -= xStep;
    }
   }
   public void setRandomYDirection(){
    if (randomYDirection){
        y += yStep;
    } else {
        y -= yStep;
    }
   }
   //Das Objekt ball wird auf die Methode make shape gesetz und voher wird es gecastet
    public void tick(){
        ball = (Rectangle2D.Double) makeShape();
    }
    public Rectangle2D.Double getBall(){
    return ball;
    }
    //Methoden für das Abrallen des balls
    public void changeXDirecction(){
         xStep = - xStep;
    }
    public void cangeYDirection(){
      yStep = - yStep;
    }

    public void collisionWithWall() {
        if (y > GameFrame.SCREEN_HEIGHT - 60) {
            cangeYDirection();
        } else if (y < 0) {
            cangeYDirection();
        }

    }
    //Methoden um festzustellen wer einen Punkt gemacht hat
    public boolean ballPassedFramePlayerSide(){
      if (x < 0){
          return true;
      }
      return false;
    }
    public boolean ballPassedFrameComputerSide(){
      if (x > 800){
          return true;
      }
      return false;
    }
    //Getter und Setter für (x , y )

    public int getX(){
    return (int) x;
    }
    public int getY(){
    return (int) y;
    }
    public void setX(int x){
     this.x = x;
    }
    public void setY(int y){
    this.y = y;
    }

    }




