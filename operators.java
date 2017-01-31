import java.util.* ;
public class operators
	{
		private static final int  l_r = 0;
		private static final int r_l = 1;
		private static final Map<String, int[] > operators = new HashMap<String , int[]>();
		static 
		{
			operators.put("+",new int[]{10,l_r});
			operators.put("-",new int[]{10,l_r});
			operators.put("*",new int[]{20,l_r});
			operators.put("/",new int[]{20,l_r});
		}
		public static final boolean check_operator(String str)
		{
			return operators.containsKey(str);
		}
		public static final boolean property(String str)
		{
			if(!check_operator(str))
			{
				throw new IllegalArgumentException("Sorry user its an invalid character");
			}
			if(operators.get(str)[1] == l_r)
			{
				return true;
			}
			return false;
		}
		public static final int preference(String str1,String str2)
		{
			if(!check_operator(str1) || !check_operator(str2))
			{
				throw new IllegalArgumentException("Sorry user these are invalid characters");	
			}
			return operators.get(str1)[0] - operators.get(str2)[0];
		}

	}