package se.mbaeumer.javafxlab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;

import javax.swing.event.ChangeEvent;

public class ComponentSkinClass implements ChangeListener<Category>, EventHandler {
    @Override
    public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {

    }

    @Override
    public void handle(Event event) {

    }
}
