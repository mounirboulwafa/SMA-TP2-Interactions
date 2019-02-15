package containers;

import agents.Buyer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyerContainer extends Application {
    private Buyer buyerAgent;
    private ObservableList<String> observableList;

    public Buyer getBuyerAgent() {
        return buyerAgent;
    }

    public void setBuyerAgent(Buyer buyerAgent) {
        this.buyerAgent = buyerAgent;
    }

    public static void main(String[] args) {
        launch(BuyerContainer.class);
    }

    private void startContainer() throws Exception {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl(false);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);

        AgentController agentController = agentContainer.createNewAgent("Buyer", "agents.Buyer", new Object[]{this});
        agentController.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* Start Container */
        startContainer();

        primaryStage.setTitle("Agent : Buyer");
        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        Label label = new Label("Livre ");
        final TextField textField = new TextField();
        Button button = new Button(" Acheter ");

        hBox.getChildren().addAll(label, textField, button);
        borderPane.setTop(hBox);

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);

        ObservableList<String> observableList = FXCollections.observableArrayList();
        ListView<String> messagesList = new ListView<String>(observableList);
        gridPane.add(messagesList, 0, 0);
        vBox.getChildren().add(gridPane);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String livre = textField.getText();
//                observableList.add(livre);
                GuiEvent guiEvent = new GuiEvent(this, 1);
                guiEvent.addParameter(livre);
                buyerAgent.onGuiEvent(guiEvent);
                textField.setText("");
            }
        });
    }

    public void viewMessage(GuiEvent guiEvent) {
        String message = guiEvent.getParameter(0).toString();
        observableList.add(message);

    }
}
