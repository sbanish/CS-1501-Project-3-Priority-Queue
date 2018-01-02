import java.util.Scanner;
import java.util.Random;

public class AptTracker {
    static DoublePQ queue = new DoublePQ();
    public static void main(String[] args){
        String input;
        int choice =0;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Apartment Tracker Options");
            System.out.println("1. Add an Apartment");
            System.out.println("2. Update an Apartment");
            System.out.println("3. Remove an Apartment");
            System.out.println("4. Get Lowest Priced Apartment");
            System.out.println("5. Get Most Footage Apartment");
            System.out.println("6. Get Lowest Priced Apartment by City");
            System.out.println("7. Get Most Footage Apartment by City");
            System.out.println("8. Print All Apartments");
            System.out.println("9. Exit");
            System.out.print("Please enter the number of the option you want: ");
            input = scan.nextLine();
            while (true){
                try {
                    choice= Integer.parseInt(input);
                    break;
                }
                catch (NumberFormatException e){
                    System.out.print("Invalid Input, only enter the number that you want:");
                    input = scan.nextLine();
                }
            }
            switch (choice){
                case 1:
                    System.out.println("Adding Apartment");
                    addApartment();
                    break;
                case 2:
                    if(queue.size ==0){
                        System.out.println("No Apartments in Queue");
                        break;
                    }
                    updateApartment();
                    break;
                case 3:
                    if(queue.size ==0){
                        System.out.println("----");
                        System.out.println("No Apartment in Queue");
                        System.out.println("----");
                        break;
                    }
                    System.out.print("Enter the address of the apartment you want to remove:");
                    String address = scan.nextLine();
                    queue.removeApartment(address);
                    break;
                case 4:
                    if(queue.size ==0){
                        System.out.println("----");
                        System.out.println("No Apartments in Queue");
                        System.out.println("----");
                        break;
                    }
                    System.out.println("Getting Lowest Priced Apartment");
                    System.out.println("----");
                    System.out.println(queue.getCheapest());
                    System.out.println("----");
                    break;
                case 5:
                    if(queue.size ==0){
                        System.out.println("----");
                        System.out.println("No Apartments in Queue");
                        System.out.println("----");
                        break;
                    }
                    System.out.println("Getting Most Footage Apartment");
                    System.out.println("----");
                    System.out.println(queue.getMostFootage());
                    System.out.println("----");
                    break;
                case 6:
                    if(queue.size ==0){
                        System.out.println("No Apartments in Queue");
                        break;
                    }
                    System.out.println("Getting Lowest Priced Apartment by City");
                    getLowPriceByCity();
                    break;
                case 7:
                    if(queue.size ==0){
                        System.out.println("----");
                        System.out.println("No Apartments in Queue");
                        System.out.println("----");
                        break;
                    }
                    System.out.println("Getting Most Footage Apartment by City");
                    getMostFootageByCity();
                    break;
                case 8:
                    if(queue.size ==0){
                        System.out.println("----");
                        System.out.println("No Apartments in Queue");
                        System.out.println("----");
                        break;
                    }
                    queue.printList();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Enter a choice within the menu");
            }

        }
    }
    public static void addApartment(){
        String input;
        int choice =0;
        Scanner scan = new Scanner(System.in);
        Apartment a = new Apartment();
        System.out.print("Enter address: ");
        input = scan.nextLine();
        if(queue.searchForAddress(input)){
            System.out.println("That address is already assigned to an apartment in the list. Try again.");
            return;
        }
        a.setAddress(input);
        System.out.print("Enter Apt Number: ");
        input = scan.nextLine();
        a.setAptNum(input);
        System.out.print("Enter city: ");
        input = scan.nextLine();
        a.setCity(input);
        System.out.print("Enter zipcode: ");
        input = scan.nextLine();
        a.setZipCode(input);
        System.out.print("Enter Apartment Price $ ");
        input = scan.nextLine();
        int price;
        while(true) {
            try {
                price = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.out.println("Enter a number for the price: ");
                input = scan.nextLine();
            }
        }
        a.setPrice(price);
        System.out.print("Enter footage: ");
        input = scan.nextLine();
        int footage;
        while(true) {
            try {
                footage = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.out.println("Enter a number for the footage: ");
                input = scan.nextLine();
            }
        }
        a.setFootage(footage);
        queue.add(a);
    }
    public static void updateApartment(){
        String input;
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the address of the apartment you want to update: ");

        input = scan.nextLine();
        if(!queue.searchForAddress(input)){
            System.out.println("Address not found, returning to previous menu.");
            return;
        }
        String input2;
        while(true) {
            System.out.println("Apartment Update Options");
            System.out.println("1. Update Price");
            System.out.println("2. Update Footage");
            System.out.println("3. Update ZipCode");
            System.out.println("4. Exit");
            System.out.print("Please enter the number of the option you want: ");
            input2 = scan.nextLine();
            while(true) {
                try {
                    choice = Integer.parseInt(input2);

                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Please enter the number of the option you want: ");
                    input2 = scan.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter the new price for the apartment: ");
                    input2 = scan.nextLine();
                    queue.updateApartment(input, input2, 2);

                    break;
                case 2:
                    System.out.print("Enter the new footage for the apartment: ");
                    input2 = scan.nextLine();
                    queue.updateApartment(input, input2, 3);

                    break;
                case 3:
                    System.out.print("Enter the new zipcode for the apartment: ");
                    input2 = scan.nextLine();
                    queue.updateApartment(input, input2, 6);

                    break;
                case 4:
                    return;
                default:
                    System.out.println("Only enter a number within the listed range");
            }
        }
    }
    public static void getLowPriceByCity(){
        String aptNum;
        String city;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the apartment number of the apartment: ");
        aptNum = scan.nextLine();
        System.out.print("Enter the city of the apartment: ");
        city = scan.nextLine();
        Apartment a = queue.getLowPriceByAptNumAndCity(aptNum,city);
        if(a == null){
            System.out.println("There was no combination of apartment number and city in the database.");
        }
        else{
            System.out.println("----");
            System.out.println(a);
            System.out.println("----");
        }

    }
    public static void getMostFootageByCity(){
        String aptNum;
        String city;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the apartment number of the apartment: ");
        aptNum = scan.nextLine();
        System.out.print("Enter the city of the apartment: ");
        city = scan.nextLine();
        Apartment a = queue.getMostFootageByAptNumAndCity(aptNum, city);
        if(a == null){
            System.out.println("There was no combination of apartment number and city in the Database.");
        }
        else{
            System.out.println("----");
            System.out.println(a);
            System.out.println("----");
        }
    }
}