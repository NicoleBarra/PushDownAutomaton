import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{

    JFrame frame;
    JPanel panel1, panel2;
    JLabel jLOpenFile, jLString;
    JTextField jTFReadFile;
    JButton jBRun;
    JTextField jTFReadString;
    JList list;
    DefaultListModel listModel;
    JScrollPane listScrollPane;

    public Main(){
        InitComponents();
    }

    private void InitComponents(){
        frame = new JFrame("Layout");
        panel1 = new JPanel();
        panel2 = new JPanel();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setFont(list.getFont().deriveFont(18f));
        listScrollPane = new JScrollPane(list);

        jLOpenFile = new JLabel("Insert name of the file: ");
        jLOpenFile.setFont(jLOpenFile.getFont().deriveFont(20f));

        jTFReadFile = new JTextField("example.txt");
        jTFReadFile.setPreferredSize(new Dimension(200,40));
        jTFReadFile.setFont(jTFReadFile.getFont().deriveFont(20f));

        jBRun = new JButton("Run");
        jBRun.setFont(jBRun.getFont().deriveFont(20f));
        jBRun.setPreferredSize(new Dimension(100,40));
        jBRun.addActionListener(this);

        jLString = new JLabel("Insert string to test: ");
        jLString.setFont(jLString.getFont().deriveFont(20f));
        jLString.setFont(jLString.getFont().deriveFont(20f));

        jTFReadString = new JTextField();
        jTFReadString.setPreferredSize(new Dimension(200,40));
        jTFReadString.setFont(jTFReadString.getFont().deriveFont(20f));

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Push Down Automaton - Nicole and Natalia");
        frame.setSize(600,600);
        panel1.setPreferredSize( new Dimension(500,50));
        panel2.setPreferredSize( new Dimension(500,50));
      
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        panel1.add(jLOpenFile);
        panel1.add(jTFReadFile);
        panel2.add(jLString);
        panel2.add(jTFReadString);
        panel2.add(jBRun);

        frame.setVisible(true);
   
    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == jBRun){
            String fileName = jTFReadFile.getText();
            String p = jTFReadString.getText();
            PushDownAutomaton pda = new PushDownAutomaton(fileName,p);
            java.util.List<String> moves =  pda.moves;
        
            listModel.addElement("  ");
            for( String m : moves){
                listModel.addElement(m);
            }

        }
    }

    

    public static void main(String[]args){
        new Main();
    }
   
}