package se.mbaeumer.javafxlab;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Callback;

public class AutoFillTextBoxSkin<T> extends SkinBase<AutocompleteComboBox<T>>
		implements ChangeListener<String>,
		EventHandler {

	private final static int TITLE_HEIGHT = 28;

	private ListView listview;

	private TextField textbox;

	private AutocompleteComboBox autocompleteComboBox;

	private ObservableList data;

	private Popup popup;

	public Window getWindow() {
		return autocompleteComboBox.getScene().getWindow();
	}

	private String temporaryTxt = "";

	public AutoFillTextBoxSkin(AutocompleteComboBox<T> text) {
		super(text);

		autocompleteComboBox = text;

		listview = text.getListview();
		if (text.getFilterMode()) {
			listview.setItems(text.getData());
		}
		listview.itemsProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				if (listview.getItems().size() > 0 && listview.getItems() != null) {
					showPopup();
				} else {
					hidePopup();
				}
			}

		});
		listview.setOnMouseReleased(this);
		listview.setOnKeyReleased(this);
		//handleCellFactory();


		//main textbox
		textbox = text.getTextbox();
		textbox.setOnKeyPressed(this);
		textbox.textProperty().addListener(this);

		textbox.focusedProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				textbox.end();
			}

		});

		//popup
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(listview);

		//list data and sorted ordered
		data = text.getData();
		FXCollections.sort(data);

		getChildren().addAll(textbox);

	}

	/**
	 * ********************************************************
	 * Selects the current Selected Item from the list and the content of that
	 * selected Item is set to textbox.
	 * ********************************************************
	 */
	public void selectList() {
		Object i = listview.getSelectionModel().getSelectedItem();
		if (i != null) {
			System.out.println("Setting the text: " + listview.getSelectionModel().getSelectedItem().toString());
			textbox.setText(listview.getSelectionModel().getSelectedItem().toString());

			listview.getItems().clear();
			textbox.requestFocus();
			textbox.requestLayout();
			textbox.end();
			temporaryTxt = "";
			hidePopup();
		}
	}

	/**
	 * ****************************************************
	 * This is the main event handler which handles all the event of the
	 * listview and textbox
	 * <p>
	 * @param evt ****************************************************
	 */
	@Override
	public void handle(Event evt) {

		/**
		 * ******************************
		 * EVENT HANDLING FOR 'TextBox' ******************************
		 */
		if (evt.getEventType() == KeyEvent.KEY_PRESSED) {
			/* --------------------------------
			 * - KeyEvent Handling for Textbox -
			 * -------------------------------- */
			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == textbox) {
				//WHEN USER PRESS DOWN ARROW KEY FOCUS TRANSFER TO LISTVIEW
				if (t.getCode() == KeyCode.DOWN) {
					if (popup.isShowing()) {
						listview.requestFocus();
						listview.getSelectionModel().select(0);
					}
				}

			}
		} /**
		 * ******************************
		 * EVENT HANDLING FOR 'LISTVIEW' ******************************
		 */
		else if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
			/* ---------------------------------
			 * - KeyEvent Handling for ListView -
			 * ---------------------------------- */
			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == listview) {
				if (t.getCode() == KeyCode.ENTER) {
					selectList();
				} else if (t.getCode() == KeyCode.UP) {
					if (listview.getSelectionModel().getSelectedIndex() == 0) {
						textbox.requestFocus();
					}
				}
			}
		} else if (evt.getEventType() == MouseEvent.MOUSE_RELEASED) {
			/* -----------------------------------
			 * - MouseEvent Handling for Listview -
			 * ------------------------------------ */
			if (evt.getSource() == listview) {
				selectList();
			}
		}
	}

	/**
	 * A Popup containing Listview is trigged from this function This function
	 * automatically resize it's height and width according to the width of
	 * textbox and item's cell height
	 */
	public void showPopup() {
		listview.setPrefWidth(textbox.getWidth());
        System.out.println("Items: " + listview.getItems().size());
		if (listview.getItems().size() > 6) {
			listview.setPrefHeight((6 * 24));
		} else {
			listview.setPrefHeight((listview.getItems().size() * 24));
		}

		//CALCULATING THE X AND Y POSITION FOR POPUP
		//Dimension2D dimen = getDimension(getParent(),getLayoutX()+getTranslateX(),getLayoutY()+getHeight() +getTranslateY());
		//SHOWING THE POPUP JUST BELOW TEXTBOX
		popup.show(
				getWindow(),
				getWindow().getX() + textbox.localToScene(0, 0).getX() + textbox.getScene().getX(),
				getWindow().getY() + textbox.localToScene(0, 0).getY() + textbox.getScene().getY() + TITLE_HEIGHT);

		listview.getSelectionModel().clearSelection();
		listview.getFocusModel().focus(-1);
	}

	public void hidePopup() {
		popup.hide();
	}

	/**
	 * *********************************************
	 * When ever the the rawTextProperty is changed then this listener is
	 * activated
	 * <p>
	 * @param ov
	 * @param t
	 * @param t1 **********************************************
	 */
	@Override
	public void changed(ObservableValue<? extends String> ov, String t, String t1) {

		if (ov.getValue().toString().length() > 0) {
			String txtdata = (textbox.getText()).trim();

			//Limit of data cell to be shown in ListView
			int limit = 0;
			if (txtdata.length() > 0) {
				ObservableList list = FXCollections.observableArrayList();
				String compare = txtdata.toLowerCase();
				for (Object dat : data) {
					String str = dat.toString().toLowerCase();

					if (str.startsWith(compare)) {
						list.add(dat);
						limit++;
					}
					if (limit == autocompleteComboBox.getListLimit()) {
						break;
					}
				}
				if (listview.getItems().containsAll(list)
						&& listview.getItems().size() == list.size() && listview.getItems() != null) {
					showPopup();
				} else {
					listview.setItems(list);
				}

			} else {
				if (autocompleteComboBox.getFilterMode()) {
					listview.setItems(data);
				} else {
					listview.setItems(null);
				}
			}
		}

		if (ov.getValue().toString().length() <= 0) {
			temporaryTxt = "";
			if (autocompleteComboBox.getFilterMode()) {
				listview.setItems(data);
				showPopup();
			} else {
				hidePopup();
			}
		}
	}
}