
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.IOException;

public class FoodMenuForm extends JFrame {
    private JList<String> foodList;
    private DefaultListModel<String> listModel;

    private JLabel descriptionLabel;
    private JLabel imageLabel;
    private JLabel priceLabel;
    private JButton addToCartButton;
    private JTextArea cartTextArea;
    private JButton submitButton;
    private double total;

    private String[] foodNames ;
    private String[] descriptions ;
    private double[] prices ;
    private String[] image ;


    public FoodMenuForm(String menu) {

        // replace input string with menu  ie inputstring = menu
        //String inputString = "name1|discription1|10|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\nname1|discription|40|sdhjbvcjhdsdbvc\nname5|discription5|20|sdhvbjshbd\n";

        String[] lines = inputString.split("\\n"); // Split input into lines

        String[] array1 = new String[lines.length];
        String[] array2 = new String[lines.length];
        String[] array3 = new String[lines.length];
        String[] array4 = new String[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\\|"); // Split each line into parts using the pipe delimiter
            array1[i] = parts[0];
            array2[i] = parts[1];
            array3[i] = parts[2];
            array4[i] = parts[3];
        }
        double[] doublearray3 = new double[array3.length];


        for (int i = 0; i < array3.length; i++) {
            doublearray3[i] = Double.parseDouble(array3[i]);
        }

        foodNames = array1;
        descriptions = array2;
        prices = doublearray3;
        image = array4;
        setTitle("Food Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3));
        setSize(600, 400); // Set the window size to 600x400 pixels

        listModel = new DefaultListModel<String>();
        for (int i = 0; i < foodNames.length; i++) {
            listModel.addElement(foodNames[i]); // Add food names to the list model
        }

        foodList = new JList<String>(listModel);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        foodList.addListSelectionListener(new FoodListListener());

        JScrollPane scrollPane = new JScrollPane(foodList);
        add(scrollPane);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        add(infoPanel);

        descriptionLabel = new JLabel("");
        infoPanel.add(descriptionLabel);

        imageLabel = new JLabel();
        infoPanel.add(imageLabel);

        priceLabel = new JLabel("");
        infoPanel.add(priceLabel);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new AddToCartButtonListener());
        infoPanel.add(addToCartButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton);

        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        add(new JScrollPane(cartTextArea));

        pack();
        setVisible(true);
    }
    private class FoodListListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                int selectedIndex = foodList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedFood = foodNames[selectedIndex];
                    String selectedDescription = descriptions[selectedIndex];
                    double selectedPrice = prices[selectedIndex];

                    // Decode the Base64 image string
                    byte[] decodedBytes = Base64.getDecoder().decode(image[selectedIndex]);

                    // Convert the byte array to a BufferedImage
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Update the ImageIcon of the imageLabel
                    ImageIcon icon = new ImageIcon(img);
                    imageLabel.setIcon(icon);
                    
                    //System.out.println("=======================");
                    descriptionLabel.setText(selectedDescription);
                    priceLabel.setText("Price: $" + selectedPrice);
                }
            }
        }
    }


    private class AddToCartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int selectedIndex = foodList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedFood = foodNames[selectedIndex];
                double selectedPrice = prices[selectedIndex];
                total += selectedPrice;
                cartTextArea.append(selectedFood + " - $" + selectedPrice + "\n");
                cartTextArea.append("Total: $" + total + "\n\n");
            }
        }
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (total == 0) {
                JOptionPane.showMessageDialog(FoodMenuForm.this, "Your cart is empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(FoodMenuForm.this, "Your total is $" + total + ". Do you want to submit your order?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Process order here
                    JOptionPane.showMessageDialog(FoodMenuForm.this, "Your order has been submitted. Thank you!");
                    total = 0;

                    //send this to server this is the order item list and total
                    String order = cartTextArea.getText();
                     
                    cartTextArea.setText("");
                    
                }

            }   
        }
    }
    public static void main(String[] args) {
       new FoodMenuForm("p");
    }
}
       
