package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class EduPaneController {


     @FXML
    public void OnClickEduDeleteButton(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getTarget());
        Button  button =(Button) ( mouseEvent.getTarget().toString().startsWith("Button") ? mouseEvent.getPickResult().getIntersectedNode() :
                mouseEvent.getPickResult().getIntersectedNode().getParent()      );
        Pane pane = (Pane) button.getParent();
        VBox vBox= (VBox) pane.getParent();
        vBox.getChildren().remove(pane);
        if(vBox.getChildren().size()>=3){
            AnchorPane eduAnchorPane= (AnchorPane) vBox.getParent();
            eduAnchorPane.setPrefHeight(eduAnchorPane.getPrefHeight()-30);
            vBox.setPrefHeight(vBox.getHeight()-30);

        }
        System.out.println();
    }


    @FXML
    public void onClickChekBoxCert(MouseEvent mouseEvent) {
        Node node =mouseEvent.getPickResult().getIntersectedNode().getParent();
        System.out.println(node );
       CheckBox chek =(CheckBox) ( node.toString().startsWith("CheckBox") ?  node  :
               node.toString().startsWith("Pane") ?  ((Pane) node).getChildren().get(((Pane) node).getChildren().size()-2) :
                node.getParent()    );
        Pane pane = (Pane) chek.getParent();
        pane.getChildren().get(pane.getChildren().size()-1).setDisable(!chek.isSelected());
    }
}
