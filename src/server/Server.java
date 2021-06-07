/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Server {

    private ServerSocket serverSocket = null;
    private Socket socket;
    private int port;
    static ArrayList<DataProcessing> arrayList = new ArrayList<DataProcessing>();

    public Server(int port) {
        this.port = port;
        initServer();
    }

    public void initServer() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        try {
            while (true) {
                socket = serverSocket.accept();
                DataProcessing dataProcessing = new DataProcessing(socket);
                arrayList.add(dataProcessing);
                dataProcessing.start();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public Server() {
    }
    public void closeConnect() throws IOException{
        socket.close();
    }

}
