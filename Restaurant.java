
/**
 * Restaurant
 */
package javax.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

public class Restaurant {

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
    // hassan
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

    // Read Jason file and send it to server upon request
    // read the contents of the JSON file into a String object using a FileReader or
    // InputStream
    String jsonFilePath = "Menu3.json"; // Replace with your file path
    JsonObject jsonObject = readJsonFile(jsonFilePath);
    sendJsonToServer(jsonObject);
  }

  public static JsonObject readJsonFile(String filePath) throws IOException {
    FileReader fileReader = new FileReader(filePath);
    JsonReader jsonReader = Json.createReader(fileReader);
    JsonObject jsonObject = jsonReader.readObject();
    jsonReader.close();
    return jsonObject;
  }

  public static void sendJsonToServer(JsonObject jsonObject) throws IOException {
    String hostName = "localhost"; // Replace with your host name or IP address
    int portNumber = 12345; // Replace with your port number

    try (Socket socket = new Socket(hostName, portNumber);
        OutputStream outputStream = socket.getOutputStream()) {
      outputStream.write(jsonObject.toString().getBytes());
    }
  }
}

// Close the socket
// socket.close();
