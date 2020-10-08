import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser extends JFrame {
    private JEditorPane browserWindow;
    private JTextField addressField;


    public Browser(){
        super("Simple Web Browser");
        // --------------- URL FIELD ----------------------
        addressField = new JTextField("Enter a URL..");
        add(addressField, BorderLayout.NORTH);
        // --------------- URL FIELD END ------------------

        // ----------------- WINDOW -----------------------

        browserWindow = new JEditorPane();
        browserWindow.setEditable(false);
        browserWindow.setContentType("text/html");



        addressField.addActionListener(this:: browserListner);
        add(new JScrollPane(browserWindow), BorderLayout.CENTER);

        // --------------- WINDOW END ---------------------
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);

    }
    public void browserListner(ActionEvent event){
        try {

            clearWindow();
            StringBuffer buffer = new StringBuffer();
            String line = "";

            URL url = new URL(addressField.getText());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            while((line = bufferedReader.readLine()) != null){
                buffer.append(line+"\n");
            }
            displayText(buffer.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearWindow() { browserWindow.setText(""); }

    public void displayText(String text){ browserWindow.setText(text); }

    public static void main(String[] args) {
        new Browser();
    }
}
 /*
 * HTMLEditorKit htmlKit = new HTMLEditorKit();
 * browserWindow.setEditorKit(htmlKit);
 *
 * */