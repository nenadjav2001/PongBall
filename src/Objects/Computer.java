package Objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Computer {

     //Computer variablen
    private double x;
    private double y;
    private final double witdh;
    private final double height;
    private final double speed;
    private int computerStepY;

    private Rectangle2D.Double computerRect;
    private Ball ball;
    int computerPoints;


    public Computer(double x , double y , double witdh , double height , Ball ball , int computerPoints){
        this.x = x;
        this.y = y;
        this.witdh = witdh;
        this.height = height;
        speed = 10.0;
        computerRect = (Rectangle2D.Double) makeShape();
        this.ball = ball;
        computerStepY = 10;
        this.computerPoints = computerPoints;
    }

    //erstellt und gibt das rect2D zurück
    private Rectangle2D makeShape(){

        return new Rectangle2D.Double(x , y , witdh , height);
    }
    public void tick(){
        computerRect = (Rectangle2D.Double) makeShape();
    }
    //Hier wird der Computer gezeichnet
    public void render(Graphics2D g){
        g.setColor(Color.white);
        g.fillRect((int) x , (int) y , (int) witdh , (int) height);

        g.setColor(Color.cyan);
        g.setStroke(new BasicStroke(2F));
        g.drawRect((int) x , (int) y , (int) witdh , (int) height);

    }
    //Mehtode für das verfolgen des balls vom Computer
    public void chaseBall(){
     if (ball.getX() > 400 && ball.getY() > y && y < 480){
         y += computerStepY;
     } else if(ball.getX() > 400 && ball.getY() < y && y > 0){
         y -= computerStepY;
     }
    }
    //Gibt das objekt in seiner shape zurück
    public Rectangle2D.Double getComputerRect(){
        return computerRect;
    }


//Getter für die Punkte des Computers und die Methode incrementScore die die Punkte des Computers um 1 erhöhen
 public int getComputerPoints(){
        return computerPoints;
 }
 public void incrementScore(){
        computerPoints++;
 }






}
