
/**
 * Client
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

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
  public static Restaurant restaurantForm;
  public static RestaurantListForm restaurantListForm;
  public static FoodMenuForm foodMenuForm;

  // Current user
  public static String name; 
  public static String email;
  public static String password;
  public static String role;

  // Public key
  public static String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq7KTB+o/EvqPsCgKiew44OXDKQmLyhuD7S+A97QApD2Sfj88FGeAkBLmAzCuGWSOkUL+wl3vFPyKKeFxaxLT5LWcsr9IsGfdYtksXFfJcfuk+NPjLtP8oOLlAIYJXW63zdAEJ9hRWZA9PYwpmTOD0eWs+rygILVAA3S2uILuZkHJbATxZUweJZYcWjBoMIpd7a+v8S2KaM0dB0Fw6Wrg/7+eS/En8d8t3O65XIpySMhcjgdinMt3r+doZ5Cpzmtc+94NrP+iG6ikuZ3HR7u61IpQOj2kIZugp3PLG3ZFBVLeBAHJTWuq2vA4CMk1mXyB39fSsz0xPStFmacc7NsvfwIDAQAB-----END PUBLIC KEY-----";

  public static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02X", b));
    }
    return sb.toString();
  }

  public static String toLowerCaseAlphabets(String input) {
    StringBuilder sb = new StringBuilder();
    for (char ch : input.toCharArray()) {
      if (Character.isAlphabetic(ch)) {
        sb.append(Character.toLowerCase(ch));
      } else {
        sb.append(ch);
      }
    }
    return sb.toString();
  }
  /**
   * Encrypt a string with the public key
   * @param str The string to encrypt
   * @return The encrypted string
   * @throws Exception If the encryption fails
   */
  public static String encryptStringWithPublicKey(String str) throws Exception {
    // Remove the PEM header and footer, and any newline characters
    String base64PublicKey = Client.publicKey
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .replaceAll("\\s", "");

    // Convert the public key string to a PublicKey object
    byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(keySpec);

    // Initialize the cipher with the public key
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);

    // Encrypt the string and encode it to Base64
    byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
    return toLowerCaseAlphabets(bytesToHex(encryptedBytes));
  }

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