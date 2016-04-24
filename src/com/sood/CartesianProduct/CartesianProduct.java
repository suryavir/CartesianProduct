package com.sood.CartesianProduct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
Braces tell the bash shell to generate what's called the "Cartesian product" of the given set, where elements of the set are comma-separated. 
The problem at hand is to implement this feature of bash, i.e. produce the same output as bash does for a given input string.

For example:
$ echo a{b,c}d{e,f,g}hi
abdehi abdfhi abdghi acdehi acdfhi acdghi
$ echo a{b,c{d,e,f}g,h}ij{k,l}
abijk abijl acdgijk acdgijl acegijk acegijl acfgijk acfgijl ahijk ahijl

In your response, please send:
(1) Your solution, written in Java, Scala, Python, Ruby, Go, or JavaScript
(2) Instructions on how to run the code
(3) Any unit tests that you wrote to demonstrate the correctness of your solution

Created by Suryavir Sood
*/

public class CartesianProduct {
	
	//Instance Variables
	static ArrayList<ArrayList<Character>> characters;
	static ArrayList<Character> placeholder;
	static ArrayList<ArrayList<Character>> CartesianProductTemp;
	static ArrayList<ArrayList<Character>> CartesianProductTemp1;
	static HashSet<ArrayList<Character>> CartesianProduct;
	static ArrayList<String> CartesianProductStrings=new ArrayList<String>();
	static ArrayList<Character> braces=new ArrayList<Character>();

//Function to Add Braces to ArrayList, and check if nested or simple expression
	public static boolean ComputeBraces(ArrayList<Character> chararraynew)
	{
		for(char ch:chararraynew)
		{
			if(ch=='{')
			{
				braces.add('{');
			}
			
			if(ch=='}')
			{
				braces.add('}');
			}
		}
		
		
		for(int i=0;i<braces.size()-1;i++)
		{
			
				if(braces.get(i)==braces.get(i+1))
				{
				return false;
				}
			
				else
				{
				i=i+2;
				}
			
				
				
		}
		
		return true;
	}
	
	
	
	//Function to create the placeholder array and store the values inside brackets inside ArrayList
	public static void CreateArraySimple(ArrayList<Character> chararraynew)
	{
		
		
		for(int i=0;i<chararraynew.size();i++)
		{

			if(chararraynew.get(i)=='{')
			{
				i++;
				ArrayList<Character> arraylist=new ArrayList<Character>();
				
				while(chararraynew.get(i)!='}')
				{
					if(chararraynew.get(i)==',')
						i++;
					
					arraylist.add(chararraynew.get(i));
					
					i++;	
				}
				
				placeholder.add('0');
				characters.add(arraylist);
				
			}
			
			else
			{
				if(chararraynew.get(i)!=',')
				placeholder.add(chararraynew.get(i));
			}
			
		}
		Collections.reverse(characters);
	}
	
	//Function to create the placeholder array and store the values inside brackets inside ArrayList
	public static ArrayList<ArrayList<Character>> CreateArrayNested(ArrayList<Character> chararraynew)
	{
		
		for(int i=0;i<chararraynew.size();i++)
		{
			//Index to see the braces index
			int j=0;
			
			//Check when character is {
			if(chararraynew.get(i)=='{')
			{
				i++;
				j++;
				
				ArrayList<Character> arraylist=new ArrayList<Character>();
				
				System.out.println("Braces are"+ braces.get(j)+ "Character is"+chararraynew.get(i));
				//Check if another { is inside a {
				if(braces.get(j)=='{' && chararraynew.get(i)=='{')
				{
					
					ArrayList<Character> chararraynew1=new ArrayList<Character>();
					
					if(chararraynew.get(i-1)!=',')
					chararraynew1.add(chararraynew.get(i-1));
					
					while(chararraynew.get(i)!='}')
					{
						if(chararraynew1.get(i)!=',')
						chararraynew1.add(chararraynew.get(i));
						
						i++;
					}
					
					i++;
					
					while(chararraynew.get(i)!=',')
						chararraynew1.add(chararraynew.get(i++));
					
					CreateArrayNested(chararraynew1);
					System.out.println("characterarraynew1 are"+chararraynew1);
				}
				
				else
				{	
					while(chararraynew.get(i)!='}')
					{
						if(chararraynew.get(i)==',')
						i++;
					
					arraylist.add(chararraynew.get(i));
					
					i++;	
					}
				
				placeholder.add('0');
				characters.add(arraylist);
				}
				
			}
			//End of main if checking {
			
			//Keep adding the alphabets to placeholder ArrayList
			else
			{
				placeholder.add(chararraynew.get(i));
			}	
			
		}// End of for loop of chararraynew list
		
		System.out.println("characters are"+characters);
		Collections.reverse(characters);
		return characters;
	}
	
