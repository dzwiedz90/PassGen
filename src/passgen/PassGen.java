package passgen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class PassGen extends JFrame implements ActionListener
{
    JButton gen = new JButton("Generate new");
    JTextField display = new JTextField(25);
    
    public PassGen()
    {
        super("Password Generator");
        setSize(450, 100);        
        setResizable(false);
        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        setLayout(flow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        display.setEditable(false);
        gen.addActionListener(this);
        pane.add(display);
        pane.add(gen);
        add(pane);
        
        setLookAndFeel();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        Object source = evt.getSource();
        String text = new String();
        String [] textTable = {"a","b","c","d","e","f","g","h","j","k","m","n","p","r","s","t","u","w","y","z",
        "A","B","C","D","E","F","G","H","J","K","L","M","N","P","R","S","T","U","W","Y","Z","2","3","4","5","6","7","8","9"};
        
        for(int i = 0; i<8; i++)
        {
            int min = 0;
            int max = textTable.length;
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            String a = textTable[randomNum];
            text += a;
        }
        
        if(source==gen)
        {
            
            display.setText(text);
        }
    }
    
    private static void setLookAndFeel()
    {
       try
       {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
       }
       catch(Exception exc)
       {}
    }
    
    public static void main(String[] args)
    {
        PassGen pg = new PassGen();
    }
}
