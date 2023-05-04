import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditCardForm extends JFrame implements ActionListener {
   private JLabel cardNumberLabel;
   private JTextField cardNumberField;
   private JLabel nameLabel;
   private JTextField nameField;
   private JLabel expiryLabel;
   private JTextField expiryField;
   private JLabel cvvLabel;
   private JTextField cvvField;
   private JButton submitButton;

   public CreditCardForm() {
      setTitle("Credit Card Form");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridLayout(5, 2));
      // display the form in the center of the screen
      setLocationRelativeTo(null);
      cardNumberLabel = new JLabel("Card Number:");
      cardNumberField = new JTextField(16);
      nameLabel = new JLabel("Card Holder's Name:");
      nameField = new JTextField(20);
      expiryLabel = new JLabel("Expiry Date:");
      expiryField = new JTextField(4);
      cvvLabel = new JLabel("CVV:");
      cvvField = new JTextField(3);
      submitButton = new JButton("Submit");

      add(cardNumberLabel);
      add(cardNumberField);
      add(nameLabel);
      add(nameField);
      add(expiryLabel);
      add(expiryField);
      add(cvvLabel);
      add(cvvField);
      add(new JLabel(""));
      add(submitButton);

      submitButton.addActionListener(this);

      pack();
      setVisible(true);
   }

   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton){
        String ccinformation = "|"+nameField.getText()+"|"+cardNumberField.getText()+"|"+expiryField.getText()+"|"+cvvField.getText();
        String encryptedcc = ccinformation;
        System.out.println(encryptedcc);
      //   String encryptedcc = encryptStringWithPublicKey("Public key" , ccinformation);
        // send encryptedcc to the server 
        this.dispose();
        new BillingForm();
    }
   }

   public static void main(String[] args) {
      new CreditCardForm();
   }
}



