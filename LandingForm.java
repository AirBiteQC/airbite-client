// Java program to implement a landing window with a text label and three buttons using Java Swing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class LandingForm extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JButton login;
    private JButton signup;
    private JLabel res;

    // constructor, to initialize the components
    // with default values.
    public LandingForm()
    {
        setTitle("Landing Form");
        setBounds(300, 90, 1000, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Welcome to AirBite Client");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(600, 30);
        title.setLocation(200, 30);
        title.setHorizontalAlignment(JLabel.CENTER);
        c.add(title);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(450, 200);
        login.addActionListener(this);
        c.add(login);

        signup = new JButton("Sign Up");
        signup.setFont(new Font("Arial", Font.PLAIN, 15));
        signup.setSize(100, 20);
        signup.setLocation(450, 250);
        signup.addActionListener(this);
        c.add(signup);

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100, 20);
        exit.setLocation(450, 300);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // send "exit" to server
                    Client.os.write("exit".getBytes());
                    System.out.println("Client page: closing socket and exit");
                    Client.socket.close();
                } catch (Exception e1) {
                    // e1.printStackTrace();
                }
                System.exit(0);
            }
        });
        c.add(exit);

        InetAddress localAddress = null;
        try {
          localAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
          e.printStackTrace();
        }
        res = new JLabel("This is " + localAddress.toString() + ":" + Client.socket.getLocalPort() + ", Connected to server at " + Client.address + ":" + Client.port);
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(800, 25);
        res.setLocation(100, 100);
        res.setHorizontalAlignment(JLabel.CENTER);
        c.add(res);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login) {
            Client.loginForm = new LoginForm();
            // f.getEntries() retrieves the Entries from the Forms
            System.out.println("Client: open login form");
            Client.loginForm.setVisible(true);
            //System.out.println(f.getEntries());
            //os.write( f.getEntries().getBytes());
        }
        else if (e.getSource() == signup) {
            Client.signupForm = new SignupForm();
            // f.getEntries() retrieves the Entries from the Forms
            System.out.println("Client: open signup form");
            Client.signupForm.setVisible(true);
            //System.out.println(f.getEntries());
            //os.write( f.getEntries().getBytes());
        }
    }
}
