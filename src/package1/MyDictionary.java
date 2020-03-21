package package1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class MyDictionary {

	//static String pathOfDictionaries="//media//chetan//WORK//CORE_JAVA_BEST_PRACTISE//C_Dictionary//YOUR_DICTIONARIES";
	static String pathOfDictionaries;
	static File file;// = new File(pathOfDictionaries);	
	static String nameOfDictionary;
	static Scanner sc1 = new Scanner(System.in);
	static Scanner scLine = new Scanner(System.in);
	static String wordStr;
	static FileReader fisOfCurrentDict;
	static BufferedReader br;
	static BufferedWriter bw;
	static FileWriter fosOfCurrentDict;
	static List<String> posList;
	static List<String> meanList;
	static List<String> exampList; 
	static String meanStr;
	static String exampStr;
	static  File userFile;
	
	static
	{
		File folder = new File("ALL_YOUR_DICTIONARIES_HERE");
		folder.mkdir();
		pathOfDictionaries = folder.getAbsolutePath();
		file = new File(pathOfDictionaries);	
	}
/*	//repeat untill its yes /no to delete
	private static void repeatUntilYesOrNoForDelete()
	{
		while(sc1.nextInt() != 1 && sc1.nextInt() != 2)
		{
			
			System.out.println("PLEASE ENTER ONLY EITHER 1 OR 2");
			sc1.next();
		}
	}*/
	//horizontal line
	private static void makeSectionByHorizontalline()
	{
		System.out.println("________________________________________________________________________________________________________________________________________________");
	}
	//display words in human readable format
	private static void displayWordInHumanReadableFormate(String s, int num)
	{
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		if( s != null && s.length() != 0)
		{
		String [] wordSA = s.split("=");
		String wordStr = wordSA[0];			
		//here
		String collOfMenaEXmStr=  wordSA[1];
		String meaningSA [] = collOfMenaEXmStr.split("#");			
		
		String dateSA []  = wordSA[1].split("%");
		String dateStr = dateSA[1];
		if(num != 0)
		{
			System.out.println("WORD  "+num+"  :  "+wordStr);
		}
		
		for(int k=0;k<meaningSA.length-1;k++)
		{
			String [] posSA = meaningSA[k].split(">");
			String posStr = posSA[0];
		
			String [] meanSA = posSA[1].split("<");
			String meanStr = meanSA[0];
			String exmStr = meanSA[1].substring(0, meanSA[1].length());
			StringBuilder sbExam = new StringBuilder();
			for(int p=0;p<exmStr.length();p++)
			{
				if(exmStr.charAt(p)=='%')
					break;
				sbExam.append(exmStr.charAt(p));
			}
			System.out.println(posStr);
			System.out.println("     MEANING = "+meanStr+", EXAMPLE = "+sbExam.toString());
			
		}
		System.out.println("");
		String dispStr =  "date Of additon/Modification  of ";
System.out.println(dispStr.toUpperCase()+"\""+wordStr+"\" = "+dateStr);
	//System.out.println("--------------------------------------------------------------------------------------------------------------");	
	
		}	
		
	}
	
	public static boolean isThereDictionaries()
	{		if(file != null)
	        {
		File []fa =file.listFiles();
		if(fa.length==0)
		return false;
		else
		return true;
	        }
	else
		return false;
			
	}
	
	//validation
	private static void validate(String n)
	{
		if(n==null || n.length()==0)
			throw new IllegalArgumentException("SORRY, WE CAN NOT CREATE ANONYMOUS DICTIONARIES. PLEASE GIVE NAME TO YOUR DICTIONARY, TQ");
		for(int i=0;i<n.length();i++)
		{
			char ch = n.charAt(i);
			if(  (ch>=65 && ch<=90)  || (ch>=97 && ch <= 122) || ch==32 || ch == 39)
			{
			}
				else
				throw new IllegalArgumentException("SORRY, WE CAN NOT CREATE DICTIONARIES WHOSE NAME CONTAINS SPECIAL CHARS / NUMBERS, PLEASE ENTER ONLY ALPHABETS, TQ");
			
		}
		if(isThereDictionaries())
		{
			File fa [] = file.listFiles();
			for(File f:fa)
			{
				String sa[] = f.toString().split("/");
				String filenameStr = sa[sa.length-1];
				if(filenameStr.equalsIgnoreCase(n))
				throw new IllegalArgumentException("SORRY, WE CAN NOT CREATE THE DICTIONARY "+n.toUpperCase()+" WHICH ALREADY EXISTS, PLEASE GIVE ANY NAME OTHERTHAN "+n+", TQ");
			}
		}
	
		
	}
	

	//displaying all dict in hdd
	private static void displayAllDictionaries()
	{	
			int i=1;
			File fa [] = file.listFiles();
			for(File f:fa)
		{
				String sa[] = f.toString().split("/");
				String filenameStr = sa[sa.length-1];
				System.out.println("DICTIONARY "+i+" = "+filenameStr);
				i++;
		}	

	}
	
	//isitint?
	private static void repeatEntryUntilItisInt()
	{
		while(!sc1.hasNextInt())
		{
			System.out.println("SORRY, PLEASE ENTER ONLY NUMERS, TQ");
			sc1.next();
		}
	}

	//2nd menu
	private static void subMenu(FileWriter fw) throws IOException
	{
		  int chOfDictOptions=0;
			while(chOfDictOptions != 6)
			{
				makeSectionByHorizontalline();
				System.out.println("CHOOSE HOW DO YOU WANT TO USE "+nameOfDictionary+" ?");
				    System.out.println("1  ADD  A WORD TO MY DICTIONARY");
					System.out.println("2  EDIT A WORD OF MY DICTIONARY");
					System.out.println("3  REMOVE A WORD FROM MY DICTIONARY");
					System.out.println("4  SHOW ME WORDS OF MY DICTIONARY");
					System.out.println("5  SEARCH FOR A WORD IN MY DICTIONARY");
					System.out.println("6  RETURN TO PREVIOUS MENU");
					System.out.println("ENTER YOUR CHOICE");
				    repeatEntryUntilItisInt();
				    chOfDictOptions= sc1.nextInt();
				    switch (chOfDictOptions)
				    {
					case 1:
						
					if(userFile.length() == 0)
					{
						 System.out.println("ENTER THE FIRST WORD FOR YOUR DICTIONARY "+nameOfDictionary+" TQ");
					       wordStr=sc1.next();
					       validateWord(wordStr);
					       addPOSMeanAndExamp(fw);
					}
					else
					{
						 System.out.println("ENTER A WORD");
					       wordStr=sc1.next();
					       validateWord(wordStr);
					       addPOSMeanAndExamp(fw);
					}
					      
						break;
					
					case 2:
												
						if(fisOfCurrentDict != null)
						{
							if(userFile.length() == 0)
							{
								System.out.println("SORRY, CURRENTLY THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO EDIT ");
								System.out.print("IF YOU STILL WANT TO EDIT COMEBACK AFTER ADDING ATLEAST 1 WORD TO YOUR DICTIONARY "+nameOfDictionary+" , TQ");
							}
							else
							{
								 System.out.println("ENTER A WORD TO EDIT FROM YOUR DICTIONARY "+nameOfDictionary);
								 String editStr = sc1.next();
								 if(isWordExist(userFile, editStr))
								 {
									 System.out.println("WHAT U WANT TO CHANGE FROM UR "+editStr);
									 System.out.println("1  I WANT TO EDIT WORD");
									 System.out.println("2  LET WORD BE "+editStr+" BUT I WANT TO EDIT/ADD ONLY "+editStr+" 's PARTS OF SPEACHES, MEANINGS & EXAMPLES");
									 repeatEntryUntilItisInt();
									 int editInt = sc1.nextInt();
									 if(editInt == 1)
									 {
										 System.out.println("NOW ENTER A NEW  WORD FOR "+editStr+" ."); 
										 String newWordStr = sc1.next();
										 validateWord(newWordStr);
										 editAWordDetails(userFile,editStr,newWordStr); 
									 }
									 else
										 editAWordDetails(userFile,editStr,editStr); 															
								 }
								 else
								 {
									 System.out.println("SORRY, WORD "+editStr+" DOES NOT EXISTS IN YOUR DICTIONARY "+nameOfDictionary+" TO EDIT, BETTER LUCK NEXT TIME, TQ");
								 }
								
							}
						}
						
					break;
					
					case 3:
						
					if(userFile.length()==0)
					{
						System.out.println("THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO REMOVE, TQ");
					}
					else
					{
						System.out.println("ENTER A WORD TO REMOVE FROM YOUR DICTIONARY "+nameOfDictionary);
						String remvStr = sc1.next();
						if(isWordExist(userFile, remvStr))
						{
						removeAWord(userFile, remvStr);
						System.out.println("CONGRATULATIONS......, YOUR WORD "+remvStr+" IS BEEN TRUNCATED FROM YOUR DICTIONARY "+nameOfDictionary+" & REST OF DICTIONARY ARE AS FOLLOWS");
						displayContentsOfDict(new FileReader(userFile), userFile);
						}
						else
						{
							System.out.println("SORRY ,WE CAN NOT REMOVE WORD "+remvStr+" WHICH DOES NOT EXISTS IN YOUR DICTIONARY "+nameOfDictionary+" , TQ");
						}
						
					}
						
						break;
					
					case 4:
						
					if(userFile.length()==0)
					{
						System.out.println("THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO SHOW , TQ");
					}
					else
					{
						//System.out.println("WORDS OF "+nameOfDictionary+" ARE AS FOLLOWS");
					    listOfWordsMenu();
					}
						
										
						break;
					
					case 5:
						
						if(userFile.length()==0)
						{
							System.out.println("THERE ARE ZERO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO SEARCH, TQ");
						}
						else
						{
							System.out.println("ENTER A WORD TO SEARCH IN YOUR DICTIONARY "+nameOfDictionary);
							String searchStr = sc1.next();
							searchWord(searchStr);
						}
								
						break;
					
					case 6:						
						System.out.println("RETURNING TO PREVIOUS WINDOW ? TQ ");
						break;
						

					default:System.out.println("PLEASE ENTER NUMBERS ONLY WITHIN 1 to 6 ,TQ");
						break;
					}
			}
	}
	
	//MenuListOfWrds
	private static void listOfWordsMenu() throws IOException
	{
		int chOfListMenu = 0;
		
		while(chOfListMenu != 4)
		{
			makeSectionByHorizontalline();
			System.out.println("HOW CAN WE SHOW YOUR DICTIONARY  "+nameOfDictionary+"'S WORDS ?");
			System.out.println("1  ALPHABATICAL ORDER OF WORDS");
			System.out.println("2  LENGTH OF WORDS");
			System.out.println("3  DATE OF ADDITION/MODIFICATION OF WORDS");
			System.out.println("4  RETURN TO PREVIOUS MENU");
			System.out.println("PLEASE ENTER YOUR CHOICE");
			repeatEntryUntilItisInt();
			chOfListMenu = sc1.nextInt();
			switch (chOfListMenu) {
			case 1:
				
			System.out.println("WORDS OF "+nameOfDictionary+" BY ALPABATICAL ORDER OF WORDS");
			fisOfCurrentDict = new FileReader(userFile);
				displayContentsOfDictAlpabatOrder(fisOfCurrentDict, userFile);
				break;	
			case 2:
						
			System.out.println("WORDS OF "+nameOfDictionary+" BY INCREASING ORDER OF THEIR LENGTHS");
			fisOfCurrentDict = new FileReader(userFile);
			displayContentsOfDictByLengthOfWords(fisOfCurrentDict, userFile);
				break;
				
			case 3:				
			fisOfCurrentDict = new FileReader(userFile);
				System.out.println("WORDS OF "+nameOfDictionary+" BY ASCENDING ORDER OF THEIR DATE OF ADDITION/MODIFICATION");				
				displayContentsOfDict(fisOfCurrentDict, userFile);
				break;
			case 4:	System.out.println("RETURNING TO PREVIOUS WINDOW ? TQ ");			
				break;

			default:System.out.println("PLEASE ENTER NUMBERS ONLY WITHIN 1 to 4, TQ");
				break;
			}
		}	
	}
	//displaybasedonlength
	private static void displayContentsOfDictByLengthOfWords(FileReader fr, File uf) throws IOException
	{
		if(fr != null)
		{
			if(uf.length() != 0)
			{
				SringLengthComparator slc = new SringLengthComparator();
				TreeSet<String> tsSlc = new TreeSet<String>(slc);
				br= new BufferedReader(fr);
				String line="";
				int k=1;
				
				while( (line=br.readLine()) != null)
				{
					tsSlc.add(line);		
				}
			
				for(String s:tsSlc)
				{
					displayWordInHumanReadableFormate(s,k);
					k++;
				}
				
			}
			else
				System.out.println("SORRY, THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO SHOW, TQ");
			
			
		}

	}
	//displayALphbaticalorder
	private static void displayContentsOfDictAlpabatOrder(FileReader fr, File uf) throws IOException
	{
		if(fr != null)
		{
			if(uf.length() != 0)
			{
				TreeSet<String> tsAlp = new TreeSet<String>();
				br= new BufferedReader(fr);
				String line="";
				int k=1;
				while( (line=br.readLine()) != null)
				{
					tsAlp.add(line);				
				}				
				for(String s:tsAlp)
				{					
				displayWordInHumanReadableFormate(s,k);
				k++;
				}
				
			}
			else
				System.out.println("SORRY, THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO SHOW, TQ");
			
			
		}

	}
	//displaycontents 
	private static void displayContentsOfDict(FileReader fr, File uf) throws IOException
	{
		
		if(fr != null)
		{
			if(uf.length() != 0)
			{
				br= new BufferedReader(fr);
				String line="";
				int k=1;
				while( (line=br.readLine()) != null)
				{			
					displayWordInHumanReadableFormate(line,k);
					k++;
				}
			}
			else
				System.out.println("THERE ARE NO WORDS IN YOUR DICTIONARY "+nameOfDictionary+" TO SHOW , TQ");
			
			
		}

	}
	//vaidate meng & exmple for only leeters
	private static void validateMeanAndexamp(String w)
	{
		if(w==null || w.length()==0)
		{
			throw new IllegalArgumentException("SORRY, BLANK[SPACE] CAN NOT EXPLAIN ANY WORD'S MEANING / EXAMPLE.... , TQ");
		}
		for(int i=0;i<w.length();i++)
		{
			char ch = w.charAt(i);
			if(  (ch>=65 && ch<=90)  || (ch>=97 && ch <= 122) || ch==32 )
			{
			}
				else
				{
				throw new IllegalArgumentException("SORRY, YOU CAN NOT EXPLAIN YOUR "+wordStr+" 'S MEANING / EXAMPLE IN SPECIAL CHARACTERS / NUMBERS , PLEASE ENTER ONLY LETTERS , TQ");
				}
				
			
		}
	}
	//pos
	private static void addPOSMeanAndExamp(FileWriter fw) throws IOException
	{
		int chOfPOS=0;
		posList = new ArrayList<String>();
		meanList = new ArrayList<String>();
		exampList = new ArrayList<String>();
		while(chOfPOS != 9)
		{			
			System.out.println("SELECT 1 PARTS OF SPEACH FROM BELOW TO ADD TO YOUR WORD  "+wordStr+" ALONG WITH ATLEAST 1 MEANING & 1 EXAMPLE ");
			System.out.println("1  VERB");
			System.out.println("2  NOUN");
			System.out.println("3  PRONOUN");
			System.out.println("4  ADJECTIVE");
			System.out.println("5  CONJUCTION");
			System.out.println("6  PREPOSITION");
			System.out.println("7  POS-7");
			System.out.println("8  POS-8");
			System.out.println("9  RETURN TO PREVIOUS MENU");
			repeatEntryUntilItisInt();
			chOfPOS = sc1.nextInt();
			switch (chOfPOS) {
			case 1:posList.add("VERB");
			
				System.out.println("ADD VERBAL MEANING FOR YOUR WORD "+wordStr);
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("ADD A EXAMPLE FOR  "+meanStr+" MEANING OF YOUR WORD "+wordStr);
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				 
				break;	
				
			case 2:
				posList.add("NOUN");
				
				System.out.println("ADD NOUN MEANING FOR YOUR WORD "+wordStr);
			
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
				
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				 
				break;
				
			case 3:
				posList.add("PRO-NOUN");
				
				System.out.println("ADD PRO-NOUN MEANING FOR YOUR WORD "+wordStr);
				
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
		
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				 
				break;
				
			case 4:
                 posList.add("ADJECTIVE");
				
				System.out.println("ADD ADJECTIVE MEANING FOR YOUR WORD "+wordStr);
	
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
			
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				
				break;
				
			case 5:
                posList.add("CONJUCTION");
				
				System.out.println("ADD CONJUNCTION MEANING FOR YOUR WORD "+wordStr);
			
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
				
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				break;
				
			case 6:
                 posList.add("PREPOSITION");
				
				System.out.println("ADD PREPOSITION MEANING FOR YOUR WORD "+wordStr);
				
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
	
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				break;
				
			case 7:
                posList.add("POS-7");
				
				System.out.println("ADD POS-7 MEANING FOR YOUR WORD "+wordStr);
		
				meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
				
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				break;
				
			case 8:
                 posList.add("POS-8");
				
				 System.out.println("ADD PPOS-8 MEANING FOR YOUR WORD "+wordStr);
		
			 	 meanStr = scLine.nextLine();				 
				 validateMeanAndexamp(meanStr);
				 meanList.add(meanStr);
				 
				 //example input
				 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+wordStr);
			
				 exampStr = scLine.nextLine();				
				 validateMeanAndexamp(exampStr);
				 exampList.add(exampStr);
				break;
				
			case 9:
				System.out.println("RETURNING TO PREVIOUS MENU ? OKAY, THANK YOU.");
			
				StringBuilder oneWordSB = new StringBuilder(wordStr.toUpperCase()+"=");
				int i=0;
				int totalPOS = posList.size();
			
				while( !posList.isEmpty() )
			      {
					oneWordSB.append(posList.get(0)+">"+meanList.get(0)+"<"+exampList.get(0)+"#");
					posList.remove(0);
					meanList.remove(0);
					exampList.remove(0);
			
					i++;
			      }
				Date dateOfAddition = new Date();
				oneWordSB.append("%"+dateOfAddition);
			
		
				if(fw != null)
				{
				    bw = new BufferedWriter(fw);
				      
					bw.write(oneWordSB.toString());
					bw.newLine();
				}
				bw.flush();
				//fosOfCurrentDict.flush();
				break;
				

			default:System.out.println("PLEASE ENTER NUMBERS ONLY WITHIN 1 to 9, TQ");
				break;
			}
			
		}
	}
	//isitleeter	
	private static void repeatUntilItisLettersCol()
	{
		while( !sc1.hasNext() )
		{
			System.out.println("PLEASE ENTER ONLY ALPHABETS ,TQ");
			sc1.next();
		}
	}
	
	//isLineLettersColle?
	private static void repeatUntilLineislettersCol()
	{
		while( !scLine.hasNext() && !scLine.hasNextLine())
		{
			System.out.println("PLEASE ENTER ONLY ALPHABETS ,TQ");
			scLine.nextLine();
		}
	}
	//validateWord added by user
	private static void validateWord(String w) throws IOException
	{
		fisOfCurrentDict = new FileReader(userFile);
		br = new BufferedReader(fisOfCurrentDict);
		//user validations
		for(int i=0;i<w.length();i++)
		{
			char ch = w.charAt(i);
			if(  (ch>=65 && ch<=90)  || (ch>=97 && ch <= 122) || ch==32 )
			{
			}
				else
				throw new IllegalArgumentException("SORRY, WE CAN NOT ADD YOUR WORD "+w+" TO YOUR DICTIONARY "+nameOfDictionary+" WHICH CONTAINS SPECIAL CHARACTERS / NUMBERS /BLANK SPACE , PLEASE ENTER ONLY ALPHABETS, TQ");
			
		}
		
		//buzinees validations
		if(fisOfCurrentDict != null)
		{
			//read 1-1 word from current file & compare:)
			if(br != null)
			{
				String line;
				while(  ( line=br.readLine() ) != null )
				{
				String sa[] = line.split("=");
				if(sa[0].equalsIgnoreCase(w))
				throw new IllegalArgumentException("SORRY, WE CAN NOT ADD YOUR WORD "+w+" TO YOUR DICTIONARY "+nameOfDictionary+" SINCE OUR DICTIONARIES DO NOT SUPPORT DUPLICATES, PLEASE ADD ANY WORD OTHER THAN "+w+" ,TQ");
						
				}
			}
		}
	}
	
	//searchWord
	private static  void searchWord(String w) throws IOException
	{
		fisOfCurrentDict = new FileReader(userFile);
		br = new BufferedReader(fisOfCurrentDict);
		boolean found =false;
		
		if(fisOfCurrentDict != null)
		{
			
			//read 1-1 word from current file & compare:)
			if(br != null)
			{
				String line;
				while(  ( line=br.readLine() ) != null )
				{
				String sa[] = line.split("=");
				if(sa[0].equalsIgnoreCase(w))
				{
					found =true;	
					System.out.println("YES , WORD "+w+" EXISTS IN YOUR DICTIONARY "+nameOfDictionary+" & IT'S PARTS OF SPEACHES, MEANINGS & EXAMPLES ARE AS FOLLOWS");
							
					displayWordInHumanReadableFormate(line,0);
										
				}				
				}				
			}
		}
		if(found==false)
			System.out.println("SORRY, WORD  "+w+" DOES NOT EXISTS IN YOUR DICTIONARY "+nameOfDictionary+" , TQ");
		
	}
	//boolean wordexist or not?
	private static boolean isWordExist(File uf,String w) throws IOException
	{
		boolean found =false;
		if(uf != null)
		{
			if(uf.length() != 0)
			{
				fisOfCurrentDict = new FileReader(uf);
				br = new BufferedReader(fisOfCurrentDict);
				int k=0;
				String line="";
				String editStrDetails="";
				while( (line=br.readLine()) != null )
				{
					 k++;				
					String saSearch[] = line.split("=");
					if(saSearch[0].equalsIgnoreCase(w))
					{
						found =true;
						editStrDetails = line;
					}
										  
				}
			}
	     }
		if(found==false)
			return false;	
			else
			return true;
	}	
		
	//remov word
	private static void removeAWord(File uf,String rStr) throws IOException
	{
	
		if(uf != null)
		{
			if(uf.length() != 0)
			{
				fisOfCurrentDict = new FileReader(uf);
				br = new BufferedReader(fisOfCurrentDict);
				int k=0;
				String line="";
				String editStrDetails="";
                          
				while( (line=br.readLine()) != null )
				{
									
					String saSearch[] = line.split("=");
					if(saSearch[0].equalsIgnoreCase(rStr))
					{
						editStrDetails = line;
                          continue;
					}
					 k++;					  
				}
					
					String [] sa = new String[k];
					int indx=0;
					fisOfCurrentDict = new FileReader(uf);
					
					br = new BufferedReader(fisOfCurrentDict);
					while( (line=br.readLine()) != null )
					{
						if(line.equals(editStrDetails))
							continue;
						sa[indx++]=line;
					}
				
				
					String editPath = userFile.getAbsolutePath();	
					//delete old file
					userFile.delete();					
							fosOfCurrentDict = new FileWriter(editPath);
																	
							if(fosOfCurrentDict != null)
							{
							    bw = new BufferedWriter(fosOfCurrentDict);
							
							    for(int t=0;t<sa.length;t++)
							    {
							    	bw.write(sa[t]);
							    	bw.newLine();
							    }														
							}
							bw.flush();
			}
		}	
	}
	
	//private eidtWord
	private static void editAWordDetails(File uf,String oldStr,String newStr) throws IOException
	{
	 boolean found =false;
		if(uf != null)
		{
			if(uf.length() != 0)
			{
				fisOfCurrentDict = new FileReader(uf);
				br = new BufferedReader(fisOfCurrentDict);
				int k=0;
				String line="";
				String editStrDetails="";
				while( (line=br.readLine()) != null )
				{
					 k++;				
					String saSearch[] = line.split("=");
					if(saSearch[0].equalsIgnoreCase(oldStr))
					{
						found =true;
						editStrDetails = line;
					}
										  
				}
	
					String [] sa = new String[k];
					int indx=0;
					fisOfCurrentDict = new FileReader(uf);
					
					br = new BufferedReader(fisOfCurrentDict);
					while( (line=br.readLine()) != null )
					{
						if(line.equals(editStrDetails))
							continue;
						sa[indx++]=line;
					}
					//let user enter complete new details for his word
					
					String editPath = userFile.getAbsolutePath();
					//delete old file
					userFile.delete();
					int chOfPOS=0;
					posList = new ArrayList<String>();
					meanList = new ArrayList<String>();
					exampList = new ArrayList<String>();
					while(chOfPOS != 9)
					{
						System.out.println("SELECT 1 PARTS OF SPEACH FROM BELOW TO ADD TO YOUR WORD  "+newStr+" ALONG WITH ATLEAST 1 MEANING & 1 EXAMPLE ");
						
						System.out.println("1  VERB");
						System.out.println("2  NOUN");
						System.out.println("3  PRONOUN");
						System.out.println("4  ADJECTIVE");
						System.out.println("5  CONJUCTION");
						System.out.println("6  PREPOSITION");
						System.out.println("7  POS-7");
						System.out.println("8  POS-8");
						System.out.println("9  RETURN TO PREVIOUS MENU");
						repeatEntryUntilItisInt();
						chOfPOS = sc1.nextInt();
						switch (chOfPOS)
						{
						case 1:posList.add("VERB");
						
							System.out.println("ADD VERBAL MEANING FOR YOUR WORD "+newStr);
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							 
							break;	
							
						case 2:
							posList.add("NOUN");
							
							System.out.println("ADD NOUN MEANING FOR YOUR WORD  "+newStr);
						
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
							
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							 
							break;
							
						case 3:
							posList.add("PRO-NOUN");
							
							System.out.println("ADD  PRO-NOUN MEANING FOR YOUR WORD  "+newStr);
							
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
					
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							 
							break;
							
						case 4:
			                 posList.add("ADJECTIVE");
							
							System.out.println("ADD ADJECTIVE MEANING FOR YOUR WORD "+newStr);
				
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
						
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							
							break;
							
						case 5:
			                posList.add("CONJUCTION");
							
							System.out.println("ADD CONJUNCTION MEANING FOR YOUR WORD "+newStr);
						
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
							
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							break;
							
						case 6:
			                 posList.add("PREPOSITION");
							
							System.out.println("ADD PREPOSITION MEANING FOR YOUR WORD "+newStr);
							
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
				
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							break;
							
						case 7:
			                posList.add("POS-7");
							
							System.out.println("ADD POS-7 MEANING FOR YOUR WORD "+newStr);
					
							meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
							
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							break;
							
						case 8:
			                 posList.add("POS-8");
							
							 System.out.println("ADD POS-8 MEANING FOR YOUR WORD  "+newStr);
					
						 	 meanStr = scLine.nextLine();				 
							 validateMeanAndexamp(meanStr);
							 meanList.add(meanStr);
							 
							 //example input
							 System.out.println("PLEASE ADD A EXAMPLE FOR "+meanStr+" MEANING OF YOUR WORD "+newStr);
						
							 exampStr = scLine.nextLine();				
							 validateMeanAndexamp(exampStr);
							 exampList.add(exampStr);
							break;
							
						case 9:
							System.out.println("RETURNING TO PREVIOUS MENU ? OKAY, THANK YOU.");
							fosOfCurrentDict = new FileWriter(editPath);
							StringBuilder oneWordSB = new StringBuilder(newStr.toUpperCase()+"=");
							int i=0;
						
							while( !posList.isEmpty() )
						      {								
								oneWordSB.append(posList.get(0)+">"+meanList.get(0)+"<"+exampList.get(0)+"#");
								posList.remove(0);
								meanList.remove(0);
								exampList.remove(0);
			
								i++;
						      }
							Date dateOfAddition = new Date();
							oneWordSB.append("%"+dateOfAddition);
										
							if(fosOfCurrentDict != null)
							{
							    bw = new BufferedWriter(fosOfCurrentDict);
							
							    for(int t=0;t<sa.length-1;t++)
							    {
							    	bw.write(sa[t]);
							    	bw.newLine();
							    }
								bw.write(oneWordSB.toString());
								bw.newLine();
							}
							bw.flush();
							//fosOfCurrentDict.flush();
							break;
							

						default:System.out.println("PLEASE ENTER NUMBERS ONLY WITHIN 1 to 9, TQ");
							break;
						}
					    //switch close
				
					}
					//while close
	
			}
		}	
	}
	//serach dictionary name from hdd
	private static boolean isDictExist(File f)
	{
		if(f !=null)
		{
			
			 if(f.exists())
			 {
				return true;
			 }
			 else
			 {
				return false;
			 }
		}
		else
			return false;
		
	}
	
	
	private static void validateLoading()
	{
		if(nameOfDictionary==null || nameOfDictionary.length()==0)
			throw new IllegalArgumentException("Hope U can enter name of ur Dictionary,which is not null/empty TQ");
		for(int i=0;i<nameOfDictionary.length();i++)
		{
			char ch = nameOfDictionary.charAt(i);
			if(  (ch>=65 && ch<=90)  || (ch>=97 && ch <= 122) || ch==32 || ch == 39)
			{
			}
				else
				throw new IllegalArgumentException("SORRY, WE DO NOT HAVE SUCH DICTIONARIES WHOSE NAME CONTAINS SPECIAL CHARACTERS / NUMBERS, PLEASE ENTER 1 NAME ONLY FROM DISPLAYED WINDOW, TQ");
			
		}
	}
	//MAIN
	public static void main(String[] args) {
		int ch=0;	

		while(ch != 4)
		{
			makeSectionByHorizontalline();
			System.out.println("1  I WANT TO CREATE A NEW DICTIONRY");
			System.out.println("2  I WANT TO LOAD MY DICTIONARY WHICH I/SOME-ONE-ELSE HAVE CREATED PREVIOUSLY");
			System.out.println("3  I WANT TO DELETE MY DICTIONARY");
			System.out.println("4. QUIT APP");
			
			System.out.println("ENTER YOUR CHOICE ");
		    repeatEntryUntilItisInt();
			ch=sc1.nextInt();
			//main menu
			switch (ch)
			{
			
			case 1:makeSectionByHorizontalline();
				   System.out.println("1");
			       System.out.println("ENTER NAME OF THE DICTIONARY");
			      try
			      {
			    	  nameOfDictionary=scLine.nextLine();
				       validate(nameOfDictionary);
				       userFile = new File(pathOfDictionaries+"//"+nameOfDictionary);
				       fosOfCurrentDict = new FileWriter(userFile,true);
				      
				       System.out.println("THANK YOU FOR CREATING YOUR DICTIONARY "+nameOfDictionary+" , NOW CHOOSE HOW DO YOU WANT TO USE IT ?");
				       subMenu(fosOfCurrentDict);
				     
						
			      }
			     catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
				}
				break;
			
			case 2:makeSectionByHorizontalline();
				try
				{
					if( isThereDictionaries()==true)
					{
						displayAllDictionaries();
						System.out.println("ENTER 1 NAME OF DICTIONARY AMONGST ABOVE SHOWN LIST OF DICTIONARIES TO LOAD IT FOR YOURSELF, TQ");
						nameOfDictionary=scLine.nextLine();
						validateLoading();
						userFile = new File(pathOfDictionaries+"//"+nameOfDictionary);
						if(isDictExist(userFile))
						{
							System.out.println();
							 System.out.println("CONGRATULATIONS.... , YOUR DICTIONARY "+nameOfDictionary+" IS BEEN LOADED & IT CONTAINS FOLLOWING WORDS");
							 fisOfCurrentDict = new FileReader(userFile);
							 displayContentsOfDict(fisOfCurrentDict,userFile);
							 
							    fosOfCurrentDict = new FileWriter(userFile,true);
							   
							 subMenu(fosOfCurrentDict);
						}
						else {
							System.out.println("SORRY, WE DO NOT HAVE DICTIONARY WHOSE NAME IS "+nameOfDictionary+" TO LOAD, TQ");
						}
						//String searchDictNameStr=scLine.nextLine();
						
						
						//subMenu();
					}
					else if( isThereDictionaries()==false)
					{
						System.out.println("SORRY, THERE ARE ZERO DICTIONARIES TO LOAD, TQ");
					}
				}
				catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			
				break;
			
			case 3:makeSectionByHorizontalline();
				
			       try
			       {
			    	   if( isThereDictionaries()==true)
						{
							displayAllDictionaries();
							System.out.println("ENTER 1 NAME OF DICTIONARY AMONGST ABOVE SHOWN LIST OF DICTIONARIES TO DELETE IT FOR EVER [CAUTION : IT CAN NOT BE UN-DONE], TQ");
							nameOfDictionary=scLine.nextLine();
							validateLoading();
							userFile = new File(pathOfDictionaries+"//"+nameOfDictionary);
							if(isDictExist(userFile))
							{
								int sureInt = 0;
								System.out.println("YES, WE HAVE YOUR DICTIONARY "+nameOfDictionary+". ARE YOU SURE YOU WANT TO DELETE IT PERMANENTLY ?");
								System.out.println("1  NO, DO NOT DELETE, IT WAS BY MISTAKE I CHOOSEN TO DELETE & RETURN TO MAIN MENU");
								System.out.println("2  YES, I AM SURE, PLEASE DELETE "+nameOfDictionary);
								
								repeatEntryUntilItisInt();
								
								sureInt = sc1.nextInt();
								if(sureInt == 2)
								{
									if(userFile.delete())
									{
									System.out.println("CONGRATULATIONS, YOUR DICTIONARY "+nameOfDictionary+" IS BEEN TRUNCATED FOR EVER, TQ");
									System.out.println("REST OF THE DICTIONARIES WITH US ARE AS FOLLOWS");
									displayAllDictionaries();
									}
									else
										System.out.println("SORRY, WE CAN NOT DELETE YOUR DICTIONARY BECAUSE OF SOME INTIGRITY ISSUES ,TQ");
								}
								else if( sureInt == 1)
								System.out.println("THANK YOU FOR NOT DELETING "+nameOfDictionary+", MAY GOD BLESS YOU");
								else
									throw new Exception("YOU HAVE CHOSEN "+sureInt+ " WHICH WAS NOT IN OPTIONS LIST, SO WE WILL NOT DELETE "+nameOfDictionary+", TQ");
							
							}
							else {
								System.out.println("SORRY, WE DO NOT HAVE YOUR DICTIONARY "+nameOfDictionary+" TO DELETE, TQ");
							}
							
						}
			    	   else
			    	   {
			    		   System.out.println("SORRY, WE DO NOT HAVE ANY DICTIONARIES TO DELETE FOR NOW, TQ");  
			    	   }
			       }
			       catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			
				break;
			
			case 4:
			       makeSectionByHorizontalline();
				   System.out.println("THANK YOU FOR USING OUR SERVICES");
			       System.out.println("HAVE A NICE DAY ................");
				break;
				

			default:System.out.println("PLEASE ENTER NUMBERS ONLY WITHIN 1 to 4, TQ");
				break;
			}
		}
		
		

	}

}
