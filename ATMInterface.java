import java.io.*;
import java.util.*;

/**
 * ATMMachine
 */
class Account {

    static int acc_number = 1111;

    String acc_holder_name;

    int pin;

    double balance;

    String unique_id;

    int a_no;


    void createAcc(){
        a_no = acc_number;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Account Holder Name: ");
        acc_holder_name = sc.nextLine();
        System.out.println("\n Enter Username: ");
        unique_id = sc.nextLine();
        int length_pin = 0;
        do{
            System.out.println("Enter Your 4 Digit PIN: ");
            pin = sc.nextInt();
            length_pin = String.valueOf(pin).length();
        } while (length_pin != 4);
        System.out.println("Enter Initial deposit Amount: ");
        balance = sc.nextDouble();
        System.out.println("Congratulations Amount Successfully Credited!!\n");
        System.out.println("Account Details-\n" + "Account Number- " + a_no + "\nAccount Holder Name- " + acc_holder_name + "\nBalance- " + balance + "\nThank You!");

        String fileName = acc_number + ".txt";
        File file = new File(fileName);

        try {
            file.createNewFile();
            FileWriter writer= new FileWriter(file);
            writer.write("Account Created\n");
            writer.write("Account Number: " + acc_number + "\n");
            writer.write("USER ID- r: " + unique_id + "\n");
            writer.write("Account Holder name: " + acc_holder_name + "\n");
            writer.write("PIN: " + pin + "\n");
            writer.write("Balance: " + balance + "\n");
            writer.write("Date: " + new Date() + "\n\n\n");
            writer.close();
            //System.out.println("File" + fileName + " created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file" + fileName);
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        acc_number++;
    }
}

class ATM{

    void withdraw(Account acc){
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("Withdraw Money Mode\n");
        System.out.println("Enter amount in Multiples of 100: ");
        double amount = sc.nextDouble();
        if (amount % 100 == 0) {
            if (acc.balance >= amount) {
                acc.balance = amount;
                System.out.print("Your Transaction is Processing\n");
                try {
                    
                    String fileName = acc.a_no + ".txt";

                    FileWriter fileWriter = new FileWriter(fileName, true);
                    BufferedWriter buffWriter = new BufferedWriter(fileWriter);

                    buffWriter.write("Date: " + new Date() + "\n");
                    buffWriter.write("Withdraw: " + amount + "\n");
                    buffWriter.write("Account Number: " + acc.a_no + "\n");
                    buffWriter.write("Remaining Balance: " + acc.balance + "\n\n");
                    buffWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to file.");
                    e.printStackTrace();
                }
                System.out.println("Thank You For Banking with Us!");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                System.out.println("Insufficient Funds");
            }
        } else {
            System.out.println("Amount not in multiple of 100!");
            System.out.println("Try Again!");
        }
    }


    void deposit_by_transfer(Account acc, double amount){
        acc.balance += amount;
        try {
            String fileName = acc.a_no + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);

            buffWriter.write("Deposit: " + amount + "\n");
            buffWriter.write("Date: " + new Date() + "\n");
            buffWriter.write("Account Number: " + acc.a_no + "\n");
            buffWriter.write("Remaining Balance: " + acc.balance + "\n\n");
            buffWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }



    void deposit(Account acc){
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Money Deposit Mode");
        System.out.println("ENter amount you want to Deposit: ");
        double amount = sc.nextDouble();
        acc.balance += amount;
        System.out.print("\033[H\033[2J");
        System.out.flush(); 

        try {
            String fileName = acc.a_no + ".txt";
            // System.out.println("The File Name - " + fileName);
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);

            buffWriter.write("Deposit: " + amount + "\n");
            buffWriter.write("Date: " + new Date() + "\n");
            buffWriter.write("Account Number: " + acc.a_no + "\n");
            buffWriter.write("Remaining Balance: " + acc.balance + "\n\n");
            buffWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
        System.out.println("Processing Your Request! Please wait...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Transaction completed successfully!");
        System.out.println("\n\nThank You For Banking With Us!");
        System.out.println("---Going to Login Page ---");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void Trasfer(Account acc1, Account acc2, double amount){
        if (acc1.balance >= amount) {
            acc1.balance = amount;
            ATM a = new ATM();
            a.deposit_by_transfer(acc2, amount);
            
            try {
                String fileName = acc1.a_no + ".txt";
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter buffWriter = new BufferedWriter(fileWriter);
    
                buffWriter.write("Transferred: " + amount + "\n");
                buffWriter.write("Date: " + new Date() + "\n");
                buffWriter.write("Account Number: " + acc1.a_no + "\n");
                buffWriter.write("Remaining Balance: " + acc1.balance + "\n\n");
                buffWriter.close();
                fileWriter.close();
    
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file.");
                e.printStackTrace();
            }
            System.out.println("Processing Your Request! Please wait...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Transaction completed successfully!");
            System.out.println("\n\nThank You For Banking With Us!");
            System.out.println("---Going to Login Page ---");
    
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        }
    }



    void display_details(Account acc){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Displaying account details...\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String file_name = String.valueOf(acc.a_no) + ".txt";
        File file = new File(file_name);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buff = new BufferedReader(reader);
            String line = null;
            while ((line = buff.readLine()) != null) {
                System.out.println(line);
            }
            buff.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Screen will automatically timeout after 30 seconds......");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }

    void quit(){
        System.out.println("Thank You for Banking With Us!!\n");
        System.exit(0);
    }
}

class run_atm{
    int account_serch_by_unique_id(Account[] ac, String unique_id){
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(unique_id, ac[i].unique_id))
            return i;  
        }
        return -1;
    }

    int account_serch_by_unique_id(Account[] ac, int account_number){
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(account_number, ac[i].a_no))
                return i;
            }
            return -1;
    }

    void demo (Account[] ac){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\nWelcome to ATM\n");
        System.out.println("\nEnter your Card/Unique ID: ");
        String unique_id = sc.nextLine();
        int i = account_serch_by_unique_id(ac, unique_id);
        if (i == -1) {
            System.out.println("Account not Registered Yet!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            return;
        } else {
            System.out.println("Enter Your PIN: ");
            int pin = sc.nextInt();
            if (pin == ac[i].pin) {
                System.out.println("Select th Option given below!\n 1. Withdraw: \n 2. Deposit:  \n 3. Display Account Details:  \n 4. Account Transfer \n 5. Quit: ");
                int ch;
                ATM atm = new ATM();
                ch = sc.nextInt();
                switch (ch) {
                    case 1 -> atm.withdraw(ac[i]);
                    case 2 -> atm.deposit(ac[i]);
                    case 3-> atm.display_details(ac[i]);
                    case 4 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Enter account number to transfer funds: ");
                        int account_transfer = sc.nextInt();
                        int j = account_serch_by_unique_id(ac, account_transfer);
                        if (j == -1) {
                            System.out.println("Account is not Correct!");
                            return;
                        } else {
                            System.out.println("Enter Amount for Transferring the Funds: ");
                            double amount = sc.nextDouble();
                            atm.Trasfer(ac[i], ac[j], amount);
                        }
                    }
                    case 5-> atm.quit();
                }
            } else {
                System.out.println("Wrong PIN Entered! \n Try Again!");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                return;
            }
        }
    }
}


public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account[] ac = new Account[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Creating Account Mode \n");
            ac[i] = new Account();
            ac[i].createAcc();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        run_atm ob = new run_atm();
        for(; ;){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ob.demo(ac);
        }
    }
    
}