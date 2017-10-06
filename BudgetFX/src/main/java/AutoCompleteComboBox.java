import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AutoCompleteComboBox<T> implements EventHandler<KeyEvent>{

	private ComboBox<T> comboBox;
	private ObservableList<T> data;
	private boolean moveCaretToPos = false;
	private int caretPos;
	
	public AutoCompleteComboBox(ComboBox<T> comboBox){
		this.comboBox = comboBox;
		data = comboBox.getItems();
		System.out.println(data.size());
		
		this.comboBox.setOnKeyPressed(e -> comboBox.hide());
		this.comboBox.setOnKeyReleased(AutoCompleteComboBox.this);
	}
	
	@Override
	public void handle(KeyEvent event) {
		System.out.println(event.getCode());
		if(event.getCode() == KeyCode.UP){
			caretPos = -1;
			moveCaret(comboBox.getEditor().getText().length());
			return;
		}else if(event.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing()) {
                comboBox.show();
            }
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
		}else if(event.getCode() == KeyCode.BACK_SPACE){
			moveCaretToPos = true;
			caretPos = comboBox.getEditor().getCaretPosition();
		}else if(event.getCode() == KeyCode.DELETE){
			moveCaretToPos = true;
			caretPos = comboBox.getEditor().getCaretPosition();
		}
		
		if (event.getCode() == KeyCode.RIGHT || 
		    event.getCode() == KeyCode.LEFT  || 
		    event.isControlDown() || 
		    event.getCode() == KeyCode.HOME                || 
		    event.getCode() == KeyCode.END || 
		    event.getCode() == KeyCode.TAB) {
            return;
        }
		
		ObservableList<T> list = FXCollections.observableArrayList();
		for(int i = 0; i < data.size(); i++){
			if(data.get(i).toString().toLowerCase().startsWith(AutoCompleteComboBox.this.comboBox.getEditor().getText().toLowerCase())){
				list.add(data.get(i));
			}		
		}
		String text = comboBox.getEditor().getText();
		
		this.comboBox.setItems(list);
		this.comboBox.getEditor().setText(text);
		if(!moveCaretToPos){
			caretPos = -1;
		}
		moveCaret(text.length());
		if(!list.isEmpty()){
			comboBox.show();
		}
	}

	private void moveCaret(int textLength) {
		if(caretPos == -1){
			comboBox.getEditor().positionCaret(textLength);
		}else{
			comboBox.getEditor().positionCaret(caretPos);
		}
		moveCaretToPos = false;
	}
}
