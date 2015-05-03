/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class Driver
{
    
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
        testui.addInputListener(client.getWriteListener());
    }
}