	public static void CartesianProductFxn()
	{
		int noofzeros=characters.size();
		ArrayList<Integer> indices=new ArrayList<Integer>();
		
		for(int i=0;i<placeholder.size();i++)
		{
			if(placeholder.get(i)=='0')
			{
				indices.add(i);
			}
		}
		
		Collections.reverse(indices);
		
		for(int i=0;i<noofzeros;i++)
		{
			CalculateCP(indices.get(i),characters.get(i));
		}
			
	}
	
	public static void CalculateCP(int index, ArrayList<Character> charsinbraces)
	{
		
		if(CartesianProductTemp.isEmpty())
		{
			
			for(Character ch: charsinbraces)
			{
				ArrayList<Character> placeholdernew=new ArrayList<Character>();
				
				placeholdernew=(ArrayList<Character>) placeholder.clone();
				placeholdernew.set(index, ch);
				
			   CartesianProductTemp.add(placeholdernew);
				
			}
			
			
				
		}
		
		else
		{
			
				for(ArrayList<Character> arraylistch: CartesianProductTemp)
				{
					
					for(Character ch: charsinbraces)
					{
						ArrayList<Character> temp=new ArrayList<Character>();
				
						temp=(ArrayList<Character>) arraylistch.clone();
				
						temp.set(index, ch);
				
						CartesianProductTemp1.add(temp);		
				   }
				
				CartesianProductTemp=(ArrayList<ArrayList<Character>>) CartesianProductTemp1.clone();
			}
			
		}
		
//For edge case when there are only elements in format of {}
		if(CartesianProductTemp1.isEmpty())
		{
			for(ArrayList<Character> arraylist:CartesianProductTemp)
			{
				if(!arraylist.contains('0'))
				{
					CartesianProduct.add(arraylist);
				}
			}
		}
		
//For all other cases	
		for(ArrayList<Character> arraylist:CartesianProductTemp1)
		{
			if(!arraylist.contains('0'))
			{
				CartesianProduct.add(arraylist);
			}
		}
			
		
	}
	
//Function to convert the ArrayList of chars to String
	public static String convertToString(ArrayList<Character> chrs)
	{
		
		StringBuilder st=new StringBuilder();
		
		for(Character ch:chrs)
		{
			st.append(ch);
		}
		
		return st.toString();
	}

//Function for Unit Testing
	public static void Test()
	{
		
		System.out.println("SOLVING NON-NESTED SETS");
		
		//First Test
        String test1_input = "a{b,c}d{e,f,g}hi";
       
        //Added in ArrayList so that while comparing sequence of the result is inconsequential
        ArrayList<String> test1_expectedresult=new ArrayList<String>();
        test1_expectedresult.add("abdehi");
        test1_expectedresult.add("abdfhi");
        test1_expectedresult.add("abdghi");
        test1_expectedresult.add("acdehi");
        test1_expectedresult.add("acdfhi");
        test1_expectedresult.add("acdghi");
        ArrayList<String >test1_actualresult=MainMethod(test1_input);
        boolean test1_result = compare(test1_actualresult,test1_expectedresult);
        
        System.out.println("the input is"+ test1_input);
        System.out.println("\nExpected Result"+ test1_expectedresult);
        System.out.println("\nActual Result"+ test1_actualresult);
        System.out.println("\ntest1 output: " + test1_result);   
        
        //Second Test
        String test2_input = "a{b,c}d{e,f,g}hi{j,k}";
        ArrayList<String> test2_expectedresult=new ArrayList<String>();
        test2_expectedresult.add("abdehij");
        test2_expectedresult.add("abdehik");
        test2_expectedresult.add("abdfhij");
        test2_expectedresult.add("abdfhik");
        test2_expectedresult.add("abdghij");
        test2_expectedresult.add("abdghik");
        test2_expectedresult.add("acdehij");
        test2_expectedresult.add("acdehik");
        test2_expectedresult.add("acdfhij");
        test2_expectedresult.add("acdfhik");
        test2_expectedresult.add("acdghij");
        test2_expectedresult.add("acdghik");
        ArrayList<String> test2_actualresult=MainMethod(test2_input);
        boolean test2_result = compare(test2_actualresult,test2_expectedresult);
        
        System.out.println("the input is"+ test2_input);
        System.out.println("\nExpected Result"+ test2_expectedresult);
        System.out.println("\nActual Result"+ test2_actualresult);
        System.out.println("test2: " + test2_result);   
        
        
       // Third Test 
        String test3_input = "a{b}d{e,f,g}hi{j}";
        ArrayList<String> test3_expectedresult=new ArrayList<String>();
        test3_expectedresult.add("abdehij");
        test3_expectedresult.add("abdfhij");
        test3_expectedresult.add("abdghij");
        ArrayList<String >test3_actualresult=MainMethod(test3_input);
        boolean test3_result = compare(test3_actualresult,test3_expectedresult);
        System.out.println("test3: " + test3_result);   
        
        System.out.println("the input is"+ test3_input);
        System.out.println("\nExpected Result"+ test3_expectedresult);
        System.out.println("\nActual Result"+ test3_actualresult);
        System.out.println("test3: " + test3_result);   
        
        //Fourth Test- Wrong expectedresult added
        String test4_input = "{z,y}";
        ArrayList<String> test4_expectedresult=new ArrayList<String>();
        test4_expectedresult.add("z");
        test4_expectedresult.add("a");
        ArrayList<String >test4_actualresult=MainMethod(test4_input);
        boolean test4_result = compare(test4_actualresult,test4_expectedresult);
        
        System.out.println("the input is"+ test4_input);
        System.out.println("\nExpected Result"+ test4_expectedresult);
        System.out.println("\nActual Result"+ test4_actualresult);
        System.out.println("test3: " + test4_result);
        
    	
		System.out.println("\n SOLVING NESTED SETS");
		String test5_input = "a{b,c{d,e,f}g,h}ij{k,l}i";
	    ArrayList<String> test5_expectedresult=new ArrayList<String>();
	    test5_expectedresult.add("abdehi");
	    test5_expectedresult.add("abdfhi");
	    test5_expectedresult.add("abdghi");
	    test5_expectedresult.add("acdehi");
	    test5_expectedresult.add("acdfhi");
	    test5_expectedresult.add("acdghi");
	    ArrayList<String >test5_actualresult=MainMethod(test1_input);
	    boolean test5_result = compare(test5_actualresult,test5_expectedresult);
	    
	    System.out.println("the input is"+ test5_input);
        System.out.println("\nExpected Result"+ test5_expectedresult);
        System.out.println("\nActual Result"+ test5_actualresult);
        System.out.println("test3: " + test5_result);
        
	}
	
