/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.GUI;

/**
 *
 * @author PC NET
 */
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author PC NET
 */
public class LoadScene { 
   private Scene scene; 
   private static Stage stage; 
   public LoadScene(Scene scene) {
       this.scene = scene;
   }
   public void DisplayScene (String style) {
       scene.getStylesheets().add(getClass().getResource(style).toExternalForm()); 
        stage.setScene(scene);
        stage.show();
   }
   public static void SetStage(Stage st) {
       stage = st;  
   }
   public static void HideStage() {
       stage.hide();
   }
   public static Stage getStage () {
       return stage; 
   }
}
