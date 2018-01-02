public class Apartment
{
	String address;
	String aptNumber;
	String city;
	String zipCode;
	int price;
	int footage;
	
	public Apartment(){}
	
	public void setAddress(String s)
	{
		address = s;
	}
	
	public void setAptNum(String s)
	{
		aptNumber = s;
	}
	
	public void setCity(String s)
	{
		city = s;
	}
	
	public void setZipCode(String s)
	{
		zipCode = s;
	}
	
	public void setPrice(int s)
	{
		price = s;
	}
	
	public void setFootage(int s)
	{
		footage = s;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public int getFootage()
	{
		return footage;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String getAptNum()
	{
		return aptNumber;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public String toString()
	{
		return ("An apartment located at " + address + " Apt # " + aptNumber + " " + city + " " + zipCode + " priced at $ " + price + " with " + footage + " sqft");
	}
}