package atl.g48982.jeu2048.fxview;

import java.time.LocalTime;
import javafx.scene.control.ListView;

/**
 * This is a List View of strings.
 */
public class EsiListView extends ListView<String> {

    final LocalTime currentTime;

    /**
     * Default Constructor.
     */
    public EsiListView() {

        super();
        setEditable(false);
        setFocusTraversable(false);
        currentTime = LocalTime.now();
    }

    /**
     * adds a String to the ListView.
     *
     * @param text the string to add to the list.
     */
    public void addItem(String text) {
        this.getItems().add(getLocalTime() + "   -" + text);
    }

    /**
     * gets the local actual time.
     *
     * @return the local actual time.
     */
    private String getLocalTime() {

        return (currentTime.getHour() + ":" + currentTime.getMinute()
                + ":" + currentTime.getSecond());
    }

    /**
     * Removes all items from the list view.
     */
    public void emptyList() {
        this.getItems().clear();
    }
}
