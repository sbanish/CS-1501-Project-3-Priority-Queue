public class PQApartment implements Comparable<PQApartment>
{
	int pos;
	int value;
	
	public PQApartment(int x, int y)
	{
		pos = x;
		value = y;
	}
	
	@Override
	public int compareTo(PQApartment a)
	{
		if (value <a.value) return -1;
		if (value == a.value) return 0;
		if (value > a.value) return 1;
		return 0;
	}
}