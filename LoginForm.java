// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
class MyFrame
    extends JFrame
    implements ActionListener {
 
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel pass;
    private JTextField tpass;
    private JComboBox Srest;
    private JCheckBox term;
    private JButton login;
    private JButton sign;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    // constructor, to initialize the components
    // with default values.
    public MyFrame()
    {
        setTitle("Login Form");
        setBounds(300, 90, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
 
        tpass = new JTextField();
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
        if (e.getSource() == sign) {
            if (term.isSelected()) {
                String data = "Email : "+ temail.getText() + "\n ";
                String data3 = "Passsword : "+(String)tpass.getText()+"\n";
                System.out.println(data  +data3 );
                
            }
            else {
                tout.setText("");
                resadd.setText("");
                res.setText("Please accept the"
                            + " terms & conditions..");
            }
        }
 
        else if (e.getSource() == sign) {
            String def = "";
            temail.setText(def);
            tpass.setText(def);

  
            term.setSelected(false);
            Srest.setSelectedIndex(0);
            resadd.setText(def);
        }
    }
}
 
// Driver Code
class LoginForm {
    public static void main(String[] args) throws Exception
    {
       new MyFrame();
    }
}