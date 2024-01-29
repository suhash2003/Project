package com.project;

import java.util.Scanner;

//Bank Account class
class BankAccount {
 private double balance;

 // Constructor to initialize balance
 public BankAccount(double balance) {
     this.balance = balance;
 }

 // Method to get balance
 public double getBalance() {
     return balance;
 }

 // Method to deposit money
 public void deposit(double amount) {
     balance += amount;
 }

 // Method to withdraw money
 public boolean withdraw(double amount) {
     if (amount <= balance) {
         balance -= amount;
         return true;
     } else {
         return false;
     }
 }
}

//ATM class
public class ATM {
 private BankAccount bankAccount;

 // Constructor to initialize bank account
 public ATM(BankAccount bankAccount) {
     this.bankAccount = bankAccount;
 }

 // Method to display options and perform transactions
 public void start() {
     Scanner scanner = new Scanner(System.in);
     int option;

     do {
         System.out.println("\nATM Menu:");
         System.out.println("1. Check Balance");
         System.out.println("2. Deposit");
         System.out.println("3. Withdraw");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");
         option = scanner.nextInt();

         switch (option) {
             case 1:
                 checkBalance();
                 break;
             case 2:
                 System.out.print("Enter amount to deposit: ");
                 double depositAmount = scanner.nextDouble();
                 deposit(depositAmount);
                 break;
             case 3:
                 System.out.print("Enter amount to withdraw: ");
                 double withdrawAmount = scanner.nextDouble();
                 withdraw(withdrawAmount);
                 break;
             case 4:
                 System.out.println("Thank you for using the ATM.");
                 break;
             default:
                 System.out.println("Invalid option. Please try again.");
         }
     } while (option != 4);

     scanner.close();
 }

 // Method to check balance
 private void checkBalance() {
     System.out.println("Your balance is: $" + bankAccount.getBalance());
 }

 // Method to deposit money
 private void deposit(double amount) {
     bankAccount.deposit(amount);
     System.out.println("$" + amount + " deposited successfully.");
 }

 // Method to withdraw money
 private void withdraw(double amount) {
     if (bankAccount.withdraw(amount)) {
         System.out.println("$" + amount + " withdrawn successfully.");
     } else {
         System.out.println("Insufficient balance.");
     }
 }

 public static void main(String[] args) {
     // Create a bank account with initial balance
     BankAccount bankAccount = new BankAccount(1000);

     // Create an ATM instance
     ATM atm = new ATM(bankAccount);

     // Start the ATM
     atm.start();
 }
}
