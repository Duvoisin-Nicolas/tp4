package fr.univ_amu.iut.exercice1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.*;


public class OthelloIHM extends Application {
    private static final int TAILLE = 8;

    private StatusBar statusBar;
    private Othellier othellier;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        statusBar = new StatusBar();
        othellier = new Othellier(this, TAILLE);
        BorderPane root = new BorderPane();

        root.setCenter(othellier);
        root.setBottom(statusBar);
        root.setTop(barreDeMenus());
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateStatus() {
        if (othellier.getJoueurCourant() == Joueur.PERSONNE) {
            afficheDialogFinDePartie();
        }

        statusBar.setJoueurCourant(othellier.getJoueurCourant());
        statusBar.updateStatus();
    }

    private void afficheDialogFinDePartie() {
        String messageFinDePartie;

        if (Joueur.BLANC.getScore() > Joueur.NOIR.getScore())
            messageFinDePartie = "Blanc a gagné !!!";
        else if (Joueur.BLANC.getScore() < Joueur.NOIR.getScore())
            messageFinDePartie = "Noir a gagné !!!";
        else
            messageFinDePartie = "Égalité !!!";

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText(messageFinDePartie);
        Optional<ButtonType> result = alert.showAndWait();
    }

    private MenuBar barreDeMenus() {
        MenuBar barre = new MenuBar();
        barre.getMenus().add(creerMenuJeu());
        return barre;
    }

    private Menu creerMenuJeu() {
        Menu menuJeu = new Menu("Jeu");
        menuJeu.getItems().add(creerMenuJeuNouveau());
        menuJeu.getItems().add(creerMenuJeuQuitter());
        return menuJeu;
    }

    private MenuItem creerMenuJeuNouveau() {
        MenuItem menu = new MenuItem("Nouveau");
        menu.setOnAction(event -> actionMenuJeuNouveau());
        return menu;
    }

    private MenuItem creerMenuJeuQuitter() {
        MenuItem menu = new MenuItem("Quitter");
        menu.setOnAction(event -> actionMenuJeuQuitter());
        return menu;
    }

    private void actionMenuJeuQuitter() {

    }

    private void actionMenuJeuNouveau() {
        othellier.nouvellePartie();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
