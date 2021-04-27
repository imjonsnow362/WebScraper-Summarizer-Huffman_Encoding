import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class SummaryTools 
{
	FileInputStream in;
	FileOutputStream out;
	ArrayList<Sentence> sentences; 
	ArrayList<Paragraph> paragraphs;
	int numSentence, numPara;
	double store = 0;
	String display;
	
	double[][] intersectionMatrix;
	LinkedHashMap<Sentence, Double> dictionary = new LinkedHashMap<>();
	ArrayList<Sentence> contentSummary = new ArrayList<Sentence>();
	
	SummaryTools()
	{
		//initializing everything to zero or null
		in = null;
		out = null;
		numSentence = 0;
		numPara = 0;
	}
	
	void initialize()
	{
		sentences = new ArrayList<Sentence>();
		paragraphs = new ArrayList<Paragraph>();
		
		numSentence = 0;
		numPara = 0;
		
		try {
			
			in = new FileInputStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\output2.txt");
			out = new FileOutputStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void extractSentences()
	{
		int nextChar, j = 0;
		int prevChar = -1;
		
		try {
			while((nextChar = in.read())!= -1)
			{
				j = 0;
				char[] temp = new char[100000];
				
				while((char)nextChar != '.')
				{
//					System.out.println(nextChar + " ");
					temp[j] = (char)nextChar;
					if((nextChar = in.read()) == -1) {
						break;
					}
					if((char)nextChar == '\n' && (char)prevChar == '\n') {
						numPara++;
					}
					j++;
					prevChar = nextChar;
				}
				
//				sentences.add(new Sentence(numSentence, (new String(temp)).trim(), 
//						(new String(temp)).trim().length(), numPara));
				
				Sentence lines= new Sentence(numSentence, new String(temp).trim(), new String(temp).trim().length(), numPara);
				sentences.add(lines);
				numSentence++;
				prevChar = nextChar;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	void groupSentencestoPara()
	{
		int paraNum = 0;
		Paragraph paragraph = new Paragraph(0);
		
		for (int i = 0; i < numSentence; i++) 
		{
		if(sentences.get(i).paranum == paraNum) 
		{
			continue;
		}
		else
		{
			
				paragraphs.add(paragraph);
				paraNum++;
				paragraph = new Paragraph(paraNum);
				
			}
			paragraphs.add(paragraph);
		}
		
	}
	
	
	double noofCommonWords(Sentence str1, Sentence str2)
	{
		double commonCount = 0;
		for (String str1Word : str1.value.split("\\s+"))
		{
			for(String str2Word : str2.value.split("\\s+"))
			{
				if(str1Word.compareToIgnoreCase(str2Word) == 0)
					commonCount++;
			}
		}
		
		return commonCount;
	}
	
	void createIntersectionMatrix()
	{
		intersectionMatrix = new double[numSentence][numSentence];
		for(int i = 0; i < numSentence; i++ )
		{
			for(int j = 0; j < numSentence; j++)
			{
					
					if(i <= j)
					{
						Sentence str1 = sentences.get(i);
						Sentence str2 = sentences.get(j);
					intersectionMatrix[i][j] = 
							noofCommonWords(str1, str2) / ((double)(str1.numWords + str2.numWords)/2);
					}
				else
					intersectionMatrix[i][j] = intersectionMatrix[j][i];
			}
		}
	}
	
	void createDictionary()
	{
		for(int i = 0; i < numSentence; i++)
		{
			double score = 0 ;
			for (int j = 0; j < numSentence; j++)
			{
				score += intersectionMatrix[i][j] ;
			}
			
			dictionary.put(sentences.get(i), score);
			((Sentence)sentences.get(i)).score = score;
			store = score;
//			System.out.println(score);
//			System.out.println();
//			System.out.println(numPara);
//			System.out.println(numSentence);
//			System.out.println(dictionary.size());
			
		}
		
	}
	
	void createSummary()
	{
		
		for(int j = 0; j <numPara; j++)
		{
			int primary = paragraphs.get(j).sentences.size()/5;
			

			//sorting based on score
			Collections.sort(paragraphs.get(j).sentences, new SentenceComparator());
			
			for(int i = 0; i <primary; i++)
			{
				contentSummary.add(paragraphs.get(j).sentences.get(i));
				System.out.println(contentSummary);
			}
		}
		
		//for proper ordering
		Collections.sort(contentSummary, new SummaryComparator());
	}
	
	void printSentences()
	{
		for (Sentence sentence : sentences)
		{
			System.out.println(sentence.num+ "-->" +sentence.value+ "-->" 
		+sentence.stringlen+ "-->"+sentence.numWords+ "-->"+sentence.paranum);
		}
	}
	
	void printIntersectionMatrix()
	{
		for(int i = 0; i < numSentence; i++)
		{
			for (int j = 0; j < numSentence; j++)
			{
				System.out.println(intersectionMatrix[i][j]);
			}
		}
		System.out.println("\n");
	}
	
	void printDictionary()
	{
		Set<Entry<Sentence,Double>> set = new HashSet<>();
		//getting set of enteries
		set = dictionary.entrySet();
		
		//Get iterator
		Iterator i = set.iterator();
		
		//dispay element
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)i.next();
//			System.out.println(((Sentence)me.getKey()).value+": ");
			display = ((Sentence)me.getKey()).value;
//			System.out.println(me.getValue());
		}
	}
	
	
	void printSummary()
	{
		System.out.println("No. of paragraphs = "+numPara);
		for (int i = 0; i < numPara; i++) {
		System.out.println(paragraphs.get(i).sentences);
		}
		ArrayList<Sentence> sentence = contentSummary;
		System.out.println(sentence);
		for(Sentence sentences : contentSummary)
		{
			System.out.println(sentences.value);
		}
	}
	double getWordCount(ArrayList<Sentence> sentenceList)
	{
		double wordCount = 0.0;
		for(Sentence sentence : sentenceList)
		{
			wordCount += (sentence.value.split(" ")).length;
		}
		return wordCount;
	}
	
	void printStats()
	{
		System.out.println("No. of words in file ="+getWordCount(sentences));
		System.out.println("No. of words in summary ="+getWordCount(contentSummary));
		System.out.println("Compression ="+getWordCount(contentSummary)/getWordCount(sentences));
	}
	
	 
			
	void improvedSummary() throws IOException
	{
		 PrintStream ps = new PrintStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\scrapedArticle.txt");
		 PrintStream console1 = System.out;
		 System.setOut(ps);
		  
		Path path = Paths.get("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\output2.txt");
		
		String content = null;
		try {
			content = Files.readString(path, StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quikFix summarize = new quikFix();
		System.out.println("\n\n\t\t\t\t ACTUAL ARTICLE : \n\n\n"+content);
		
		System.setOut(console1);
		
		 PrintStream o = new PrintStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\summary.txt");
		 PrintStream console = System.out;
		 System.setOut(o);
		 
		String display = summarize.Summarize(content, numSentence/5);
		System.out.println("\n\n\t\t\t\t SUMMARY : \n\n");
		System.out.println(display);
		
		System.setOut(console);
//		huffman_encode freq = new huffman_encode();
//		freq.frequency(content);
//		
//		freq.encoding(content);
		
		Huffman hf = new Huffman();
		try {
			hf.calling(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
