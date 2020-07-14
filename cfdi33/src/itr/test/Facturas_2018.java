package itr.test;

import itr.JAXBSAT;
import utils.ITR_Utils;

public class Facturas_2018 {
	
	private static String DETAIL 	= "detail";
	private static String NO_DETAIL = "no_detail";
	
	private static String NO_PRINT 	= "no_print";
	private static String ORGANIZE 	= "organize";
	
	public static void main(String[] args) {
			
		int year = -1;
		try {
			year = ITR_Utils.scanInt("Enter year: ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}finally
		{
			ITR_Utils.closeScanner();
		}

		if(args.length >= 1)
		{
			if (args[0].equalsIgnoreCase(DETAIL))
			{
				JAXBSAT.read_XML_FILES(true,true, year);
			}
			else if (args[0].equalsIgnoreCase(NO_DETAIL))
			{
				JAXBSAT.read_XML_FILES(false,true, year);
			}
			else if (args[0].equalsIgnoreCase(NO_PRINT))
			{
				JAXBSAT.read_XML_FILES(true,false, year);
			}
			else if (args[0].equalsIgnoreCase(ORGANIZE))
			{
				JAXBSAT.read_XML_FILES(year);
			}
			
		} else
		{
			System.out.println("no args");
			System.exit(0);
		}
			
		//JAXBSAT.read_XML_FILES();
		//JAXBSAT.read_XML_FILES(false);
	}

}
