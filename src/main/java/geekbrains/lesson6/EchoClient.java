package geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoClient();
    }

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(EchoConstants.SERVER_ADDR, EchoConstants.SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        PrintWriter printWriter = new PrintWriter(out, true);

        KeyListener keyListener = new KeyListener(printWriter);
        keyListener.setDaemon(true);
        keyListener.start();

        sendMessage();
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(in);
        while (true) {
            String str = "";
            try {
                str = scanner.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            ;
            if (str.equals("/end")) {
                System.out.println("Сервер отключен");
                break;
            }
            System.out.println("Сервер: " + str);
        }
    }
}