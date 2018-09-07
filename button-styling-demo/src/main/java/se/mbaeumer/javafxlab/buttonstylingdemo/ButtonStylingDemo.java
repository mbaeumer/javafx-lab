package se.mbaeumer.javafxlab.buttonstylingdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ButtonStylingDemo extends Application {

    private Group root =  new Group();
    private Scene scene;
    private FlowPane flowFilter;
    private Button btnPureColor;
    private Button btnLinearGradient;
    private Button btnLinearAndRadientGradient;
    private Button btnRadientGradient;
    private Button btnRadiusTen;
    private Button btnRadiusNinety;
    private Button btnEffectDropShadowThreePassBox;
    private Button btnEffectDropShadowOnePassBox;
    private Button myFullyStyledButton;

    public void start(Stage stage) {
        this.scene = new Scene(this.root, 500, 350, Color.WHITESMOKE);
        scene.getStylesheets().add(getClass().getResource("/test.css").toExternalForm());
        stage.setTitle("Button stylling demo");
        stage.setScene(this.scene);
        stage.show();
        this.initLayout();
        //this.initFullyStyledButton();
        this.initPureColorButton();
        this.initLinear();
        this.initRadialOnly();
        this.initLinearAndRadial();
        this.initRadiusTen();
        this.initRadiusNinety();
        this.initDropShadowEffectThreePassBox();
        this.initDropShadowEffectOnePassBox();

    }

    private void initLayout(){
        this.flowFilter = new FlowPane();
        this.flowFilter.setOrientation(Orientation.VERTICAL);
        this.flowFilter.setVgap(10);
        FlowPane.setMargin(flowFilter, new Insets(5,5,5,5));

        this.root.getChildren().add(this.flowFilter);

    }

    private void initFullyStyledButton(){
        this.myFullyStyledButton = new Button("Fully styled button");
        this.myFullyStyledButton.getStyleClass().add("glass-grey-shadow");
        this.flowFilter.getChildren().add(this.myFullyStyledButton);
    }

    private void initPureColorButton(){
        this.btnPureColor = new Button("Pure color");
        this.btnPureColor.getStyleClass().add("pure-color");
        this.flowFilter.getChildren().add(this.btnPureColor);
    }

    private void initLinear(){
        this.btnLinearGradient = new Button("Linear gradient");
        this.btnLinearGradient.getStyleClass().add("linear");
        this.flowFilter.getChildren().add(this.btnLinearGradient);
    }

    private void initLinearAndRadial(){
        this.btnLinearAndRadientGradient = new Button("Linear and radial gradient");
        this.btnLinearAndRadientGradient.getStyleClass().add("linear-and-radial");
        this.flowFilter.getChildren().add(this.btnLinearAndRadientGradient);
    }

    private void initRadialOnly(){
        this.btnRadientGradient = new Button("Radial gradient");
        this.btnRadientGradient.getStyleClass().add("radial-only");
        this.flowFilter.getChildren().add(this.btnRadientGradient);
    }

    private void initRadiusTen(){
        this.btnRadiusTen = new Button("Linear and radial gradient and radius 10");
        this.btnRadiusTen.getStyleClass().add("radius-10");
        this.flowFilter.getChildren().add(this.btnRadiusTen);
    }

    private void initRadiusNinety(){
        this.btnRadiusNinety = new Button("Linear and radial gradient and radius 90");
        this.btnRadiusNinety.getStyleClass().add("radius-90");
        this.flowFilter.getChildren().add(this.btnRadiusNinety);
    }

    private void initDropShadowEffectThreePassBox(){
        this.btnEffectDropShadowThreePassBox = new Button("Dropdown effect three-pass-box");
        this.btnEffectDropShadowThreePassBox.getStyleClass().add("dropshadow-three-pass-box");
        this.flowFilter.getChildren().add(this.btnEffectDropShadowThreePassBox);
    }

    private void initDropShadowEffectOnePassBox(){
        this.btnEffectDropShadowOnePassBox = new Button("Dropdown effect one-pass-box");
        this.btnEffectDropShadowOnePassBox.getStyleClass().add("dropshadow-one-pass-box");
        this.flowFilter.getChildren().add(this.btnEffectDropShadowOnePassBox);
    }



    public static void main(String[] args) {
        launch(args);
    }

}
