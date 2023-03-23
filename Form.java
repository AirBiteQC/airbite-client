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
    private JLabel name;
    private JTextField tname;
    private JLabel email;
    private JTextField temail;
    private JLabel rest;
    private JComboBox Srest;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
 
    private String Resturants[]
        = { "Resturant 1", "resturant 2" , "resturant 3" };

 
    // constructor, to initialize the components
    // with default values.
    public MyFrame()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
 
        c = getContentPane();
        c.setLayout(null);
 
        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
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
 
        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 150);
        c.add(email);
 
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(150, 20);
        temail.setLocation(200, 150);
        c.add(temail);
 
        rest = new JLabel("Resturants");
        rest.setFont(new Font("Arial", Font.PLAIN, 20));
        rest.setSize(100, 20);
        rest.setLocation(100, 250);
        c.add(rest);
 
        Srest = new JComboBox(Resturants);
        Srest.setFont(new Font("Arial", Font.PLAIN, 15));
        Srest.setSize(50, 20);
        Srest.setLocation(200, 250);
        c.add(Srest);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);
        c.add(term);
 
        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);
 
        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
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
                String data = "Name : "+ tname.getText() + "\n ";
                String data2 = "Resturant Selected : "+ (String)Srest.getSelectedItem()+ "\n";
                String data3 = "Email : "+(String)temail.getText()+"\n";
                System.out.println(data  + data2 +data3 );
                
            }
            else {
                tout.setText("");
                resadd.setText("");
                res.setText("Please accept the"
                            + " terms & conditions..");
            }
        }
 
        else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            temail.setText(def);

  
            term.setSelected(false);
            Srest.setSelectedIndex(0);
            resadd.setText(def);
        }
    }
}
 
// Driver Code
class Form {
    public static void main(String[] args) throws Exception
    {
       new MyFrame();
    }
}