import java.util.*;  // all of imports are stored here

public abstract class Loan {
    protected String recordID = "AAAXXX";   // attribute for recordID
    protected String loan_type = "N/A";   // attribute for loan_type
    protected float interestRate = 0; // attribute for interestRate
    protected float loan_term_left = 0; // attribute for loan_term_left
    protected float amount_lft = 0; // attribute for amount_lft
    protected double overPayment; // attribute for overpayment
    static Set<String> usedRecords = new HashSet<> (Collections.emptySet()); // my set used to store reocrdID to identify if a recordID is unique if its not in the set

    // my basic constructor
    public Loan(String recordID, String loan_type, float interestRate, float loan_term_left, float amount_lft,double overPayment) {
        this.recordID = recordID;
        this.loan_type = loan_type;
        this.interestRate = interestRate;
        this.loan_term_left = loan_term_left;
        this.amount_lft = amount_lft;
        this.overPayment = overPayment;
    }

    public float getAmount_lft() {
        return amount_lft;
    } // getter for getAmount_lft

    public void setAmount_lft(float amount_lft) {
        this.amount_lft = amount_lft;
    } // setter for setAmount_lft

    public String getRecordID() {
        return recordID;
    }// getter for getRecordID

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }// setter for setRecordID

    public String getLoan_type() {
        return loan_type;
    }// getter for getLoan_type

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }// setter for setLoan_type

    public float getInterestRate() {
        return interestRate;
    }// getter for getInterestRate

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }// setter for setInterestRate

    public float getLoan_term_left() {
        return loan_term_left;
    }// getter for getLoan_term_left

    public void setLoan_term_left(float loan_term_left) {
        this.loan_term_left = loan_term_left;
    }// setter for setLoan_term_left

    public Loan() { // my main method for inputing data into a loan
        Scanner scanner = new Scanner(System.in); // new scanner

        // record ID input
        System.out.println("Please enter a Record ID"); // asks the user for recordiD
        setRecordID(scanner.nextLine());  // sets recordID from the input
        while (!recordID.matches("^[0-9]{6}$") || usedRecords.contains(recordID)) { // this while loop is used to check if the record Id is the correct length and also if its within the set used records to check if a recordID is unqie
            System.out.println("Invalid or duplicate Record ID. Please enter a valid ID of maximum length 6 and ensure it's unique:"); // prompt the user with an error message
            recordID = scanner.nextLine(); // consumes the value
        }
        usedRecords.add(recordID); // adds the record ID if valid to the set to be compared later



        // interest rate input
        while(true) { // while true loop
            try { // exception handling
                System.out.println("Please enter a valid interest Rate");// prompt the user with a question to input
                setInterestRate(scanner.nextFloat());// sets interestrate from the input
                scanner.nextLine();// consumes the input
                if (interestRate < 0) { // checks if the input is less than 0
                    System.out.println("Invalid interest rate");// prompt the user with an error message
                    interestRate = scanner.nextFloat(); // consumes the input
                } else {
                    break; // breaks the loop once a valid input in submitted
                }

            } catch (InputMismatchException e) {  // error for any none int input
                System.out.println("Invalid input.  Please enter a non-negative int value:");// prompt the user with an error message
                scanner.nextLine(); // consumes the input
            }

        }
        // time left input
        while(true) {// while true loop
            try {// exception handling
                System.out.println("Please enter the loan time left in years");;// prompt the user with a question to input
                setLoan_term_left(scanner.nextFloat());// sets loan term left from the input
                scanner.nextLine();// consumes the input
                if (loan_term_left < 0) { // checking if input is less than 0
                    System.out.println("Invalid Time left.");// prompt the user with an error message
                } else {
                    break; // breaks the loop once a valid input in submitted
                }

            }catch (InputMismatchException e){ // error for any none int input
                System.out.println("Invalid input. Please enter a valid time value in years:");// prompt the user with an error message
                scanner.nextLine();// consumes the input
            }
        }
        while(true) {// while true loop
            try {// exception handling
                System.out.println("Please your amount left to pay");// prompt the user with a question to input
                setAmount_lft(scanner.nextFloat());// sets amount  left from the input
                scanner.nextLine();// consumes the input
                if (amount_lft < 0) { // checks if the input is less than 0
                    System.out.println("Invalid interest rate");// prompt the user with an error message
                    amount_lft = scanner.nextFloat(); // consumes the input
                } else {
                    break; // breaks the loop once a valid input in submitted
                }

            } catch (InputMismatchException e) {  // error for any none int input
                System.out.println("Invalid input.  Please enter a non-negative int value:");// prompt the user with an error message
                scanner.nextLine(); // consumes the input
            }

        }

    }
}
// these are my subclasses for 5 loans , of which builder and mortgage have an overpayment option
class Autoloan extends Loan {  // autoloan class extending Loan
    public Autoloan(){  // method in which I use my superclass to inherit the to inherit attributes and methods from one class to another
        super(); // super class
        loan_type = "Auto"; // setting the loan type
    }
}

class BuilderLoan extends Loan {// BuilderLoan class extending Loan

    public BuilderLoan() { // method in which I use my superclass to inherit the to inherit attributes and methods from one class to another
        super();// super class
        loan_type = "Builder";// setting the loan type
        while (true) {
            Scanner scanner = new Scanner(System.in); // new scanner
            try {
                System.out.println("Please enter the overpayment percentage (0-2):"); // asks for input
                double overPaymentPercentage = scanner.nextDouble(); // inputs the users input
                scanner.nextLine(); // Consume newline

                if (overPaymentPercentage >= 0 && overPaymentPercentage <= 2) { // checks if the input if greater than or equal to 0 and is less than or equal to 2
                    this.overPayment = overPaymentPercentage; // sets the overpaymentPercentage
                    break; // breaks loop

                } else {
                    System.out.println("Invalid overpayment percentage. Must be between 0 and 2."); // error message to user
                }


            } catch (InputMismatchException e) { // error exception
                System.out.println("Invalid input. Please enter a valid double value.");
                scanner.nextLine(); // Consume newline
            }
        }
    }
}


class PersonalLoan extends Loan{// Personal Loan class extending Loan
    public PersonalLoan(){// method in which I use my superclass to inherit the to inherit attributes and methods from one class to another
        super();// super class
        loan_type = "Personal";// setting the loan type
    }

}
class otherLoan extends Loan {// otherLoan class extending Loan
    public otherLoan() {// method in which I use my superclass to inherit the to inherit attributes and methods from one class to another
        super();// super class
        loan_type = "other";// setting the loan type
    }

}

class MortgageLoan extends Loan{// autoloan class extending Loan
    public MortgageLoan(){// method in which I use my superclass to inherit the to inherit attributes and methods from one class to another
        super();// super class
        loan_type = "Mortgage";// setting the loan type
        while (true) {
            Scanner scanner = new Scanner(System.in); // new scanner
            try {
                System.out.println("Please enter the overpayment percentage (0-2):");// asks for input
                double overPaymentPercentage = scanner.nextDouble();// inputs the users input
                scanner.nextLine(); // Consume newline

                if (overPaymentPercentage >= 0 && overPaymentPercentage <= 2) {// checks if the input if greater than or equal to 0 and is less than or equal to 2
                    overPayment = overPaymentPercentage;// sets the overpaymentPercentage
                    break;

                } else {
                    System.out.println("Invalid overpayment percentage. Must be between 0 and 2."); // error message to user
                }


            } catch (InputMismatchException e) {// error exception
                System.out.println("Invalid input. Please enter a valid double value."); // error message to user
                scanner.nextLine(); // Consume newline
            }
    }

 }
}

