import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BillingForm extends JFrame implements ActionListener {
   private JLabel countryLabel;
   private JTextField countryField;
   private JLabel stateLabel;
   private JTextField stateField;
   private JLabel cityLabel;
   private JTextField cityField;
   private JLabel addressLabel;
   private JTextField addressField;
   private JLabel zipLabel;
   private JTextField zipField;
   private JButton submitButton;

   public BillingForm() {
      setTitle("Billing Address Form");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(6, 2));

      countryLabel = new JLabel("Country:");
      countryField = new JTextField(16);
      stateLabel = new JLabel("State:");
      stateField = new JTextField(20);
      addressLabel = new JLabel("Address:");
      addressField = new JTextField(20);
      cityLabel = new JLabel("City:");
      cityField = new JTextField(15);
      zipLabel = new JLabel("Zip:");
      zipField = new JTextField(15);    
      submitButton = new JButton("Submit");

      add(countryLabel);
      add(countryField);
      add(stateLabel);
      add(stateField);
      add(addressLabel);
      add(addressField);
      add(cityLabel);
      add(cityField);
      add(zipLabel);
      add(zipField);
      add(new JLabel(""));
      add(submitButton);

      submitButton.addActionListener(this);

      pack();
      setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton){
        String billinginformation = "Country"+countryField.getText() + " City:"+cityField.getText()
                                        +" Address:"+addressField.getText() + " State:"+stateField.getText()
                                        +" Zip:"+zipField.getText();
        System.out.print(billinginformation);
        this.dispose();
    }
   }
   
}



