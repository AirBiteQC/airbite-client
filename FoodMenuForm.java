
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FoodMenuForm extends JFrame {
    private JList<String> foodList;
    private DefaultListModel<String> listModel;

    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JButton addToCartButton;
    private JTextArea cartTextArea;
    private JButton submitButton;
    private double total;

    private String[] foodNames ;
    private String[] descriptions ;
    private double[] prices ;

    public FoodMenuForm(String menu) {

        // replace input string with menu  ie inputstring = menu
        String inputString = "name1|discription1|10\nname1|discription|40\nname5|discription5|20\n";

        String[] lines = inputString.split("\\n"); // Split input into lines

        String[] array1 = new String[lines.length];
        String[] array2 = new String[lines.length];
        String[] array3 = new String[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\\|"); // Split each line into parts using the pipe delimiter
            array1[i] = parts[0];
            array2[i] = parts[1];
            array3[i] = parts[2];
        }
        double[] doublearray3 = new double[array3.length];

        for (int i = 0; i < array3.length; i++) {
            doublearray3[i] = Double.parseDouble(array3[i]);
        }

        foodNames = array1;
        descriptions = array2;
        prices = doublearray3;
        setTitle("Food Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));
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
       
