/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author ASUS
 */
public class SDServer {
    public static void main(String[] args) {
        Server server = new Server(9999);
        server.run();
    }
}
