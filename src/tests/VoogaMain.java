package tests;

import gamedata.JSONManager;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring.concretefeatures.menus.JSONBob;

/**
 * Used to test JSON reader in back-end
 * 
 * @author annamiyajima, Rica Zhang
 *
 */
public class VoogaMain {
    public static void testJSONwrite() {
        String saveTo = "src/resources/json/FINALSAMPLE.json";
        JSONManager myJSONmanager = new JSONManager();
        JSONBobTester jb = new JSONBobTester();
        myJSONmanager.writeToJSON(jb.createNewGame(), saveTo);
    }
    
    public static void testJSONload() {
        JSONManager jsonManager = new JSONManager();
        try {
            jsonManager.readFromJSONFile("C:\\Users\\Rica\\Desktop\\GAME.json");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {
        System.out.println("main is running");
        //testJSONload();
        testJSONwrite();
    }
}