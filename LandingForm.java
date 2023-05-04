// Java program to implement a landing window with a text label and three buttons using Java Swing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class LandingForm extends JDialog implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JButton login;
    private JButton signup;
    private JTextArea res;
    private ImageIcon image = new ImageIcon("./img/AirBite-128px-round.png");

    // constructor, to initialize the components
    // with default values.
    public LandingForm()
    {
        setTitle("Landing Form");
        setBounds(25, 25, 800, 600);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // add image to the frame
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(340, 25, 128, 128);
        c.add(imageLabel);
        

        title = new JLabel("Welcome to AirBite Client");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(600, 30);
        title.setLocation(100, 180);
        title.setHorizontalAlignment(JLabel.CENTER);
        c.add(title);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(350, 350);
        login.addActionListener(this);
        c.add(login);

        signup = new JButton("Sign Up");
        signup.setFont(new Font("Arial", Font.PLAIN, 15));
        signup.setSize(100, 20);
        signup.setLocation(350, 400);
        signup.addActionListener(this);
        c.add(signup);

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100, 20);
        exit.setLocation(350, 450);
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
        res = new JTextArea("This is " + localAddress.toString() + ":" + Client.socket.getLocalPort() + ",\n Connected to server at " + Client.address + ":" + Client.port);
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(600, 50);
        res.setLocation(220, 250);
        // word-wrap and line-wrap turned on
        res.setLineWrap(true);
        res.setWrapStyleWord(true);
        // center align
        res.setAlignmentX(Component.CENTER_ALIGNMENT);
        res.setAlignmentY(Component.CENTER_ALIGNMENT);
        res.setEditable(false);
        res.setFocusable(false);
        res.setOpaque(false);
        
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
            this.toBack();
            Client.signupForm.toFront();
            //Client.signupForm.setVisible(true);
            //System.out.println(f.getEntries());
            //os.write( f.getEntries().getBytes());
        }
    }
}
