package se.mbaeumer.javafxlab;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextBoxAutoComplete {

    private TextField textField;
    private FlowPane flowPane;
    private String filter = "";
    private List<String> originalWordList;

    public TextBoxAutoComplete(TextField textField, FlowPane aPane, List<String> words) {
        this.textField = textField;
        this.flowPane = aPane;
        this.originalWordList = words;
        this.textField.setOnKeyPressed(this::handleOnKeyPressed);

        flowPane.setVisible(false);
        setFlowPane();

    }

    public void handleOnKeyPressed(KeyEvent e) {
        List<String> filteredList = new ArrayList<>();
        filteredList.clear();

        KeyCode code = e.getCode();

        if (code.isLetterKey()) {
            filter += e.getText();
        }
        if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
            filter = filter.substring(0, filter.length() - 1);
            //cmb.getItems().setAll(originalItems);
        }
        if (code == KeyCode.ESCAPE) {
            filter = "";
        }
        if (filter.length() == 0) {
            flowPane.setVisible(false);
            filteredList.clear();
        } else {
            filteredList.clear();
            flowPane.setVisible(true);
            String txtUsr = unaccent(filter.toLowerCase());
            System.out.println("txtUser: " + txtUsr);
            originalWordList.stream().filter(el -> unaccent(el.toLowerCase()).contains(txtUsr)).forEach(filteredList::add);
            /*
            cmb.getTooltip().setText(txtUsr);
            Window stage = cmb.getScene().getWindow();
            double posX = stage.getX() + cmb.getBoundsInParent().getMinX();
            double posY = stage.getY() + cmb.getBoundsInParent().getMinY();
            cmb.getTooltip().show(stage, posX, posY);
            cmb.show();
            */
        }
        flowPane.getChildren().clear();

        for (int i=0; i<filteredList.size(); i++){
            Label label = new Label(filteredList.get(i));
            label.setPadding(new Insets(0, 0, 2, 5));
            flowPane.getChildren().add(label);
        }
        //cmb.getItems().setAll(filteredList);
    }

    private void setFlowPane(){
        flowPane.prefWidthProperty().bind(textField.widthProperty());
    }

    private String unaccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
