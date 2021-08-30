package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WorkExpController {


    @FXML
    public void onClickDeleteWorkButton(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getTarget());
        Button button =(Button) ( mouseEvent.getTarget().toString().startsWith("Button") ? mouseEvent.getPickResult().getIntersectedNode() :
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


}
