// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
 
public class LoginForm
    extends JDialog
    implements ActionListener {
 
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel pass;
    private JPasswordField tpass;
    private JButton login;
    private JButton sign;

    String entries;

    // constructor, to initialize the components
    // with default values.
    public LoginForm()
    {
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Login Form");
        setBounds(300, 90, 500, 400);
      //  setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("AirBite Login");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(200, 35);
        title.setLocation(150, 30);
        c.add(title);
 
        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 100);
        c.add(email);
 
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(200, 100);
        c.add(temail);
 
        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(100, 150);
        c.add(pass);
 
        tpass = new JPasswordField(20);
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(190, 20);
        tpass.setLocation(200, 150);
        c.add(tpass);
 
        // Buttons
        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(150, 250);
        login.addActionListener(this);
        c.add(login);
 
        // sign = new JButton("Sign up");
        // sign.setFont(new Font("Arial", Font.PLAIN, 15));
        // sign.setSize(100, 20);
        // sign.setLocation(150, 280);
        // sign.addActionListener(this);
        // c.add(sign);
    }
 
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login) {
            
            entries = "login|";
            entries += temail.getText(); 
            String p = new String(tpass.getPassword());
            entries += "|"+p; 
            System.out.println("LoginForm: send to server " + entries);
            // send to server
            try {
                Client.os.write(entries.getBytes());
                // get server response
                byte[] response = new byte[1024];
                Client.is.read(response);
                String res = new String(response);
                System.out.println("LoginForm: server response " + new String(res));
                // display a dialog box if the response is "Login failed"
                if(res.contains("failed")) {
                    System.out.println("LoginForm: Login failed");
                    JOptionPane.showMessageDialog(this, "Login failed due to incorrect email or password.", "Login failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("./img/AirBite-64px-round.png"));
                }
                else {
                    System.out.println("LoginForm: Login successful");
                    String[] parts = res.split("\\|");
                    Client.email = temail.getText();
                    Client.name = parts[1];
                    Client.role = parts[2].replace("\n", "").replace("\r", "");
                    System.out.println("LoginForm: Welcome " + Client.role + " " + Client.name);
                    JOptionPane.showMessageDialog(this, "Welcome back, " + Client.role + " " + Client.name + "!", "Login successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./img/AirBite-64px-round.png"));
                    // close the landing form
                    Client.landingForm.dispose();
                    // open the restaurant list form
                    Client.restaurantListForm = new RestaurantListForm();
                    Client.restaurantListForm.setVisible(true);
                    this.dispose(); // closing the login Frame
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
 
        // else if (e.getSource() == sign) {
        //     // Open Signup form
        //     SignupForm S = new SignupForm();
        //     entries =  S.getEntries();
        //     this.dispose();     // close the frame
        //    // System.out.println("Sign up " + Entries);
        // }
    }
    public String getEntries(){
        return entries;
    }
}
  