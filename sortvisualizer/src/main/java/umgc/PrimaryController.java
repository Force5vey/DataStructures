package umgc;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController
{

    @FXML
    public static void displayArray(int[] arr)
    {
        // placeholder for the sort visualizations.
        // They will need to get shifted to their own page.
    }

    @FXML
    private void handleGoToBST() throws IOException
    {
        App.setRoot("bst");
    }

    @FXML
    private void handleGoToHuffman() throws IOException
    {
        App.setRoot("huffman");
    }
}
