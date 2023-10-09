package atl.g48982.jeu2048.fxview;

import atl.g48982.jeu2048.fxcontroller.ControllerFx;
import atl.g48982.jeu2048.model.Game;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *This is the main class for the Graphical user Interface.
 * @author Jules Cesar
 */
public class CheckersApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Game game = new Game();
        MainView mainView = new MainView(stage);
        ControllerFx controllerFx = new ControllerFx(mainView, game);
        mainView.setController(controllerFx);
        mainView.showStage();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
