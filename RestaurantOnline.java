// Java program to implement
// a Simple Registration Form
// using Java Swing
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
 
public class RestaurantOnline
    extends JDialog
    implements ActionListener {
 
    // Components of the Form
    private Container c;
    Container panel ;
    JLabel title;
    String entries;
    JButton btn;
    JComboBox<String> menu;
    String[] choices = { "Windys", "Burger King"," Mc Donalds" };
    // constructor, to initialize the components
    // with default values.
    public RestaurantOnline()
    {      
 
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Resaurant Available");
        setBounds(300, 90, 500, 400); 
        setResizable(false);
       // setVisible(true);
    
    
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code
    
        this.add(panel);
    
        title = new JLabel("Resturants Online");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(200, 35);
        title.setLocation(150, 80);
    
        panel.add(title);
    
    
        menu = new JComboBox<String>(choices);
        menu.setLocation(160, 90);// added code
        menu.addActionListener(this);
        panel.add(menu);
    
        btn = new JButton("Select");
        btn.setLocation(180, 100); // added code
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setSize(100, 20);
        btn.addActionListener(this);
        panel.add(btn);
    
        setVisible(true); // added code

    }
 
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn) {
            System.out.println("Btn Pressed");
        }
        if (e.getSource() == menu) {
            String sel = (String)menu.getSelectedItem();
            System.out.println(sel);
        }
    }

    public static void main (String[] args){
        new RestaurantOnline();
    }

}
  