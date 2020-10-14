import javax.swing.*;
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
    private JButton htmlBtn;


    public Browser(){
        super("Simple Web Browser");
        // --------------- URL FIELD ----------------------
        addressField = new JTextField("https://dsv.su.se/");
        add(addressField, BorderLayout.NORTH);
        htmlBtn = new JButton("HTML Renderer");
        add(htmlBtn, BorderLayout.SOUTH);
        htmlBtn.addActionListener(this:: htmlBtnListner);
        // --------------- URL FIELD END ------------------

        // ----------------- WINDOW -----------------------

        browserWindow = new JEditorPane();
        browserWindow.setEditable(false);




        addressField.addActionListener(this:: browserListner);
        add(new JScrollPane(browserWindow), BorderLayout.CENTER);

        // --------------- WINDOW END ---------------------
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 500);

    }

    /* Metod som sätter contenttype på Editorpane browserwindow
    * till html eller plain */
    public void htmlBtnListner(ActionEvent event){
        if(browserWindow.getContentType() != "text/html"){
            browserWindow.setContentType("text/html");
        } else {
            browserWindow.setContentType("text/plain");
        }
        browserListner(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));


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