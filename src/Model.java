import util.ListenerThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Model {
    // https://github.com/MagnusSilverdal/ClientServerExample

    public void hostSession(int port) {
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started.");

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections...");
                socket = serverSocket.accept();
                // Go
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream()))) {};
                Thread listener = new Thread(in);
                listener.start();
                System.out.println("Client connected!");
                Scanner tgb = new Scanner(System.in);

                String msg = tgb.nextLine();
                out.println("SERVER: " + msg);

            }
        } catch (Exception e) {
            System.out.println("Server fail");
        }
    }

    public void connect(String ip, int port) {
        Socket socket = null;

        try {
            socket = new Socket(ip,port);
        } catch (Exception e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            Scanner tgb = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            Thread listener = new Thread(in);
            listener.start();
            boolean run = true;
            while (run) {
                String msg = tgb.nextLine();
                out.println(msg);
            }

            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }

    public void send(String msg) {

    }

}
