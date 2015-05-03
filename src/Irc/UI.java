/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author blane
 */
public class UI
{

    public static final Color FOREGROUND_COLOR_DEFAULT = new Color(0xEA, 0xCF, 0x4C);
    public static final Color BACKGROUND_COLOR_DEFAULT = new Color(0x00, 0x00, 0x00);
    private JFrame window;
    private JPanel panel;
    private JScrollPane scroller;
    private JTextArea output;
    private JTextField input;

    public UI()
    {
        window = new JFrame("IRC");
        panel = new JPanel();
        input = new JTextField(80);
        output = new JTextArea(40, 80);
        scroller = new JScrollPane(output);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel.setPreferredSize(new Dimension(900, 675));
        panel.setForeground(FOREGROUND_COLOR_DEFAULT);
        panel.setBackground(BACKGROUND_COLOR_DEFAULT);
        
        input.setForeground(FOREGROUND_COLOR_DEFAULT);
        input.setBackground(BACKGROUND_COLOR_DEFAULT);
        input.setCaretColor(FOREGROUND_COLOR_DEFAULT);
        
        output.setEditable(false);
        output.setLineWrap(true);
        output.setForeground(FOREGROUND_COLOR_DEFAULT);
        output.setBackground(BACKGROUND_COLOR_DEFAULT);
        ((DefaultCaret) output.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        output.getCaret().setVisible(true);
        output.setCaretColor(FOREGROUND_COLOR_DEFAULT);
        output.addFocusListener(new 
            FocusListener()
            {

                @Override public void focusGained(FocusEvent fe) {}

                @Override public void focusLost(FocusEvent fe)
                    {output.getCaret().setVisible(true);}
            });
        
        window.add(panel);
        panel.add(scroller);
        panel.add(input);
        window.pack();
        window.setVisible(true);
    }
    
    public void addInputListener(ActionListener al)
    {
        input.addActionListener(al);
    }
    
    public void removeInputListener(ActionListener al)
    {
        input.removeActionListener(al);
    }
    
    public ActionListener getOutputListener()
    {
        return new 
            ActionListener()
            {
                @Override public void actionPerformed(ActionEvent ae)
                {
                    SwingUtilities.invokeLater(new 
                        Runnable()
                        {
                            @Override public void run(){output.append(ae.getActionCommand()+ "\n");}
                        });
                }
            };
    }
}
