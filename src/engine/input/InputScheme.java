package engine.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

import java.util.HashMap;

public class InputScheme implements EventHandler<KeyEvent>
{
    @Override
    public void handle(KeyEvent event)
    {
        if(keyMap.containsKey(event.getCode())) {
            Pair<InputAction, Object[]> actionIdentifier = keyMap.get(event.getCode());
            actionIdentifier.getKey().execute(actionIdentifier.getValue());
        }
    }

    private HashMap<KeyCode, Pair<InputAction, Object[]>> keyMap;

    public InputScheme()
    {
        keyMap = new HashMap<>();
    }

    public void setKeyAction(KeyCode eventCode, InputAction action, Object ... actionParameters)
    {
        keyMap.put(eventCode, new Pair<>(action, actionParameters));
    }

    public void removeKeyEvent(KeyCode eventCode)
    {
        keyMap.remove(eventCode);
    }


}
