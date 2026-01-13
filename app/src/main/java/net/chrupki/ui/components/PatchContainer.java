package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import net.chrupki.ui.model.ProjectModel;


public class PatchContainer extends VBox {

    public PatchContainer(ProjectModel model, String content, String type) {

        HBox box = new HBox(10);
        Label contentLabel = new Label(content);
        Label typeLabel = new Label(type);
        Button edit = new Button("Edit patch");

        box.getChildren().addAll(typeLabel, contentLabel, edit);

        edit.setOnAction(e ->{
            model.setEditPatchProperty(true);
        });


        this.getChildren().add(box);
    }
}
