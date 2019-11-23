import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{

    JFrame frame;
    JPanel panel1;
    JPanel panel3;
    JPanel panel4;
    JLabel jLOpenFile;
    JTextField jTFReadFile;
    JButton jBRun;
    JLabel jLString;
    JTextField jTFReadString;
    JLabel jLResult; 
    JLabel hola;

    public Main(){
        InitComponents();
    }

    private void InitComponents(){
        frame = new JFrame("Layout");
        panel1 = new JPanel();
        //JPanel panel2 = new JPanel();
        //panel2.setBackground(Color.red);
        panel3 = new JPanel();
        panel3.setBackground(Color.blue);
        panel4 = new JPanel();
        

        jLOpenFile = new JLabel("Insert Name of File: ");
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

        jLResult = new JLabel("NADA");
        hola = new JLabel("NADA2");


        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Push Down Automaton - Nicole and Natalia");
        frame.setSize(600,600);
        panel1.setPreferredSize( new Dimension(500,50));
        panel4.setPreferredSize( new Dimension(500,50));
        //jBCheck.setPreferredSize(new Dimension(300,100));

        frame.add(panel1, BorderLayout.NORTH);
        //frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel4, BorderLayout.SOUTH);
        panel1.add(jLOpenFile);
        panel1.add(jTFReadFile);
       // panel2.add(jLResult);
        //panel3.add(jLResult);
        panel4.add(jLString);
        panel4.add(jTFReadString);
        panel4.add(jBRun);

        frame.setVisible(true);
   
    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == jBRun){
            String fileName = jTFReadFile.getText();
            String p = jTFReadString.getText();
            PushDownAutomaton pda = new PushDownAutomaton(fileName,p);
            java.util.List<String> moves =  pda.moves;
            int dimension1 = moves.size();
            System.out.println(dimension1);
            panel3.setLayout(new GridLayout(dimension1,1));
            //panel3.add(JLResult);
            panel3.add(hola);
            panel3.add()
            /*for(String m : moves){
                jLResult = new JLabel("HOLA");
                jLResult.setText(m);
                panel3.add(jLResult);
            }*/


            /*StringBuilder result = new StringBuilder();
           
            jLResult.setText("¿Como tan?");*/
            //"<html>" + result.toString() + "</html>"
        }
    }

    

    public static void main(String[]args){
        new Main();
    }
   
}