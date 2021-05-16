package geekbrains.lesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(EchoConstants.SERVER_PORT);
             Socket socket = server.accept();
             Scanner scanner = new Scanner(socket.getInputStream());
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);) {
            System.out.println("Клиент подключен");
            KeyListener keyListener = new KeyListener(printWriter);
            keyListener.setDaemon(true);
            keyListener.start();
            while (true) {
                String str = "";
                try {
                    str = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    break;
                }
                ;
                if (str.equals("/end")) {
                    System.out.println("Клиент отключен");
                    keyListener.interrupt();
                    break;
                }
                System.out.println("Клиент: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}