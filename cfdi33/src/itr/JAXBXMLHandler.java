package itr;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import itr.sat.cfdi.v33.Comprobante;


public class JAXBXMLHandler {


	//private static final String V22_XSD = ".\\xsd\\cfdv22.xsd";
	//private static final String V32_XSD = ".\\xsd\\cfdv32.xsd";
	private static final String V33_XSD = ".\\xsd\\v33\\cfdv33.xsd";
	//private static final String CAN_XSD = ".\\xsd\\acuse.xsd";
	//private static final String V22tf_XSD = ".\\xsd\\cfdv22_tf.xsd";
	//private static final String V32tf_XSD = ".\\xsd\\cfdv32_tf.xsd";

	public static final int V22 = 22;
	public static final int V32 = 32;
	public static final int V33 = 32;
	public static final int verCAN = 1;
	public static final int wrongVer = -1;

	public static itr.sat.cfdi.v33.Comprobante unmarshal_v33(File importFile) throws JAXBException 
	{
		itr.sat.cfdi.v33.Comprobante comprobante = null;
		JAXBContext context;

		context = JAXBContext.newInstance(itr.sat.cfdi.v33.Comprobante.class);
		Unmarshaller um = context.createUnmarshaller();
		comprobante = (itr.sat.cfdi.v33.Comprobante) um.unmarshal(importFile);

		return comprobante;
	}
	
	// Import: Unmarshalling
	/*public static Comprobante unmarshal_v32(File importFile) throws JAXBException 
	{
		Comprobante comprobante = null;
		JAXBContext context;

		context = JAXBContext.newInstance(Comprobante.class);
		Unmarshaller um = context.createUnmarshaller();
		comprobante = (Comprobante) um.unmarshal(importFile);

		return comprobante;
	}*/

	// Import: Unmarshalling
	/*public static sat.cfdv22.Comprobante unmarshal_v22(File importFile) throws JAXBException 
	{
		sat.cfdv22.Comprobante comprobante = null;
		JAXBContext context;

		context = JAXBContext.newInstance(sat.cfdv22.Comprobante.class);
		Unmarshaller um = context.createUnmarshaller();
		comprobante = (sat.cfdv22.Comprobante) um.unmarshal(importFile);

		return comprobante;
	}*/

	// Import: Unmarshalling
	/*public static acuse.Acuse unmarshal_CAN(File importFile) throws JAXBException 
	{
		acuse.Acuse cancelacion = null;
		JAXBContext context;

		context = JAXBContext.newInstance(acuse.Acuse.class);
		Unmarshaller um = context.createUnmarshaller();
		cancelacion = (acuse.Acuse) um.unmarshal(importFile);

		return cancelacion;
	}*/
	
	public static boolean isMonthOK33(itr.sat.cfdi.v33.Comprobante c, int month, final int year)
	{
		boolean r = true;
		
		XMLGregorianCalendar cal = c.getFecha();
		boolean m = month != cal.getMonth();
		boolean y = year  != cal.getYear();
		if(m || y)
		{
			r = false;
		}
		
		return r;
	}

	/*public static boolean isMonthOK32(sat.cfdv32.Comprobante c, int month, final int year)
	{
		boolean r = true;
		
		XMLGregorianCalendar cal = c.getFecha();
		boolean m = month != cal.getMonth();
		boolean y = year  != cal.getYear();
		if(m || y)
		{
			r = false;
		}
		
		return r;
	}*/
	
	/*public static boolean isCANMonthOK(acuse.Acuse c, int month)
	{
		boolean r = true;
		
		XMLGregorianCalendar cal = c.getFecha();
		if(month != cal.getMonth())
		{
			r = false;
		}
		
		return r;
	}*/
	
	/*public static boolean isMonthOK22(sat.cfdv22.Comprobante c, int month, final int year)
	{
		boolean r = true;
		
		XMLGregorianCalendar cal = c.getFecha();
		boolean m = month != cal.getMonth();
		boolean y = year  != cal.getYear();
		if(m || y)
		{
			r = false;
		}
		
		return r;
	}*/
	
	 public static int checkXSDVersion33(final File xmlFile)
	 {
	 	int ver = V33;

		/*SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		CustomValidationEventHandler evtHandler = null;
		
		try {
			Schema schema32 = sf.newSchema(new File(V32_XSD));

			JAXBContext jc = JAXBContext.newInstance(Comprobante.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema32);
			evtHandler = new CustomValidationEventHandler(); 
			unmarshaller.setEventHandler(evtHandler);
			unmarshaller.unmarshal(xmlFile);
		} 
		catch(SAXException e)
		{
			e.printStackTrace();
		} 
		catch(JAXBException e)
		{
			if(isVersion22(xmlFile) == true)
			{
				ver = V22;
			}
			//System.out.println("---- XML VERSION 22!!!!!!!!!!!!");
		}*/

	 	ver = isVersion(xmlFile);
		
	 	return ver;
	}

	public static boolean isComprobante32(Object o)
	{
		return o instanceof Comprobante;
	}
	
	/*public static boolean isComprobante22(Object o)
	{
		return o instanceof sat.cfdv22.Comprobante;
	}*/

	public static boolean isComprobante33(Object o)
	{
		return o instanceof itr.sat.cfdi.v33.Comprobante;
	}
	
