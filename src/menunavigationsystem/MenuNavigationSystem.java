/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menunavigationsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alex
 */
public class MenuNavigationSystem extends Application {
    public static String[] menu;
    //public static String[] menu2 = new String[]{"Ribeye Steak","20-oz juicy steak marinated in special sauce.","$29.99","ribeye.png","Spaghetti","A plate of delicious Spaghetti with meat sauce.","$25.99","spaghetti.png"};
    public static int i=0;
    @Override
    public void start(Stage primaryStage) {
        List<String> lines = new ArrayList<String>();
        
        //try {
            Scanner scnr = new Scanner(getClass().getResourceAsStream("config.txt"));
            //String line = null;
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                String result = line.substring(line.indexOf(":") + 1);
                String result2 = result.trim();
                
                lines.add(result2);
                
            }
        //}
        String[] menu = lines.toArray(new String[0]);
        
        primaryStage.setTitle("SmartRestaurant Menu");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Text scenetitle = new Text(menu[i]);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 1, 0, 2, 1);
            
        Label description = new Label(menu[i+1]);
        
        description.setPrefWidth(200);
        description.setWrapText(true);
        grid.add(description, 1, 1,2,1);
        
        
        Label price = new Label("Price:");
        grid.add(price, 1, 2);
        
        Label pricetag = new Label(menu[i+2]);
        grid.add(pricetag, 2, 2);
        
        final Image image = new Image(MenuNavigationSystem.class.getResourceAsStream(menu[i+3]));
        final ImageView imgView = new ImageView();
        imgView.setFitHeight(300);
        imgView.setPreserveRatio(true);
        imgView.setImage(image);
        
        Button btn = new Button("Next >");
        HBox hbBtn = new HBox(3);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 3);
            
        final Text actiontarget = new Text();
        grid.add(actiontarget, 2, 4);
            
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(i+4== menu.length){
                    i=0;
                    scenetitle.setText(menu[i]);
                    description.setText(menu[i+1]);
                    pricetag.setText(menu[i+2]);
                    imgView.setImage(new Image(MenuNavigationSystem.class.getResourceAsStream(menu[i+3])));
                }
                else{
                    i=i+4;
                    scenetitle.setText(menu[i]);
                    description.setText(menu[i+1]);
                    pricetag.setText(menu[i+2]);
                    imgView.setImage(new Image(MenuNavigationSystem.class.getResourceAsStream(menu[i+3])));
                }
                actiontarget.setFill(Color.FIREBRICK);
                
            }
        });
        
        Button btn2 = new Button("< Prev");
        HBox hbBtn2 = new HBox(3);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 3);
            
        final Text actiontarget2 = new Text();
        grid.add(actiontarget2, 0, 4);
            
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent b) {
                if (i==0){
                    i=(menu.length-4);
                    scenetitle.setText(menu[i]);
                    description.setText(menu[i+1]);
                    pricetag.setText(menu[i+2]);
                    imgView.setImage(new Image(MenuNavigationSystem.class.getResourceAsStream(menu[i+3])));
                }
                else{
                    i=i-4;
                    scenetitle.setText(menu[i]);
                    description.setText(menu[i+1]);
                    pricetag.setText(menu[i+2]);
                    imgView.setImage(new Image(MenuNavigationSystem.class.getResourceAsStream(menu[i+3])));
                }
                actiontarget2.setFill(Color.FIREBRICK);
                
            }
        });
        
        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imgView);
        grid.add(pictureRegion, 0, 1);
        //grid.setGridLinesVisible(true);
        
        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
