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
        UI testui = new UI();
        String serverHostname = JOptionPane.showInputDialog("Enter server hostname");
        int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter port number\n (6667 is a common port number)"));
        
        client = new IOHandler(serverHostname, portNumber);
        client.setReadListener(testui.getOutputListener());
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
