//Kevin Cook
//5/19/19
//Software Engineering I
//Program to search a text file and find the # of occurrences per word.

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




public class Start {
	
	/**
	 * Date: July 14th-2019
	 * <h1>Word Occurrence App</h1>
	 * This application searches through a text file to determine the amount of times each word appears.
	  		 *  This class is the main class that searches through the text file. It creates a hash-map for each string and each time the 
			 *  program detects this string, it is added to the integer. The main while loop looks through each line and places letters into an array
			 *  to denote a word. it separates each word and closes the array if a special character or space is after the final character in the array. 
			 *  Next, the word is turned into lower case and entered into the hash map.
			 *  There, it is entered into the map and given an integer value which accumulates as each successive word is added. 
	 * @author Kevin Cook
	 * @version 1.1
	 * @param mainString String passed from the main window
	 * @return the text needing to be shown in the main window
	 * @throws FileNotFoundException Exception to check for file
	 */
	public String beginSearch(String mainString) throws FileNotFoundException 
	{	
		
		String test = mainString;
		// Creating the file stream and buffered reader objects
		FileInputStream fstream = new FileInputStream("src\\play.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		try 
		{
			//creating instance of a new has map to store values and # of occurrences
			HashMap<String, Integer> myMap = new HashMap<String, Integer>();
			String strPlayLine;
			String strCompareArray = null;
			StringBuilder plus;
			Boolean duplicateWord = false;
			
			// while each line is read in the text file, the following code executes
			while ((strPlayLine = br.readLine()) != null)
			{				
				duplicateWord = false;
				// an array is created to hold each word on the line, separated by characters
				String[] wordsArray = strPlayLine.split("[\"!&()-?,.;:\\s+]");
				
				//loop for each word in the array
				for(int i =0; i < wordsArray.length; i++)
				{					
					//assigns the current index of the array to a string, forcing it to be lower case
					strCompareArray = wordsArray[i].toLowerCase();
					if(strCompareArray.equals(""))
						//eliminating spaces as words
					{continue; }
					
					//a for loop that searches through each of the current sets in the hash map
					//to check to see if the current selected word is already apart of the map
					for(HashMap.Entry<String, Integer> entry : myMap.entrySet())
					{
						//assigning strings to what is currently in the set
						String key = entry.getKey();
						Integer value = entry.getValue();
						
						//if the current word was the same as the key currently selected from the map, it will
						// increase the value of that set by one
						if (strCompareArray.equals(key))
						{
							value = myMap.get(key) + 1;
							myMap.put(strCompareArray, value);
							duplicateWord = true;
							break;							
						}
					}

				if(duplicateWord == false)
				{
					//if the previous if statement did not occur and duplicateWord is still false
					//a new entry is added to the map with a value of one
					myMap.put(strCompareArray.toLowerCase(), 1);
				}

				}
				
			}
			
		
			//this will print out each set of the hash map going in descending order,
			//with the most occurrences at the top
			for(HashMap.Entry<String, Integer> entry : myMap.entrySet())
			{
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 PrintStream ps = new PrintStream(baos);
			 PrintStream old = System.out;
			 System.setOut(ps);
			 myMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
			 System.out.flush();
			 System.setOut(old);
			 test = baos.toString();
			 
			 
			}


		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;

		
		

	}

}
