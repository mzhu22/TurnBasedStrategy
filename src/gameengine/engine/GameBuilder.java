package gameengine.engine;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


/**
 * Interprets and parses the JSON then builds the game components.
 * Calls factories to initialize each component.
 *
 * Creates a list of levels and a list of players to be used in initializing the game
 *
 * @author annamiyajima
 */
public class GameBuilder {
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources/";
    final static String SAMPLE_JSON = "./src/resources/test.json";

    /**
     * A game builder should be created for every time the entire program runs.
     * The same game builder should load information for the gamePlayer to play and then save
     * information for the authoring environment to modify
     */
    public GameBuilder () {
    }

    public String getFilePath () {
        return SAMPLE_JSON;
    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writeToJSON (Game g, String fileName) {
        Gson gson = new Gson();
        System.out.println("gson created");

        String json = gson.toJson(g);
        System.out.println("game converted to json");
        try {
            // write converted json data to a file named "CountryGSON.json"
            FileWriter writer = new FileWriter(DEFAULT_JSON_DIRECTORY + fileName + ".json");
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    /**
     * Given a filePath, read in a JSON file and construct a game with that data
     * 
     * @param filePath
     * @throws FileNotFoundException
     */
    public Game readFromJSONFile (String path) throws FileNotFoundException {
        System.out.println("read method called");
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(
                                               new FileReader(path));
        
        //LEVEL WORKS
        //Level l = gson.fromJson(br, Level.class);
        //System.out.println(l);
        
        Game g = gson.fromJson(br, Game.class);
        System.out.println(g);
        return g;
    }

}
