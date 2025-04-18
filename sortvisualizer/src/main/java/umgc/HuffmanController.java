package umgc;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import umgc.models.HuffmanCode;

import java.io.IOException;

public class HuffmanController
{

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputArea;

    @FXML
    private void handleGenerate()
    {
        String text = inputField.getText();
        if (text == null || text.isEmpty())
        {
            outputArea.setText("Please enter some text.");
            return;
        }

        int[] counts = HuffmanCode.getCharacterFrequency(text);
        HuffmanCode.Tree tree = HuffmanCode.getHuffmanTree(counts);
        String[] codes = HuffmanCode.getCode(tree != null ? tree.root : null);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code"));
        for (int i = 0; i < codes.length; i++)
        {
            if (counts[i] != 0)
            {
                sb.append(String.format("%-15d%-15s%-15d%-15s\n", i, (char) i + "", counts[i], codes[i]));
            }
        }
        outputArea.setText(sb.toString());
    }

    @FXML
    private void handleBack() throws IOException
    {
        App.setRoot("primary");
    }
}
