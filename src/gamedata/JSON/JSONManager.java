package gamedata.JSON;

import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.rules.Rule;
import gamedata.wrappers.LevelData;
import gamedata.wrappers.PlayerData;
import gamedata.wrappers.RuleData;
import gameengine.player.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Creates JSON file from Authoring Environment data. Loads and parses JSON file
 * to create a Game for Game Engine/Game Player.
 *
 * @author annamiyajima, Rica Zhang
 */
public class JSONManager {
    final static String DEFAULT_JSON_DIRECTORY = "./src/resources/";
    final static String SAMPLE_JSON = "./src/resources/test.json";

    /**
     * Constructor
     */
    public JSONManager () {

    }

    /**
     * Write a game and its contents into a JSON file.
     * 
     * @param game
     */
    public void writeToJSON (Game g, String fileName) {
        Gson gson = new Gson();
        String json = gson.toJson(g);
        System.out.println("JSONManager: game converted to json!");
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a filePath, read in a JSON file and construct a game with that data
     * 
     * @param JSON file location
     * @throws FileNotFoundException
     */
    public Game readFromJSONFile (String jsonFileLocation) throws FileNotFoundException {
        System.out.println("JSONManager: read method called");
        // Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Rule.class, new AbstractDeserializer());
        Gson gson = builder.create();

        BufferedReader br = new BufferedReader(new FileReader(jsonFileLocation));

        RuleData r = gson.fromJson(br, RuleData.class);
        System.out.println(r.toString());

        PlayerData myPlayers = gson.fromJson(br, PlayerData.class);
        System.out.println(myPlayers.getPlayers().get(0).getID());
        System.out.println(myPlayers.getPlayers().get(1).getID());

        RuleData myRules = gson.fromJson(br, RuleData.class);
        System.out.println(myRules.getRules().get(0).toString());

        Player player = gson.fromJson(br, Player.class);
        System.out.println(player.toString());

        LevelData myLevels = gson.fromJson(br, LevelData.class);
        System.out.println(myLevels.getLevels().size());

        return null;
    }

}