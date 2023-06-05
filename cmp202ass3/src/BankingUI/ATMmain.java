package BankingUI;

import BankingUI.Bankaccounts.BankAccounts;
import BankingUI.Bankaccounts.CurrentAccount;
import BankingUI.Bankaccounts.SavingsAccount;
import file.MyFileWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ATMmain {

    private BankAccounts account;

    public float accountbalance = 100000;

    private float accumulatedWithdrawal = 0;

    public static void main(String[] args) {
        ATMmain atmui = new ATMmain();
        atmui.homepage();
    }

    JFrame homepageframe = new JFrame("Supreme Intelligence Bank");

    JLabel welcome = new JLabel("Welcome to Supreme Intlligence Bank");

    JLabel inputpinlabel = new JLabel("Input pin:");

    JTextField inputpin = new JTextField();

    JButton login = new JButton("Login");


    public void homepage() {
        homepageframe.setSize(150, 200);
        homepageframe.setLayout(new GridLayout(3, 1));
        homepageframe.setVisible(true);
        homepageframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        homepageframe.add(welcome);
        homepageframe.add(inputpinlabel);
        homepageframe.add(inputpin);
        homepageframe.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accounttype();
            }
        });
    }


    JFrame accounttypeframe = new JFrame("Account Type");

    JLabel accounttypeselect = new JLabel("Select your account type");
    JButton savings = new JButton("Savings Account");

    JButton current = new JButton("Current Account");

    public void accounttype() {

        String enteredpin = inputpin.getText();
        if (!enteredpin.equals("5555")) {
            JOptionPane.showMessageDialog(null, "Invalid pin");
            return;
        }
        accounttypeframe.setSize(150, 200);
        accounttypeframe.setLayout(new GridLayout(3, 1));
        accounttypeframe.setVisible(true);
        accounttypeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        accounttypeframe.add(accounttypeselect);
        accounttypeframe.add(savings);
        accounttypeframe.add(current);

        savings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {account = new SavingsAccount(100000);
                transaction("Savings Account");
            }
        });

        current.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { account = new CurrentAccount(100000);
                transaction("Current Account");
            }
        });
    }


    JFrame transactionframe = new JFrame("Transaction type");

    JLabel transactiontype = new JLabel("Select the transaction you want to perform");

    JButton deposit = new JButton("Deposit");

    JButton withdraw = new JButton("Withdraw");


    public void transaction(String accounttype) {
        transactionframe.add(transactiontype);
        transactionframe.add(deposit);
        transactionframe.add(withdraw);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit(accounttype);
            }
        });
        transactionframe.add(withdraw);

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(accounttype);
            }
        });

        transactionframe.setSize(150, 200);
        transactionframe.setLayout(new GridLayout(3, 1));
        transactionframe.setVisible(true);
        transactionframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    JFrame depositframe = new JFrame("Deposit");

    JLabel depositammount = new JLabel("Input the amount you want to deposit");

    JTextField inputdammount = new JTextField();

    JButton clickdeposit = new JButton("Deposit");

    public void deposit(String accounttype) {
        depositframe.add(depositammount);
        depositframe.add(inputdammount);
        depositframe.add(clickdeposit);

        clickdeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float amount = Float.parseFloat(inputdammount.getText());
                    JOptionPane.showMessageDialog(null, "you have successfully deposited: N" + amount);
                    MyFileWriter.appendData("Account Type: " + accounttype + "--deposited: " + amount);
                } catch (NumberFormatException | IOException exception) {
                    JOptionPane.showMessageDialog(null, "invalid input");
                }
            }
        });

        depositframe.setSize(150, 200);
        depositframe.setLayout(new GridLayout(3, 1));
        depositframe.setVisible(true);
        depositframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

        JFrame withdrawframe = new JFrame("Withdraw");

        JLabel withdrawammount = new JLabel("Input the amount you want to withdraw");

        JTextField inputwammount = new JTextField();

        JButton clickwithdraw = new JButton("Withdraw");

    public void withdraw(String accounttype){
        withdrawframe.add(withdrawammount);
        withdrawframe.add(inputwammount);
        withdrawframe.add(clickwithdraw);

        clickwithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float amount = Float.parseFloat(inputwammount.getText());
                        if (accounttype.equals("Savings Account")) {
                                float totalWithdrawal = accumulatedWithdrawal + amount;
                            if (totalWithdrawal > 20000) {
                                JOptionPane.showMessageDialog(null, "Withdrawal limit exceeded.");
                                return;
                            }
                            accumulatedWithdrawal = totalWithdrawal;
                        } else if (accounttype.equals("Current Account")) {
                            if (amount > accountbalance) {
                                JOptionPane.showMessageDialog(null, "Insufficient funds.");
                                return;
                            }
                        }

                    accountbalance = amount;
                    JOptionPane.showMessageDialog(null, "Withdrawn: " + amount);
                    MyFileWriter.appendData("Account Type: " + accounttype + "--Withdrawn: " + amount);

                }catch (NumberFormatException | IOException exception){
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });

        withdrawframe.setSize(150, 200);
        withdrawframe.setLayout(new GridLayout(3, 1));
        withdrawframe.setVisible(true);
        withdrawframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
