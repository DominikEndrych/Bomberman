
package Bomberman.Game;

import Bomberman.Display.Display;
import Bomberman.GameObjects.ObjectController;
import Bomberman.Map.Map;
import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;


public class Game{
    
    private final Display display;
    private boolean isRunning;
    private boolean isFinished;
    private final  Map map;
    
    private final int defaultPlayerX;
    private final int defaultPlayerY;
    
    private final int nEnemies;
    
    private final ObjectController objectController;
    
    public Game(Display display, int nEnemies) throws FileNotFoundException{
        this.display = display;
        this.map = new Map(this.display.getCanvas().getGraphicsContext2D());
        this.defaultPlayerX = 40;
        this.defaultPlayerY = 40;
        
        this.objectController = new ObjectController(this.map, this.display.getWidth(), this.display.getHeight()); 
        
        this.nEnemies = nEnemies;
    }
    
    private void init(){
	display.Launch(display.getStage());
        this.objectController.placeBoxes(10);
        this.objectController.placeEnemies(nEnemies);
        this.objectController.placePlayer(this.defaultPlayerX, this.defaultPlayerY);
        this.objectController.startInput(this.display);
        System.out.println("The game is running...");
        
              
    }
    
    
    private void tick(){
        
        this.objectController.tickBombs();
        this.objectController.checkExplosions();
        this.objectController.moveEnemies();
        if(!this.objectController.isPlayerAlive()){
            this.stopGame();
        }
        else if(this.objectController.playerExit()){
            this.isFinished = true;
            this.stopGame();
        }
        
    }
    
    private void render(){
        this.map.draw(this.display.getCanvas().getGraphicsContext2D());
        this.objectController.drawPlayer(this.display.getCanvas().getGraphicsContext2D());
        this.objectController.drawBoxes(this.display.getCanvas().getGraphicsContext2D());
        this.objectController.drawEnemies(this.display.getCanvas().getGraphicsContext2D());
        this.objectController.drawBombs(this.display.getCanvas().getGraphicsContext2D());
        this.objectController.drawExit(this.display.getCanvas().getGraphicsContext2D());
    }
    
    
    private void run(){
        
        this.init();
        
        if(isRunning){
            AnimationTimer timer  = new AnimationTimer(){
                @Override
                public void handle(long now){
                    if(isRunning){
                        tick();
                        render();
                    }
                    
                    else{
                        if(isFinished){
                            display.Close(display.getStage());
                            System.out.println("The game is finished!");
                            this.stop();
                        }
                        else{
                            System.out.println("Game Over");
                            System.out.println("Restarting...");
                            this.stop();
                            restartGame();
                        }
                        
                    }
                    
                }
            };
            timer.start();
        }
    }
    
    public void startGame() {
        this.isRunning = true;
        this.isFinished = false;
        this.run();
    }
    
    public void stopGame(){
        this.isRunning = false;
    }
    
    public void restartGame(){
        this.objectController.clearObjects();
        this.startGame();
    }
    
    
}
