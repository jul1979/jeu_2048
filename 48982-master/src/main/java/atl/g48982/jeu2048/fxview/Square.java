
package atl.g48982.jeu2048.fxview;


import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents a Tile on a grid. 
 * @author Jules
 */
public class Square extends StackPane {
    
    private int value;
    private Text number;
    private  Rectangle box ;


    /**
     *Default Constructor.
     */
    public Square() {
    
        super();
        box = new Rectangle(100,100);
        box.getStyleClass().add("mybox");
        this.value = 0;
        number = new Text(changeToString(value));
        number.setId("mynumber");
        getChildren().addAll(box,number);
    }
    
    /**
     *Adds a style contextually.
     *@param styleId the css style identification.
     */
    public void addStyle(String styleId){
      box.setId(styleId);
    }
    
    
    
  
     
     

    /**
     *Changes an integer into a String.
     * @param number the number to change into a String.
     * @return the String represantation for that number.
     */
    private String changeToString(int number){
        return Integer.toString(number);
   }

    /**
     * updates the value attribute with a new number.
     * @param value the actual value
     */
    public void setValue(int value) {
        this.value = value;
        this.number.setText(changeToString(value));
    }
    
    
    

}


