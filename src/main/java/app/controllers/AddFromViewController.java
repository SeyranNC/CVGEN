package app.controllers;


import app.Main;
import app.models.Education;
import app.models.User;
import app.models.Work_Exp;
import app.services.*;
import app.view_models.UserForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddFromViewController implements Initializable , Serializable {


    @FXML  public Pane rootPane;
    @FXML  public AnchorPane eduAnchorPane;
    @FXML  public Button addEduButton;
    @FXML  public Button addWorkButton;
    @FXML  public TextField fName;
    @FXML  public TextField lName;
    @FXML  public MenuButton selectDep;
    @FXML  public MenuButton selectPos;
    @FXML  public VBox eduvBOX;
    @FXML  public VBox workExpvBOX;
    @FXML  public AnchorPane workAnchorPane;

    int  id = Integer.parseInt(Main.context.getProperty("userEditID"));

    UserService service = new UserService();



   @FXML
    public void onClickSaveButton(MouseEvent mouseEvent) {

           User user = new User(id , fName.getText(), lName.getText(),selectDep.getText(), selectPos.getText());
           if(!service.save(user)) return;
           UserForm userForm =new UserForm(user.getId(),user.getFirstName()+" "+user.getLastName(), user.getDepartment(), user.getPosition());
           if(id>0) {
               int  list_id = Integer.parseInt(Main.context.getProperty("list_id"));
              UserFormService.list.set(list_id,userForm);
           } else {
                   UserFormService.list.add(userForm);
               }
       List<? extends Node>   elements= eduvBOX.getChildren();
        service.clean_old(id);
       for(Node el : elements){
           List<? extends Node>  nodes = ( (Pane) el).getChildren();
           TextField  name = (TextField) nodes.get(1);
           if(name.getText().trim().equals(""))  continue;
           DatePicker date= (DatePicker) nodes.get(2);
           CheckBox  checkBox = (CheckBox) nodes.get(3);
           TextField   cert_id= (TextField) nodes.get(4);
           Education education= new Education(name.getText(),date.getValue(),checkBox.isSelected(),cert_id.getText());
           new EducationServices().save( id , education);
           System.out.println(education);
       }
        elements= workExpvBOX.getChildren();
       for(Node el : elements){
           List<? extends Node>  nodes = ( (Pane) el).getChildren();
           TextField  name = (TextField) nodes.get(1);
           if(name.getText().trim().equals(""))  continue;
           DatePicker date_from= (DatePicker) nodes.get(2);
           DatePicker date_to= (DatePicker) nodes.get(3);
           Work_Exp work_exp= new Work_Exp(name.getText(),date_from.getValue(),date_to.getValue());
           new Work_Exp_Services().save(id, work_exp);
           System.out.println(work_exp);
       }
       Stage stage = (Stage) rootPane.getScene().getWindow();
       stage.close();



    }
    @FXML
    public void onClickCancelButton(MouseEvent mouseEvent) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onClickPrepareButton(MouseEvent mouseEvent) {

        Pane pane = new Pane();
        VBox  box = new VBox();
        CheckBox checkEdu = new CheckBox();
        checkEdu.setText(" Education  ");
        checkEdu.setSelected(true);
        CheckBox checkXP = new CheckBox();
        checkXP.setText("  Work Experience   ");
        checkXP.setSelected(true);
        box.getChildren().add( checkEdu);
        box.getChildren().add( checkXP);
        HBox hBox= new HBox();
        Button gen = new Button();
        gen.setText("Prepare CV");
        gen.setOnMouseClicked((e -> {
            ((Stage) pane.getScene().getWindow()).close();
            genetrateCV(checkEdu.isSelected(),checkXP.isSelected());
        }));
        Button cancel = new Button();
        cancel.setText("Cancel");
        cancel.setOnMouseClicked((e ->  ((Stage) pane.getScene().getWindow()).close()));
        hBox.getChildren().add(gen);
        hBox.getChildren().add(cancel);
        box.getChildren().add(hBox);
        pane.getChildren().add(box);
        Stage stage = new Stage();


        stage.setTitle("Simple Employee Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.show();




    }

    private void genetrateCV(boolean selectedEdu, boolean selectedXP) {
        List<Education> educations= new ArrayList<>();
        List<Work_Exp> exps= new ArrayList<>();



        if(selectedEdu) {
            List<? extends Node> elements = eduvBOX.getChildren();


            for (Node el : elements) {
                List<? extends Node> nodes = ((Pane) el).getChildren();
                TextField name = (TextField) nodes.get(1);
                if (name.getText().trim().equals("")) continue;
                DatePicker date = (DatePicker) nodes.get(2);
                CheckBox checkBox = (CheckBox) nodes.get(3);
                TextField cert_id = (TextField) nodes.get(4);
                Education education = new Education(name.getText(), date.getValue(), checkBox.isSelected(), cert_id.getText());
                educations.add(education);
            }
        }
        if(selectedXP) {
            List<? extends Node> elements = workExpvBOX.getChildren();
            for (Node el : elements) {
                List<? extends Node> nodes = ((Pane) el).getChildren();
                TextField name = (TextField) nodes.get(1);
                if (name.getText().trim().equals("")) continue;
                DatePicker date_from = (DatePicker) nodes.get(2);
                DatePicker date_to = (DatePicker) nodes.get(3);
                Work_Exp work_exp = new Work_Exp(name.getText(), date_from.getValue(), date_to.getValue());
                exps.add(work_exp);
            }
        }
        /* I decided not to add animation */
     new Thread(() ->new ReportService().print(educations,exps)).start();

    }

    @FXML
    public void onClickAddEduButton(MouseEvent mouseEvent) throws IOException {
          addEduPaneInVBox();    }




    @FXML
    public void onClickAddWorkButton(MouseEvent mouseEvent) throws IOException {
       addWorkPaneInVBox();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

             try {

                 if(id>0) {
                     User user = service.getUserById(id);
                     lName.setText(user.getLastName());
                     fName.setText(user.getFirstName());
                     selectDep.setText(user.getDepartment());
                     selectPos.setText(user.getPosition());
                  addEdus(new EducationServices().getEduByUserID(id));
                    addWork_EXP(new Work_Exp_Services().getWorK_ExpByUserID(id));
                 }else {
                          addEduPaneInVBox();
                           addWorkPaneInVBox();
                    }
             } catch (Exception e) {
                 //LOG++
                 e.printStackTrace();
             }

        service.getAllDepartment().stream().forEach(dep -> selectDep.getItems().add(newMI(dep,'d')));
        service.getAllPosition().stream().forEach(dep -> selectPos.getItems().add(newMI(dep,'p')));



    }




    private void addWorkPaneInVBox() throws IOException {


        Pane pane =  FXMLLoader.load(getClass().getResource("/fxml/WorkPane.fxml"));
         workExpvBOX.getChildren().add(pane);


        if(workExpvBOX.getChildren().size()>3){

            workAnchorPane.setPrefHeight(workAnchorPane.getPrefHeight()+30);
            workExpvBOX.setPrefHeight(workExpvBOX.getHeight()+30);

        }

    }


    private synchronized void addEduPaneInVBox( ) throws IOException {
        Pane pane =  FXMLLoader.load(getClass().getResource("/fxml/EduPane.fxml"));


        if(eduvBOX.getChildren().size()>3){

            eduAnchorPane.setPrefHeight(eduAnchorPane.getPrefHeight()+30);
            eduvBOX.setPrefHeight(eduvBOX.getHeight()+30);

        }
        eduvBOX.getChildren().add(pane);

    }



    private void addEdus(List<Education> edus) throws IOException {
         ObservableList<Pane> panes= FXCollections.observableArrayList();
        for(Education edu : edus){


            Pane pane =  FXMLLoader.load(getClass().getResource("/fxml/EduPane.fxml"));
            List<? extends Node>  nodes =pane.getChildren();
            ((TextField) nodes.get(1)).setText(edu.getName());
            ((DatePicker) nodes.get(2)).setValue(edu.getDate());
            ((CheckBox) nodes.get(3)).setSelected(edu.isCert_check());
            ((TextField) nodes.get(4)).setText(edu.getCert_id());

            panes.add(pane);


        }
       int  size=   panes.size() ;

       if(size>3) {
          eduAnchorPane.setPrefHeight(eduAnchorPane.getPrefHeight() + 30 * (size - 3));
       }
        eduvBOX.getChildren().setAll(panes);





    }
    private void addWork_EXP(List<Work_Exp> worK_exps) throws IOException {
        ObservableList<Pane> panes= FXCollections.observableArrayList();
       for(Work_Exp xp : worK_exps){

           Pane pane = FXMLLoader.load(getClass().getResource("/fxml/WorkPane.fxml"));
           List<? extends Node>  nodes =pane.getChildren();
          ( (TextField) nodes.get(1)).setText(xp.getName());
           ((DatePicker) nodes.get(2)).setValue(xp.getData_from());
           ((DatePicker) nodes.get(3)).setValue(xp.getData_to());

           panes.add(pane);


       }
        int  size=   panes.size() ;

         if(size>3) {
             workAnchorPane.setPrefHeight(workAnchorPane.getPrefHeight() + 30 * (size - 3));
         }
        workExpvBOX.getChildren().setAll(panes);




    }


    public  MenuItem  newMI(String text, char type){

        MenuItem item= new MenuItem(text);
        switch (type) {
            case 'd':
                item.setOnAction(e -> selectDep.setText(((MenuItem) e.getSource()).getText())); break;
            case 'p':
                item.setOnAction(e -> selectPos.setText(((MenuItem) e.getSource()).getText()));
        }
        return item;

    }


}
