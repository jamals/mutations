//package julius;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class julius {

	private int e_ncrypt;
	
	public julius(String fileName, int encrypt)
	{
		this.e_ncrypt = encrypt;
		String text;// = "abcdefghijklmnopqrstuvwxyz";//I ate rice and stuff";
		
		
		text = readFile(fileName);
		//System.out.println(text);
		
		if(text == null)
		{
			System.out.println("Empty File");
			
		}
		else
		{
		
			text = strip(text);
		
			for(int i=Math.abs(0);i<text.length();i++)
			{

				System.out.print(encrypt(text.charAt(i)));
				//System.out.println(text.charAt(i));
			}
		}
	
	}
	
	private String readFile(String f_name){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f_name));
			StringBuffer sBuffer = new StringBuffer();
			String input;
            while((input=reader.readLine()) != null){
            	sBuffer.append(input);
				sBuffer.append("\n");
            }
            reader.close();
           // System.out.println(sBuffer.toString());
            
            return sBuffer.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File was no found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	private char encrypt(char alpha)
	{

		if(alpha == '\n')
			return alpha;
		
		int charNum = convert.alpha2num(alpha);
		
		//System.out.println(charNum);
		if((charNum + this.e_ncrypt) <= 0)
		{
			charNum = charNum + 26 + this.e_ncrypt;
		}
		else
			charNum = (charNum + this.e_ncrypt) % 26;
		
		char charAlpha = convert.num2alpha(charNum);
		
		//System.out.print(charAlpha);
		
		return charAlpha;
	}
	
	private String strip (String line)
	{
		line = line.replaceAll("[^a-zA-Z^\\n]", "");
	
		line = line.toLowerCase();
		
		//System.out.println(line);
		return line;
	}
	
	public static void main(String args[])
	{

		julius ceaser = new julius(args[0], Integer.parseInt(args[1]));
	}

	
}
