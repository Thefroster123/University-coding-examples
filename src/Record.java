
import java.util.Scanner; // used for my scanner to get inputs
import java.util.InputMismatchException; // used to check user input

public class Record {
    private String RecordID = "";     // my variables used in my project these have been given default values
    private String CustomerID = "";
    private String Loantype = "";

    private float IntrestRate = 0;
    private float Timeleft = 0;
    private float Amountleft= 0;


    public String getCustomerID() {   // standard getters and setters for my project
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String RecordID) {
        this.RecordID = RecordID;
    }

    public String getLoantype() {
        return Loantype;
    }

    public void setLoantype(String Loantype) {
        this.Loantype = Loantype;
    }

    public float getAmountleft() {
        return Amountleft;
    }

    public void setAmountleft(float Amountleft) {
        this.Amountleft = Amountleft;
    }

    public float getTimeleft() {
        return Timeleft;
    }

    public void setTimeleft(float Timeleft) {
        this.Timeleft = Timeleft;
    }

    public float getIntrestRate() {
        return IntrestRate;
    }

    public void setIntrestRate(float IntrestRate) {
        this.IntrestRate = IntrestRate;
    }

    public void TheInput() {   // my part used for the input and validation of most data
        Scanner scanner = new Scanner(System.in);

        // record ID input
        System.out.println("Please enter a Record ID");
        setRecordID(scanner.nextLine());
        while (!RecordID.matches("^[0-9]{6}$")) {  // checking if not equal to numbers 0 to 9 and 6 numbers long
            System.out.println("Invalid RecordID. Please enter a Valid ID of max length 6:");
            RecordID = scanner.nextLine(); // consumes the input
        }


        // customer ID input
        System.out.println("Please enter a customer ID");
        setCustomerID(scanner.nextLine());
        while (!CustomerID.matches("^[A-Z]{3}[0-9]{3}$")) {  // checking if not equal to capital A-Z Chars of length 3 and numbers 0-9 of length 3
            System.out.println("Invalid CustomerID. Please enter a Valid ID of max length 6:");
            CustomerID = scanner.nextLine();	// consumes the input
        }



        // loan type input
        System.out.println("Please type one of the following loan types:  Auto , Builder ,  Mortgage ,  Personal or other");
        setLoantype(scanner.nextLine());
        while (!Loantype.equals("Auto") && !Loantype.equals("Builder") && !Loantype.equals("Mortgage") && !Loantype.equals("personal") && !Loantype.equals("other")) { // checking if not given the specific loan type
            System.out.println("Invalid Loan. Please enter a valid Loan  Auto ,  Builder ,  Mortgage ,  Personal  or  other:");
            Loantype = scanner.nextLine(); // consumes the input

        }

        // interest rate input
        while(true) {
            try {
                System.out.println("Please enter a valid interest Rate");
                setIntrestRate(scanner.nextFloat());
                scanner.nextLine();// consumes the input
                if (IntrestRate < 0) { // checks if the input is less than 0
                    System.out.println("Invalid interest rate");
                    IntrestRate = scanner.nextFloat(); // consumes the input
                } else {
                    break;
                }

            } catch (InputMismatchException e) {  // error for any none int input
                System.out.println("Invalid input.  Please enter a non-negative int value:");
                scanner.nextLine(); // consumes the input
            }

        }

        // amount input
        while(true) {
            try {
                System.out.println("Please enter the amountleft owed");
                setAmountleft(scanner.nextFloat());
                scanner.nextLine();// consumes the input
                if (Amountleft < 0) {     // checks if the input is less than 0
                    System.out.println("Invalid debt");
                } else {
                    break;
                }

            } catch (InputMismatchException e) { // error for any none int input
                System.out.println("Invalid input. Please enter a non-negative int value:");
                scanner.nextLine(); // consumes the input
            }

        }

        // time left input
        while(true) {
            try {
                System.out.println("Please enter the timeleft to pay");
                setTimeleft(scanner.nextFloat());
                scanner.nextLine();// consumes the input
                if (Timeleft < 0) {	   // checking if input is less than 0
                    System.out.println("Invalid Time left.");
                } else {
                    break;
                }

            }catch (InputMismatchException e){ // error for any none int input
                System.out.println("Invalid input. Please enter a valid time value in years:");
                scanner.nextLine();// consumes the input
            }
        }


    }

}