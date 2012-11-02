package julius;

public class convert {
	
	static int alpha2num(char alpha){
		
		int num = (int)alpha;
		
		num = num - 97;
		
		return num;
	}
	
	static char num2alpha(int num)
	{
		num = num + 97;
		char c_alpha = (char)num;
//		if(c_alpha == '!')
//			c_alpha = '\n';
		return c_alpha;
		
	}
	
	public void main(String args[])
	{
		
		char k = num2alpha(3);
		System.out.println(k);
	}
}
