/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.GameObjects;

import Bomberman.Map.Box;
import Bomberman.Map.Map;
import Bomberman.Display.Display;
import Bomberman.Game.ConfigReader;
import Bomberman.Interfaces.Destroyable;
import Bomberman.Interfaces.Explodable;
import Bomberman.Interfaces.Killable;
import Bomberman.Map.Exit;
import Bomberman.Map.Tile;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Dominik
 */
public class ObjectController {
    
    private ArrayList<Killable> enemies;
    private ArrayList<Destroyable> boxes;
    private ArrayList<Explodable> bombs;
    private Player player;
    private boolean playerAlive;
    private final Map map;
    private final int displayWidth;
    private final int displayHeight;
    private Tile exit;
    
    private final ConfigReader config;
    
    public ObjectController(Map map, int width, int height) throws FileNotFoundException{
        this.map = map;
        this.enemies = new ArrayList<Killable>();
        this.boxes = new ArrayList<Destroyable>();
        this.bombs = new ArrayList<Explodable>();
        this.displayWidth = width;
        this.displayHeight = height;  
        
        this.config= new ConfigReader();
        
    }
    
    public void addEnemy(Enemy e){
        this.enemies.add(e);
    }
    
    public void startInput(Display display){
        display.onKeyPress(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();

                if (code.equals(config.up())) {
                    player.goUp(displayWidth, displayHeight, map);
                }
                if(code.equals(config.down())){
                    player.goDown(displayWidth, displayHeight, map);
                }
                if(code.equals(config.right())){
                    player.goRight(displayWidth, displayHeight, map);
                }
                if(code.equals(config.left())){
                    player.goLeft(displayWidth, displayHeight, map);
                }
                if(code.equals("F")){
                    placeBomb();
                }
            }
        });
    }
    
    private void placeBomb(){
        if(this.bombs.size() + 1 < 3){
            int bombX = ( (this.player.getX() / 80) * 80) + 40;
            int bombY = ( (this.player.getY() / 80) * 80) + 40;

            this.bombs.add(new Bomb(bombX, bombY));
        }
    }
    
    public void placeBoxes(int n){
        Random rand = new Random();
        int bX;
        int bY;
        
        for(int i = 0; i < n; i++){
            bX = rand.nextInt(this.map.getCols() - 1) + 1;
            bY = rand.nextInt(this.map.getRows() - 1) + 1;
            while(!this.map.addBox(bX, bY)){
               bX = rand.nextInt(this.map.getCols() - 1) + 1;
               bY = rand.nextInt(this.map.getRows() - 1) + 1;
            }
            this.boxes.add(new Box(bX, bY));
        }
        
    }
    
    public void placeEnemies(int n){
        Random rand = new Random();
        int eX;
        int eY;
        
        for(int i = 0; i < n; i++){
            eX = (40 + (rand.nextInt(this.map.getCols() - 3) + 1) * 80 );
            eY = (40 + (rand.nextInt(this.map.getRows() - 3) + 1) * 80 );
            
            while(this.map.checkMatrix( (eY - 40) / 80, (eX - 40) / 80) != 0){
                eX = (40 + (rand.nextInt(this.map.getCols() - 3) + 2) * 80 );
                eY = (40 + (rand.nextInt(this.map.getRows() - 3) + 2) * 80 );
            }
            this.enemies.add(new Enemy(eX, eY));
        }
    }
    
    public void placePlayer(int x, int y){
        this.player = new Player(x,y);
        this.playerAlive = true;
    }
    
    public boolean isPlayerAlive(){
        return this.playerAlive;
        /*
        Iterator iterator = this.enemies.iterator();
        Enemy enemy;
        while(iterator.hasNext()){
            enemy = (Enemy)iterator.next();
            if(this.player.isHitByEnemy(enemy)){
                return false;
            }
        }
        return true;
        */
    }
    
    public void checkExplosions(){
        if(!this.bombs.isEmpty()){
            Iterator bombIterator = this.bombs.iterator();
            Iterator boxIterator;
            Iterator enemyIterator;
            Explodable bomb;
            
            while(bombIterator.hasNext()){
                bomb = (Explodable)bombIterator.next();
                if(bomb.exploded()){
                    if(this.player.isHit(bomb)){
                        this.playerAlive = false;
                    }
                    boxIterator = this.boxes.iterator();
                    enemyIterator = this.enemies.iterator();
                    Box box;
                    Killable enemy;
                    
                    while(enemyIterator.hasNext()){
                        enemy = (Killable)enemyIterator.next();
                        if(enemy.isHit(bomb)){
                            enemyIterator.remove();
                            this.enemies.remove(enemy);
                        }
                    }
                    
                    while(boxIterator.hasNext()){
                        box = (Box)boxIterator.next();
                        if(box.isHit(bomb)){
                            //System.out.println("Box Exploded");
                            this.map.removeBox(box.getX(), box.getY());
                            if(this.boxes.size() == 1){
                                this.exit = new Exit(box.getX(), box.getY());
                            }
                            boxIterator.remove();
                            this.boxes.remove(box);
                        }
                    }
                    
                    
                    this.bombs.remove(bomb);
                }
                if(this.bombs.isEmpty())
                        return;
            }
        }
    }
    
    public void moveEnemies(){
            Iterator iterator = this.enemies.iterator();
            Enemy e;
            while(iterator.hasNext()){
                e = (Enemy)iterator.next();
                e.moveEnemy(this.displayWidth, this.displayHeight, this.map);
                if(this.player.isHitByEnemy(e)){
                    this.playerAlive = false;
                }
            }
    }
    
    public void tickBombs(){
        if(!this.bombs.isEmpty()){
            Iterator iterator = this.bombs.iterator();
            Explodable bomb;
            while(iterator.hasNext()){
                bomb = (Explodable)iterator.next();
                bomb.tick();
            }
        }
    }
    
    public void drawBoxes(GraphicsContext gc){
        Iterator iterator = this.boxes.iterator();
        Box box;
        while(iterator.hasNext()){
            box = (Box)iterator.next();
            box.draw(gc);
        }
    }
    
    public void drawEnemies(GraphicsContext gc){
        Iterator iterator = this.enemies.iterator();
        Enemy enemy;
        
        while(iterator.hasNext()){
            enemy = (Enemy)iterator.next();
            enemy.draw(gc);
        }
    }
    
    public void drawBombs(GraphicsContext gc){
        if(!this.bombs.isEmpty()){
            Iterator iterator = bombs.iterator();
            Bomb bomb;
            while(iterator.hasNext()){
                bomb = (Bomb)iterator.next();
                bomb.draw(gc);
            }
        }
    }
    
    public void drawPlayer(GraphicsContext gc){
        this.player.draw(gc);
    }
    
    public void clearObjects(){
        this.enemies.clear();
        this.bombs.clear();
        this.boxes.clear();
        this.map.clearMap();
    }
    
    public void drawExit(GraphicsContext gc){
        if(this.boxes.isEmpty()){
            this.exit.draw(gc);
        }
        else
            return;
    }
    
    public boolean playerExit(){
        if(this.exit != null && this.player.getX()/80 == this.exit.getX() && this.player.getY()/80 == this.exit.getY()){
            return true;
        }
        else
            return false;
    }    
    
}
