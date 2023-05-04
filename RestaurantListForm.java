// Java program to implement a window with a text label and a JList using Java Swing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class RestaurantListForm extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JList<String> list;
    private JButton select;
    private JButton back;

    // constructor, to initialize the components
    // with default values.
    public RestaurantListForm()
    {
        setTitle("Restaurant List Form");
        setBounds(300, 90, 900, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Restaurant List");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        list = new JList<String>();
        list.setFont(new Font("Arial", Font.PLAIN, 15));
        list.setSize(300, 200);
        list.setLocation(300, 100);
        // send "list|restaurant" to server
        try {
            System.out.println("RestaurantListForm: Sending list|restaurant to server");
            Client.os.write("list|restaurant".getBytes());
            // get server response
            byte[] response = new byte[1024];
            Client.is.read(response);
            String res = new String(response).replace("\n", "").replace("\r", "").replace("\"", "").replace("\0", "");
            System.out.println("RestaurantListForm: Server response: " + res);
            String[] restaurants = res.split("\\|");
            list.setListData(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.add(list);

        select = new JButton("Select");
        select.setFont(new Font("Arial", Font.PLAIN, 15));
        select.setSize(100, 20);
        select.setLocation(350, 300);
        select.addActionListener(this);
        c.add(select);

        back = new JButton("Logout");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(500, 300);
        back.addActionListener(this);
        c.add(back);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == select) {
            // send "select|restaurant name" to server
            try {
                String str = "select|" + list.getSelectedValue();
                Client.os.write(str.getBytes());
                // get server response
                // wait until all bytes are received
                byte[] response = new byte[104800000];
                Client.is.read(response);
                String res = new String(response).replace("\r", "").replace("\"", "").replace("\0",
                        "");
                System.out.println("RestaurantListForm: Server response: " + res);
                Client.foodMenuForm = new FoodMenuForm(list.getSelectedValue(), res);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == back) {
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
}