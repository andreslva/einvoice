package itr.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import itr.CustomValidationEventHandler;
import itr.sat.cfdi.v33.Comprobante;

public class Test_v33_00 {

	private static final String V33_XSD = ".\\xsd\\v33\\cfdv33.xsd";
	
	public static void main(String[] args) {
		
		//File file33 = new File(".\\xml\\65504514291_Enero2018_Egreso.xml");
		  File file33 = new File(".\\xml\\65506992374_Enero2019_Ingreso.xml");

		itr.sat.cfdi.v33.Comprobante comprobante33 = null;
		try {
			comprobante33 = unmarshal_v33(file33);
			System.out.println(" isComprobante33: "+ isComprobante33(comprobante33));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		itr.sat.cfdi.v33.Comprobante.Emisor emisor33 = comprobante33.getEmisor();
		
	}

	
	public static Comprobante unmarshal_v33(File importFile) throws JAXBException 
	{
		Comprobante comprobante = null;
		JAXBContext context;

		context = JAXBContext.newInstance(itr.sat.cfdi.v33.Comprobante.class);
		Unmarshaller um = context.createUnmarshaller();
		comprobante = (Comprobante) um.unmarshal(importFile);

		return comprobante;
	}
	
	public static boolean isComprobante33(Object o)
	{
		return o instanceof itr.sat.cfdi.v33.Comprobante;
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

	
}
