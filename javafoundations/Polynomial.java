/**
 * A polynomial is a group of terms stored in a Queue.
 * Terms in the queue are ordered by decreasing exponents.   
 *
 * @author Kaori Hayashi, Francesca Gazzolo
 * @version 4/11/2018
 */
package javafoundations;

import java.util.*;


public class Polynomial
{
    private LinkedQueue<Term> termQueue;
    
    /**
     * Creates a polynomial with no terms/
     */
    public Polynomial(){
        termQueue = new LinkedQueue<Term>();
    }
    
    /**
     * Gets the queue of terms.
     * 
     * @return LinkedQueue<Term> - termQueue instance variable
     */
    public LinkedQueue<Term> getQueue(){
        return termQueue;
    }
    
    /**
     * Returns a string representing a polynomial using " + " between terms.
     * Prints a message if queue is empty.
     * 
     * @return String - String representation of polynomial
     */
    public String toString(){
        if (termQueue.isEmpty()){
            return "The polynomial is empty.";
        }
        else{
            String result = "";
            LinkedQueue<Term> temp = new LinkedQueue<Term>();
            
            while(!termQueue.isEmpty()){
                Term t = termQueue.dequeue();
                //System.out.println("Current term: " + t);
                result += t + " + ";
                //System.out.println("current result:" + result);
                temp.enqueue(t);
            }
            
            while(!temp.isEmpty()){
                termQueue.enqueue(temp.dequeue());
            }
            
            //deletes last " + " from end of string
            return result.substring(0, result.length()-2);
        }
    }
    
    
    /**
     * Adds new term to polynomial queue while maintaining 
     * decreasing order of exponents.
     * 
     * @param next - term to be added
     */
    public void addTerm(Term next){
        Term nextValue = new Term(next.getCoefficient(), next.getExponent());
        LinkedQueue<Term> temp = new LinkedQueue<Term>();
        
        if(this.termQueue.isEmpty())
         {
             // if the termQueue is empty, automatically enqueues input to tempQueue
            temp.enqueue(nextValue);
            
        }
        else{
            
              if(this.termQueue.first().compareTo(nextValue) == 0){
                    addEqualTerm(nextValue, temp);
                    
                }
                else if(this.termQueue.first().compareTo(nextValue) < 0){
                    addGreaterTerm(nextValue, temp);
                }
                else
                {
                    // if the input term's exponent is smaller than the exponent o the 
                    // top term in termQueue, it enqueues the top term of termQueue to tempQueue
                    // until the input term's exponent is equal to or larger than the term
                    // at the front of term queue. When this happens, the input term is enqueued
                    // to tempQueue and the rest of the terms in termQueue is also enqueued to
                    // temp Queue
                    
                    while(!this.termQueue.isEmpty()){
                        
                        // enqueue the front term of termQueue while the exponent of the front 
                        // term is larger than the input term's coefficient
                       
                        
                        while (!this.termQueue.isEmpty() && (this.termQueue.first()).compareTo(nextValue) > 0){
                            temp.enqueue(this.termQueue.dequeue());
                        }
                        
                        
                        // enqueue the input term when the exponent is larger or equal to
                        // the front term of termQueue
                        if(this.termQueue.isEmpty()){
                             temp.enqueue(nextValue);
                        }else{
                            if(this.termQueue.first().compareTo(nextValue) == 0){
                                    addEqualTerm(nextValue, temp);
                        
                            }
                            else {
                                    addGreaterTerm(nextValue, temp);
                           
                        
                            }
                        }
                        }
                   
                    }
                }
        
                
        while(!temp.isEmpty())
        {
            termQueue.enqueue(temp.dequeue());
        }
    }
    
    /**
     * Helper method for addTerm.
     * Adds a term that is equal to the present term in the polynomial queue.
     * 
     * @param next - term to be added
     * @param LinkedQueue<Term> temp - temporary queue for storing terms
     */
    private void addEqualTerm(Term next, LinkedQueue<Term> temp)
    {
        Term nextValue = new Term(next.getCoefficient(), next.getExponent());
        
        // if the input term's exponent is equal to the term at the top of the
        // termQueue, it adds the terms and puts the added term to the temp Queue
        nextValue.add(this.termQueue.dequeue());
        temp.enqueue(nextValue);
        while(!this.termQueue.isEmpty()){
            temp.enqueue(this.termQueue.dequeue());
    
        }
                    
    }
    
    /**
     * Helper method for addTerm.
     * Adds a term that is greater than the present term in the polynomial queue.
     * 
     * @param next - term to be added
     * @param LinkedQueue<Term> temp - temporary queue for storing terms
     */
    private void addGreaterTerm(Term next, LinkedQueue<Term> temp)
    {
        Term nextValue = new Term(next.getCoefficient(), next.getExponent());
        
        // if the input term's exponent is larger than any exponents in 
        // termQueue, it puts the input term at the front of temp queue and 
        // enqueues all the terms in termQueue to temp queue
        
        temp.enqueue(nextValue);
        while(!this.termQueue.isEmpty()){
            temp.enqueue(this.termQueue.dequeue());
    
        }
    }

    /**
     * Adds two polynomials and returns a new polynomial
     * that is the sum.
     * 
     * @param p - polynomial to be added to this polynomial
     * @return Polynomial - sum of both polynomials
     */
    public Polynomial addPolynomial(Polynomial p){
        Polynomial newP = new Polynomial();
        
        while(!p.getQueue().isEmpty()){
            newP.addTerm(p.getQueue().dequeue());
        }
        
        while(!this.getQueue().isEmpty())
        {
            newP.addTerm(this.getQueue().dequeue());
        }
        
        return newP;
    }
    
    /**
     * Main method for testing.
     */
    public static void main (String[] args){
        Polynomial poly1 = new Polynomial();
        
        
        Term term1 = new Term(3,2);
        Term term2 = new Term(4,2);
        Term term3 = new Term(2,1);
        Term term7 = new Term(2,5);
        Term term4 = new Term(0,3);
        Term term5 = new Term(4,0);
        Term term6 = new Term(1,1);
        
        poly1.addTerm(term1);
        System.out.println("Testing addTerm. Expected 3x^2. Got: "+poly1);
        poly1.addTerm(term2);
        System.out.println("Testing addTerm. Expected 7x^2. Got: "+poly1);
        poly1.addTerm(term3);
        System.out.println("Testing addTerm. Expected 7x^2 + 2x. Got: "+poly1);
        poly1.addTerm(term7);
        System.out.println("Testing addTerm. Expected 2x^5 + 7x^2 + 2x. Got: "+poly1);
        
        Polynomial poly2 = new Polynomial();
        
        System.out.println("Expected: is empty. Got: " + poly2);
        poly2.addTerm(term4);
        System.out.println("Testing addTerm. Expected 0. Got: "+poly2);
        poly2.addTerm(term5);
        System.out.println("Testing addTerm. Expected 4. Got: "+poly2);
        poly2.addTerm(term6);
        System.out.println("Testing addTerm. Expected x + 4. Got: "+poly2);
        
        System.out.println("Testing addTerm. Expected 2x^5 + 7x^2 + 2x. Got: "+poly1);
        System.out.println("Testing addTerm. Expected x + 4. Got: "+poly2);
        
        Polynomial poly3 = poly1.addPolynomial(poly2);
        System.out.println("Testing addPolynomial(). Expected: 2x^5 + 7x^2 + 3x + 4. Got: " + poly3);
        
    
    }
}
