/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        JFrame window = new JFrame("window");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(900,675));
        
        inputfield = new JTextField(80);
        inputfield.addActionListener(new Driver().new UserInputAction());
        
        outputfield = new JTextArea(40, 80);
        outputfield.setEditable(false);
        outputfield.setLineWrap(true);
        
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
