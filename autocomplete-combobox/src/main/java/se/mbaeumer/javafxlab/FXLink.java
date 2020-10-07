package se.mbaeumer.javafxlab;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;

public class FXLink extends Application{

	private Group root = new Group();
	private Scene scene;
	private FlowPane flowGeneral;
	private Label lblInfo;

	private ComboBox<String> cmb = new ComboBox<>();

	private static final String[] LISTA = { "Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",
			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",
			"Figo", "Figo da Índia", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Jambolão",
			"Kino (Kiwano)", "Kiwi", "Laranja Bahia", "Laranja para suco", "Laranja seleta", "Laranja serra d’água",
			"Laranjinha kinkan", "Lichia", "Lima da pérsia", "Limão galego", "Limão Taiti", "Maçã argentina",
			"Maçã Fuji", "Maçã gala", "Maçã verde", "Mamão formosa", "Mamão Havaí", "Manga espada", "Manga Haden",
			"Manga Palmer", "Manga Tommy", "Manga Ubá", "Mangostim", "Maracujá doce", "Maracujá para suco", "Melancia",
			"Melancia sem semente", "Melão", "Melão Net", "Melão Orange", "Melão pele de sapo", "Melão redinha",
			"Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", "Mirtilo", "Morango", "Nectarina",
			"Nêspera ou ameixa amarela", "Noni", "Pera asiática", "Pera portuguesa", "Pêssego", "Physalis", "Pinha",
			"Pitaia", "Romã", "Tamarilo", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",
			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",
			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",
			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",
			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Jiló", "Massa de alho", "Maxixe", "Milho",
			"Pimenta biquinho fresca", "Pimenta de bode fresca", "Pimentão amarelo", "Pimentão verde",
			"Pimentão vermelho", "Quiabo", "Repolho", "Repolho roxo", "Tomate cereja", "Tomate salada",
			"Tomate sem acidez", "Tomate uva", "Vagem", "Agrião", "Alcachofra", "Alface", "Alface americana",
			"Almeirão", "Brócolis", "Broto de alfafa", "Broto de bambu", "Broto de feijão", "Cebolinha", "Coentro",
			"Couve", "Espinafre", "Hortelã", "Mostarda", "Rúcula", "Salsa", "Ovos brancos", "Ovos de codorna",
			"Ovos vermelhos" };

	private static final String[] names = { "Anton", "Berta",
			"Lasse" };

	public void start(Stage stage) {
		this.scene = new Scene(this.root, 1100, 700, Color.LIGHTGRAY);
		stage.setTitle("Filtrando um ComboBox");

		stage.setScene(this.scene);
		stage.show();
		this.initLayout();
		this.initAutocompleBox();
		this.initLabel();
		this.initTextField();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initLayout() {
		this.createGeneralFlowPane();
	}
	
	public void createGeneralFlowPane() {
		this.flowGeneral = new FlowPane();
		this.flowGeneral.setOrientation(Orientation.HORIZONTAL);
		this.flowGeneral.setPrefWrapLength(700);
		this.flowGeneral.setHgap(10);
		this.root.getChildren().add(this.flowGeneral);

	}

	private void initAutocompleBox(){
		cmb.setTooltip(new Tooltip());
		cmb.getItems().addAll(LISTA);

		this.flowGeneral.getChildren().add(this.cmb);
		new ComboboxAutocomplete<String>(cmb);


	}

	private void initLabel(){
		this.lblInfo = new Label();
		this.lblInfo.textProperty().bind(this.cmb.getSelectionModel().selectedItemProperty());
		this.flowGeneral.getChildren().add(this.lblInfo);

		DatePicker datePicker = new DatePicker();
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("Hello World: " + flowGeneral.widthProperty().get());
			}
		};
		datePicker.addEventHandler(MouseEvent.MOUSE_ENTERED, eventHandler);

		this.flowGeneral.getChildren().add(datePicker);

	}

	private void initTextField(){
		FlowPane stackPane = new FlowPane();
		TextField textField = new TextField();
		textField.setLayoutX(50);
		textField.setLayoutY(100);
		stackPane.setLayoutX(50);
		stackPane.setOrientation(Orientation.VERTICAL);
		stackPane.setLayoutY(123);
		stackPane.setBackground(createBackground());
		DropShadow dropShadow = new DropShadow(5, Color.GRAY);
		this.flowGeneral.setEffect(dropShadow);


		this.root.getChildren().addAll(textField, stackPane);
		new TextBoxAutoComplete(textField, stackPane, Arrays.asList(names));
	}

	private Background createBackground(){
		Insets bgInsets = new Insets(5, 5, 5, 5);
		BackgroundFill bgFill = new BackgroundFill(Color.WHITE, null, bgInsets);
		return new Background(bgFill);
	}
}