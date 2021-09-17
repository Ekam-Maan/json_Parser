//----------------------------------------------------------------------------------------
//Assignment-3
//Written By:- Sanyam Kadd(40106824) and Ekamjot Singh(40106849)
//----------------------------------------------------------------------------------------
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Assignment-3
 * This is Software called BibCreator which converts a file into three different forms
 * @author Sanyam Kadd(40106824) and Ekamjot Singh(40106849)
 * @version 3.0
 * Due date: 15-November-2019 
 */
public class BibCreatorDriver {
	static Scanner[] scarr = new Scanner[10];             // this is an array of Scanner objects to read the latex files.
	static PrintWriter[][] pwarr = new PrintWriter[10][3];// this is an array of the printwriter objects which is a 2-D array where innner array of the length three store IEEE, ACM, and NJ files. 
	static File[][] farr = new File[10][3];             
	static int pwctr = 0;

	public static void print(String s) {
		System.out.println(s);
	}

	/**
	 * This method is the Engine method of the program , it does two tasks First it checks if a file has any empty field or not.
	 * Second, it converts the latex files into the three formats namely IEEE, ACM, and NJ. 
	 * @param scArr this is an array of Scanner objects to read the latex files.
	 */
	public static void processFilesForValidation(Scanner scArr[]) {
		
		
		String line,author="",title = null,journal=null,vol=null,no=null,pg=null,kw=null,month=null,year=null,doi=null; // initailizing the vairables
		for(int i=0;i<scArr.length;i++) {
			boolean invalid=false;
			int acmSno=0;
			while(scArr[i].hasNextLine()) { // this will end when there will be the end of the passed file.
				line=scArr[i].nextLine();
				 if(line.isEmpty()) { // cheking if a line empty
					// scArr[i].nextLine();
					 continue;
				 }
				 if (line.length() == 1 && line.charAt(0) == '}') // detecting the end of an article in a file
						continue;
				 
				 if(line.substring(0,8).equals("@ARTICLE")||line.substring(0,8).equals("﻿@ARTI")) {  // detecting the starting of an article in a file
					 acmSno+=1;
					scArr[i].useDelimiter(",");
					 while(scArr[i].hasNextLine()) {
						if(scArr[i].hasNextLong()) {  // ingnoring the long part of the input file
							scArr[i].nextLine();
							continue;
						}
						
						line = scArr[i].nextLine();
						if (line.isEmpty()) {    // checking if a line is empty
							scArr[i].nextLine();
							continue;
						}
						if (line.equals("\n")) { 
							scArr[i].nextLine();
							continue;
						}
						if (line.length() == 1 && line.charAt(0) == '}') // detecting the end of an article in a file
							break;
						else {
							String[] nl=line.split("=");
							//print("nl[0] "+nl[0]);
							//print("check the split one "+nl[1].substring(1, nl[1].length()-2));
							if(nl[0].equals("author")) {
								if(nl[1].equals("{}, ")) { // checking if a file is inValid due to an empty field
									try {
										throw(new FileInvalidException ("author",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;
									}	
										
								}
								else {
									author=nl[1].substring(1, nl[1].length()-3); // storing the author of an article
								}
							}
							else if(nl[0].equals("journal")) {
								if(nl[1].equals("{}, ")) { // checking if a file is inValid due to an empty field
									try {
										throw(new FileInvalidException ("journal",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									journal=nl[1].substring(1, nl[1].length()-3);// storing the journal of an article
								}
							}
							else if(nl[0].equals("title")) {
								if(nl[1].equals("{}, ")) {// checking if a file is inValid due to an empty field
									try {
										throw(new FileInvalidException ("title",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									title=nl[1].substring(1, nl[1].length()-3);// storing the title of an article
								}
							}
							else if(nl[0].equals("year")) {
								if(nl[1].equals("{}, ")) {// checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("year",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									year=nl[1].substring(1, nl[1].length()-3);// storing the year of an article
								}
							}
							else if(nl[0].equals("volume")) {
								if(nl[1].equals("{}, ")) {
									try {
										throw(new FileInvalidException ("volume",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									vol=nl[1].substring(1, nl[1].length()-3);// storing the vol of an article
								}
							}
							else if(nl[0].equals("number")) {
								if(nl[1].equals("{}, ")) {
									try {
										throw(new FileInvalidException ("number",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									no=nl[1].substring(1, nl[1].length()-3);// storing the number of an article
								}
							}
							else if(nl[0].equals("pages")) {
								if(nl[1].equals("{}, ")) {// checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("pages",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										e.getMessage();
										break;}	
										
								}
								else {
									pg=nl[1].substring(1, nl[1].length()-3);// storing the pages of an article
								}
							}
							else if(nl[0].equals("keywords")) {
								if(nl[1].equals("{}, ")) { // checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("keywords",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										e.getMessage();
										break;}	
										
								}
								else {
									kw=nl[1].substring(1, nl[1].length()-3);// storing the keywords of an article
								}
							}
							else if(nl[0].equals("doi")) {
								if(nl[1].equals("{}, ")) { // checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("doi",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									doi=nl[1].substring(1, nl[1].length()-3);// storing the DOI of an article
								}
							}
							else if(nl[0].equals("ISSN")) {
								if(nl[1].equals("{}, ")) { // checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("ISSN",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										 pwarr[i][0].close();
										 pwarr[i][1].close();
										 pwarr[i][2].close();
										if(farr[i][0].exists())
											farr[i][0].delete();
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
							}
							else if(nl[0].equals("month")) {
								if(nl[1].equals("{},")) { // checking if a file is year due to an empty field
									try {
										throw(new FileInvalidException ("month",(i+1)));
									} catch (FileInvalidException e) {
										invalid=true;
										pwarr[i][0].close();
										pwarr[i][1].close();
										pwarr[i][2].close();
										if(farr[i][0].exists())
											print("deleted "+farr[i][0].delete());
										if(farr[i][1].exists())
											farr[i][1].delete();
										if(farr[i][2].exists())
											farr[i][2].delete();
										print(e.getMessage());
										break;}	
										
								}
								else {
									month=nl[1].substring(1, nl[1].length()-2);// storing the month of an article
								}
							}
							
							}
						}
					 
					 }
				 if(!invalid) {  // it generating the authors' names for different format
						String[] st =author.split(" and ");
						String ieeeAuthor="",njAuthor="",acmAuthor=st[0]+" et al";
						
						for(int k=0;k<st.length;k++) {
							if(k<st.length-1)
								ieeeAuthor=ieeeAuthor+st[k]+", ";
							else {
								ieeeAuthor=ieeeAuthor+st[k];
							}
							if(k<st.length-1)
								njAuthor=njAuthor+ st[k]+" & ";
							else {
								njAuthor=njAuthor+ st[k];
							}
							
						}
						
						// printing the article in the three different format in their respective files
						pwarr[i][0].println(ieeeAuthor+". \""+title+"\", "+journal+", vol. "+vol+", no. "+no+", p. "+pg+", "+month+" "+year+".");
						pwarr[i][1].println("["+acmSno+"] "+acmAuthor+". "+year+". "+title+". "+journal+". "+vol+", "+no+"("+year+"), "+pg+". DOI:https://doi.org/"+doi+".");
						pwarr[i][2].println(njAuthor+". "+title+". "+journal+". "+vol+", "+pg+"("+year+").");
					}
				 
				
				 
				 if(invalid)
					 break;
					 
				 }
	
			 scarr[i].close();    // closing the input and output files
			 pwarr[i][0].close();
			 pwarr[i][1].close();
			 pwarr[i][2].close();
		
				
			}
		}

	/**
	 * In the the main method create and opens all the input and output file 
	 * Calls the processFilesForValidation(Scanner[] sc) which is the engine of this software.
	 * It also prompts the user to enter the file of which they wants to see data.
	 * @param args no Arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~WELCOME TO THE BIBCREATOR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for (int i = 0; i < scarr.length; i++) { // opening file for reading and checking weather or not the files are
													// exists.
			try {
				int fno = i + 1;
				scarr[i] = new Scanner(new FileInputStream("Latex" + fno + ".bib"));
			} catch (FileNotFoundException e) {
				print("Could not open input file Latex" + (i + 1)
						+ " for reading. Please check if file exists! Program will terminate after closing any opened files");
				for (int j = 1; j < i; j++) {
					scarr[j].close();
				}
				System.exit(0);
			}
		}

		for (int i = 0; i < farr.length; i++) { // creating the input files.
			int fno = i + 1;
			farr[i][0] = new File("IEEE" + fno + ".json");
			farr[i][1] = new File("ACM" + fno + ".json");
			farr[i][2] = new File("NJ" + fno + ".json");
		}

		for (int i = 0, j = 0; i < pwarr.length && j < farr.length; i++, j++) { // accessing the input files
			int fno = j + 1;
			try {
				pwarr[i][0] = new PrintWriter(farr[j][0]);
				pwctr = i;
			} 
			catch (FileNotFoundException e) {

				if (!farr[j][0].exists())
					print("The file named IEEE" + fno + ".json does not exsits!");
				else
					print("The file named IEEE" + fno + ".json does exsits!, but it cannot be opened.");
				closeFiles();
				deleteFiles();
				System.exit(0);
				
			}
			try {
				pwarr[i][1] = new PrintWriter(farr[j][1]);
				pwctr = i;
			}
			catch (FileNotFoundException e) {
				if (!farr[j][0].exists())
					print("The file named ACM" + fno + ".json does not exsits!");
				else
					print("The file named ACM" + fno + ".json does exsits!, but it cannot be opened.");
				closeFiles();
				deleteFiles();
				System.exit(0);
				
			}
			try {
				pwarr[i][2] = new PrintWriter(farr[j][2]);
				pwctr = i;
			} 
			catch (FileNotFoundException e) {
				if (!farr[j][0].exists())
					print("The file named NJ" + fno + ".json does not exsits!");
				else
					print("The file named NJ" + fno + ".json does exsits!, but it cannot be opened.");
				closeFiles();
				deleteFiles();
				System.exit(0);
			}

		}
		
		processFilesForValidation(scarr); // calling the engine method where validations, farmatting and writing to the output files is done  
		
		Scanner kb=new Scanner(System.in);
		print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		int tries=0;
		BufferedReader br=null;
		while(tries<2) {
			
			System.out.print("Please enter the exact file name you want to veiw: "); // prompting user to enter file name 
			String file1=kb.nextLine();
			//String[] filearr=file1.split(".");
		    //file1=filearr[0].toUpperCase()+"."+filearr[1].toLowerCase();
			print("");
			try {
				br=new BufferedReader(new FileReader(file1));
				break;
			}
			catch(FileNotFoundException e) {
				tries++;
				if(tries==2) {
					System.out.print("You have exhausted both tries! The program will terminate....\n========================================Have a great day!========================================");
					System.exit(0);
				}
				
				System.out.print("The file you enterd does not exists! you have only ONE try left!.");
				
			}
			
		}
		String readingfile = null;
		try {
			readingfile = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
             print("Error: in reading the file");		}
		while(readingfile!=null) { // reading files to output their data
			print(readingfile);
			try {
				readingfile=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 print("Error: in reading the file");}
		}
		System.out.print("\n========================================Bye! Have a great day.========================================");

		
		

	}

	/**
	 * This closeFiles()  method closes all the input files 
	 */
	private static void closeFiles() {
		for (int i = 0; i < pwctr; i++) {
			if (pwarr[i][0] != null)
				pwarr[i][0].close();
			if (pwarr[i][1] != null)
				pwarr[i][1].close();
			if (pwarr[i][2] != null)
				pwarr[i][2].close();
		}
		
	}

	/**
	 * This deleteFIles() method delete all the created files.
	 */
	private static void deleteFiles() {
		for (int i = 0; i < farr.length; i++) {
			if (farr[i][0].exists())
				farr[i][0].delete();
			if (farr[i][1].exists())
				farr[i][1].delete();
			if (farr[i][2].exists())
				farr[i][2].delete();
		}
	}

}
