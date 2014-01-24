package person;

import java.util.ArrayList;
import java.util.Random;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Brent Satterwhite
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		ArrayList letters = new ArrayList();
	  for (int i = 0; i < input.length(); ++i) {
		  letters.add(input.charAt(i));
	  }
	  
	  String output = "";
	  Random rand = new Random();
	  while (letters.size() > 0) {
		  int index = rand.nextInt(letters.size());
		  output = output + letters.remove(index);
	  }
	  return output;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

}
