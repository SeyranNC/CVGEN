package app.controllers;

import app.Main;
import app.services.UserFormService;
import app.view_models.UserForm;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ListFromViewController implements Initializable {

    @FXML
    public Button addButton;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public TableView rootTableView;
    @FXML
    public TableColumn<UserForm,String> nameColumn;
    @FXML
    public TableColumn<UserForm,String> departamentColumn;
    @FXML
    public TableColumn<UserForm,String> positionColumn;
    @FXML
    public TableColumn<UserForm, ImageView> editColumn;
    @FXML
    public TableColumn<UserForm, ImageView>  deleteColumn;

    UserFormService service = new UserFormService();

    ObservableList<UserForm> list= service.getUserList();

    @FXML
    public void onClickAddButton(MouseEvent mouseEvent) throws Exception{
        openAddOrEditUserStage(0,0);
    }

    private void openAddOrEditUserStage(int id, int list_id) throws Exception{

        Main.context.setProperty("userEditID", ""+id);
        Main.context.setProperty("list_id", ""+list_id);
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddFormVew.fxml"));
        stage.setTitle("Simple Employee Catalog");
       stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
             rootTableView.setItems(list);
    }

     @FXML
    public void onTableClick(MouseEvent mouseEvent) throws Exception {

    TableView.TableViewSelectionModel selectionModel = rootTableView.getSelectionModel();
    String nodeID =mouseEvent.getPickResult().getIntersectedNode().getId();
    if(nodeID==null) return;
    if(nodeID.startsWith("delete")){
        list.remove(selectionModel.getFocusedIndex());

    }
    else
       if( mouseEvent.getPickResult().getIntersectedNode().getId().startsWith("edit")){

         int userID=   list.get(selectionModel.getFocusedIndex()).getId();
           openAddOrEditUserStage(userID, selectionModel.getFocusedIndex());


    }

    }




}
