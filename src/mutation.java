import java.io.BufferedReader;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mutation {
	

	public static ArrayList<String> variables = new ArrayList<String>();
	static int numberOfMutants=0;
	static ArrayList<String> lines = new ArrayList<String>();
	static String FileName;
	static int fileNum = 0;
	
	static void readFile(String f_name)
	{
		FileName = f_name;
		String input2[];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f_name));
 
			String input;
			
			while((input=reader.readLine()) != null){
				lines.add(input);
			}

            reader.close();
            
            for(int i = 0; i < lines.size(); i++)
    		{ 
            	add_abs(i);
            	changeOperator(i);
            	ror(i);
            	lcr(i);
            	unary(i);
    		}
          
		} catch (FileNotFoundException e) {
			System.out.println("File was no found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	private static void unary(int i)
	{
		String input = lines.get(i);
		
		ArrayList<String> input_minus =  new ArrayList<String>(lines);
		
		if(input.indexOf("=")>0 && (input.indexOf("+")>0 || input.indexOf("-")>0 || input.indexOf("*")>0))
		{
			
			String words[] = null;
			words = input.split("=");
			words = words[1].split(";");
			
			input_minus.set(i, input.replace(words[0], "+" + words[0]));
			writeFile(input_minus);
			

			input_minus.set(i, input.replace(words[0], "-" + words[0]));
			writeFile(input_minus);
			

			input_minus.set(i, input.replace(words[0], "!" + words[0]));
			writeFile(input_minus);
		}
		
		
	}
	private static void ror(int i)
	{
		String input = lines.get(i);
		
		ArrayList<String> input_minus =  new ArrayList<String>(lines);
		
		if(input.indexOf("&&")>0 || input.indexOf("||")>0)
		{
			input_minus.set(i, input.replace("&&", "||"));
			writeFile(input_minus);
			

			input_minus.set(i, input.replace("||", "&&"));
			writeFile(input_minus);
			
			
		}
		
		
	}
	private static void lcr(int i)
	{
		String input = lines.get(i);
		
		ArrayList<String> input_minus =  new ArrayList<String>(lines);
		
		if(input.indexOf("==")>0)
		{
			input_minus.set(i, input.replace("==", "!="));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace("==", ">="));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace("==", "<="));
			writeFile(input_minus);
			
		}
		if(input.indexOf("<")>0)
		{
			input_minus.set(i, input.replace("<", ">"));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace("<", ">="));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace("<", "<="));
			writeFile(input_minus);
			
		}
		if(input.indexOf(">")>0)
		{
			input_minus.set(i, input.replace(">", ">"));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace(">", ">="));
			writeFile(input_minus);
			
			input_minus.set(i, input.replace(">", "<="));
			writeFile(input_minus);
			
		}
		
		
	}
	
	static void add_abs(int i)
	{			
		String input = lines.get(i);
		
		ArrayList<String> input_minus =  new ArrayList<String>(lines);

		if(input.indexOf("int")>0 && input.indexOf("=")>0)
		{
			
			String words[] = null;
			words = input.split("=");
			words = words[1].split(";");	
			input_minus.set(i, input.replace(words[0], "Math.abs("+words[0] + ")"));
			writeFile(input_minus);
			input_minus.set(i, input.replace(words[0], "-1* Math.abs("+words[0] + ")"));	
			writeFile(input_minus);
			input_minus.set(i, input.replace(words[0], "0"));	
			writeFile(input_minus);
		}	
	}
	
	private static void writeFile(ArrayList<String>  input_minus)
	{
		
		FileWriter fstream;
		try {
			new File("mutations").mkdir();
			new File("mutations/mutation" +fileNum).mkdir();
			fstream = new FileWriter( "mutations/mutation" +fileNum +"/" +FileName);
			BufferedWriter out = new BufferedWriter(fstream);
			
			for(int j=0; j < input_minus.size(); j++)
				out.write(input_minus.get(j)+ "\r\n");
			 
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileNum++;		
	}
	
	
	private static void find_variables(String input)
	{
		
	
//		
//		if(input.indexOf("=")>0)
//		{
//			System.out.println(input);
//			String words[] = null;
//			words = input.split("=");
//			System.out.println(words[1]);
//		}
//		
//	
		
		
		if(input.indexOf("int")>0 && input.indexOf("print")<0)
		{

			ArrayList <Character> array_words = new ArrayList<Character>(); 
			
			int start = input.indexOf("int")+4;
			
			for (int i = start; i < input.length()  ; i++)
			{

				array_words.add(input.charAt(i));
				
				if(input.charAt(i+1) == ')')
					break;
				if(input.charAt(i+1) == '=')
					break;
				if(input.charAt(i+1) == ';')
					break;
				
			}
			
			char words[] = new char[array_words.size()];
			
			
			for(int z = 0; z< words.length ; z++)
			{
				words[z] = array_words.get(z);
				
			}
			variables.add(new String(words));

		}	
		
	}
	private static void changeOperator(int i)
	{

		String input = lines.get(i);
		if(input.indexOf("for")<0 && input.indexOf("if")<0 && input.indexOf("+")>0)
		{
			ArrayList<String> input_minus =  new ArrayList<String>(lines);
			
			input_minus.set(i, input.replace("+", "-"));
			writeFile(input_minus);
			input_minus.set(i, input.replace("+", "*"));
			writeFile(input_minus);
			input_minus.set(i, input.replace("+", "/"));
			writeFile(input_minus);
		}
			

		
	}
	private static void writeOtherFile(String filename){

		ArrayList<String> lines2 = new ArrayList<String>();

		String input;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));

			while((input=reader.readLine()) != null){
				lines2.add(input);	
			}
			reader.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < fileNum; i++)
		{
			FileWriter fstream;
			try {
				fstream = new FileWriter( "mutations/mutation" +i +"/" +filename);
				BufferedWriter out = new BufferedWriter(fstream);
				
				for(int j=0; j < lines2.size(); j++)
					out.write(lines2.get(j)+ "\r\n");
				 
				out.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public static void main(String args[]){
		
		readFile("julius.java");
		writeOtherFile("convert.java");
		System.out.println(fileNum + " mutators have been created.");
//		readFile("convert.java");
//		writeOtherFile("julius.java");
		
	}
	
}
