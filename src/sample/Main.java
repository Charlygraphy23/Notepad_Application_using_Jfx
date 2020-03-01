package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/notepad_view.fxml"));
        primaryStage.setTitle("Notepad Application");
        primaryStage.setScene(new Scene(root));
        try {
            Image icon=new Image("file:///C:/Users/Person/Downloads/n.jpg");
            primaryStage.getIcons().add(icon);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
