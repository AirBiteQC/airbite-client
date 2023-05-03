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
        String encryptedcc = encryptStringWithPublicKey("Public key" , ccinformation);
        // send encryptedcc to the server 
        this.dispose();
        new BillingForm();
    }
   }
   public static String encryptStringWithPublicKey(String publicKeyString, String str) throws Exception {
      // Remove the PEM header and footer, and any newline characters
      String base64PublicKey = publicKeyString
              .replace("-----BEGIN PUBLIC KEY-----", "")
              .replace("-----END PUBLIC KEY-----", "")
              .replaceAll("\\s", "");

      // Convert the public key string to a PublicKey object
      byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(keySpec);

      // Initialize the cipher with the public key
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);

      // Encrypt the string and encode it to Base64
      byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encryptedBytes);
  }
   public static void main(String[] args) {
      new CreditCardForm();
   }
}



