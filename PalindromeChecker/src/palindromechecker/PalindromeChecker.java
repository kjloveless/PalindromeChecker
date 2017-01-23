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

    /**
     * checkPalindrome takes in a string and prints out if the string is a 
     * palindrome or not.
     * @param sock This param is the active socket created when a client 
     * tries to connect to the server.
     * @throws IOException 
     */
    static private void checkPalindrome(Socket sock) throws IOException
    {
        BufferedReader in = new BufferedReader
                                (new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println("Input a string that you would like to test to see if"
                + "is a palindrome.");
        out.println("Enter \"bye\" or press enter if you would like to "
                + "exit the program.");
        out.flush();

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
                out.println("The string " + cmd + " is a palindrome.");
                out.flush();
            } else
            {
                out.println("The string " + cmd + " is not a palindrome");
                out.flush();
            }
            line = in.readLine();
        }
        in.close();
        out.close();
        sock.close();
    }
}
