package umgc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import umgc.searches.BST;
import umgc.views.TreeVisualization;

import java.net.URL;
import java.util.ResourceBundle;

public class BSTController implements Initializable
{
    @FXML
    private Label statusLabel;
    @FXML
    private Pane treePane;
    @FXML
    private TextField keyField;

    private BST<Integer> tree;
    private TreeVisualization treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tree = new BST<>();
        treeView = new TreeVisualization(tree);
        treeView.prefWidthProperty().bind(treePane.widthProperty());
        treeView.prefHeightProperty().bind(treePane.heightProperty());
        treePane.getChildren().add(treeView);
        treeView.setStatus("Tree is empty.");
    }

    @FXML
    private void handleInsert()
    {
        String text = keyField.getText();
        try
        {
            int key = Integer.parseInt(text);
            if (tree.search(key))
            {
                statusLabel.setText(key + " is already in the tree");
            } else
            {
                tree.insert(key);
                statusLabel.setText(key + " is inserted in the tree");
            }
            treeView.displayTree();
        } catch (NumberFormatException e)
        {
            statusLabel.setText("Please enter a valid integer.");
        }
        keyField.clear();
    }

    @FXML
    private void handleDelete()
    {
        String text = keyField.getText();
        try
        {
            int key = Integer.parseInt(text);
            if (!tree.search(key))
            {
                statusLabel.setText(key + " is not in the tree");
            } else
            {
                tree.delete(key);
                statusLabel.setText(key + " is deleted from the tree");
            }
            treeView.displayTree();
        } catch (NumberFormatException e)
        {
            statusLabel.setText("Please enter a valid integer.");
        }
        keyField.clear();
    }
}
