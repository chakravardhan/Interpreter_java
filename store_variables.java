import java.util.*;
public class store_variables
	{
	private ArrayList<variables> variablObjects= new ArrayList<variables>();
	public void addVariable(String name,Double value)
		{
		for(variables v:variablObjects)
			{
			if(v.returnVariablename()==name)
				{
				v.setValue(value);
				return;
				}
			}
		variables v = new variables();
		v.setName(name);
		v.setValue(value);
		variablObjects.add(v);
		}
	public Double retValue(String name) throws IllegalArgumentException,NullPointerException
		{
		try
{		for(variables v : variablObjects)
					{
					if(name.equals(v.returnVariablename()))
						return v.returnVariablevalue();
					}
				return null;
		}
		catch(Exception e)
		{		throw new IllegalArgumentException("Sorry user variable is not declared before");}
		
		}
	public void print()
		{
		for(variables v : variablObjects)
			{
			System.out.println(v.returnVariablename()+v.returnVariablevalue());
			}
		}
	}
