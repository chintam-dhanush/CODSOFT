import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  

class Balance {
    int balance;

    Balance() {
        balance = 1000; 
    }

    void deposit(int amount, JTextField balanceText) {
        if (amount > 0) {
            balance += amount; 
            balanceText.setText(String.valueOf(balance)); 
            JOptionPane.showMessageDialog(null, "Deposit successful! Your balance: " + balance, "Deposit", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void withdraw(int amount, JTextField balanceText) {
        if (amount <= balance) {
            balance -= amount; 
            balanceText.setText(String.valueOf(balance));
            JOptionPane.showMessageDialog(null, "Withdrawal successful! Your balance: " + balance, "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Not enough balance", "Withdrawal", JOptionPane.WARNING_MESSAGE);
        }
    }

    void checkBalance(JTextField balanceText) {
        JOptionPane.showMessageDialog(null, "Your balance: " + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
    }
}

public class Atm_interface implements ActionListener {
    JButton btnDeposit, btnWithdraw, btnCheckBalance;
    JFrame frame;
    JPanel panel1, panel2;
    JLabel titleLabel, amountLabel, balanceLabel;
    public JTextField amountTextField, balanceTextField;
    Balance account = new Balance();

    public Atm_interface() {
        createFrame();
        createPanel();
        addPanel();
        createLabel();
        createTextField();
        addLabelTextField();
        createButton();
        addButtons();
        frame.setVisible(true); 
    }

    public void createFrame() {
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(null);
    }

    public void createPanel() {
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 400, 500);
        panel1.setBackground(new Color(33, 33, 33));  
        panel1.setLayout(null); 

        panel2 = new JPanel();
        panel2.setBounds(400, 0, 200, 500);
        panel2.setBackground(new Color(3, 169, 244)); 
        panel2.setLayout(null);
    }

    public void addPanel() {
        frame.add(panel1);
        frame.add(panel2);
    }

    public void createLabel() {
        titleLabel = new JLabel("ATM MANAGEMENT SYSTEM");
        titleLabel.setBounds(60, 60, 300, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(70, 150, 200, 50);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        amountLabel.setForeground(Color.WHITE);

        balanceLabel = new JLabel("Balance:");
        balanceLabel.setBounds(70, 250, 200, 50);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setForeground(Color.WHITE);
    }

    public void createTextField() {
        amountTextField = new JTextField();
        amountTextField.setBounds(70, 200, 250, 30);
        amountTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountTextField.setBackground(Color.WHITE);

        balanceTextField = new JTextField();
        balanceTextField.setBounds(70, 300, 250, 30);
        balanceTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceTextField.setText(String.valueOf(account.balance)); 
        balanceTextField.setBackground(Color.WHITE);
        balanceTextField.setEditable(false);
    }

    public void addLabelTextField() {
        panel1.add(titleLabel);
        panel1.add(amountLabel);
        panel1.add(balanceLabel);
        panel1.add(amountTextField);
        panel1.add(balanceTextField);
    }

    public void createButton() {
        btnDeposit = new JButton("Deposit");
        btnWithdraw = new JButton("Withdraw");
        btnCheckBalance = new JButton("Check Balance");

        btnDeposit.setBounds(40, 50, 120, 40); 
        btnDeposit.setFocusable(false);
      
        btnWithdraw.setBounds(40, 100, 120, 40);
        btnWithdraw.setFocusable(false);
      
        btnCheckBalance.setBounds(40, 150, 120, 40);
        btnCheckBalance.setFocusable(false);
      
        btnDeposit.addActionListener(this);
        btnWithdraw.addActionListener(this);
        btnCheckBalance.addActionListener(this);
    }

    public void addButtons() {
        panel2.add(btnDeposit);
        panel2.add(btnWithdraw);
        panel2.add(btnCheckBalance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDeposit) {
            int amount = Integer.parseInt(amountTextField.getText());
            amountTextField.setText("");
            account.deposit(amount, balanceTextField);
        }
        
        if (e.getSource() == btnWithdraw) {
            int amount = Integer.parseInt(amountTextField.getText());
            amountTextField.setText("");
            account.withdraw(amount, balanceTextField);
        }

        if (e.getSource() == btnCheckBalance) {
            amountTextField.setText("");
            account.checkBalance(balanceTextField);
        }
    }

    public static void main(String[] args) {
        new Atm_interface();
    }
}

