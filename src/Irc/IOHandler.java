/*
 * To change this license header, choose License Headers socketInWrapper Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template socketInWrapper the editor.
 */




import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * 
 */
public class IOHandler
{
    
    private Socket sock;
    private DataOutputStream outStream;
    private Scanner socketInWrapper;
    private ActionListener readListener;
    private String streamInput;
    private Thread readerThread;
    
    
    public IOHandler(String server, int port)
    {
        try
        {
            sock = new Socket(server, port);
            outStream = new DataOutputStream(sock.getOutputStream());
            socketInWrapper = new Scanner(sock.getInputStream());
            readerThread = new Thread(new ReadThreaded());
            readerThread.setDaemon(true);
            readerThread.start();
        }
        catch(UnknownHostException exc) {stringOutClient("Unknown host: " + server);}
        catch(IOException exc) {stringOutClient("IO exception during setup");}
    }
    
    public void writeToSocket(String str)
    {
        if (outStream == null){stringOutClient("err: no connection");return;}
        try{outStream.writeBytes(str + "\r\n");} catch(IOException exc) {stringOutClient("err: write failed: " + str);}
    }
    
    private void stringOutClient(String str)
    {
        readListener.actionPerformed(new ActionEvent(this, 0, str));
    }
    
    private void pingReply(String str)
    {
        writeToSocket("PONG " + str.substring(5));
    }
    
    public void setReadListener(ActionListener al)
    {
        readListener = al;
    }
    
    private class ReadThreaded implements Runnable
    {
        @Override public void run()
        {
            while(true)
            {
                if (socketInWrapper.hasNextLine())
                {
                    streamInput = socketInWrapper.nextLine();
                    if(streamInput.startsWith("PING ")) {pingReply(streamInput);}
                    else {stringOutClient(streamInput);}
                }
            }
        }
    }
}
