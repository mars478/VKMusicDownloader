package vkmusicdownloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mars
 */
public class GUI_adapter {

    private GUI gui;
    private VKApi vka;
    private JCheckBox[] cbx;
    private LinkedList<Track> llt;

    GUI_adapter(GUI gui){
        this.gui = gui;
        vka = new VKApi();
    }

    public void doSearch(){
        String req = gui.getTxtSearch();

        try {
            llt = vka.makeTrackList(vka.searchAudio(req, 1000));

            cbx = new JCheckBox[llt.size()];
            String[][] data = new String[llt.size()][4];
            for (int i=0;i<llt.size();i++)
            {
                cbx[i] = new JCheckBox();
                cbx[i].setEnabled(true);
                cbx[i].setSelected(true);

                Track tmp = llt.get(i);

                data[i] = new String[] {""+(i+1),tmp.getArtist(),tmp.getTitle(),""+tmp.getDuration()};
            }


            //gui.setCheckList(cbx);
            gui.clearTable();
            gui.fillTable(llt.size(), data, cbx);
            gui.setLabel("Tracks:"+llt.size());


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() , "Error" ,JOptionPane.ERROR_MESSAGE);
        }

    }

    public void doSave() {

        try {

            gui.setLabel("Downloading...");
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(gui.getMainFrame());

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File dir = fc.getSelectedFile();

                for (int i=0;i<llt.size();i++)
                {
                    gui.setLabel("Done: " + i + "\\" + llt.size());
                    gui.repaintJPanel();
                    Track trck = llt.get(i);
                    File temp = new File(dir.getAbsoluteFile()+"\\"+checkSymbols(trck.getTitle())+".mp3");
                    FileUtils.copyURLToFile(new URL(trck.getUrl()), temp);
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() , "Error" ,JOptionPane.ERROR_MESSAGE);
        } finally {
            gui.setLabel("Tracks:"+llt.size());
        }

    }

    public void button_info(){
        new Copyright();
    }

    private String checkSymbols(String inp) {
        //stolen from http://habrahabr.ru/post/183546/
        String[] wrongSmb = new String[] {"<", ">", ":", "\"", "/", "\\", "|", "?", "*"};
        for (String smb : wrongSmb) {
            inp = inp.replace(smb, "");
        }
        if (inp.length()>50)
            inp = inp.substring(0, 49);
        return inp;
    }
}
