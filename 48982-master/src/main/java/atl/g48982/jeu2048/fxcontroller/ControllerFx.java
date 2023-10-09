package atl.g48982.jeu2048.fxcontroller;

import atl.g48982.jeu2048.fxview.MainView;
import atl.g48982.jeu2048.model.Direction;
import atl.g48982.jeu2048.model.Game;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Jules
 */
public class ControllerFx {

    private MainView mainview;

    private Game game;

    /**
     *
     * Default Constructor.
     *
     * @param mainview the gui view.
     * @param game the model.
     */
    public ControllerFx(MainView mainview, Game game) {

        Objects.requireNonNull(mainview, "mainview must not be null");
        Objects.requireNonNull(game, "game must not be null");
        this.mainview = mainview;
        this.game = game;

    }

    /**
     * Add a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        game.addPropertyChangeListener(listener);
    }

    /**
     * Validates a keyEvent based on the KeyCode.
     *
     * @param code the keycode value.
     */
    public void isValidKey(KeyCode code) {

        Objects.requireNonNull(code);
        try {

            switch (code) {

                case UP:
                    game.play(Direction.UP);
                    checkChanges();
                    break;
                case LEFT:
                    game.play(Direction.LEFT);
                    checkChanges();
                    break;
                case RIGHT:
                    game.play(Direction.RIGHT);
                    checkChanges();
                    break;
                case DOWN:
                    game.play(Direction.DOWN);
                    checkChanges();
                    break;
                default:
                    mainview.displayErrorMessage();
                    break;
            }

        } catch (Exception e) {
            mainview.displayErrorMessage();
        } finally {

            if (game.isOver()) {
                mainview.actionStop();
            }

        }
    }

    /**
     * Calls for the view to show initial state before game can begin.
     */
    public void initGame() {
        mainview.fill(game.numbers());
    }

    /**
     * Calls for the game to set itself in the beginning state.
     */
    public void setNewGame() {
        game.initNewGame();
    }

    /**
     * Checks for a Winner in the game.
     *
     * @return true if there is any false otherwise.
     */
    public boolean gameHasWinner() {
        return game.gameWon();
    }

    /**
     * Interface updater as a consequence of any changes occuring in the game.
     */
    private void checkChanges() {

        if (!game.isArrayChanged()) {
            mainview.impossibleMessage();
        }
    }

}
