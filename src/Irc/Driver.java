/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 *
 * 
 */
public class Driver
{
    
    public static final Color FOREGROUND_COLOR = new Color(0xEA, 0xCF, 0x4C);
    public static final Color BACKGROUND_COLOR = new Color(0x00, 0x00, 0x00);
    private static IOHandler client;
    private static JTextField inputfield;
    private static JTextArea outputfield;
    
    public static void main(String[] args)
    {
        JFrame window = new JFrame("IRC");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(900,675));
        panel.setForeground(FOREGROUND_COLOR);
        panel.setBackground(BACKGROUND_COLOR);
        
        inputfield = new JTextField(80);
        inputfield.addActionListener(new Driver().new UserInputAction());
        inputfield.setForeground(FOREGROUND_COLOR);
        inputfield.setBackground(BACKGROUND_COLOR);
        inputfield.setCaretColor(FOREGROUND_COLOR);
        
        outputfield = new JTextArea(40, 80);
        outputfield.setEditable(false);
        outputfield.setLineWrap(true);
        outputfield.setForeground(FOREGROUND_COLOR);
        outputfield.setBackground(BACKGROUND_COLOR);
        ((DefaultCaret)outputfield.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        outputfield.getCaret().setVisible(true);
        outputfield.setCaretColor(FOREGROUND_COLOR);
        outputfield.addFocusListener(new 
            FocusListener()
            {

                @Override public void focusGained(FocusEvent fe) {}

                @Override public void focusLost(FocusEvent fe)
                {outputfield.getCaret().setVisible(true);}
            });
        
        JScrollPane scroller = new JScrollPane(outputfield);
        
        window.add(panel);
        panel.add(scroller);
        panel.add(inputfield);
        window.pack();
        window.setVisible(true);
        
        String serverHostname = JOptionPane.showInputDialog("Enter server hostname");
        int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter port number\n (6667 is a common port number)"));
        
        client = new IOHandler(serverHostname, portNumber);
        client.setReadListener(new Driver().new ClientOutputAction());
    }
    
    private class UserInputAction implements ActionListener
    {

        @Override public void actionPerformed(ActionEvent ae)
        {
            client.writeToSocket(inputfield.getText());
            outputfield.append(inputfield.getText() + "\n");
            inputfield.setText("");
        }
        
    }
    
    private class ClientOutputAction implements ActionListener
    {

        @Override public void actionPerformed(ActionEvent ae)
        {
            outputfield.append(ae.getActionCommand() + "\n");
        }
        
    }
}
