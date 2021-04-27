import java.util.Comparator;

class SentenceComparator implements Comparator<Sentence>
 {

	@Override
	public int compare(Sentence o1, Sentence o2) //comparing two sentences 
	{
		// TODO Auto-generated method stub
		if(o1.score > o2.score) 
			return -1;
		
		else if (o1.score < o2.score)
			return 1;
		
		else
			return 0;
	}
	 

 }
