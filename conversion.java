import java.util.* ;
import java.lang.NullPointerException;
public class conversion
	{
		static int if_new=0;
		static String new_name="";
		private static store_variables vs = new store_variables();
		static ArrayList<String> qu = new ArrayList<String>();
		static Stack<String> st = new Stack<String>();
		public static boolean isoperator(char op)
		{
		if(op=='*' || op=='/' || op=='+' || op=='-')
			return true;
		return false;
		}
		public static String remove_whitespaces(String str)
		{
			String without_spaces="";
			for(int i=0;i<str.length();i++)
				{
					if(str.charAt(i) != ' ')
					{
						without_spaces=without_spaces+String.valueOf(str.charAt(i));
					}
				}
			return without_spaces;	
		}
		public static  ArrayList<String> list_strings(String without_spaces)
		{
				String temp="";
				char prev=' ';
				ArrayList<String> input=new ArrayList<String>();
				for(int i=0; i <= without_spaces.length();i++)
				{

					if(i != 0)
					{
						prev=without_spaces.charAt(i-1);
					}
					if(i == without_spaces.length())
					{
						if(temp != "")
							input.add(temp);	
					}
					else if(!isoperator(without_spaces.charAt(i)) && without_spaces.charAt(i) != '(' 
						  	&& without_spaces.charAt(i) != ')')
					{
						if(without_spaces.charAt(i) != '=')
						{
							temp=temp+String.valueOf(without_spaces.charAt(i));
						}
						else 
						{
							input.add(temp);
							input.add(String.valueOf(without_spaces.charAt(i)));
							temp="";
						}
					}
					else
					{	
						if((prev == '*' || prev == '(' || prev == '/' || prev == '-' || prev == '+' || prev == '=')
							&& (without_spaces.charAt(i) == '+'
									 || without_spaces.charAt(i) == '-'))
						{
							temp=temp+String.valueOf(without_spaces.charAt(i));
						}
						else if(temp != "")
						{
							input.add(temp);
							input.add(String.valueOf(without_spaces.charAt(i)));
							temp="";
						}
						else if(temp == "")
						{
							input.add(String.valueOf(without_spaces.charAt(i)));
							temp="";	
						}	
						else
						{
							throw new IllegalArgumentException("Sorry user its  invalid ");
						}
					}
					if(i < without_spaces.length())
						prev=without_spaces.charAt(i);
				}
				return input;
		}
		public static void main(String args[]) throws IllegalArgumentException,NullPointerException
		{
			try
			{
				while(true)
						{
							ArrayList<String> input=new ArrayList<String>();
							String without_spaces="";
							Scanner a = new Scanner(System.in);
							System.out.print("->->->" + " ");
							String str = a.nextLine();
							without_spaces = remove_whitespaces(str);
							input=list_strings(without_spaces);
							Double result = convert(input);
							conversion.if_new=0;
							conversion.new_name="";
						}
			}
			catch(NullPointerException e)
			{
				System.out.println("Its an undeclared variable");
			}
			// catch(Exception e)
			// {
			// 	System.out.println("Its an illegal operator");
			// }
			catch(IllegalArgumentException e)
			{
				System.out.println("sorry its an invalid expression");
			}
		}
		public static String seq_operators(String str)
		{
			String tmp="",temp="";
			for(int i=0;i<str.length();i++)
			{
				if(str.charAt(i) == '+' || str.charAt(i) == '-')
				{
					if(str.charAt(i) == '+')
						temp= "+";
					else
						temp= "-";
				}
				else
				{
					if(tmp == "")
					{
						tmp=temp+String.valueOf(str.charAt(i));
					}
					else
					{
						tmp=tmp+String.valueOf(str.charAt(i));
					}
				}
			}
			return tmp;	
		}
		public static void if_operator(String current_str)
		{
			while(!st.empty() && operators.check_operator(st.peek()))
			{
				if(operators.property(current_str) && operators.preference(current_str,st.peek()) <= 0 )
				{
					qu.add(st.pop());
					continue;
				}
				else if(operators.property(current_str) && operators.preference(current_str,st.peek()) < 0)
				{

					qu.add(st.pop());
					continue;
				}
					break;
			}
			st.push(current_str);
		}
		public static Double convert(ArrayList<String> input) throws IllegalArgumentException,NullPointerException 
		{
			int newvar=0,varfound=0;
			String varname="";
			if(input.size() >2 && input.get(1).equals("="))
			{
				varname=input.get(0);
				int i=0;
				for(String inp : input)
				{
					if(i>1)
					{
						input.set(i-2,input.get(i));
					}
					i++;
				}
				input.remove(input.size()-1);
				input.remove(input.size()-1);
				newvar=1;
				conversion.new_name=varname;
				conversion.if_new=1;
			}
			int i=0;
			if(newvar==0)
			{
				for(String inp : input)
				{
					String temp="";
					String foundname="";
					varfound=0;
					for(int j=0;j<inp.length();j++)
					{
						if((inp.charAt(j)>='a' && inp.charAt(j)<='z') || (inp.charAt(j)>='A' && inp.charAt(j)<='Z'))
							varfound=1;
					}
					if(varfound==1)
					{
						int tempvarfound=0;
						for(int j=0;j<inp.length();j++)
						{	
							if(isoperator(inp.charAt(j)) || inp.charAt(j)==')')
							{
								for(int k=0;k<foundname.length();k++)
								{
									if((foundname.charAt(k)>='a' && foundname.charAt(k)<='z' )|| (foundname.charAt(k)>='A' && foundname.charAt(k)<='Z'))
										tempvarfound=1;
								}
								if(tempvarfound==1)
								{
									temp+=Double.toString(vs.retValue(foundname));
									foundname="";
									tempvarfound=0;
								}
								else
								{
									temp+=foundname;
									foundname="";
									tempvarfound=0;
								}
								temp+=inp.charAt(j);
							}
							else if(inp.charAt(j) != '(')
							{
								foundname+=inp.charAt(j);
							}
							else if(inp.charAt(j) == '(')
							{
								temp+="(";
							}
						}
					for(int k=0;k<foundname.length();k++)
					{
						if((foundname.charAt(k)>='a' && foundname.charAt(k)<='z' )|| (foundname.charAt(k)>='A' && foundname.charAt(k)<='Z'))
							tempvarfound=1;
					}
					if(tempvarfound==1)
					{
						temp+=Double.toString(vs.retValue(foundname));
						foundname="";
						tempvarfound=0;
					}
					else
					{
						temp+=foundname;
						foundname="";
						tempvarfound=0;
					}
					input.set(i,temp);
					}
				i++;
				}
			}
		i=0;
			for(String current_str:input)
			{
				if(operators.check_operator(current_str))
				{
					if_operator(current_str);
				}
				else if(current_str.equals("("))
				{
					st.push(current_str);
				}
				else if(current_str.equals(")"))
				{
					while(!st.empty() && !(st.peek().equals("(")))
					{
						qu.add(st.pop());
					}
					st.pop();
				}
				else
				{
					if(current_str.charAt(0) == '+' || current_str.charAt(0) == '-' )
					{
						if(current_str.charAt(1) == '+' || current_str.charAt(1) == '-')
						{
							String temp=seq_operators(current_str);
							qu.add(temp);
						}
						else
						{
							qu.add(current_str);		
						}
					}
					else
					{
						qu.add(current_str);	
					}
				}
			}
			while(!st.empty())
			{
				qu.add(st.pop());
			}	
			if(newvar==0)
			{
			
				if(conversion.if_new == 1)
				{
					return output.get_output();
				} 
				else
				{
					Double result=output.get_output();
					System.out.println(result);
					return result;
				}
			}
			else
			{
					qu.clear();
					Double result=convert(input);
					vs.addVariable(new_name,result);
			}
		return  0.0;
		}
	}
