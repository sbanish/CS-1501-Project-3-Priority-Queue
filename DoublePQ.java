import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
import java.util.HashMap;

public class DoublePQ
{
	static PriorityQueue footage = new PriorityQueue();
	static PriorityQueue price = new PriorityQueue();
	static ArrayList<Apartment> apartments = new ArrayList<>();
	static int size;
	
	public DoublePQ()
	{
		size = 0;
	}
	
	public void add (Apartment a)
	{
		apartments.add(a);
		System.out.println("----");
		System.out.println("Added " + a + " to the list ");
		System.out.println("----");
		
		PQApartment aptFootage = new PQApartment(size, a.getFootage());
		footage.add(aptFootage);
		PQApartment aptPrice = new PQApartment(size, a.getPrice());
		price.add(aptPrice);
		size++;
	}
	
	public Apartment getCheapest()
	{
		if (apartments.size() == 0) return null;
		int num = ((PQApartment) price.peek()).pos;
		return apartments.get(num);
	}
	
	public Apartment getMostFootage()
	{
		if (apartments.size() == 0) return null;
		int num = ((PQApartment) footage.peek()).pos;
		return apartments.get(num);
	}
	
	public static void printList()
	{
        for(int i =0; i<apartments.size(); i++)
		{
            if(apartments.get(i) !=null)
                System.out.println(apartments.get(i));
        }
    }
	
	  public static void removeApartment(String address)
	  {
        if(apartments.size() == 0) return;
        Stack<PQApartment> tempPrice = new Stack<>();
        Stack<PQApartment> tempFootage = new Stack<>();
        int num = -1;
        for(int i =0; i<apartments.size(); i++)
		{
            if(address.equalsIgnoreCase(apartments.get(i).getAddress()))
			{
                System.out.println("----");
                System.out.println("Removed  " + apartments.get(i) + " from the list.");
                System.out.println("----");
                apartments.set(i,null);
                size--;
                num = i;
            }
        }
        if(num == -1)
		{
            System.out.println("----");
            System.out.println("Address Not Found");
            System.out.println("----");
            return;
        }
        while(!price.isEmpty())
		{
            PQApartment tempApartment = (PQApartment)price.remove();
            if(tempApartment.pos == num)
			{
                break;
            }
            tempPrice.add(tempApartment);
        }
        while(!tempPrice.isEmpty())
		{
            price.add(tempPrice.pop());
        }
        while(!footage.isEmpty())
		{
            PQApartment tempApartment = (PQApartment)footage.remove();
            if(tempApartment.pos == num)
			{
                break;
            }
            tempFootage.add(tempApartment);
        }
        while(!tempFootage.isEmpty())
		{
            footage.add(tempFootage.pop());
        }
    }
	
	 public void updateApartment(String address, String updateValue, int updateField){
        if(apartments.size() == 0) return;
        int found = -1;
        for(int i =0; i<apartments.size(); i++)
		{
            if(apartments.get(i).getAddress().equalsIgnoreCase(address))
			{
                found = i;
                break;
            }
        }
        if(found < 0)
		{
            System.out.println("----");
			System.out.println("Apartment not found");
            System.out.println("----");
            return;
        }
        switch (updateField)
		{

            case 2:
                int num;
                Scanner scan = new Scanner(System.in);
                while(true) 
				{
                    try {
                        num = Integer.parseInt(updateValue);
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Enter a number for the price: ");
                        updateValue = scan.nextLine();
                    }
                }
                apartments.get(found).setPrice(num);
                System.out.println("----");
                System.out.println("Updated Apartment to: "+apartments.get(found));
                System.out.println("----");
                Stack<PQApartment> tempPrice = new Stack<>();
                while(!price.isEmpty())
				{
                    PQApartment tempApartment = (PQApartment)price.remove();
                    if(tempApartment.pos == found)
					{
                        tempApartment.value = num;
                        tempPrice.add(tempApartment);
                        break;
                    }
                    tempPrice.add(tempApartment);
                }
                while(!tempPrice.isEmpty())
				{
                    price.add(tempPrice.pop());
                }

                break;
            case 3:
                int num2;
                Scanner scan2 = new Scanner(System.in);
                while(true) 
				{
                    try {
                        num2 = Integer.parseInt(updateValue);
                        break;
                    }catch (NumberFormatException e){
                        System.out.println("Enter a number for the Footage: ");
                        updateValue = scan2.nextLine();
                    }
                }
                apartments.get(found).setFootage(num2);
                System.out.println("----");
                System.out.println("Updated Apartment to: "+apartments.get(found));
                System.out.println("----");
                Stack<PQApartment> tempFootage = new Stack<>();
                while(!footage.isEmpty())
				{
                    PQApartment tempApartment = (PQApartment)footage.remove();
                    if(tempApartment.pos == found){
                        tempApartment.value = num2;
                        tempFootage.add(tempApartment);
                        break;
                    }
                    tempFootage.add(tempApartment);
                }
                while(!tempFootage.isEmpty())
				{
                    footage.add(tempFootage.pop());
                }
                break;
            case 6:
                apartments.get(found).setZipCode(updateValue);
                System.out.println("----");
                System.out.println("Updated Apartment to: "+apartments.get(found));
                System.out.println("----");
                break;
        }

    }
    public Apartment getLowPriceByAptNumAndCity(String aptNum, String city)
	{
        if(apartments.size() == 0) return null;
        Apartment found = new Apartment();
        Stack<PQApartment> tempPrice = new Stack<>();
        boolean notFound = true;
        while(!price.isEmpty())
		{
            PQApartment tempApartment = (PQApartment)price.remove();
            if(apartments.get(tempApartment.pos).getAptNum().equalsIgnoreCase(aptNum) && apartments.get(tempApartment.pos).getCity().equalsIgnoreCase(city) )
			{
                found = apartments.get(tempApartment.pos);
                tempPrice.add(tempApartment);
                notFound = false;
                break;
            }
            tempPrice.add(tempApartment);
        }

        while(!tempPrice.isEmpty())
		{
            price.add(tempPrice.pop());
        }
        if(notFound)
		{
            return null;
        }
        return found;
    }
    public Apartment getMostFootageByAptNumAndCity(String aptNum, String city)
	{
        Apartment found = new Apartment();
        Stack<PQApartment> tempFootage = new Stack<>();
        boolean notFound = true;
        while(!footage.isEmpty())
		{
            PQApartment tempApartment = (PQApartment)footage.remove();
            if(apartments.get(tempApartment.pos).getAptNum().equalsIgnoreCase(aptNum) && apartments.get(tempApartment.pos).getCity().equalsIgnoreCase(city) ){
                found = apartments.get(tempApartment.pos);
                notFound = false;
                tempFootage.add(tempApartment);
                break;
            }
            tempFootage.add(tempApartment);
        }
        while(!tempFootage.isEmpty())
		{
            footage.add(tempFootage.pop());
        }
        if(notFound)
		{
            return null;
        }
        return found;
    }
    public boolean searchForAddress(String address)
	{
        for(int i =0; i<size; i++)
		{
            if(address.equalsIgnoreCase(apartments.get(i).getAddress())) return true;
        }
        return false;
    }
}