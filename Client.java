/**
 * Client
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

  // Connect to the TCP server at localhost:3721

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 3721);
    System.out.println("Connected to server");

    // Get the input and output streams
    InputStream is = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    // Send the message to the server
    String message = "Hello Server";
    System.out.println("Sending message to server: " + message);
    os.write(message.getBytes());

    // Read the response from the server
    byte[] response = new byte[1024];
    is.read(response);
    System.out.println("Server response: " + new String(response));

    // Close the socket
    socket.close();
  }

}