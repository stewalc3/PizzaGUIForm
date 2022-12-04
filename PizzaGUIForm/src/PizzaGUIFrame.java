import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class PizzaGUIFrame extends JFrame {
    JPanel MainPanel;
    JPanel TopP;
    JPanel BotP;

    JPanel CrustPanel;
    JRadioButton Thin;
    JRadioButton Regular;
    JRadioButton DeepDish;
    ButtonGroup CrustButtons;


    JPanel SizePanel;
    String[] SizeStrings = {"Small", "Medium", "Large", "Super"};
    JComboBox SizeBox;


    JPanel TopPanel;

    JCheckBox PepperoniBox;
    JCheckBox SausageBox;
    JCheckBox AnchoviesBox;
    JCheckBox TomatoBox;
    JCheckBox FetaBox;
    JCheckBox OnionBox;


    JPanel ReceiptPanel;
    JTextArea ReceiptDisplay;

    JPanel MenuPanel;
    JButton OrderButton;
    JButton ClearButton;
    JButton QuitButton;

    double BaseCost;
    ArrayList<String> Toppings = new ArrayList<>();
    double SubTotal;

    public PizzaGUIFrame() throws HeadlessException {

        MainPanel = new JPanel(new BorderLayout());
        TopP = new JPanel();
        TopP.setLayout(new BoxLayout(TopP, BoxLayout.Y_AXIS));
        BotP = new JPanel();

        CrustPanel();
        SizePanel();
        TopPanel();
        ReceiptPanel();
        MenuPanel();

        TopP.add(CrustPanel);
        TopP.add(SizePanel);
        TopP.add(TopPanel);
        BotP.add(TopP);
        BotP.add(ReceiptPanel);
        MainPanel.add(BotP, BorderLayout.CENTER);
        MainPanel.add(MenuPanel, BorderLayout.SOUTH);
        add(MainPanel);
    }

    private void CrustPanel() {
        CrustPanel = new JPanel();
        CrustButtons = new ButtonGroup();
        Thin = new JRadioButton("Thin");
        Thin.setSelected(true);
        Regular = new JRadioButton("Regular");
        DeepDish = new JRadioButton("Deep-dish");
        CrustButtons.add(Thin);
        CrustButtons.add(Regular);
        CrustButtons.add(DeepDish);
        CrustPanel.add(Thin);
        CrustPanel.add(Regular);
        CrustPanel.add(DeepDish);
        TitledBorder CrustBorder;
        CrustBorder = BorderFactory.createTitledBorder("Crust");
        CrustPanel.setBorder(CrustBorder);

    }

    private void SizePanel() {
        SizePanel = new JPanel();
        SizeBox = new JComboBox(SizeStrings);
        SizeBox.setSelectedIndex(0);
        SizePanel.add(SizeBox);
        TitledBorder SizeBorder;
        SizeBorder = BorderFactory.createTitledBorder("Size");
        SizePanel.setBorder(SizeBorder);
    }

    private void TopPanel() {
        TopPanel = new JPanel();
        PepperoniBox = new JCheckBox("Pepperoni");
        SausageBox = new JCheckBox("Sausage");
        AnchoviesBox = new JCheckBox("Anchovies");
        TomatoBox = new JCheckBox("Tomato");
        FetaBox = new JCheckBox("Feta");
        OnionBox = new JCheckBox("Onions");
        TopPanel.add(PepperoniBox);
        TopPanel.add(SausageBox);
        TopPanel.add(AnchoviesBox);
        TopPanel.add(TomatoBox);
        TopPanel.add(FetaBox);
        TopPanel.add(OnionBox);
        TitledBorder TopBorder;
        TopBorder = BorderFactory.createTitledBorder("Toppings");
        TopPanel.setBorder(TopBorder);
    }

    private void ReceiptPanel() {
        ReceiptPanel = new JPanel();
        ReceiptDisplay = new JTextArea("", 20, 40);
        ReceiptDisplay.setEditable(false);
        ReceiptDisplay.setFont(new Font("Courier New", Font.PLAIN, 12));
        ReceiptDisplay.setText("Please place an order.");
        ReceiptPanel.add(ReceiptDisplay);
    }

    private void MenuPanel() {
        MenuPanel = new JPanel();
        QuitButton = new JButton("Quit");
        QuitButton.addActionListener((ActionEvent ae) -> {
        Object[] options = {"Yes, Quit", "No, Cancel"};
        int Answer = JOptionPane.showOptionDialog(null, "What would you like to do?","Quit:Continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options,options[1]);
        if(Answer == JOptionPane.YES_OPTION){

            System.exit(0);
        }
        else if (Answer == JOptionPane.CANCEL_OPTION) {
            return;
        }
        });

        ClearButton = new JButton("Clear");
        ClearButton.addActionListener(new ClearListener());
        OrderButton = new JButton("Order");
        OrderButton.addActionListener(new OrderListener());
        MenuPanel.add(OrderButton);
        MenuPanel.add(ClearButton);
        MenuPanel.add(QuitButton);
    }

    public class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DecimalFormat df2 = new DecimalFormat("#.##");
            Object Size;
            String Crust = "";
            Size = SizeBox.getSelectedItem();
            if (Size == "Small") {
                BaseCost = 8.00;
            } else if (Size == "Medium") {
                BaseCost = 12.00;
            } else if (Size == "Large") {
                BaseCost = 16.00;
            } else if (Size == "Super") {
                BaseCost = 20.00;
            } else {
                BaseCost = 99999.00;
            }

            if (Thin.isSelected()) {
                Crust = "Thin";
            } else if (Regular.isSelected()) {
                Crust = "Regular";
            } else if (DeepDish.isSelected()) {
                Crust = "Deep-dish";
            } else {
                Crust = "Error";

            }

            Toppings.clear();


            if (PepperoniBox.isSelected()) {
                Toppings.add("Pepperoni");
            }
            if (SausageBox.isSelected()) {
                Toppings.add("Sausage");
            }
            if (AnchoviesBox.isSelected()) {
                Toppings.add("Anchovies");
            }
            if (TomatoBox.isSelected()) {
                Toppings.add("Tomato");
            }
            if (FetaBox.isSelected()) {
                Toppings.add("Feta");
            }
            if (OnionBox.isSelected()) {
                Toppings.add("Onions");
            }

            SubTotal = BaseCost + Toppings.size();
            ReceiptDisplay.setText("==================================================\n");
            ReceiptDisplay.append(String.format("%-39s", (Size.toString() + " " + Crust + " Crust " + "Pizza")) + String.format("%6s", BaseCost) + "\n");
            for (String t : Toppings) {
                ReceiptDisplay.append(String.format("%-39s", t) + String.format("%6s", "1.0") + "\n");
            }
            ReceiptDisplay.append("\n" + String.format("%-39s", "Sub-Total:") + String.format("%6s", SubTotal) + "\n");
            ReceiptDisplay.append(String.format("%-39s", "Tax:") + String.format("%6s", df2.format(SubTotal * 0.07)) + "\n");
            ReceiptDisplay.append("--------------------------------------------------\n");
            ReceiptDisplay.append(String.format("%-39s", "Total:") + String.format("%6s", df2.format(SubTotal * 1.07)) + "\n");
            ReceiptDisplay.append("==================================================\n");
        }
    }

    public class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thin.setSelected(true);
            SizeBox.setSelectedIndex(0);
            PepperoniBox.setSelected(false);
            SausageBox.setSelected(false);
            AnchoviesBox.setSelected(false);
            TomatoBox.setSelected(false);
            FetaBox.setSelected(false);
            OnionBox.setSelected(false);
            ReceiptDisplay.setText("Please place an order.");
        }
    }
}
