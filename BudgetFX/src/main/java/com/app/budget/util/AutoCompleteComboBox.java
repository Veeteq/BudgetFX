package com.app.budget.util;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

public class AutoCompleteComboBox<T> implements EventHandler<KeyEvent> {

	private ComboBox<T> comboBox;
	private ObservableList<T> data;
	private int cursorPos;
	private boolean moveCursorToPos = false;
	
	public AutoCompleteComboBox(ComboBox<T> comboBox) {
		this.comboBox = comboBox;
		this.data = comboBox.getItems();
	}

	@Override
	public void handle(KeyEvent event) {
		System.out.println(event.getCode());
		switch(event.getCode()){
		case UP:
			cursorPos = -1;
			moveCursor(comboBox.getEditor().getText().length());
			break;
		case DOWN:
			if(!comboBox.isShowing()) {
				comboBox.show();
			}
			cursorPos = -1;
			moveCursor(comboBox.getEditor().getText().length());
			break;
		default:
			break;
		}
		
		ObservableList<T> list = FXCollections.observableArrayList();
		for(int i = 0; i < data.size(); i++){
			if(data.get(i).toString().toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())){
				list.add(data.get(i));
			}
		}
		
		String text = comboBox.getEditor().getText();
		this.comboBox.setItems(list);
		this.comboBox.getEditor().setText(text);
		if(!moveCursorToPos){
			cursorPos = -1;
		}
		moveCursor(text.length());
		if(!list.isEmpty()){
			comboBox.show();
		}
	}
	
	private void moveCursor(int pos){
		if(cursorPos == -1){
			comboBox.getEditor().positionCaret(pos);
		}else{
			comboBox.getEditor().positionCaret(cursorPos);
		}
		moveCursorToPos = false;
	}

}
