import java.util.Comparator;

class SummaryComparator implements Comparator<Sentence>
{

	@Override
	public int compare(Sentence o1, Sentence o2)
	//does same as Sentence Comparator but greater obj is not with large score, but greater number
	//will implement in the summary tool class to ensure the sentence appear in order as in orginial text
	{
		// TODO Auto-generated method stub
		if (o1.num > o2.num)
			return 1;
		
		else if (o1.num < o2.num)
			return -1;
		
		else
			return 0;
	}
	

}
