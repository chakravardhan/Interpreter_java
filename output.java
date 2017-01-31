import java.util.* ;
import java.lang.IllegalArgumentException;
import java.lang.*;
public class output
{
	static  Stack<String> out_stk = new Stack<String>();
	public static  Double get_output() throws IllegalArgumentException
	{
	// try
	// {	
			for(String s: conversion.qu)
				{
					if(!operators.check_operator(s))
					{
	
						if(!Character.isDigit(s.charAt(0)))
						{
							throw new IllegalArgumentException("Illegal characetr");
						}
					}
				}			
			for(String str: conversion.qu)
			{
				if(!operators.check_operator(str))
				{
					out_stk.push(str);
				}
				else
				{
					Double result=0.0;
					Double x2=Double.valueOf(out_stk.pop());
					if(out_stk.empty())
					{
						if(str.equals("+"))
						{
							result=x2;
						}
						else if(str.equals("-"))
						{
							result = (-1 )* (x2);
						}
						else if(str.equals("*"))
						{
							result= x2;
						}
						else if(str.equals("/"))
						{
							result= x2;
						}		
					}
					else
					{
						Double x1=Double.valueOf(out_stk.pop());
						if(str.equals("+"))
						{
							result=x1+x2;
						}
						else if(str.equals("-"))
						{
							result= x1-x2;
						}
						else if(str.equals("*"))
						{
							result=x1*x2;
						}
						else if(str.equals("/"))
						{
							if(x2 != 0)
								result=x1/x2;
							else
							{
								throw new IllegalArgumentException("Sorry user you cannot divide with zero");
							}
						}
					}	
					out_stk.push(String.valueOf(result));
				}
			}
			conversion.qu.clear();
			return Double.valueOf(out_stk.pop());
		// }
		// catch(Exception e)
		// {
		// 	System.out.println("fuck u ");
		// 	return 0.0;
		// }
	 
	}
}