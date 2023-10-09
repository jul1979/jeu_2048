
package atl.g48982.jeu2048.fxview;

/**
 *The Different messages that can be displayed in the ListView.
 * @author Jules.
 */
public enum Message{

    WELCOME("Bienvenue au 2048."),
    GAMEOVER("Partie terminée."),
    IMPOSSIBLE("Mouvement impossible."),
    LOST("Vous avez perdu."),
    REPLAY("Partie recommencée."),
    ERROR("Commande invalide"),
    WON("Vous avez gagné.");
    

    /**
     *Default Constructor.
     *@param value the String value.
     */
    Message(String value) {
        this.value = value;
    }

    private String value;

    /**
     *Accessor for value.
     *@return the String value.
     */
    public String value() {
        return value;
    }

}
