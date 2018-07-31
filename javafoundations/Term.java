/**
 * Creates a term of a coefficient, x, and exponent.
 * Example: 3x^2 
 * 
 * @author Kaori Hayashi, Francesca Gazzolo
 * @version 4/11/2018
 */
package javafoundations;

public class Term implements Comparable<Term>
{
    private int coefficient;
    private int exponent;

    /**
     * Creates a Term instance.
     * 
     * @param c - coefficient of term
     * @param e - exponent of term
     */
    public Term(int c, int e){
        coefficient = c;
        exponent = e;
    }

    /**
     * Returns a string representation of a term.
     * Special cases for coefficients and exponents of 1 and 0. 
     * Examples:
     *      0x^2 returns 0
     *      2x^0 returns 2
     *      3x^1 returns 3x
     *      1x^3 returns x^3
     *      2x^3 returns 2x^3
     *      
     * @return String - String representation of term
     */
    public String toString(){
        String result = "";
        
        //if coefficient is 0, term is "0" regardless of exponent
        if (this.coefficient == 0){
            result += "0";
        } 
        //x^0 is 1, so term is 1*coefficient, or coefficient
        else if (this.exponent == 0){
            result += coefficient;
        } 
        else if (this.coefficient == 1){
            //if coefficient is 1 but exponent is > 1, term is x^y
            if (this.exponent != 1){
                result += "x^"+exponent;
            } 
            //if coefficient is 1 and exponent is 1, term is "x" 
            else{
                result += "x";
            }
            
        } 
        //if exponent is 1 but coefficient > 1, term is Cx
        else if (this.exponent == 1){
            result += coefficient + "x";
        }
        //otherwise, term is Cx^y
        else{   
        result += coefficient + "x^" + exponent;
       }
        
        return result;
    }
    
    /**
     * Gets coefficient of term.
     * 
     * @return int - coefficient instance variable
     */
    public int getCoefficient(){
        return coefficient;
    }
    
    /**
     * Gets exponent of term.
     * 
     * @return int - exponent instance variable
     */
    public int getExponent(){
        return exponent;
    }
    
    
    /**
     * Sets coefficient of term to desired input.
     * Private method to avoid mutation from outside users.
     * 
     * @param newC - new coefficient
     */
    private void setCoefficient(int newC){
        coefficient = newC;
    }
    
    /**
     * Sets exponent of term to desired input.
     * Private method to avoid mutation from outside users.
     * 
     * @param newE - new exponent
     */
    private void setExponent(int newE){
        exponent = newE;
    }
    
    /**
     * Compares this term to another term based on exponent.
     * 
     * @param other - term to compare this term to
     * @return int - 0 if exponents are equal
     *               negative if this exponent < other exponent
     *               positive if this exponent > other exponent
     */
    public int compareTo(Term other){
        return this.exponent - other.getExponent();
    }
    
    /**
     * Adds terms together.
     * If terms do not have the same exponent, a warning message appears.
     * 
     * @param other - term to add this term to
     */
    public void add(Term other)
    {
        
        if(this.compareTo(other) == 0){
            this.coefficient = this.coefficient + other.getCoefficient();
        }
        else {
            System.out.println("Cannot add terms with different exponents.");
        }
    }
    
    /**
     * Main method for testing.
     */
    public static void main (String [] args){
        Term term1 = new Term(3,2);
        Term term2 = new Term(4,2);
        Term term3 = new Term(2,1);
        Term term4 = new Term(0,3);
        Term term5 = new Term(4,0);
        Term term6 = new Term(1,1);
        
        System.out.println("Testing toString(). Expected: 3x^2. Got:" + term1);
        System.out.println("Testing toString(). Expected: 0. Got:" + term4);
        System.out.println("Testing toString(). Expected: 4. Got:" + term5);
        
        System.out.println("Testing getCoefficient(). Expected: 3. Got: " + term1.getCoefficient());
        System.out.println("Testing getExponent(). Expected: 2. Got: " + term2.getExponent());
        
        System.out.println("Original term6: " + term6);
        term6.setExponent(3);
        term6.setCoefficient(4);
        System.out.println("Testing setCoefficient and setExponent. Expected: 4x^3. Got: " + term6);
        
        System.out.println("Testing compareTo(). Expected: 0. Got: " + term1.compareTo(term2));
        System.out.println("Testing compareTo(). Expected: 1. Got: " + term1.compareTo(term3));
        System.out.println("Testing compareTo(). Expected: -1. Got: " + term1.compareTo(term4));
        
        term1.add(term2);
        System.out.println("Testing add(). Expected: 7x^2. Got: " + term1);
        
        System.out.println("Testing add(). Expected: Cannot add terms. Got: " ); 
        term5.add(term4);
        System.out.println("Expected: 4. Got: " + term5);
        
        
        
        
    }
    
}
