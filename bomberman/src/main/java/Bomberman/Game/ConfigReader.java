/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author endry
 */
public class ConfigReader {
    
    private BufferedReader reader;
    private Map configMap;

    public ConfigReader() throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader("config.txt"));
        configMap = new HashMap<String,Character>();
        
        try {
            this.setControls();
        } catch (IOException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void setControls() throws IOException{
        String line;
        char[] values = new char[7];
        int i = 0;
        while((line = reader.readLine()) != null){
            int index = line.indexOf('=');
            values[i] = line.charAt(index + 1);
            i++;
        }
        
        configMap.put("up", values[0]);
        configMap.put("down", values[1]);
        configMap.put("left", values[2]);
        configMap.put("right", values[3]);
        configMap.put("bomb", values[4]);
        /*
        configMap.put("nEnemies", values[5]);
        configMap.put("nBoxes", values[6]);
        */
    }
    
    public String up(){
        return configMap.get("up").toString().toUpperCase();
    }
    
    public String down(){
        return configMap.get("down").toString().toUpperCase();
    }
    
    public String left(){
        return configMap.get("left").toString().toUpperCase();
    }
    
    public String right(){
        return configMap.get("right").toString().toUpperCase();
    }
    
    public char bomb(){
        return (char)configMap.get("bomb");
    }
    
    public char nEnemies(){
        return (char)configMap.get("nEnemies");
    }
    
    public char nBoxes(){
        return (char)configMap.get("nBoxes");
    }
    
}
