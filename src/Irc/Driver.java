/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class Driver
{
    
    public static void main(String[] args)
    {
        UI testui = new UI();
        String serverHostname = testui.getUserInputBlocking("Enter server hostname");
        int portNumber = Integer.parseInt(testui.getUserInputBlocking("Enter port number\n (6667 is a common port number)"));
        IOHandler client = new IOHandler(serverHostname, portNumber);
        client.setReadListener(testui.getOutputListener());
        testui.addInputListener(client.getWriteListener());
    }
}
