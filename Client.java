
/**
 * Client
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

  // Connect to the TCP server at <address>:<port>

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Usage: java Client <address> <port>");
      return;
    }
    String address = args[0];
    int port = Integer.parseInt(args[1]);

    // Connect to the server
    Socket socket = new Socket(address, port);
    System.out.println("Connected to server " + socket.getInetAddress() + ":" + socket.getPort());

    // Get the input and output streams
    //hassan
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

    LoginForm f = new LoginForm();
    // f.getEntries() retrives the Entries from the Forms
    System.out.println("Client page "+f.getEntries());
    //System.out.println(f.getEntries());
    os.write( f.getEntries().getBytes());

    
    // Close the socket
    socket.close();
  }

}