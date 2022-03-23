package demos.fibonacci.scribble;

/**
 * Generated by StMungo
 * Wed Apr 08 17:27:31 BST 2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BMain {
    public static String safeRead(BufferedReader readerB) {
        String readline = "";
        try {
            readline = readerB.readLine();
        } catch (IOException e) {
            System.out.println("Input/Output error, unable to read");
            System.exit(-1);
        }
        return readline;
    }

    public static void main(String[] args) {
        // Create the current role
        BRole currentB = new BRole();
        // readerB can be used to input strings, and then use them in send method invocation
        BufferedReader readerB = new BufferedReader(new InputStreamReader(System.in));
        // Method invocation follows the B typestate
        _Fib:
        do {
            switch (currentB.receive_Choice1LabelFromA()) {
                case FIBONACCI:
                    Long payload1 = currentB.receive_fibonacciLongFromA();
                    System.out.println("Received from A: " + payload1);
                    System.out.print("Send to A: ");
                    Long payload2 = /* parse me! */ Long.parseLong(safeRead(readerB));
                    currentB.send_fibonacciLongToA(payload2);
                    continue _Fib;
                case END:
                    currentB.receive_endFromA();
                    break _Fib;
            }
        } while (true);
    }
}
