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
    
    private static IOHandler client;
    
    public static void main(String[] args)
    {
        UI testui = new UI();
        String serverHostname = testui.getUserInputBlocking("Enter server hostname");
        int portNumber = Integer.parseInt(testui.getUserInputBlocking("Enter port number\n (6667 is a common port number)"));
        client = new IOHandler(serverHostname, portNumber);
        client.setReadListener(testui.getOutputListener());
        testui.addInputListener(client.getWriteListener());
    }
}
