package person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  
 *  @author Nigel
 *  @version 1.1
 */
public class Person4 {
  /** Holds the persons real name */
  private String name;
    /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
  public Person4(String pname) {
    name = pname;
  }
    /**
     * This method should return a string
     * where each character is 1 greater 
     * than its previous value.  So
     * given "abc123" it should return
     * "bcd234".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    private String calc(String input) {
      char[] inputArray = input.toCharArray();
      for (int ndx = 0; ndx < input.length(); ndx++){
    	  inputArray[ndx] = ++inputArray[ndx];
      }
      return new String(inputArray);
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
    
    public static void main(String[] args){
    	Person4 per = new Person4("Nigel");
    	System.out.println(per.toString("Nigel"));
    	
    }

}