	//Function to compare Actual Result my program received and the Expected correct result
	public static boolean compare(ArrayList<String> actualresult, ArrayList<String> expectedresult)
	{
		
		//Check if size is different, if it is then return false (not the same output)
		if(actualresult.size()!=expectedresult.size())
			return false;
		
		for(String actualresultstring: actualresult)
		{   Boolean found=false;
			
			for(String expectedresultstring: expectedresult)
			{
				if(expectedresultstring.equals(actualresultstring))
				found=true;
					
			}
		
			if(found==false)
				return false;
		
		}

		
		return true;
	}
	
	
//Function to be called everytime a new expression is entered	
	public static ArrayList<String> MainMethod(String expression)
	{
		char[] chararray=expression.toCharArray();
		ArrayList<Character> chararraynew=new ArrayList<Character>();
		
		characters=new ArrayList<ArrayList<Character>>();
		placeholder=new ArrayList<Character>();
		CartesianProductTemp=new ArrayList<ArrayList<Character>>();
		CartesianProductTemp1=new ArrayList<ArrayList<Character>>();
		CartesianProduct=new HashSet<ArrayList<Character>>();
		CartesianProductStrings=new ArrayList<String>();
		
		for(char ch: chararray)
		{
			chararraynew.add(ch);
		}
		
		
		if(ComputeBraces(chararraynew))
		{
			CreateArraySimple(chararraynew);
		}
		
		else
		{
			CreateArrayNested(chararraynew);
			
		}
		
		
		CartesianProductFxn();

		/*System.out.println("boolean value is "+ComputeBraces(chararraynew));
		
		System.out.println("Characters is "+ characters);
		System.out.println("String placeholder is "+placeholder);
		
		System.out.println("Cartesian Pr temp size is "+ CartesianProduct.size());
		System.out.println("Cartesian Pr  is "+ CartesianProduct);
		*/
		
		for(ArrayList<Character> ch:CartesianProduct)
		{
			CartesianProductStrings.add(convertToString(ch));
		}
		
		Collections.sort(CartesianProductStrings);
		//System.out.println("Cartesian pr string is "+CartesianProductStrings);
		
		return CartesianProductStrings;
		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String expression="a{b,c}d{e,f,g}hi{j,k}";
		//System.out.println(MainMethod(expression));
		//Test();
		
		
		//Created Scanner Object to read from System.in
		Scanner reader = new Scanner(System.in); 
		System.out.println("Options to Choose from: \n 1. Test Cases \n 2. Enter expression yourself");
		int options= reader.nextInt();
		
		switch(options)
		{
		case 1: 
				//If 1 then run all test cases
				Test();
				break;
			
		case 2:
				//If 2 then take input from User
				System.out.println("Enter the Expression: ");
				Scanner readernew = new Scanner(System.in);
				String expressionnew= readernew.nextLine();
				System.out.println(MainMethod(expressionnew));
				break;
			
		default:
			System.out.println("Please enter available options- 1 or 2");
		}
		
		reader.close();
	
		
		
		
		

	}

}
