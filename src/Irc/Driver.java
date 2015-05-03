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
        UI clientUI = new UI();
        String serverHostname = clientUI.getUserInputBlocking("Enter server hostname");
        int portNumber = Integer.parseInt(clientUI.getUserInputBlocking("Enter port number\n (6667 is a common port number)"));
        IOHandler clientIO = new IOHandler(serverHostname, portNumber);
        clientIO.setReadListener(clientUI.getOutputListener());
        clientUI.addInputListener(clientIO.getWriteListener());
    }
}
