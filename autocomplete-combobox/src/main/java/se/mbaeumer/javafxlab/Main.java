package se.mbaeumer.javafxlab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private FlowPane flowPane;
    private Scene scene;
    private Label lblInfo;

    @Override
    public void start(Stage stage) throws Exception {
        this.initFlowPane();
        flowPane.getChildren().add(createAutocompleteCombobox());
        initInfoLabel();
        this.scene = new Scene(this.flowPane, 1100, 700, Color.WHITESMOKE);
        stage.setTitle("Autocomplete combobox demo");
        stage.setScene(this.scene);
        stage.show();
    }

    private void initFlowPane(){
        this.flowPane = new FlowPane();
    }

    private AutocompleteComboBox createAutocompleteCombobox(){
        AutocompleteComboBox<String> autocompleteComboBox =
                new AutocompleteComboBox<>(FXCollections.observableArrayList(getNames()));
        return autocompleteComboBox;
    }

    private void initInfoLabel(){
        this.lblInfo = new Label("");
        this.flowPane.getChildren().add(this.lblInfo);
    }



    private ComboBox<Category> createComboBox(){
        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll(getCategories());
        return categoryComboBox;
    }

    private List<Category> getCategories(){
        Category cat1 = new Category("one", 1);
        Category cat2 = new Category("two", 2);
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        return categories;
    }

    private List<String> getNames(){
        List<String> names = new ArrayList<>();
        names.add("one");
        names.add("two");
        names.add("onethree");
        names.add("onefour");
        names.add("onefive");
        names.add("onesix");
        names.add("oneseven");
        return names;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
