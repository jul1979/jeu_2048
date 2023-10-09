
package atl.g48982.jeu2048.fxview;

import javafx.scene.layout.GridPane;

/**
 *The grid of numbers.
 * @author Jules.
 */
public class EsiGridPane extends GridPane {
    
    final  int NBROWS;
    final  int NBCOLUMNS;

    /**
     *Default Constructor.
     */
    public EsiGridPane() {
        super();
        NBCOLUMNS=4;
        NBROWS=4;
        createContent();
        setId("mygridpane");
    }

    /**
     * builds a board of  tiles 
     * each containing a number.
     */
    private void createContent() {

        Square square;

        for (int i = 0; i < NBROWS; i++) {

            for (int j = 0; j < NBCOLUMNS; j++) {

                square = new Square();
                
               GridPane.setRowIndex(square, i);
               GridPane.setColumnIndex(square, j);

                this.getChildren().add(square);


            }
        }

    }
    
   
    
    
    

}


