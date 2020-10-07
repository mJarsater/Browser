import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Browser extends JFrame {
    private JEditorPane browserWindow;
    private JTextField addressField;


    public Browser(){
        super("Simple Web Browser");
        // --------------- URL FIELD ----------------------
        addressField = new JTextField("Enter a URL..");
        addressField.addActionListener(this:: searchListner);
        add(addressField, BorderLayout.NORTH);
        // --------------- URL FIELD END ------------------

        // ----------------- WINDOW -----------------------

        browserWindow = new JEditorPane();
        browserWindow.setEditable(false);
        browserWindow.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event) {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                loadSite(event.getURL().toString());
            }
            }
        });
        add(new JScrollPane(browserWindow), BorderLayout.CENTER);

        // --------------- WINDOW END ---------------------
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);

    }

    public void searchListner(ActionEvent actionEvent){
        loadSite(actionEvent.getActionCommand());
    }

    private void loadSite(String text){
        try{
            browserWindow.setPage(text);
            addressField.setText(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Browser();
    }
}
