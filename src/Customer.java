import java.util.InputMismatchException; // all my imports
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.System.in;

public class Customer implements CheckerPrinter { // my class customer that implements checkprinter

    private String customerID = "";// attribute for customerID
    private Float customer_income = (float) 0;// attribute for customer_income
    private String eligbility_status = "YES";// attribute for  eligbility_status
    private ArrayList<Loan> creditrecord = new ArrayList<Loan>(1);// Arraylist used for the creditrecord

    private static int registeredLoans = 0; // Counter for registered loans
    public String getCustomerID() {
        return customerID;
    }// getter for getCustomerID

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }// setter for setCustomerID

    public double getCustomer_income() {
        return customer_income;
    }// getter for  getCustomer_income

    public void setCustomer_income(float customer_income) {
        this.customer_income = customer_income;
    }// setter for setCustomer_income

    public String getEligbility_status() {
        return eligbility_status;
    }// getter for  getEligbility_status

    public void setEligbility_status(String eligbility_status) {
        this.eligbility_status = eligbility_status;
    }// setter for setEligbility_status

    public ArrayList<Loan> getCreditrecord() {
        return creditrecord;
    }// getter for getCreditrecord

    public void setCreditrecord(ArrayList<Loan> creditrecord) {
        this.creditrecord = creditrecord;
    }// setter for setCreditrecord
    public static void incrementTotalLoans() { // method used to increment the amount of loans used for all customers
        registeredLoans++; // increment by one
    }
    public Customer() { // method for all my customer inputs

        Scanner scanner = new Scanner(in); // new scanner

        // customer ID input
        System.out.println("Please enter a customer ID"); // asking user for input
        setCustomerID(scanner.nextLine()); // sets that input if correct
        while (!customerID.matches("^[A-Z]{3}[0-9]{3}$")) {  // checking if not equal to numbers 0 to 9 and 6 numbers long
            System.out.println("Invalid customerID. Please enter a Valid ID of max length 6:"); // error message
            customerID = scanner.nextLine(); // consumes the input
        }
        // customer income rate input
        while (true) {
            try {
                System.out.println("Please enter your income");// asking user for input
                setCustomer_income(scanner.nextFloat());// sets that input if correct
                scanner.nextLine();// consumes the input
                if (customer_income < 0) { // checks if the input is less than 0
                    System.out.println("Invalid income");
                    customer_income = scanner.nextFloat(); // consumes the input
                } else {
                    break; // breaks loop
                }

            } catch (InputMismatchException e) {  // error for any none int input
                System.out.println("Invalid input.  Please enter a non-negative int value:");
                scanner.nextLine(); // consumes the input
            }

        }


    }

    public void addrecord() { // this is the method in which the records are added to the credit record , this uses a switch case and also has a checker to see if the maximum loans have been reached
        if (registeredLoans >= XYZBank.Arraysz) { // if the registared loans so far is greater than or equal to the maxiumum set by the user in XYZ
            System.out.println("Maximum number of loans has been reached. you Cannot add more loans."); // prompt
            return; // returns to main menu
        }
        Scanner scanner = new Scanner(in); // new scanner
        boolean correct = false; // variable used for loop
        while (!correct) {
            correct = true;
            System.out.println("pick a loan you would like to create(Auto ,Builder , mortgage , Personal , Other)"); // prompt
            String loan_type = scanner.nextLine(); // appends that prompt

            switch (loan_type.toLowerCase()) { // switch case which inputs the users input to lower case and then compares cases
                case "auto": // if add it will add a new auto loan to credit-record
                    creditrecord.add(new Autoloan());
                    break;
                case "builder":// if add it will add a new builder loan to credit-record
                    creditrecord.add(new BuilderLoan() );
                    break;
                case "mortgage":// if add it will add a new mortgage loan to credit-record
                    creditrecord.add(new MortgageLoan());
                    break;
                case "personal":// if add it will add a new personal loan to credit-record
                    creditrecord.add(new PersonalLoan() );
                    break;
                case "other":// if add it will add a new other loan to credit-record
                    creditrecord.add(new otherLoan());
                    break;
                default: // if input is wrong then this will loop back
                    System.out.println("loan type does not exist");
                    correct = false;
                    continue;
            }
            correct = true;
            incrementTotalLoans(); // this then increments the total loans

        }

    }
    public void removeLoan() { // method used to remove the loans , first checks if there is any loans , then it ask the user which loan they would like to delete, then deletes it , finally it will lower the loan total by one as you deleted a loan
        Scanner scanner = new Scanner(System.in); // new scanner
        if (creditrecord.isEmpty()) {  // if there is no loans
            System.out.println("No loans to remove."); // prompt
            return; // returns back if no loans
        }
        System.out.println("Enter 0 If you want to delete your first loan , 1 to delete your second  loan, 2 for your third loan..... "); // prompt
        int recordIndex = scanner.nextInt(); // applies that prompt

        if (recordIndex >= 0 && recordIndex < creditrecord.size()) { // checks if the record index is greater than or equal to 0 and if its smaller than the credit record size
            creditrecord.remove(recordIndex); // this then removes the selected loan via the user input
            System.out.println("Loan removed"); // prompt
            registeredLoans--; // lowers the overall loans as you just deleted one clearly
        } else {
            System.out.println("Invalid loan index."); // error message
        }

    }


    public boolean checkeligbility() { // method used to check the eligibilty of a customer to make a new loan
        float totalamount = 0;
        for (Loan loan : creditrecord) { // searches through the creditrecord
            totalamount += loan.getAmount_lft(); // increases the total-amount var from the total of all the amount lefts in a customers loan record
        }
        if (customer_income * 4 > totalamount){ // if the customer income *4 is greater than the total amount they are eligible
            this.eligbility_status = "YES";
        }else{
            this.eligbility_status = "NO"; // if not they arnt eligible
        }

        return (customer_income * 4 > totalamount); // then returns it
    }

    @Override
    public void printdetails() { // this method is used to print either one customer or all the customers
        Scanner scanner = new Scanner(in); // new scanner
        System.out.println("\nEnter the customer ID for a single customer or type (all) for all customers:"); // prompt
        String customerID = scanner.nextLine(); // appends that input to customer uID

        if (customerID.equals("all")) { // if the input = all then it will print all the customers
                System.out.println("Maximum amount of Loans: " +XYZBank.Arraysz); // prints the Maximum amount of Loans
                System.out.println("Registered Loans: " + registeredLoans);// prints the Registered Loans of Loans
            for (Customer customer : XYZBank.customers) { // loops through all the customers printing each one
                System.out.println("-------------------------------------------------------------"); // fancy stuff
                System.out.println("CustomerID: " +  customer.getCustomerID()); // prints the customer id
                System.out.println("Eligible to arrange new loan: " + eligbility_status); // prints if they are still eligible
                System.out.println("RecordID  LoanType  IntRate  AmountLeft  TimeLeft "); // my printing stuff
                for (Loan loan : customer.getCreditrecord()) { // loops through credit record
                    System.out.printf("%-9s %-9s %-8.2f £%-10.0f %-2.2f Years %n", // print f format to make it look good
                            loan.getRecordID(), loan.getLoan_type(), loan.getInterestRate(), // the data used
                            loan.getAmount_lft(), loan.getLoan_term_left());
                }
                System.out.println("-------------------------------------------------------------"); // fancy stuff

            }

        } else {

            for (Customer customer : XYZBank.customers) {// if the input = customerId then it will print the customer
                if (customer.getCustomerID().equals(customerID)) { // checks for the customer
                    System.out.println("Maximum amount of Loans: " +XYZBank.Arraysz); // prints the Maximum amount of Loans
                    System.out.println("Registered Loans: " + registeredLoans);// prints the Registered Loans of Loans
                    System.out.println("-------------------------------------------------------------");// fancy stuff
                    System.out.println("CustomerID: " +  customerID );
                    System.out.println("Eligible to arrange new loan: " + eligbility_status); // prints if they are still eligible
                    System.out.println("RecordID  LoanType  IntRate  AmountLeft  TimeLeft");// my printing stuff
                    for (Loan loan : customer.getCreditrecord()) {// loops through credit record
                        System.out.printf("%-9s %-9s %-8.2f £%-10.0f %-2.2f Years %n",// print f format to make it look good
                                loan.getRecordID(), loan.getLoan_type(), loan.getInterestRate(),// the data used
                                loan.getAmount_lft(), loan.getLoan_term_left());
                    }
                    System.out.println("-------------------------------------------------------------");// fancy stuff
                    break; // Stop searching once found
                }
            }
        }
    }
}