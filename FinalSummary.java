import java.io.IOException;

class FinalSummary 
{
	public static void main(String[] args) throws IOException
	{
		JsoupRun jsouprun = new JsoupRun();
		jsouprun.JsoupDemo();
//		
		SummaryTools summary = new SummaryTools();
		summary.initialize();
		summary.extractSentences();
//		
//		
		summary.improvedSummary();
		
		System.out.println("SUCCESS!!!!");
		
//		summary.groupSentencestoPara();
//		summary.printSentences();
//		summary.createIntersectionMatrix();
//		summary.printIntersectionMatrix();
//		
//		summary.createDictionary();
//		summary.printDictionary();
////		
//
//		summary.createSummary();
//		summary.printSummary();
////		
//		summary.printStats();
		
//		huffman_encode hf = new huffman_encode();
//		hf.encoding();
//		
	}
}
