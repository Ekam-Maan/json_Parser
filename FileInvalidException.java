//----------------------------------------------------------------------------------------
//Assignment-3
//Written By:- Sanyam Kadd(40106824) and Ekamjot Singh(40106849)
//----------------------------------------------------------------------------------------
/**
 * Assignment-3
 * This is an Exception class whose's object is thrown when Invalid file is detected
 * @author Sanyam Kadd(40106824) and Ekamjot Singh(40106849)
 * @version 3.0.1
 * Due date: 15-November-2019 
 */
public class FileInvalidException extends Exception {
	private String field="";
    private int no=0;
	/**
	 * This is the parameterised constructor which set the field and no atributes.
	 * @param s this store the field which is found empty.
	 * @param i this store the number of the file in which the field is found empty.
	 */
    public FileInvalidException(String s,int i) {
		this.field=s;
		this.no=i;
		
	}
	/**
	 * This getMessage() method returns the string stating in which file and which field is found empty
	 * @return the string stating in which file and which field is found empty 
	 */
    public  String  getMessage() {
		return (("\nError detected empty feild!\n================================\n"
				+"Problem detected with input file: Latex"+no+".bib"
							+" File is invalid: Field \""+field+"\" is Empty. Processing stopped at this point"
							+ ". Other empty fields may be present as well !"));
	}

}
