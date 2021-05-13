package geekbrains.lesson6;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class KeyListener extends Thread {

    private final PrintWriter printWriter;

    private final Scanner keyScanner;

    public KeyListener(PrintWriter printWriter) {
        this.printWriter = printWriter;
        this.keyScanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String inputString = "";
            try {
                inputString = keyScanner.nextLine();
            }
            catch (NoSuchElementException e) {
                e.printStackTrace();
                break;
            }
            this.printWriter.println(inputString);
        }
    }
}