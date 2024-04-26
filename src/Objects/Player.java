package Objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {

    //Varibalen des Players
    private double x;
    private double y;
    private final double witdh;
    private final double height;
    private final double speed;

    private boolean hasMoveUp;
    private boolean hasMoveDown;

    //Player wird als Rect gezeichnet
    private Rectangle2D.Double playerRect;
    int points;


public Player(double x , double y , double witdh , double height , int points){
    this.x = x;
    this.y = y;
    this.witdh = witdh;
    this.height = height;
    speed = 10.0;
    playerRect = (Rectangle2D.Double) makeShape();
    this.points = points;
}

//Info : rect wird in der ganzen Klasse als rect2D gecastet

    //erstellt und gibt das rect2D zurück
private Rectangle2D makeShape(){

return new Rectangle2D.Double(x , y , witdh , height);
}
public void tick(){
    playerRect = (Rectangle2D.Double) makeShape();

}
public void render(Graphics2D g){
    g.setColor(Color.green);
    g.fillRect((int) x , (int) y , (int) witdh , (int) height);

    g.setColor(Color.white);
    g.setStroke(new BasicStroke(2F));
    g.drawRect((int) x , (int) y , (int) witdh , (int) height);

}
//Methoden für die movement Logik
public void moveUp() {
    if (hasMoveUp && y > 0) {
        y -= speed;
    }
}
    public void moveDown(){
        if (hasMoveDown && y < 480){
            y += speed;
        }
    }

public void setHasMoveUp(boolean hasMoveUp){
    this.hasMoveUp = hasMoveUp;
}
public void setHasMoveDown(boolean hasMoveDown){
    this.hasMoveDown = hasMoveDown;
}
public Rectangle2D.Double getShape(){
    return playerRect;
}

//Gibt die Points des Players zurück
public int getPoints(){
    return points;
}

    //der Score des Player wird um 1 erhöt
    public void incrementScore() {
    points++;
}
}




