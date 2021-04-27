import java.util.PriorityQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
	
public class huffman_encode {

	public huffman_encode() {
		// TODO Auto-generated constructor stub
	}

	
	FileInputStream in;
	int[] frequency = new int[100000];

	class HuffmanNode {
	  int item;
	  char c;
	  HuffmanNode left;
	  HuffmanNode right;
	}

	// For comparing the nodes
	class ImplementComparator implements Comparator<HuffmanNode> {
	  public int compare(HuffmanNode x, HuffmanNode y) {
		
	    return x.item - y.item;
	  }
	}


	  public static void printCode(HuffmanNode root, String s) {
	    if (root.left == null && root.right == null && Character.isLetter(root.c)) 
	    {
	      System.out.println(root.c + "   |  " + s);
	      
	      return;
	    }
	    
	    	printCode(root.left, s + "0");
	    
	    	printCode(root.right, s + "1");
	  }
	    
	      void frequency(String str) {  
//	          String str = "picture perfect";
	    	  
//	    	  System.out.println(str);
	          int[] freq = new int[str.length()];  
	          int i, j;  
	            
	          //Converts given string into character array  
	          char string[] = str.toCharArray();  
	            
	          for(i = 0; i <str.length(); i++) {  
	              freq[i] = 1;  
	              for(j = i+1; j <str.length(); j++) {  
	                  if(string[i] == string[j]) {  
	                      freq[i]++;  
	                        
	                      //Set string[j] to 0 to avoid printing visited character  
	                      string[j] = '0';  
	                  }  
	              } 
	              frequency[i] = freq[i]; 
	          }  
	            
	          //Displays the each character and their corresponding frequency  
	          System.out.println("Characters and their corresponding frequencies");  
	          for(i = 0; i <freq.length; i++) {  
	              if(string[i] != ' ' && string[i] != '0')  
	                  System.out.println(string[i] + "-" + freq[i]);  
	          }  
	          
//	          for(i = 0; i <freq.length; i++) {  
//	        	  if(string[i] != ' ' && string[i] != '0') 
//	        		  frequency[i] = freq[i];  
//	          }
	      }  
	  

	  void encoding(String str) {

//	    int n = 4;
	    char[] charArray = str.toCharArray();
//	    int[] charfreq = { 5, 1, 6, 3 };

//	    try {
//			
//			 in = new FileInputStream("C:\\Users\\Aditya\\eclipse-workspace\\Jsoup\\bin\\output2.txt");
//			
//		} 
//	    
//	    catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	    int nextChar, i = 0;
//	    char[] temp = new char[1000000];
//	    
//	    try {
//			while((nextChar = in.read()) != -1) {
//				
//				temp[i] = (char)nextChar;
//				i++;
//				System.out.println(temp[i]);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	    PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(str.length(), new ImplementComparator());

	    for (int j = 0; j < str.length(); j++) {
	      HuffmanNode hn = new HuffmanNode();

	      hn.c = charArray[j];
	      hn.item = frequency[j];

	      hn.left = null;
	      hn.right = null;

	      q.add(hn);
	    }

	    HuffmanNode root = null;

	    while (q.size() > 1) {

	      HuffmanNode x = q.peek();
	      q.poll();

	      HuffmanNode y = q.peek();
	      q.poll();

	      HuffmanNode f = new HuffmanNode();

	      f.item = x.item + y.item;
	      f.c = '-';
	      f.left = x;
	      f.right = y;
	      root = f;

	      q.add(f); 
	      
	    }
	    
	    System.out.println(" Char | Huffman code ");
	    System.out.println("--------------------");
	    printCode(root, " ");
	  }
	}

