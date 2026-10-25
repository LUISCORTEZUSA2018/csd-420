
/**
 * Student: Luis Leonardo Cortez Velez
 * University: Bellevue University
 * Course: CSD420-300H - Advanced Java Programming
 * Instructor: Josh Guardino
 * Assignment: Module 1.3 - Random Playing Cards
 * Date: October 24, 2026
 *
 * Description:
 * This JavaFX application displays four randomly selected
 * playing cards from a standard deck of 52 cards.
 *
 * The user can click the Refresh Cards button to display
 * another random selection of four different cards.
 *
 * Card images are stored in the project's resources/cards
 * directory. A lambda expression handles the button event.
 */

package com.luiscortez.module1_3;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main JavaFX application for the Module 1.3 assignment.
 *
 * @author Luis Leonardo Cortez Velez
 */
public class App extends Application {

    // Total number of cards in a standard playing card deck.
    private static final int TOTAL_CARDS = 52;

    // Number of cards displayed at one time.
    private static final int DISPLAY_COUNT = 4;

    // Dimensions used to display each playing card.
    private static final double CARD_WIDTH = 120;
    private static final double CARD_HEIGHT = 175;

    // Container for displaying the four playing cards.
    private final HBox cardContainer = new HBox(15);

    /**
     * Creates and displays the main JavaFX application window.
     *
     * @param stage the primary application window
     */
    @Override
    public void start(Stage stage) {

        // Create the title displayed at the top of the window.
        Label titleLabel = new Label("Random Playing Cards");

        titleLabel.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #243447;"
        );

        // Create instructions for the user.
        Label instructionLabel = new Label(
                "Four randomly selected cards from a standard 52-card deck."
        );

        instructionLabel.setStyle(
                "-fx-font-size: 14px;"
                + "-fx-text-fill: #555555;"
        );

        // Center the playing cards horizontally.
        cardContainer.setAlignment(Pos.CENTER);

        // Create the Refresh Cards button.
        Button refreshButton = new Button("Refresh Cards");

        refreshButton.setStyle(
                "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-color: #2563A6;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 6;"
                + "-fx-padding: 10 20 10 20;"
        );

        // Lambda expression for the button's action event.
        refreshButton.setOnAction(event -> displayRandomCards());

        // Arrange all interface elements vertically.
        VBox root = new VBox(
                25,
                titleLabel,
                instructionLabel,
                cardContainer,
                refreshButton
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.setStyle("-fx-background-color: #F5F7FA;");

        // Display four random cards when the application starts.
        displayRandomCards();

        // Create the application scene.
        Scene scene = new Scene(root, 650, 420);

        // Configure and display the application window.
        stage.setTitle("CSD420 - Module 1.3 Playing Cards");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Randomly selects four unique cards and displays them.
     *
     * Collections.shuffle() randomizes the order of the deck.
     * Selecting the first four cards ensures no duplicates
     * appear within the same selection.
     */
    private void displayRandomCards() {

        // Remove the previously displayed cards.
        cardContainer.getChildren().clear();

        // Create a list representing all 52 playing cards.
        List<Integer> deck = new ArrayList<>();

        for (int cardNumber = 1; cardNumber <= TOTAL_CARDS; cardNumber++) {
            deck.add(cardNumber);
        }

        // Randomize the order of the playing cards.
        Collections.shuffle(deck);

        // Display the first four cards from the shuffled deck.
        for (int index = 0; index < DISPLAY_COUNT; index++) {

            int selectedCard = deck.get(index);

            // Build the image path inside resources/cards.
            String imagePath = "/cards/" + selectedCard + ".png";

            try (InputStream imageStream =
                    getClass().getResourceAsStream(imagePath)) {

                // Check whether the requested image exists.
                if (imageStream == null) {
                    throw new IllegalStateException(
                            "Card image not found: " + imagePath
                    );
                }

                // Load the selected playing card image.
                Image cardImage = new Image(imageStream);

                // Check whether JavaFX successfully loaded the image.
                if (cardImage.isError()) {
                    throw new IllegalStateException(
                            "Unable to load card image: " + imagePath,
                            cardImage.getException()
                    );
                }

                // Create the visual representation of the card.
                ImageView cardView = new ImageView(cardImage);

                // Set consistent dimensions for all cards.
                cardView.setFitWidth(CARD_WIDTH);
                cardView.setFitHeight(CARD_HEIGHT);
                cardView.setPreserveRatio(true);
                cardView.setSmooth(true);

                // Add the selected card to the display.
                cardContainer.getChildren().add(cardView);

            } catch (Exception exception) {

                // Report image-loading errors to the console.
                System.err.println(
                        "Error displaying card "
                        + selectedCard + ": "
                        + exception.getMessage()
                );

                exception.printStackTrace();

                // Display a message instead of leaving an empty space.
                Label errorLabel = new Label(
                        "Card " + selectedCard + "\nnot available"
                );

                errorLabel.setMinWidth(CARD_WIDTH);
                errorLabel.setAlignment(Pos.CENTER);

                errorLabel.setStyle(
                        "-fx-text-fill: red;"
                        + "-fx-border-color: gray;"
                        + "-fx-padding: 20;"
                );

                cardContainer.getChildren().add(errorLabel);
            }
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
