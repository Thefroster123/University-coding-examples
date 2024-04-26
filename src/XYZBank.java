import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class XYZBank {
       public static ArrayList<Customer> customers = new ArrayList<Customer>(1); // my global ArrayList for customers
       public static int Arraysz; // global maximum number of records var
    public static void main(String[] args) { // main method

        Scanner scanner = new Scanner(System.in); // scanner

        while(true) {	// asking the user for a number for the records , then giving the input to generate an array size
            try {
                System.out.println("Provide a maximum number of records"); // prompt
                Arraysz = scanner.nextInt(); // applies the input to var
                scanner.nextLine(); // consumes and moves on
                break;

            } catch (InputMismatchException e) {   // error providing an input of other than int is made
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        while (true) {
            try{ // bellow is the menu which shows the user the options to pick and is using a else if statment and while true loop to loop back after somethings done and will only end once someone exits
                System.out.println("1.Register a new customer 2 Update information about existing customer 3. print information about a particular user 4.exit");
                int menuChoice = scanner.nextInt(); // applies the input to var
                scanner.nextLine(); // consumes and moves on

                if (menuChoice == 1) { // option one to add a customer
                    System.out.println("Customer registered successfully.");
                    Customer c = new Customer();
                    customers.add(c);// adds a loan in customer class and asks for prompts


                } else if (menuChoice == 2) {  // update information about a user
                    if (customers.isEmpty()) { // if there's no customer registered it will loop back
                        System.out.println("No customers added");
                        continue;

                    }


                    Customer custUpdate = null;
                    boolean custFound = false;
                    while (!custFound) { // this while loop which uses vars to identify the customer and append it to a var called custfound
                        System.out.println("\nUpdate information about an existing customer:");// prompts
                        System.out.print("Enter the customer ID: ");// prompts
                        String customerID = scanner.nextLine();
                        for (Customer customer : customers) { // searching through customers
                            if (customer.getCustomerID().equals(customerID)) {// if the customer id input equal the one in customer
                                custUpdate = customer; // puts the customer as custupdate to use later
                                custFound = true;
                                break;
                            }
                        }
                        if (!custFound) { // will loop back if theres an error in input
                            System.out.println("you have inputted a none existing customerID , please put in your correct ID");
                        }
                    }


                    // Second nested switch case which now asks the user for income, status , add loan and remove a Loan
                    System.out.println("\n Which would you like to update (1 income, 2 status, 3 add loan , 4 remove Loan ");
                    String secmenu = scanner.nextLine();

                    switch (secmenu) {
                        case "1": // case one for updating income
                            System.out.print("Enter the new income: "); // prompt
                            float newIncome = scanner.nextFloat(); // applies to var
                            custUpdate.setCustomer_income(newIncome); // then it sets the customers income with the new var
                            System.out.println("Income updated successfully."); // prompt
                            break;
                        case "2": // checks if the customer is eligible
                            if (custUpdate.checkeligbility()) {// will check if the customer inputed is eligible
                                System.out.println("Customer is eligible for a new loan or loan update.");
                            } else {
                                System.out.println("Customer is not eligible for a new loan or loan update.");
                            }
                            break;
                        case "3":
                            // THIS adds a new record to the customer and says beforehand if they are eligible or not , if not is wont let you add one
                            if (custUpdate.checkeligbility()) {
                                System.out.println("Customer is eligible for a new loan or loan update.");
                                custUpdate.addrecord();
                            } else {
                                System.out.println("Customer is not eligible for a new loan or loan update.");

                            }
                            break;
                        case "4": // remove loan
                            custUpdate.removeLoan(); // this will use the method in customer to remove a loan
                            break;

                        default:
                            System.out.println("error input"); // error checking
                            break;
                }

            }
            else if (menuChoice == 3) { // print all the details
                if (customers.isEmpty()) { // checks if the customer is empty
                    System.out.println("No customers added");
                    continue; // will return back
                }
                System.out.println("Printing information for a particular user");
                for (Customer customer : XYZBank.customers) { // loops through customers and will work corresponding to input in printdetails in customer
                    customer.printdetails(); // method in customers
                }
            }
            else if (menuChoice == 4) { // an exit option to end program in the CW2
                    System.out.println("exiting");
                    return;

                }
            } catch (InputMismatchException e) { // error checking the menu for input errors
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer

                }
            }
        }

}
