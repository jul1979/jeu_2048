package atl.g48982.jeu2048.controller;

import atl.g48982.jeu2048.model.Direction;
import atl.g48982.jeu2048.model.Game;
import atl.g48982.jeu2048.view.View;

/**
 * The class in charge of interacting with the view and the game classes
 *
 * @author Jules
 */
public class Controller {

    private final View view;

    private final Game game;

    /**
     * Default constructor
     *
     * @param view the view instance
     * @param game the game instance
     */
    public Controller(View view, Game game) {

        this.view = view;
        this.game = game;

    }

    /**
     * Starts the Game.
     *
     * @param args the main arguments.
     */
    public static void main(String[] args) {

        Game game = new Game();
        View view = new View();
        game.addPropertyChangeListener(view);
        Controller controller = new Controller(view, game);

        int selection;

        controller.view.welcome();

        controller.view.displayBoard(controller.game.numbers());
        controller.view.displayScore(controller.game.score());

        while (!controller.game.isOver()) {

            controller.view.displayInfo();
            try {
                selection = Integer.parseInt(view.getKeyBoard().nextLine());

                switch (selection) {
                    case 1:

                        controller.game.play(Direction.RIGHT);
                        break;

                    case 2:
                        controller.game.play(Direction.LEFT);
                        break;

                    case 3:
                        controller.game.play(Direction.UP);
                        break;

                    case 4:
                        controller.game.play(Direction.DOWN);
                        break;

                    default:
                        controller.view.displayErrorMessage();
                        break;

                }

            } catch (NumberFormatException nbe) {

                controller.view.displayErrorMessage();

            } finally {

                if (!controller.game.isArrayChanged()) {

                    controller.view.impossibleMove();
                }

            }
        }
        controller.view.endMessage(game.gameWon());
    }
}