	private static int isVersion(final File xmlFile)
	{
		int ver = wrongVer;

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		boolean unMarshallingOK = isUnMarshallingOK(sf, V33_XSD, itr.sat.cfdi.v33.Comprobante.class, xmlFile);

		if(unMarshallingOK)
		{
			ver = V33;
		}
		/*else
		{
			unMarshallingOK = isUnMarshallingOK(sf, V32_XSD, sat.cfdv32.Comprobante.class, xmlFile);

			if(unMarshallingOK)
			{
				ver = V32;
			}
			else
			{
				unMarshallingOK = isUnMarshallingOK(sf, CAN_XSD, acuse.Acuse.class, xmlFile, true);

				if(unMarshallingOK)
				{
					ver = verCAN;
				}
			}
		}*/

		return ver;
	}

	protected static Object getVersion(final File xmlFile) throws JAXBException
	{
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Object unMarshalledObject = unmarshal_v33(xmlFile);
				//orig: unMarshall(sf, V33_XSD, itr.sat.cfdi.v33.Comprobante.class, xmlFile, true);

		/*if(unMarshalledObject == null)
		{
			unMarshalledObject = unMarshall(sf, V32_XSD, sat.cfdv32.Comprobante.class, xmlFile, false);

			if(unMarshalledObject == null)
			{
				unMarshalledObject = unMarshall(sf, V33_XSD, sat.cfdv33.Comprobante.class, xmlFile, true);
				
				if(unMarshalledObject == null)
				{	
					unMarshalledObject = unMarshall(sf, CAN_XSD, acuse.Acuse.class, xmlFile, true);
				}
			}
		}*/

		return unMarshalledObject;
	}

	private static boolean isUnMarshallingOK(SchemaFactory sf, String xsdPathName, Class<?> cl, final File xmlFile)
	{
		return isUnMarshallingOK(sf, xsdPathName, cl, xmlFile, true);
	}
	
	private static boolean isUnMarshallingOK(SchemaFactory sf, String xsdPathName, Class<?> cl, final File xmlFile, boolean printStackTrace)
	{
		boolean res = false;

		try {
			Schema schemaCAN = sf.newSchema(new File(xsdPathName));

			JAXBContext jc = JAXBContext.newInstance(cl);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schemaCAN);
			CustomValidationEventHandler evtHandler = new CustomValidationEventHandler(); 
			unmarshaller.setEventHandler(evtHandler);
			Object obj = unmarshaller.unmarshal(xmlFile);
			
			res = true;
		}
		catch(SAXException c)
		{
			res = false;
			//c.printStackTrace();
			System.out.println(" Exception file: "+xmlFile.getName());
		} catch (JAXBException e1) {
			res = false;
			if(printStackTrace)
			{	
				e1.printStackTrace();
			}
		}

		return res;
	}
	
	private static Object unMarshall(SchemaFactory sf, String xsdPathName, Class<?> cl, final File xmlFile, boolean printStackTrace)
	{
		Object res = null;

		try {
			Schema schemaCAN = sf.newSchema(new File(xsdPathName));

			JAXBContext jc = JAXBContext.newInstance(cl);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schemaCAN);
			CustomValidationEventHandler evtHandler = new CustomValidationEventHandler(); 
			unmarshaller.setEventHandler(evtHandler);
			res = unmarshaller.unmarshal(xmlFile);
		}
		catch(SAXException c)
		{
			res = null;
			//c.printStackTrace();
			System.out.println(" Exception file: "+xmlFile.getName());
		} catch (JAXBException e1) {
			res = null;
			if(printStackTrace)
			{	
				e1.printStackTrace();
				System.out.println(" JAXBException file: "+xmlFile.getName());
			}
		}

		return res;
	}

	public static void main(String args[])
	{
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		File v33_f00 = new File(".\\xml\\65504514291_Enero2018_Egreso.xml"); 
		
		//boolean unMarshallingOK = isUnMarshallingOK(sf, CAN_XSD, acuse.Acuse.class, new File("C:\\Users\\HP\\Google Drive\\dev\\workspace\\sat-xml_v01_24dic2014\\xml\\JIPE7907227Q7-CAN-460.xml"), true);
		//boolean unMarshallingOK = isUnMarshallingOK(sf, V32tf_XSD, sat.cfdv32_tf.Comprobante.class, new File("C:\\Users\\HP\\Google Drive\\dev\\workspace\\sat-xml_v01_24dic2014\\xml\\Factura 8231.xml"), true);
		//boolean unMarshallingOK = isUnMarshallingOK(sf, V22tf_XSD, sat.cfdv22_tf.Comprobante.class, new File("C:\\Users\\HP\\Google Drive\\dev\\workspace\\sat-xml_v01_24dic2014\\xml\\SECFD_11_28_2013 5_55_05 PM.xml"), true);
		//boolean unMarshallingOK = isUnMarshallingOK(sf, V33_XSD, itr.sat.cfdi.v33.Comprobante.class, new File("C:\\Users\\HP\\Google Drive\\dev\\workspace\\sat-xml_v01_24dic2014\\xml\\ce6be2f3-bede-4eb3-b935-b22f5a8e037a.xml"), true);
		  boolean unMarshallingOK = isUnMarshallingOK(sf, V33_XSD, itr.sat.cfdi.v33.Comprobante.class, v33_f00, true);
		
		System.out.println("unMarshallingOK: " + unMarshallingOK);
	}
	
}
