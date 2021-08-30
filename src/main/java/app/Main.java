package app;


import app.services.CreaterService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Properties;

public class Main extends Application {

 public static   Properties context=  new Properties();


    private static void initDB() {

        String dbUrl ="jdbc:derby:database/thisdbx;create=true";
        String driver= "org.apache.derby.jdbc.ClientDriver";
     context.put("dbUrl",dbUrl);
     context.put("driver",driver);
        CreaterService createrService = new CreaterService();
        createrService.createEDU();
        createrService.createWXP();
        createrService.createUserDATA();
        createrService.createAndinitDepapartment();
        createrService.createAndinitPostions();


        /* DELETE IN E*/
       //    UserService service = new UserService();
      //    service.save(new User("hoparito","hoparinskiy","duda", "duka"));
     //   service.save(new User("hoparito1","hoparinskiy1","duda1", "duka1"));
    //   service.save(new User("hoparito2","hoparinskiy2","duda2", "duka2"));



    }






    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ListFromView.fxml"));
        primaryStage.setTitle("Simple Employee Catalog");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        initDB();
        launch(args);

    }


}