
 class Sentence 
 {
	 int paranum; //for paragraph no. of the sentence
	 int num; //sentence no. in entire text
	 int stringlen; //no. of chars in the sentence
	 double score; //score of the sentence for its importance
	 int numWords; //getting no. of words
	 String value; //actual string (sentence)
	 
	 Sentence (int num, String value, int stringlen, int paranum)
	 {
		 this.num = num;
		 this.value = new String(value); //sentence value is actual string
		 this.stringlen = stringlen;
		 this.paranum = paranum;
		 
		 numWords = value.split("\\s+").length; //getting no. of words by word tokenizing sentence
		 score = 0.0; 
	 }
 }
