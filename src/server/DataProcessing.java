/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DataProcessing extends Thread {

    private Socket socket;
    private BufferedReader br;
    private DataOutputStream dos;

    public DataProcessing(Socket socket) {
        this.socket = socket;
    }

    public void send(String message) {
        try {
            dos.writeBytes(message+'\n');
        } catch (IOException ex) {
            Logger.getLogger(DataProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendToAllClient(String massage) {
        for (int i = 0; i < Server.arrayList.size(); i++) {
            DataProcessing dataProcessing = Server.arrayList.get(i);
            if (dataProcessing != this) {
                dataProcessing.send(massage);
            }
        }
    }

    @Override
    public void run() {

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                try {
                    String message = br.readLine();
                    System.out.println(message);
                    sendToAllClient(message);
                } catch (IOException ex) {
                    socket.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
