package se.mbaeumer.javafxlab.java11;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXLink extends Application{

	private Group root = new Group();
	private Scene scene;
	private FlowPane flowGeneral;
	private FlowPane flowFilter;
	private FlowPane flowSelection;
	private FlowPane flowActions;
	private Label lblItems;
	private ComboBox<String> cmbItems;
	private Label lblCategories;
	private Button btnResetFilter;
	private Button btnShowImportHistory;
	private Button btnImportTextFile;
	private Button btnShowSearchPane;
	private Button btnDeleteLinks;
	private Button btnCreateItem;
	private Button btnMoveToCategory;
	private Button btnGenerateTitle;

	private FlowPane flowSearch;
	private Button btnSearch;
	private Label lblSearchTerm;
	private TextField tfSearchTerm;
	private CheckBox chkSearchURL;
	private CheckBox chkSearchTitle;
	private CheckBox chkSearchDescription;
	private Label lblSearchError;

	private Button btnWriteBackup;
	private Button btnReadBackup;
	private Button btnSelectAll;
	private Button btnDeselectAll;
	
	private ContextMenu contLinks;
	private ContextMenu contTags;
	private FlowPane flowStatus;
	private Label lblStatusItemCountText;
	private Label lblStatusItemCount;

	public void start(Stage stage) {
		this.scene = new Scene(this.root, 1100, 700, Color.WHITESMOKE);
		stage.setTitle("JavaFX lab: Java 8 example");
		stage.setScene(this.scene);
		stage.show();
		this.initLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initLayout() {
		this.createGeneralFlowPane();
		this.createButton();
	}
	
	public void createGeneralFlowPane() {
		this.flowGeneral = new FlowPane();
		this.flowGeneral.setOrientation(Orientation.VERTICAL);
		this.flowGeneral.setPrefWrapLength(700);
		this.flowGeneral.setVgap(10);
		this.root.getChildren().add(this.flowGeneral);
	}

	private void createButton(){
		this.btnCreateItem = new Button("Example button");

		this.flowGeneral.getChildren().add(this.btnCreateItem);
	}


	

}