// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
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

    String Entries;

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
 
        title = new JLabel("Login Form");
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
 
        sign = new JButton("Sign up");
        sign.setFont(new Font("Arial", Font.PLAIN, 15));
        sign.setSize(100, 20);
        sign.setLocation(150, 280);
        sign.addActionListener(this);
        c.add(sign);

        setVisible(true);
    }
 
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login) {
            
            Entries = temail.getText(); 
            String p = new String(tpass.getPassword());
            Entries =  Entries +" "+p; 
            this.dispose();     // closing the login Frame     
            // Open Resturant List
        }
 
        else if (e.getSource() == sign) {
            // Open Signup form
            Signup S = new Signup();
            Entries =  S.getEntries();
            this.dispose();     // close the frame
           // System.out.println("Sign up " + Entries);
        }
    }
    public String getEntries(){
        return Entries;
    }
}
  