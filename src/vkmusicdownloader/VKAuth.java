package vkmusicdownloader;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mars
 */
public class VKAuth {
    private static JFrame mainFrame;
    private JButton button_done; 
    private JLabel label_link;
    private JPanel jPanel1;
    private JTextField textField_response;
    
    private VKApi vka;
    private GUI gui;
    
    VKAuth(GUI_adapter ga){
        
        this.vka = ga.getVKA();
        this.gui = ga.getGUI();
        
        mainFrame       = new JFrame();
        button_done     = new JButton();
        label_link      = new JLabel();
        jPanel1         = new JPanel();
        textField_response   = new JTextField();
        
        mainFrame.setTitle("VK Music downloader");
        mainFrame.setPreferredSize(new Dimension(250,150));
        mainFrame.setMinimumSize(new Dimension(250,150));
        mainFrame.setLayout(new BorderLayout());
        
        label_link.setText("<html>Follow the link: <a href=https://oauth.vk.com/authorize?client_id=4156308&scope=audio&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.7&response_type=token>click</a> </html>");
        label_link.setPreferredSize(new Dimension(200, 25));
        label_link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://oauth.vk.com/authorize?client_id=4156308&scope=audio&redirect_uri=https://oauth.vk.com/blank.html&display=popup&v=5.7&response_type=token"));
                    } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() , "Error" ,JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        textField_response.setMinimumSize(new Dimension(200, 25));
        textField_response.setPreferredSize(new Dimension(200, 25));
        
        button_done.setText("Done");
        button_done.setSize(100,25);
        button_done.setEnabled(true);
        button_done.addActionListener(new Button_done_Listener());
        
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(label_link);
        jPanel1.add(textField_response);
        jPanel1.add(button_done);

        mainFrame.getContentPane().add(jPanel1);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void close(){
        mainFrame.dispose();
    }
    private class Button_done_Listener implements ActionListener {

        public Button_done_Listener() {}

        public void actionPerformed(ActionEvent e) {
            if (!vka.setATokenRequest(textField_response.getText())){
                gui.rollback();
                return;
            }
            if (vka.auth2()) 
                gui.setSearchEnabled(true);
            else
                gui.setSearchEnabled(false);
            close();
            
        }
    }
}
