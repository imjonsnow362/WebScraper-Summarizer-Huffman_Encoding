import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

class JsoupRun 
{
	//global variable for the search operation
	public static final String SEARCH_URL= "https://www.wikihow.com/";

	    void JsoupDemo() throws IOException {
		// TODO Auto-generated constructor stub
		int i=0, j=0; 
		String[] artLink = new String[5];
	
		
		//initializing the scanner (here 'sc')
		Scanner sc = new Scanner(System.in);
		
		//taking input search term from the user
		System.out.println("Please enter your question :");
		String searchTerm = sc.nextLine();
		
		//replacing white spaces with '+' for searching  
		searchTerm = searchTerm.replaceAll("\\s", "+");
		
		
		
		//this is the format of search URL in general
		String searchURL = SEARCH_URL + "wikiHowTo?search=" +searchTerm;
		
		//declaring document to store the source code of the web page 
		Document d = Jsoup.connect(searchURL).get();
	
		//declaring element to store the 'div = searchresults_list' from the entire source code 
		Elements ele = d.select("#searchresults_list");
		 
		System.out.println("\nHere are the Top 5 articles to answer your question : \n");
		
		//for loop to declare element to store 'div = result_link' from searchresults_list div
		for (Element element : ele.select("a.result_link"))
		{
			//to restrict the results to TOP5 in the website
			if (j<5) 
			{
				//storing and printing title of article into a string
				 String title = element.select("div.result_title").text();
				 System.out.println("Title : " +title);
				 
				 //element to store stats of the article
				 for (Element stats : element.select("ul.search_results_stats"))
						 {
					 	//storing and displaying 'views'
					 	String views = stats.select("li.sr_view").text();
					 	System.out.println("Views : " +views);
					 	
					 	//storing and displaying 'updated' section
					 	String updated = stats.select("li.sr_updated").text();
					 	System.out.println("Last " +updated);
					 	
					 	//storing and displaying whether the article is verified (as per mentioned on website)
					 	String verified = stats.select("li.sp_verif").text();
					 	System.out.println(verified);
						 }
				 
				 //storing and displaying the link of the article
				 artLink[j] = element.attr("href");
				 System.out.println("Link : "+artLink[j]);
			
				 System.out.println("------------------------------------------------");
			
				 j++;
			}
		}
		
		System.out.println("Please enter the no. of article you want to read");
		i= sc.nextInt();
		
		PrintStream o = new PrintStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\output2.txt");
		PrintStream console = System.out;
		System.setOut(o);
		if (i==1) 
		{
			Document doc = Jsoup.connect(artLink[0]).get();
//			System.out.println(doc);
			
			Elements element = doc.select("#content_inner");
//			System.out.println(element);
			
			for (Element context : element.select("div.mw-parser-output"))
			{
				for(	Element block : context.select("div.mf-section-1"))
				{
					for (Element step : block.select("div.section_text")) 
					{
						for (Element steplist : step.select("ol.steps_list_2"))
						{
							for (Element stepdiv : steplist.select("div.step"))
							{
								String display = stepdiv.text();
								System.out.println(display);
								System.out.println("\n");
							}
						}
					}	
				}
			}
		}
		
		else if (i==2) 
		{
			Document doc = Jsoup.connect(artLink[1]).get();
//			System.out.println(doc);
			
			Elements element = doc.select("#content_inner");
//			System.out.println(element);
			
			for (Element context : element.select("div.mw-parser-output"))
			{
				for(	Element block : context.select("div.mf-section-1"))
				{
					for (Element step : block.select("div.section_text")) 
					{
						for (Element steplist : step.select("ol.steps_list_2"))
						{
							for (Element stepdiv : steplist.select("div.step"))
							{
								String display = stepdiv.text();
								System.out.println(display);
								System.out.println("\n");
							}
						}
					}	
				}
			}
		}
		
		else if (i==3) 
		{
			Document doc = Jsoup.connect(artLink[2]).get();
//			System.out.println(doc);
			
			Elements element = doc.select("#content_inner");
//			System.out.println(element);
			
			for (Element context : element.select("div.mw-parser-output"))
			{
				for(	Element block : context.select("div.mf-section-1"))
				{
					for (Element step : block.select("div.section_text")) 
					{
						for (Element steplist : step.select("ol.steps_list_2"))
						{
							for (Element stepdiv : steplist.select("div.step"))
							{
								String display = stepdiv.text();
								System.out.println(display);
								System.out.println("\n");
							}
						}
					}	
				}
			}
		}
		
		else if (i==4) 
		{
			Document doc = Jsoup.connect(artLink[3]).get();
//			System.out.println(doc);
			
			Elements element = doc.select("#content_inner");
//			System.out.println(element);
			
			for (Element context : element.select("div.mw-parser-output"))
			{
				for(	Element block : context.select("div.mf-section-1"))
				{
					for (Element step : block.select("div.section_text")) 
					{
						for (Element steplist : step.select("ol.steps_list_2"))
						{
							for (Element stepdiv : steplist.select("div.step"))
							{
								String display = stepdiv.text();
								System.out.println(display);
								System.out.println("\n");
							}
						}
					}	
				}
			}
		}
		sc.close();
		System.setOut(console);
		
	}


	
		
	
}


