// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class SignupForm
    extends JDialog
    implements ActionListener {
 
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel pass;
    private JPasswordField tpass;
    private JLabel email;
    private JTextField temail;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JLabel res;
    String entries;

    // constructor, to initialize the components
    // with default values.
    public SignupForm()
    {
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Sign up Form");
        setBounds(300, 90, 600, 400);
       // this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Sign Up Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(100, 30);
        c.add(title);
 
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);
 
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);
 
        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(100, 128);
        c.add(pass);
 
        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(190, 20);
        tpass.setLocation(200, 128);
        c.add(tpass);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 155);
        c.add(email);
 
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(200, 155);
        c.add(temail);
 


        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 200);
        c.add(term);
 
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 250);
        sub.addActionListener(this);
        c.add(sub);
 
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(150, 280);
        reset.addActionListener(this);
        c.add(reset);
 
 
        setVisible(true);
    }
 
    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            if (term.isSelected()) {
                entries = "register|";
                entries += tname.getText();
                entries += "|"+(String)temail.getText();
                String p = new String(tpass.getPassword());
                entries += "|"+p;
               
            System.out.println("SingupForm: Sending to Server : "+ entries);
            try{
                Client.os.write(entries.getBytes());
                // get server response
                byte[] response = new byte[1024];
                Client.is.read(response);
                String res = new String(response);
                System.out.println("LoginForm: server response " + new String(res));
                // display a dialog box if the response is "Login failed"
                if(res.contains("failed")) {
                    System.out.println("signupForm: Registeration Failed");
                    JOptionPane.showMessageDialog(this, "Email already Exist", "Sign-up failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("./img/AirBite-64px-round.png"));
                }
                else{
                    System.out.println("Signup Form: Signup successful");
                   // JOptionPane.showMessageDialog(this, "Login Successful Welcome "+ tname.getText() );
                }
            }
            catch(Exception ioe){
                ioe.printStackTrace();
            }
                
                this.setModal(false);
                this.dispose();
            }
        }
 
        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            temail.setText(def);
            tpass.setText(def);
            entries = null;
            term.setSelected(false);
        }
    }

    public static void main(String[] args){
       // new SignupForm();
    }
    public String getEntries(){
        return entries;
    }
}
 