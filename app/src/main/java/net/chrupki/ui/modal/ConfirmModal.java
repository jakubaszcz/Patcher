package net.chrupki.ui.modal;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.chrupki.model.HubModel;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class ConfirmModal extends ModalTemplate {

    private VBox content = new VBox();

    public enum Type {
        Project,
        Version,
        Tag,
        Patch
    }

    private static final ObjectProperty<Type> type =
            new SimpleObjectProperty<>();


    public static void changeType(Type nType) {
        type.set(nType);
    }

    public ConfirmModal(
        Runnable onClose,
        Runnable onDeleteProject,
        Consumer<Integer> onDeleteVersion,
        Consumer<Integer> onDeleteTag,
        BiConsumer<Integer, Integer> onDeletePatch
    ) {
        super("Delete", onClose);
        titleLabel.textProperty().bind(
                Bindings.createStringBinding(
                        this::setTitle,
                        type,
                        HubModel.projectModel().getName(),
                        HubModel.versionModel().getName(),
                        HubModel.tagModel().getName(),
                        HubModel.patchModel().getContent()
                )
        );

        type.addListener((obs, oldVal, newVal) -> {
            setContent(
                    onClose,
                    onDeleteProject,
                    onDeleteVersion,
                    onDeleteTag,
                    onDeletePatch
            );
        });


        visibleProperty().bind(GlobalModel.getSwitchConfirmModal());
        managedProperty().bind(GlobalModel.getSwitchConfirmModal());

        setOnMouseClicked(Event::consume);

        getChildren().add(content);

    }

    private String setTitle() {

        Type t = type.get();

        if (t == null) return "Youps";

        return switch (t) {

            case Project -> "Delete project : " + HubModel.projectModel().getName().get();

            case Version -> "Delete version : " + HubModel.versionModel().getName().get();

            case Tag -> "Delete tag : " + HubModel.tagModel().getName().get();

            case Patch -> "Delete patch : " + HubModel.patchModel().getContent().get();
        };
    }

    private void setContent(
            Runnable onClose,
            Runnable onDeleteProject,
            Consumer<Integer> onDeleteVersion,
            Consumer<Integer> onDeleteTag,
            BiConsumer<Integer, Integer> onDeletePatch
    ) {

        Type t = type.get();

        if (t == null) return;

        content.getChildren().clear();

        Button confirm = new Button("Confirm");
        new Styles().apply(confirm, ButtonTheme.NORMAL);

        switch (t) {
            case Project -> {
                Label title = new Label("Deleting a project involve deleting too much data, to provide more security please type the exact project name in the input field, then 'confirm'.");
                title.setWrapText(true);
                new Styles().apply(title, TextTheme.TEXT_MAIN);

                TextField textField = new TextField();

                confirm.setOnAction(e -> {
                    if (textField.getText().equals(HubModel.projectModel().getName().get()))
                    {
                        textField.clear();
                        onDeleteProject.run();
                        onClose.run();
                    } else {
                        textField.clear();
                        GlobalModel.setErrorMessage("Project name doesn't match");
                        return;
                    }
                });

                content.getChildren().addAll(title, textField, confirm);
            }
            case Version, Tag, Patch -> {
                Label title = new Label("After deleting, you won't be able to recover it back. Are you sure you want to delete that ?");
                title.setWrapText(true);
                new Styles().apply(title, TextTheme.TEXT_MAIN);

                confirm.setOnAction(e -> {
                    switch (t) {
                        case Version -> {
                            onDeleteVersion.accept(HubModel.versionModel().getId().get());
                            onClose.run();
                        }
                        case Tag -> {
                            onDeleteTag.accept(HubModel.tagModel().getId().get());
                            onClose.run();
                        }
                        case Patch -> {
                            onDeletePatch.accept(HubModel.patchModel().getId().get(), HubModel.patchModel().getVid().get());
                            onClose.run();
                        }
                    }
                });
                content.getChildren().addAll(title, confirm);
            }
            default -> {
                content.getChildren().add(new Label("Nothing has been found."));
            }
        }
    }


}
