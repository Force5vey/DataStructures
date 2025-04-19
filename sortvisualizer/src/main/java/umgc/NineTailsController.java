package umgc;

import umgc.models.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.util.*;

public class NineTailsController
{
    private char[] state;
    private final NineTailModel model = new NineTailModel();

    @FXML
    private GridPane grid;

    @FXML
    public void initialize()
    {
        int idx = new Random().nextInt(NineTailModel.NUMBER_OF_NODES);
        state = NineTailModel.getNode(idx);

        for (int i = 0; i < 9; i++)
        {
            Button b = new Button(String.valueOf(state[i]));
            int pos = i;
            b.setMinSize(60, 60);
            b.setOnAction(e -> handleFlip(pos));
            grid.add(b, i % 3, i / 3);
        }
    }

    private void handleFlip(int pos)
    {
        int oldIndex = NineTailModel.getIndex(state);
        int newIndex = NineTailModel.getFlippedNode(state.clone(), pos);

        char[] newState = NineTailModel.getNode(newIndex);

        for (int i = 0; i < 9; i++)
        {
            Button b = (Button) grid.getChildren().get(i);
            b.setText(String.valueOf(newState[i]));
            if (newState[i] != state[i])
            {
                b.setStyle("-fx-text-fill: red;");
            } else
            {
                b.setStyle("-fx-text-fill: black;");
            }
        }

        state = newState;
    }

    @FXML
    private void handleShowSolution()
    {
        int currentIndex = NineTailModel.getIndex(state);
        List<Integer> path = model.getShortestPath(currentIndex);
        System.out.println("Moves to goal: " + path);
    }

}
