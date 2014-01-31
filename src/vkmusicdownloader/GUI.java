package vkmusicdownloader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author mars
 */

public class GUI {

    private static JFrame mainFrame;
    private JButton button_search; 
    private JButton button_save;
    private JButton button_info;
    private JLabel label_cntTracks;
    private JTextField textField_search;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private DefaultTableModel TM;
    private JPanel jPanel1;
    //private JCheckBox[] checkList;

    GUI_adapter gui_adp;

    private static String[] tableHeader = { "#",
                                            "Artist",
                                            "Track",
                                            "Lenght"}; //,
                                            //"Save"};

    GUI(){

        gui_adp= new GUI_adapter(this);

        mainFrame       = new JFrame();
        button_save     = new JButton();
        button_info     = new JButton();
        button_search   = new JButton();
        jScrollPane1    = new JScrollPane();
        jTable1         = new JTable();
        label_cntTracks = new JLabel();
        jPanel1         = new JPanel();
        textField_search   = new JTextField();

        mainFrame.setTitle("VK Music downloader");
        mainFrame.setPreferredSize(new Dimension(950,480));
        mainFrame.setMinimumSize(new Dimension(950,480));
        mainFrame.setLayout(new BorderLayout());

        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_search.setText("Search");
        button_search.setSize(100,25);
        button_search.setEnabled(true);
        button_search.addActionListener(new Button_search_Listener());

        
        button_save.setText("Save");
        button_save.setSize(100,25);
        button_save.setEnabled(true);
        button_save.addActionListener(new Button_save_Listener());

        button_info.setText("Info");
        button_info.setSize(100,25);
        button_info.addActionListener(new Button_info_Listener());

        label_cntTracks.setText("Tracks: ");
        label_cntTracks.setHorizontalAlignment(JLabel.CENTER);
        label_cntTracks.setPreferredSize(new Dimension(100, 25));

        textField_search.setMinimumSize(new Dimension(300, 25));
        textField_search.setPreferredSize(new Dimension(300, 25));

        
        Object[] columnNames = tableHeader;
        Object[][] data = {  {"","","","",""}//,null}
                            ,{"","","","",""}};//,null}};
        TableModel model = new DefaultTableModel(data, columnNames);
        jTable1 = new JTable(model);
        jScrollPane1 = new JScrollPane(jTable1);
        mainFrame.getContentPane().add(jScrollPane1);
        TM=(DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setMaxWidth(25);
        jTable1.getColumnModel().getColumn(1).setMinWidth(250);
        jTable1.getColumnModel().getColumn(2).setMinWidth(250);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(50);
        //jTable1.getColumnModel().getColumn(4).setMinWidth(40);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        //jTable1.getColumnModel().getColumn(4).setResizable(false);

        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(button_info);
        jPanel1.add(button_search);
        jPanel1.add(textField_search);
        jPanel1.add(button_save);
        jPanel1.add(label_cntTracks);

        mainFrame.getContentPane().add(jPanel1,BorderLayout.NORTH);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public void clearTable(){
        TM.setRowCount(0);
    }

    public void resetTable(){
        clearTable();
        TM.addRow(new String[]{"","","","","",});
        TM.addRow(new String[]{"","","","",""});
    }

    public void fillTable(int size, String[][] data, JCheckBox[] check){
        for (int i=0;i<size;i++)
            TM.addRow( new Object[]{ data[i][0],data[i][1],data[i][2],data[i][3]}  );
    }

    public void revalidateTable(){
        jTable1.revalidate();
        jTable1.repaint();
        mainFrame.repaint();
    }

    public void repaintJPanel(){
        jPanel1.repaint();
    }

    public String getTxtSearch(){
        return textField_search.getText();
    }

    public JFrame getMainFrame(){
        return this.mainFrame;
    }

    public void setLabel(String txt){
        label_cntTracks.setText(txt);
        label_cntTracks.repaint();
    }
    /*
    public JCheckBox[] getCheckList(){
        return checkList;
    }

    public void setCheckList(JCheckBox[] checkList){
        this.checkList = checkList;
    }
*/
    private class Button_info_Listener implements ActionListener  {

        public Button_info_Listener() {}

        public void actionPerformed(ActionEvent e) {
            gui_adp.button_info();
        }
    }

    private class Button_save_Listener implements ActionListener  {

        public Button_save_Listener() {}

        public void actionPerformed(ActionEvent e) {
            gui_adp.doSave();
        }
    }

    private class Button_search_Listener implements ActionListener  {

        public Button_search_Listener() {}

        public void actionPerformed(ActionEvent e) {
            gui_adp.doSearch();
        }
    }
}
