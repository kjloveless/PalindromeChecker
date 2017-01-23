/**
 *  Kyle Loveless
 * CSC 469
 *  Dr. Muganda
 * 
 * This program is intended to check if a string is a palindrome.
 * This is done by reading in a string, removing whitespace and punctuation, 
 * making the string all lower case, and then reversing that string and 
 * comparing the two.
 */
package palindromechecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PalindromeChecker
{

    public static void main(String[] args) throws IOException
    {
        System.out.println("Server is starting...");
        ServerSocket serverSock = new ServerSocket(50002);        
        while (true)
        {
            Socket sock = serverSock.accept();
            System.out.println("Accepted a connection...");
            checkPalindrome(sock);
        }
    }

    static private void checkPalindrome(Socket sock) throws IOException
    {
        BufferedReader in = new BufferedReader
                                (new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

        String line = in.readLine();
        while (!line.equals("bye"))
        {
            System.err.println("Got the command: " + line);
            Scanner scanner = new Scanner(line);
            String cmd = scanner.next();
            String originalString = cmd.toLowerCase().replaceAll("\\W", "");
            String reversedString = new StringBuilder
                                        (originalString).reverse().toString();

            if (originalString.matches(reversedString))
            {
                out.println("The input was a palindrome");
                out.flush();
            } else
            {
                out.println("The input was not a palindrome");
                out.flush();
            }
            line = in.readLine();
        }
        in.close();
        out.close();
        sock.close();
    }
}
