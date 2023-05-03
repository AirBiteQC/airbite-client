import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MenuParser {

    public static void main(String[] args) {
        String filename = "Menu.txt";
        ArrayList<MenuItem> menuItems = parseMenuFile(filename);
        // send menuItems to server using socket programming
    }
    
    public static ArrayList<MenuItem> parseMenuFile(String filename) {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                String[] element = line.split("|");
                String food = element[0].trim();
                String restaurantName = element[1].trim();
                double price = Double.parseDouble(element[2].trim()); //parse a string to double

                String picture = element[3].trim();//?????????????????????????????????????????

                // Decode the base64-encoded picture data
                byte[] imageData = Base64.getDecoder().decode(picture);
                MenuItem item = new MenuItem(food, restaurantName, price, picture);
                menuItems.add(item);
                line = reader.readLine();
                // Create an ImageIcon from the decoded byte array
                // ImageIcon imageIcon = new ImageIcon(imageData);

                // // Display the ImageIcon in a JLabel
                // JLabel pictureLabel = new JLabel(imageIcon);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public static void sendMenuToServer(ArrayList<MenuItem> menuItems) {
        try {
            Socket socket = new Socket(address, port);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(menuItems);
            outputStream.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static class MenuItem {
        String itemName;
        String description;
        double price;
        byte[] imageData;
        
        public MenuItem(String itemName, String description, double price, byte[] imageData) {
            this.itemName = itemName;
            this.description = description;
            this.price = price;
            this.imageData = imageData;
        }
    }
}











































// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.nio.charset.StandardCharsets;
// import java.util.Arrays;

// public class MenuParser {
//     public static void main(String[] args) {
//         try {
//             InputStream inputStream = MenuParser.class.getResourceAsStream("/Menu.json"); // load json file
//             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); // ead the contents
//                                                                                                    // of the input
//                                                                                                    // stream as
//                                                                                                    // characters using
//                                                                                                    // the UTF-8 encoding

//             // read the characters from the InputStreamReader
//             // and append them to a StringBuilder to create a string representation of the
//             // JSON data.
//             BufferedReader bufferedReader = new BufferedReader(reader);
//             StringBuilder stringBuilder = new StringBuilder();
//             String line;
//             while ((line = bufferedReader.readLine()) != null) {
//                 stringBuilder.append(line);
//             }

//             String json = stringBuilder.toString();

//             Menu[] menus = parseMenuJson(json);

//             // Do something with the menus array
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     /*
//      * pass "json" string to the parseMenuJson method, which manually parses the
//      * JSON data into an array of Menu
//      * objects by splitting the JSON string into tokens, then splitting each token
//      * into key-value pairs
//      * and populating a new Menu object with the appropriate values.
//      */

//     private static Menu[] parseMenuJson(String json) {
//         String[] tokens = json.split("[{}]");
//         Menu[] menus = new Menu[tokens.length / 2];
//         int i = 0;
//         for (int j = 1; j < tokens.length; j += 2) {
//             String[] props = tokens[j].split(",");
//             Menu menu = new Menu();
//             for (String prop : props) {
//                 String[] keyValue = prop.split(":");
//                 String key = keyValue[0].trim();
//                 String value = keyValue[1].trim();
//                 switch (key) {
//                     case "\"name\"":
//                         menu.setName(value.substring(1, value.length() - 1));
//                         break;
//                     case "\"price\"":
//                         menu.setPrice(Double.parseDouble(value));
//                         break;
//                     case "\"img\"":
//                         menu.setImg(value.substring(1, value.length() - 1));
//                         break;
//                     case "\"restaurant\"":
//                         menu.setRestaurant(value.substring(1, value.length() - 1));
//                         break;
//                 }
//             }
//             menus[i++] = menu;
//         }
//         return menus;
//     }
// }
