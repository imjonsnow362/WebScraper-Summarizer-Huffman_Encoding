import java.util.ArrayList;

class Paragraph 
{

	int paranum; //paragraph number
	ArrayList<Sentence> sentences; //collection of sentences makes a paragraph
	
	Paragraph (int paranum)
	{
		this.paranum = paranum;
		sentences = new ArrayList<Sentence>();
		
	}
	
}
