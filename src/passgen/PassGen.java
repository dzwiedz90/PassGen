package passgen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

public class PassGen extends JFrame implements ActionListener{
    JButton gen = new JButton("Generate new");
    JTextField display = new JTextField(25);
    
    public PassGen(){
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
    
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        
        int [] drawnNumbers = drawNumbers();
        String drawnCharacters = drawCharacters(drawnNumbers);
        
        if(source==gen){
            display.setText(drawnCharacters);
        }
    }
    
    private static int[] drawNumbers(){
        //Losowanie bez powtórzeń tabeli cyfr, które będą oznaczać indeksy znakow pobieranych z tabeli textTable
        int [] drawnNum = new int[8];
        int min = 0;
        int max = 49;
        //true będzie oznaczać, że dana liczba jest już w tabeli, false, że jej nie ma
        boolean ifExists = true;
        
        for(int i = 0; i<drawnNum.length;){
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            //Sprawdzenie, czy dana liczba jest już w tabeli
            for(int j = 0; j<drawnNum.length; j++){
                if(randomNum==drawnNum[j])
                    ifExists = true;
                else
                    ifExists = false;
            }
            
            if(ifExists==true)
                continue;
            else if(ifExists==false){
                drawnNum[i] = randomNum;
                i++;
            }
        }
        
        return drawnNum;
    }
    
    private static String drawCharacters(int[] drawnNums){
        int [] drawnNumbers = drawnNums;

        //String, który będzie zwrócony
        String text = new String();
        //Tablca z danymi, które będą pobierane
        String [] textTable = {"a","b","c","d","e","f","g","h","j","k","m","n","p","r","s","t","u","w","y","z",
        "A","B","C","D","E","F","G","H","J","K","L","M","N","P","R","S","T","U","W","Y","Z","2","3","4","5","6","7","8","9"};
        
        for(int i = 0; i<drawnNumbers.length; i++){
            int a = drawnNumbers[i];
            text += textTable[a];
        }
        
        return text;
    }
    
    private static void setLookAndFeel(){
       try{
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
       }
       catch(Exception exc)
       {}
    }
    
    public static void main(String[] args){
        PassGen pg = new PassGen();
    }
}