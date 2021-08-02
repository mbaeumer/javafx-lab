package se.mbaeumer.javafxlab;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoApp extends Application {

    private Group root = new Group();
    private Scene scene;
    private Button buttonTraditional;
    private Button buttonNew;
    private Label label;
    private CheckBox chkTraditional;
    private CheckBox chkNew;

    private FlowPane flowGeneral;

    public void start(Stage stage) {
        this.scene = new Scene(this.root, 1100, 700, Color.WHITESMOKE);
        stage.setTitle("JavaFX lab: Demo of lambda eventhandlers");
        stage.setScene(this.scene);
        stage.show();
        this.initLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initLayout() {
        this.createGeneralFlowPane();
        this.createButtons();
        this.createLabel();
        this.createTraditionalCheckBox();
        this.createLambdaCheckBox();
    }

    public void createGeneralFlowPane() {
        this.flowGeneral = new FlowPane();
        this.flowGeneral.setOrientation(Orientation.VERTICAL);
        this.flowGeneral.setPrefWrapLength(700);
        this.flowGeneral.setVgap(10);
        this.root.getChildren().add(this.flowGeneral);
    }

    private void createButtons(){
        this.buttonTraditional = new Button("Example button with traditional event handler");

        this.buttonTraditional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                label.setText("Action performed in the traditional event handler");
            }
        });

        this.buttonNew = new Button("Example button with lambda event handler");
        this.buttonNew.setOnAction(actionEvent -> label.setText("Action performed in the lambda"));
        this.buttonNew.setOnAction(this::handleButtonPressed);
        this.flowGeneral.getChildren().addAll(this.buttonTraditional, this.buttonNew);
    }

    private void handleButtonPressed(ActionEvent actionEvent){
        label.setText("Action performed in the method");
    }

    private void createLabel(){
        this.label = new Label();
        this.flowGeneral.getChildren().add(this.label);
    }

    private void createTraditionalCheckBox(){
        this.chkTraditional = new CheckBox("Example checkbox with traditional event handler");
        this.chkTraditional.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                String message = newValue ? "Traditional checkbox checked" : "Traditional checkbox unchecked";
                label.setText(message);
            }
        });
        this.flowGeneral.getChildren().add(this.chkTraditional);
    }

    private void createLambdaCheckBox(){
        this.chkNew = new CheckBox("Example checkbox with lambda event handler");

        //this.chkNew.selectedProperty().addListener((observable, oldValue, newValue) -> {label.setText("lambda checkbox changed");});
        //this.chkNew.setOnAction(this::handleStatusChange);
        this.flowGeneral.getChildren().add(this.chkNew);
    }

    private void handleStatusChange(ActionEvent actionEvent){
        String message = chkNew.isSelected() ? "Lambda checkbox checked" : "Lambda checkbox unchecked";
        label.setText(message);
    }
}