package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ITR_Utils {

	private final static char COMA = ',';
	private final static char DOUBLE_QUOTE = '"';
	public static final String pathSeparator = "\\";

	private static final String MES00_ENE = "ENERO";
	private static final String MES01_FEB = "FEBRERO";
	private static final String MES02_MAR = "MARZO";
	private static final String MES03_AVR = "ABRIL";
	private static final String MES04_MAI = "MAYO";
	private static final String MES05_JUN = "JUNIO";
	private static final String MES06_JUL = "JULIO";
	private static final String MES07_AGO = "AGOSTO";
	private static final String MES08_SEP = "SEPTIEMBRE";
	private static final String MES09_OCT = "OCTUBRE";
	private static final String MES10_NOV = "NOVIEMBRE";
	private static final String MES11_DIC = "DICIEMBRE";


	// DatatypeFactory creates new javax.xml.datatype Objects that map XML
	// to/from Java Objects.
	private static DatatypeFactory df = null;

	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch(DatatypeConfigurationException e) {
			throw new IllegalStateException(
					"Error while trying to obtain a new instance of DatatypeFactory", e);
		}
	}
	
	// Getting a default locale object
	public static final Locale locale = Locale.getDefault(); 
	/**
	 * @param in The integer value
	 * @param fill The number of digits to fill
	 * @return The given value left padded with the given number of digits
	 */
	public static String lPadZero(int in, int fill){

		boolean negative = false;
		int value, len = 0;

		if(in >= 0){
			value = in;
		} else {
			negative = true;
			value = - in;
			in = - in;
			len ++;
		}

		if(value == 0){
			len = 1;
		} else{         
			for(; value != 0; len ++){
				value /= 10;
			}
		}

		StringBuilder sb = new StringBuilder();

		if(negative){
			sb.append('-');
		}

		for(int i = fill; i > len; i--){
			sb.append('0');
		}

		sb.append(in);

		return sb.toString();       
	}


	public static String remove_quotes_coma(String str) {
		//remove quotes and ending comas
		int last_char_index = str.length()-1;
		if((str.charAt(0) == DOUBLE_QUOTE) && (str.charAt(last_char_index)  == DOUBLE_QUOTE))
		{
			str = str.substring(1, (str.length()-1));						
		}
		else if (str.charAt(last_char_index) == COMA)
		{
			str = str.substring(0,last_char_index);
		}
		return str;
	}

	/*
	 * no longer necessary here!
	 * public static void combine(String folder, PDFMergerUtility mergePdf)
	{
		try
		{
			File _folder = new File(folder);
			File[] filesInFolder;
			filesInFolder = _folder.listFiles();
			for (File fileEntry : filesInFolder)
			{
				if (fileEntry.isDirectory())
				{
					combine(fileEntry.getAbsolutePath(), mergePdf);
				}
				else 
				{
					String rootPath = fileEntry.getParent();
					boolean isMergeAll = (rootPath.equalsIgnoreCase(folder));

					if (fileEntry.isFile() && !isMergeAll) 
					{
						mergePdf.addSource(fileEntry);  
					}
				}
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/

	/*not used and dangerous
	 * public static int roundCalificacion(float calificacion) {

		int roundedCalif = 0;

		if(calificacion != (float) ITR_Semestre.NO_GRADE) // is zero
		{
			if(Math.floor(calificacion) <= ITR_Semestre.MINIMUM_GRADE) // no llega a 6
			{
				roundedCalif = ITR_Semestre.MINIMUM_GRADE;
			}
			else // es 6 o mas
			{
				roundedCalif = Math.round(calificacion);
			}
		}

		return roundedCalif;
	}*/

	public static boolean isEmtpy(String s)
	{
		boolean r = false;

		if( (s == null) || (s.trim().equalsIgnoreCase("")) )
		{
			r = true;
		}	

		return r;
	}

	public static String getCurrMonthStr(int month) {
		String mes = "N/A";
		switch (month) {
		case GregorianCalendar.JANUARY:
			mes = MES00_ENE;
			break;
		case GregorianCalendar.FEBRUARY:
			mes = MES01_FEB;
			break;
		case GregorianCalendar.MARCH:
			mes = MES02_MAR;
			break;
		case GregorianCalendar.APRIL:
			mes = MES03_AVR;
			break;
		case GregorianCalendar.MAY:
			mes = MES04_MAI;
			break;
		case GregorianCalendar.JUNE:
			mes = MES05_JUN;
			break;
		case GregorianCalendar.JULY:
			mes = MES06_JUL;
			break;
		case GregorianCalendar.AUGUST:
			mes = MES07_AGO;
			break;
		case GregorianCalendar.SEPTEMBER:
			mes = MES08_SEP;
			break;
		case GregorianCalendar.OCTOBER:
			mes = MES09_OCT;
			break;
		case GregorianCalendar.NOVEMBER:
			mes = MES10_NOV;
			break;
		case GregorianCalendar.DECEMBER:
			mes = MES11_DIC;
			break;
		default:
			break;
		}

		return mes;
	}

	private static final String	dateFormat1 = "%d DE %s DE %d";
	public static final int	dateFormat1ID 	= 1;
	private static final String	dateFormat2 = "%s/%s/%s";
	public static final int	dateFormat12D 	= 2;
	private static final String	dateFormat3 = "%s_%s_%s";
	public static final int	dateFormatfile  = 3;

	public static String getDateTodayStr(int formatid)
	{
		StringBuffer date = new StringBuffer();
		Formatter formatter3 = new Formatter(date, ITR_Utils.locale);
		Calendar c = new GregorianCalendar();
		int day 		= c.get(GregorianCalendar.DAY_OF_MONTH);
		String month 	= ITR_Utils.getCurrMonthStr(c.get(GregorianCalendar.MONTH));
		int monthNo 	= c.get(GregorianCalendar.MONTH);
		int year 		= c.get(GregorianCalendar.YEAR);

		switch (formatid) {
		case dateFormat1ID:
			formatter3.format(dateFormat1, day, month, year);			
			break;
		case dateFormat12D:
			formatter3.format(dateFormat2, lPadZero(day,2), lPadZero(++monthNo,2), lPadZero(year,2));
			break;
		case dateFormatfile:
			formatter3.format(dateFormat3, lPadZero(day,2), lPadZero(++monthNo,2), lPadZero(year,2));
			break;
		default:
			break;
		}

		String fecha = formatter3.toString();

		formatter3.close();

		return fecha;
	}

	public static float roundTwoDecimals(float d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Float.valueOf(twoDForm.format(d));
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public static boolean isFileClosed(File file) {  
		boolean closed = false;
		RandomAccessFile randomAccessFile = null;
		FileChannel channel = null;
		FileLock lock = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			channel = randomAccessFile.getChannel();
			lock = channel.tryLock();//channel.lock();
			if(lock == null)
			{
				closed = false;
			}else
			{
				closed = true;
				lock.release();
			}
			channel.close();
			randomAccessFile.close();
			channel = null;
		} catch (OverlappingFileLockException e) {
			closed = false;
		}catch(Exception ex) {
			closed = false;
		} finally {
			//if(channel!=null)
			if (lock != null) 
			{
				try {
					lock.release();
					channel.close();
					randomAccessFile.close();
				} catch (IOException ex) {
					System.out.print("ITR_Utils.isFileClosed(File) exception!!! in finally");
				}
			}
		}
		return closed;
	}

	public static boolean deleteUnnecessaryFile(File deleteFile) {
		boolean deleted = false;
		if(deleteFile.exists())
		{
			try{
				deleted = deleteFile.delete();
			} catch(SecurityException e)
			{
				System.out.println("Cant delete file: " + deleteFile.getAbsolutePath());
			}
		}
		return deleted;
	}

	public static int checkPeriodDGETI_ID(final String[] args, final int index)
	{
		int perDGETI = -1;
		boolean notOK = (args.length == 0) || (index > (args.length-1));
		if(notOK)
		{
			return perDGETI;
		}

		try
		{
			perDGETI = Integer.parseInt(args[index]);
		} catch(NumberFormatException e)
		{
			System.out.println("wrong dgeti period: " + args[index]);
			return -2;
		}
		return perDGETI;
	}

	public static String getUserInput()
	{
		String answer = null;

		System.out.println("Enter period # (6 ... ): ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (Exception e) {
			System.err.println("IO error trying to read period #");
		} finally
		{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(answer == null)
		{
			System.out.println("no period entered!!!");
		}

		return answer;
	}

	public static void main(String[] args) {

		try {
			scanInt("Enter dgeti period: ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		// using InputStreamReader
		/*try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));          
			System.out.print("Enter your name: ");

			String name = reader.readLine();
			System.out.println("Your name is: " + name);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// using Scanner
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your nationality: ");
		String nationality = scanner.nextLine();
		System.out.println("Your nationality is: " + nationality);

		// using Console
		Console console = System.console();
		if (console == null) {
			System.out.println("No console: not in interactive mode!");
			System.exit(0);
		}

		System.out.print("Enter your username: ");
		String username = console.readLine();

		System.out.print("Enter your password: ");
		char[] password = console.readPassword();

		System.out.println("Thank you!");
		System.out.println("Your username is: " + username);
		System.out.println("Your password is: " + String.valueOf(password));

		// using Console with formatted prompt
		String job = console.readLine("Enter your job: ");

		String passport = console.readLine("Enter your %d (th) passport number: ", 2);

		System.out.println("Your job is: " + job);
		System.out.println("Your passport number is: " + passport);*/
	}  

	private static Scanner scanner = new Scanner(System.in);

	private static void scanLine() {
		System.out.print("Please enter a line, enter/return  will be end of line: ");
		if (scanner.hasNextLine()) {
			String scannedLine = scanner.nextLine();
			System.out.println("Your input has been evaluated to line: " + scannedLine);
		} else {
			System.out.println("You input is not a line,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanLong() {
		System.out.print("Please enter a long value. The signed long has a minimum value of -263 and a maximum value of 263-1: ");
		if (scanner.hasNextLong()) {
			long scannedLong = scanner.nextLong();
			System.out.println("Your input has been evaluated to long value: " + scannedLong);
		} else {
			System.out.println("You input is not long,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanDouble() {
		System.out.print("Please enter a double value: ");
		if (scanner.hasNextDouble()) {
			double scannedDouble = scanner.nextDouble();
			System.out.println("Your input has been evaluated to double value: " + scannedDouble);
		} else {
			System.out.println("You input is not double,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanFloat() {
		System.out.print("Please enter a float value: ");
		if (scanner.hasNextShort()) {
			short scannedFloat = scanner.nextShort();
			System.out.println("Your input has been evaluated to float value: " + scannedFloat);
		} else {
			System.out.println("You input is not float,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanShort() {
		System.out.print("Please enter a short value which has a minimum value of -32,768 and a maximum value of 32,767: ");
		if (scanner.hasNextShort()) {
			short scannedShort = scanner.nextShort();
			System.out.println("Your input has been evaluated to short value: " + scannedShort);
		} else {
			System.out.println("You input is not short,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanBoolean() {
		System.out.print("Please enter either true or false: ");
		if (scanner.hasNextBoolean()) {
			boolean scannedBoolean = scanner.nextBoolean();
			System.out.println("Your input has been evaluated to: " + scannedBoolean);
		} else {
			System.out.println("You input is not boolean,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	private static void scanInt() {
		System.out.print("Please enter an int value: ");
		if (scanner.hasNextInt()) {
			int scannedInteger = scanner.nextInt();
			System.out.println("Your input has been evaluated to: " + scannedInteger);
		} else {
			System.out.println("You input is not an integer value,Your input has been evaluated to: " + scanner.nextLine());
		}
	}

	public static int EXIT_CODE = 0;
	
	public static int scanInt(String msg) throws Exception {
		int scannedInteger = 0;
		boolean cond = true;
		while(cond)
		{
			System.out.print(msg);
			if (scanner.hasNextInt()) {
				scannedInteger = scanner.nextInt();
				System.out.println("Your input has been evaluated to: " + scannedInteger);
				
				if(scannedInteger == EXIT_CODE)
				{
					throw new Exception("EXIT_CODE: "+EXIT_CODE);
				}
				break;
			} else {
				System.out.println("Not a valid integer: " + scanner.nextLine());
				continue;
			}
		}
		return scannedInteger;
	}
	
	public static boolean scanBoolean(String msg) throws Exception
	{
		boolean scannedBoolean = false;
		boolean cond = true;
		while(cond)
		{
			System.out.print(msg);
			System.out.println(", true or false? ");
			if (scanner.hasNextBoolean()) {
				scannedBoolean = scanner.nextBoolean();
				System.out.println("Your input has been evaluated to: " + scannedBoolean);

				break;
			} else {
				System.out.println("Not a valid option (true/false): " + scanner.nextLine());
				throw new Exception("EXIT_CODE: "+EXIT_CODE);
			}
		}
		return scannedBoolean;
	}
	
	public static void closeScanner()
	{
		scanner.close();
	}

	public static List<String> readFileIntoList(String file) throws IOException {
	    List<String> lines = Collections.emptyList();
	    try {
	      lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return lines;
	  }

	public static Date getDate(XMLGregorianCalendar obj){
        java.util.Date date= obj.toGregorianCalendar().getTime();
        Date d=new Date(date.getTime());
        //System.out.println("inside date parse class");
        return d;
    }

	// Converts a java.util.Date into an instance of XMLGregorianCalendar
	public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
		if(date == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return df.newXMLGregorianCalendar(gc);
		}
	}

	// Converts an XMLGregorianCalendar to an instance of java.util.Date
	public static java.util.Date asDate(XMLGregorianCalendar xmlGC) {
		if(xmlGC == null) {
			return null;
		} else {
			return xmlGC.toGregorianCalendar().getTime();
		}
	}

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for(Object o: c)
			r.add(clazz.cast(o));
		return r;
	}
}
