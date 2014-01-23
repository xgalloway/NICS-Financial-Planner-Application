package person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  @author Bob 
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person1(String pname) {
    name = pname;
    
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  String res = input; // set resulting string to the input to allow for manipulation
	  int current = 0; // set a pointer to the 0th index of the string
	  String temp = ""; // create a temporary string
	  while(current < input.length()){ // as long as the pointer doesn't point beyond the string
		  temp += input.charAt(current); // add the character at the pointer's position to the temporary string
		  current++; // move the pointer to the next position
		  res = input.substring(current); // get rid of the character at the zeroth index
		  if(current == 2){ // when pointer exceeds the second position break out of the loop
			  break;
		  }
	  }
	  res += temp; // concatenate the resulting string the temporary string of length 2
	  return res;
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
