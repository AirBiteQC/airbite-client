
/**
 * Client
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

  // Connect to the TCP server at <address>:<port>
  public static String address = "localhost";
  public static int port = 3721;
  public static Socket socket;
  public static InputStream is;
  public static OutputStream os;

  // Forms
  public static LandingForm landingForm;
  public static LoginForm loginForm;
  public static SignupForm signupForm;
  public static RestaurantListForm restaurantListForm;

  // Current user
  public static String name; 
  public static String email;
  public static String password;
  public static String role;


  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Usage: java Client <address> <port>");
      return;
    }
    address = args[0];
    port = Integer.parseInt(args[1]);

    // Connect to the server
    socket = new Socket(address, port);
    System.out.println("Connected to server " + socket.getInetAddress() + ":" + socket.getPort());

    // Get the input and output streams
    is = socket.getInputStream();
    os = socket.getOutputStream();

    // Send the message to the server
    String message = "Hello Server";
    System.out.println("Sending message to server: " + message);
    os.write(message.getBytes());

    // Read the response from the server
    byte[] response = new byte[1024];
    is.read(response);
    System.out.println("Server response: " + new String(response));

    landingForm = new LandingForm();
    landingForm.setVisible(true);
    
    // // Close the socket
    // socket.close();
  }

}