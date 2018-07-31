
/**
 * Driver for Polynomial class.
 * 
 * NOTA BENE: We had difficulty using the delimiters 
 * and the split() method for our String, so some inputs
 * may generate errors.
 *
 * @author Kaori Hayashi, Francesca Gazzolo
 * @version 4/11/2018
 */
package javafoundations;

import java.util.*;
import java.util.regex.Pattern;
public class PolynomialDriver
{
    /** 
     * Main method for testing.
     * 
     */
    public static void main (String[] args){
        //instance variable of the polynomial
        Polynomial poly1 = new Polynomial() ;
        Polynomial poly2 = new Polynomial();
        
        //divide input by + and get each term
        Scanner scan1 = new Scanner(System.in);//.useDelimiter("\\+|\\s"); 
        System.out.println("Enter a polynomial p1 (using only numbers, x, ^, and +):");
            
        while(scan1.hasNext()){
            String termString1 = scan1.next();
            System.out.println(termString1);
            Polynomial newPoly1 = readPolynomial(termString1);
           
        }
        
        scan1.close();
        System.out.println(poly1);
        
        Scanner scan2 = new Scanner(System.in);//.useDelimiter("\\+|\\s");
        System.out.println("Enter a polynomial p2 (using only numbers, x, ^, and +):");
        
        while(scan2.hasNext()){
            String termString2 = scan2.next();
            Polynomial newPoly2 = readPolynomial(termString2);
           
        }
        
        scan2.close();
        System.out.println(poly2);
        }
        
    /**
     * Helper method that reads a polynomial from a String input.
     * Separates polynomial input into Terms,then adds them to 
     * a new Polynomial.
     * 
     * @param termInput - String of polynomial
     * @return Polynomial - new polynomial based on input
     */
    public static Polynomial readPolynomial(String termInput)
    {
         //splits String into separate tokens
         //based on " + " pattern
        String[] splitString = termInput.split(Pattern.quote("\\+"));
            // for(int x = 0; x < splitString.length; x++)
            // {
            // System.out.println(splitString[x]);
            // }
        
            Term newTerm;
            Polynomial newPoly = new Polynomial();
            String co = "";
            String ex = "";
            int integer = 0;
            
            for(int i = 0; i<splitString.length; i++){
                // while the character is before x, add it to the coefficient variable
                while(integer < splitString[i].length() && splitString[i].charAt(integer) != 'x'){
                    co += splitString[i].charAt(integer);
                    integer++;
                }
                 
                // after it hits x, ignore x and ^ and add the numbers after them to the exponent variable
                for(int j = integer; j < splitString[i].length(); j++){
                    if(splitString[i].charAt(j) == 'x' || splitString[i].charAt(j) == '^'){
                           j++;
                    } else {
                        ex += splitString[i].charAt(j);
                        j++;
                    }
                }
                
                //convert the string variable into int
                int intCo = Integer.parseInt(co);
                int intEx = Integer.parseInt(ex);
            
            
                //create a term with the coefficient and exponent
                newTerm = new Term(intCo, intEx);
                
                newPoly.addTerm(newTerm);
            }
            
            return newPoly;
    }
}

