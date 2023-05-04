import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Restaurant GUI that displays the list of incoming orders for the currently logged in restaurant.
 * The restaurant can select an order and mark it as complete.
 * 
 * There is a button to logout and return to the login screen.
 *
 */

public class Restaurant extends JDialog implements ActionListener {
    private Container c;
    private JLabel title;
    private JList<String> ordersList;
    private DefaultListModel<String> listModel;
    private JButton completeButton;
    private JButton logoutButton;
    private String[] orders;
    private String[] orderStatuses;
    private String[] orderTotals;
    private String[] orderTimes;
    private String[] orderCustomers;

    public Restaurant() {
        setTitle("Restaurant");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        c = getContentPane();
        c.setLayout(null);
        setSize(1000, 800);

        title = new JLabel(Client.name + "'s Incoming Orders");
        listModel = new DefaultListModel<String>();
        ordersList = new JList<String>(listModel);
        completeButton = new JButton("Mark as Complete");
        completeButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(500, 30);
        title.setLocation(250, 30);
        ordersList.setBounds(100, 100, 800, 500);
        completeButton.setBounds(100, 650, 200, 50);
        logoutButton.setBounds(700, 650, 200, 50);
        c.add(title);
        c.add(ordersList);
        c.add(completeButton);
        c.add(logoutButton);

        // try to get orders from server every 5 seconds
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // getOrders();
                // updateOrdersList();
                System.out.println("Restaurant: Sending list|restaurant to server");
            }
        });
        timer.start();

      }

      public void actionPerformed(ActionEvent e) {
          if (e.getSource() == completeButton) {
              // send "complete|orderID" to server
              try {
                  System.out.println("Restaurant: Sending complete|orderID to server");
                  Client.os.write("complete|orderID".getBytes());
                  // get server response
                  byte[] response = new byte[1024];
                  Client.is.read(response);
                  String res = new String(response).replace("\n", "").replace("\r", "").replace("\"", "").replace("\0", "");
                  System.out.println("Restaurant: Server response: " + res);
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          } else if (e.getSource() == logoutButton) {
            // send "logout" to server
            try {
              System.out.println("RestaurantListForm: Sending logout to server");
              Client.os.write("logout".getBytes());
              this.dispose();
              Client.landingForm = new LandingForm();
              Client.landingForm.setVisible(true);
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
      }

      public static void main(String[] args) {
          Restaurant restaurant = new Restaurant();
          restaurant.setVisible(true);
      }
}

