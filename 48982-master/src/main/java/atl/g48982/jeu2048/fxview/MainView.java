package atl.g48982.jeu2048.fxview;

import atl.g48982.jeu2048.fxcontroller.ControllerFx;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Central Node.
 *
 * @author Jules
 */
public class MainView implements PropertyChangeListener {

    private Stage stage;
    private EsiGridPane esigridpane;
    private EsiListView esilistview;
    private EsiButton esibuttons;
    private ControllerFx controllerFx;

    /**
     * Constuctor, creates a scene and passes it to the stage.
     *
     * @param aStage the stage to set the scene on.
     */
    public MainView(Stage aStage) {

        VBox root = new VBox();
        initialize(root);
        Scene scene = new Scene(root, 1000, 750);
        this.stage = aStage;
        stage.setTitle("HEB ESI-2048");
        root.getStylesheets().add("mystyle.css");
        stage.setScene(scene);
        stage.setFullScreen(true);

    }

    /**
     * Updates the values on squares.
     *
     * @param tab a 2D array of numbers.
     */
    public void fill(int[][] tab) {

        for (Node node : esigridpane.getChildren()) {

            if (node instanceof Square) {

                ((Square) node).setValue(tab[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]);

            }
        }

    }

    /**
     * Sets the colors back to the default settings at the beginning of a round.
     */
    private void resetColors() {

        esigridpane.getChildren().stream()
                .filter(node -> (node instanceof Square))
                .forEachOrdered(node -> {
                    ((Square) node).addStyle(updateColors(0));
                });

    }

    /**
     * Updates the colors and the values on squares as the game evolves.
     *
     * @param tab ,the 2D array of numbers to be updated.
     */
    public void fillColours(int[][] tab) {

        for (Node node : esigridpane.getChildren()) {

            if (node instanceof Square) {

                ((Square) node).setValue(tab[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]);
                ((Square) node).addStyle(updateColors(tab[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)]));

            }
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        this.fillColours((int[][]) evt.getNewValue());
    }

    /**
     * Configurates the main Node of the Application.
     *
     * @param layout the VBOX the main container layout.
     */
    private void initialize(VBox layout) {

        esigridpane = new EsiGridPane();
        esilistview = new EsiListView();
        esibuttons = new EsiButton();
        HBox esigridlistpane = new HBox(esigridpane, esilistview);
        esigridlistpane.setId("gridandlist");
        layout.setId("topcontainer");
        layout.getChildren().addAll(esigridlistpane, esibuttons);

        EventHandler<KeyEvent> keyEventHandler
                = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent k) {

                if (k.getEventType() == KeyEvent.KEY_RELEASED) {
                    controllerFx.isValidKey(k.getCode());
                }

            }

        };

        esibuttons.getStart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                actionStart();
                controllerFx.initGame();
                layout.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler);

            }
        });

        esibuttons.getStop().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                actionStop();
                layout.removeEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler);
                controllerFx.setNewGame();
            }

        });

    }

    /**
     * Displays an error message on the listview whenever an invalid key is
     * typed.
     */
    public void displayErrorMessage() {
        esilistview.addItem(Message.ERROR.value());
    }

    /**
     * Displays a winning message on the listview whenever there is a winner.
     */
    public void winnerMessage() {
        esilistview.addItem(Message.WON.value());
    }

    /**
     * Displays a loosing message on the listview whenever the round ends
     * without a winner.
     */
    public void looserMessage() {
        esilistview.addItem(Message.LOST.value());
    }

    /**
     * Displays a message on the listview whenever the player attempts an
     * invalid move.
     */
    public void impossibleMessage() {
        esilistview.addItem(Message.IMPOSSIBLE.value());
    }

    /**
     * Sets the default settings before a round of the game can begin.
     */
    private void actionStart() {

        esigridpane.setFocusTraversable(true);
        esilistview.emptyList();
        esilistview.addItem(Message.WELCOME.value());
        esibuttons.getStart().setDisable(true);
        esibuttons.getStop().setDisable(false);

    }

    /**
     * Updates the interface on the context of a round of the game prematurely
     * ending following a player action.
     */
    public void actionStop() {
        esibuttons.getStart().setDisable(false);
        esibuttons.getStop().setDisable(true);
        esigridpane.setFocusTraversable(false);
        esilistview.addItem(Message.GAMEOVER.value());
        if (controllerFx.gameHasWinner()) {
            winnerMessage();
        } else {
            looserMessage();
        }
        resetColors();
    }

    /**
     * Shows the stage.
     */
    public void showStage() {
        stage.show();
    }

    /**
     * Sets the controller for this View.
     *
     * @param aControllerFx a Controller.
     */
    public void setController(ControllerFx aControllerFx) {
        this.controllerFx = aControllerFx;
        this.controllerFx.addPropertyChangeListener(this);
    }

    /**
     * Gets the identification of css style ID depending on the value passed.
     *
     * @param value a number.
     * @return the denomination of the style.
     */
    public String updateColors(int value) {

        String result;

        switch (value) {

            case 2:
                result = "two";
                break;

            case 4:
                result = "four";
                break;
            case 8:
                result = "eight";
                break;
            case 16:
                result = "sixteen";
                break;
            case 32:
                result = "threetwo";
                break;
            case 64:
                result = "sixfour";
                break;
            case 128:
                result = "onetwoeight";
                break;
            case 256:
                result = "twofivesix";
                break;
            case 512:
                result = "fiveonetwo";
                break;
            case 2048:
                result = "maximum";
                break;
            default:
                result = "zero";
                break;

        }

        return result;
    }

}
