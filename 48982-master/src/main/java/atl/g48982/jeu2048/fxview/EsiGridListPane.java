
package atl.g48982.jeu2048.fxview;

import javafx.scene.layout.HBox;

/**
 * The central Nodes combined in a Horizontal Box layout.
 *
 * @author Jules.
 */
public class EsiGridListPane extends HBox {

    private EsiGridPane esigridpane;
    private EsiListView esilistview;

    /**
     *Default Constructor.
     */
    public EsiGridListPane() {
        super();
        esigridpane = new EsiGridPane();
        esilistview = new EsiListView();
        this.getChildren().addAll(esigridpane, esilistview);

    }

}

