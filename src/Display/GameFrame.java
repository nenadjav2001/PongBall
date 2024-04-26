package Display;

import Objects.Ball;
import Objects.Computer;
import Objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame implements KeyListener {

    //Konstanten
    public static final int SCREEN_WITDH = 800;
    public static final int SCREEN_HEIGHT = 600;

    //Klassen-Objekte
    private GamePanel gamePanel;
    private Player player;
    private Ball ball;
    private Computer computer;

    //Variablen
    private boolean playerHasMoved;
    private int scorePlayer;
    private int scoreComputer;
    boolean resetBall;



    public GameFrame(){

        super("Pong");
        setSize(SCREEN_WITDH , SCREEN_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Klassen-Objekte werden erstellt
        gamePanel = new GamePanel(this);
        player = new Player(10.0 , 300.00 , 20.00 , 80.00 , 0);
        ball = new Ball(400.0 , 300.0 , 25.0 , 25.0);
        computer = new Computer(755.0 , 300 , 20.0 , 80.0 ,ball , 0);

        //Punkte für Spieler und Computer
        scoreComputer = 0;
        scorePlayer = 0;

        //KeyListener wird hinzugefügt
        addKeyListener(this);

        playerHasMoved = false;

        setVisible(true);

        //GameLoop wird hier gestartet
        startLoop();
    }

    //Die Punkte und etc werden gerendert. Das Panel wird als fillRect auf die GUI gezeichnet
    public void render(Graphics2D g2){

        g2.setColor(Color.BLACK);
        g2.fillRect( 0 , 0  , gamePanel.getWidth() , gamePanel.getHeight());

        player.render(g2);
        ball.render(g2);
        computer.render(g2);

        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Arial" , Font.BOLD , 30));
        g2.drawString("Player : " + Integer.toString(player.getPoints()) , 150 , 50);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial" , Font.BOLD , 30));
        g2.drawString("Computer : " + Integer.toString(computer.getComputerPoints()) , 500 , 50);
    }

    //Die GameLoop wird hier erstellt und enthält wichtige methoden für das Spiel
    private void startLoop(){
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()-> {

            tick();
            gamePanel.repaint();
            player.moveUp();
            player.moveDown();
            ball.setRandomXDirecttion();
            ball.setRandomYDirection();
            ball.collisionWithWall();
            checkWin();


         } , 0L , 1000 / 60 , TimeUnit.MILLISECONDS);
    }

    //hier werden die ganzen Spieler getickt
    public void tick(){

       player.tick();
       ball.tick();
       computer.tick();
       collisions();
       computer.chaseBall();

    }
    //In der collisions Methode wird die Kollision zwischen allen objekten überprüft , die punkte erhört wenn bedinung eintrifft
    //Ball wird neu gesetz
    public void collisions(){

        if (ball.getBall().intersects(player.getShape())){
            ball.changeXDirecction();
        } else if (ball.getBall().intersects(computer.getComputerRect())){
            ball.changeXDirecction();
        }
        if (ball.ballPassedFramePlayerSide()){
               computer.incrementScore();

                ball.setX(400);
                ball.setY(300);
                ball.changeXDirecction();
                ball.cangeYDirection();
        }
          if(ball.ballPassedFrameComputerSide()){
            player.incrementScore();
            ball.setX(400);
            ball.setY(300);
            ball.changeXDirecction();
            ball.cangeYDirection();
        }
    }
    //Wer zuerst 5 Punkte hat gewinnt das Spiel und die GUI wird geschlossen
    public void checkWin(){

        int computerPoints = computer.getComputerPoints();
        int playerPoints = player.getPoints();

        if (computerPoints == 5){
            JOptionPane.showMessageDialog(null , "Computer hat gewonnen" , "Win" ,JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (playerPoints == 5){
            JOptionPane.showMessageDialog(null , "Player hat gewonnen" , "Win" ,JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
     //Dont need
    }

    //Setzt beim drücken dieser tasten den jeweiligen boolean auf true damit der Spieler sich bewegen kann
    @Override
    public void keyPressed(KeyEvent e) {

        int value = e.getKeyCode();
        switch (value){
            case KeyEvent.VK_W ->  {
                player.setHasMoveUp(true);
            }
            case KeyEvent.VK_S -> {
                player.setHasMoveDown(true);
            }
        }
    }
    //Setzt alles auf false da sich der Player durchgehen bewegt
    @Override
    public void keyReleased(KeyEvent e) {
           player.setHasMoveDown(false);
           player.setHasMoveUp(false);
    }

//Main Methode mit einem gameFrame objekt damit die GUI sich öffnet
public static void main (String [] args){
    GameFrame gameFrame = new GameFrame();
}



}
