

package atl.g48982.jeu2048.fxview;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *The main buttons.
 * @author Jules
 */
public class EsiButton extends HBox {
    
    private Button start;
    private Button stop;

    /**
     *Default Constructor.
     */
    public EsiButton() {    
        super();
        start=new Button("START");
        stop=new Button("STOP");
        this.setId("mybuttons");
        this.getChildren().addAll(start,stop);
    }

    /**
     *Accessor for start button.
     * @return the start button.
     */
    public Button getStart() {
        return start;
    }

    /**
     *Accessor for stop button.
     * @return the stop button.
     */
    public Button getStop() {
        return stop;
    }

    
}
