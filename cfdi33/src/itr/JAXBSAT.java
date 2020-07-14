package itr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConstants;

import itr.exception.SAT_ITR_UUID_REPEATED_Exception;
import itr.exception.SAT_ITR_WRONG_MONTH_Exception;
// import itr.jpa.SAT_ITR_TO_Assembler;
// import sat.cfdv32.Comprobante;
// import sat.cfdv32.Comprobante.Conceptos.Concepto;
import itr.sat.cfdi.v33.Comprobante;
import itr.sat.cfdi.v33.Comprobante.Conceptos.Concepto;

public class JAXBSAT {

	private static final String dflt_props_dir = ".\\prop\\sat.cfg";
	protected static final String root_dir_property = "ano_fiscal";
	protected static final String root_dir_itr_property = "ano_fiscal_itr";
	private static final String report_year_property = "rpt_year";

	private static final String recibidas_dir = "recibidas";
	private static final String emitidas_dir = "emitidas";

	private static final String pdf_FILEDIR    = "pdf";
	private static final String xml_FILEDIR    = "xml";
	private static final String xml_FILEPREFIX = "xml";

	private static final String ENE01DIR = "01-ene";
	private static final String FEB02DIR = "02-feb";
	private static final String MAR03DIR = "03-mar";
	private static final String ABR04DIR = "04-abr";
	private static final String MAY05DIR = "05-may";
	private static final String JUN06DIR = "06-jun";
	private static final String JUL07DIR = "07-jul";
	private static final String AGO08DIR = "08-ago";
	private static final String SEP09DIR = "09-sep";
	private static final String OCT10DIR = "10-oct";
	private static final String NOV11DIR = "11-nov";
	private static final String DIC12DIR = "12-dic";
	private static final String YEAR = "13";

	//private static final boolean USE_OLD_VERSION = false;
	
	private static final String facturasFileName = "facturas_recibidas";
	private static final String facturasItrFileName = "facturas_itr";
	
	private static HashMap<String, Integer> num_months;

	static
	{
		num_months = new HashMap<String, Integer>(13);
		num_months.put(ENE01DIR, new Integer(DatatypeConstants.JANUARY));
		num_months.put(FEB02DIR, new Integer(DatatypeConstants.FEBRUARY));
		num_months.put(MAR03DIR, new Integer(DatatypeConstants.MARCH));
		num_months.put(ABR04DIR, new Integer(DatatypeConstants.APRIL));
		num_months.put(MAY05DIR, new Integer(DatatypeConstants.MAY));
		num_months.put(JUN06DIR, new Integer(DatatypeConstants.JUNE));
		num_months.put(JUL07DIR, new Integer(DatatypeConstants.JULY));
		num_months.put(AGO08DIR, new Integer(DatatypeConstants.AUGUST));
		num_months.put(SEP09DIR, new Integer(DatatypeConstants.SEPTEMBER));
		num_months.put(OCT10DIR, new Integer(DatatypeConstants.OCTOBER));
		num_months.put(NOV11DIR, new Integer(DatatypeConstants.NOVEMBER));
		num_months.put(DIC12DIR, new Integer(DatatypeConstants.DECEMBER));
		num_months.put(YEAR, new Integer((DatatypeConstants.DECEMBER+1)));
	}

	private static final String XML_DIR = "xml";

	//protected SAT_ITR_TO_Assembler toa;

	protected Properties prop;
	protected String root_dir;
	protected String root_dir_itr;
	private int report_year;

	private LinkedHashSet<String> months;
	private String month;

	private boolean printDetail;
	private boolean organizeDir;
	
	public boolean organizeDir() {
		return organizeDir;
	}

	public void setOrganizeDir(boolean oranizeFile) {
		this.organizeDir = oranizeFile;
	}

	public int getMonth() {
		return num_months.get(this.month);
	}

	public static String getMonthDir(int monthIndex) {
		String mes = "N/A";
		switch (monthIndex) {
		case DatatypeConstants.JANUARY:
			mes = ENE01DIR;
			break;
		case DatatypeConstants.FEBRUARY:
			mes = FEB02DIR;
			break;
		case DatatypeConstants.MARCH:
			mes = MAR03DIR;
			break;
		case DatatypeConstants.APRIL:
			mes = ABR04DIR;
			break;
		case DatatypeConstants.MAY:
			mes = MAY05DIR;
			break;
		case DatatypeConstants.JUNE:
			mes = JUN06DIR;
			break;
		case DatatypeConstants.JULY:
			mes = JUL07DIR;
			break;
		case DatatypeConstants.AUGUST:
			mes = AGO08DIR;
			break;
		case DatatypeConstants.SEPTEMBER:
			mes = SEP09DIR;
			break;
		case DatatypeConstants.OCTOBER:
			mes = OCT10DIR;
			break;
		case DatatypeConstants.NOVEMBER:
			mes = NOV11DIR;
			break;
		case DatatypeConstants.DECEMBER:
			mes = DIC12DIR;
			break;
		default:
			break;
		}

		return mes;
	}

	/*public SAT_ITR_TO_Assembler getToa() {
		return toa;
	}

	public void setToa(SAT_ITR_TO_Assembler toa) {
		this.toa = toa;
	}*/

	public void setMonth(String month) {
		this.month = month;
	}

	public String getRoot_dir() {
		return root_dir;
	}

	public String getRoot_dir_itr() {
		return root_dir_itr;
	}
	
	public int getReport_year() {
		return report_year;
	}

	/*public JAXBSAT(boolean printDetail) {
		this(dflt_props_dir);
		
		this.printDetail = printDetail;
	}*/
	
	/*public JAXBSAT(boolean printDetail) {
		this(dflt_props_dir, getYearFromDate(new GregorianCalendar().getTime()));
		this.printDetail = printDetail;
	}*/
	
	public JAXBSAT(boolean printDetail, final int year) {
		this(dflt_props_dir, year);
		setPrintDetail(printDetail);//this.printDetail = printDetail;
	}
	
	/*public JAXBSAT(String props_dir) {
		super();

		this.printDetail = true;
		this.organizeDir = false;

		prop = new Properties();

		try
		{
			prop.load(new FileInputStream(props_dir));

			System.out.println("properties found");

			root_dir = prop.getProperty(root_dir_property).toString();
			root_dir_itr = prop.getProperty(root_dir_itr_property).toString();
			try {
					String year = prop.getProperty(report_year_property);
					report_year = Integer.parseInt(year);
			} catch(NumberFormatException e)
			{
				System.out.println("rpt_year is not an INT!!!");
				GregorianCalendar g = new GregorianCalendar();
				report_year = getYearFromDate(g.getTime());
			}

		} catch (IOException io)
		{
			io.printStackTrace();
		}

		months = new LinkedHashSet<String>(12);

		months.add(ENE01DIR);
		months.add(FEB02DIR);
		months.add(MAR03DIR);
		months.add(ABR04DIR);
		months.add(MAY05DIR);
		months.add(JUN06DIR);
		months.add(JUL07DIR);
		months.add(AGO08DIR);
		months.add(SEP09DIR);
		months.add(OCT10DIR);
		months.add(NOV11DIR);
		months.add(DIC12DIR);
	}*/

	protected JAXBSAT(String props_dir, final int year) {
		super();

		report_year = year;

		setPrintDetail(true);//this.printDetail = true;
		this.organizeDir = false;

		months = new LinkedHashSet<String>(12);
		months.add(ENE01DIR);
		months.add(FEB02DIR);
		months.add(MAR03DIR);
		months.add(ABR04DIR);
		months.add(MAY05DIR);
		months.add(JUN06DIR);
		months.add(JUL07DIR);
		months.add(AGO08DIR);
		months.add(SEP09DIR);
		months.add(OCT10DIR);
		months.add(NOV11DIR);
		months.add(DIC12DIR);
		
		prop = new Properties();
		try
		{
			prop.load(new FileInputStream(props_dir));
			System.out.println("properties found");
		} catch (IOException io)
		{
			io.printStackTrace();
		}
		
		setUpDir(getReport_year());
	}
	
	protected void setUpDir(final int year) {
		String root_dir_tmp = prop.getProperty(root_dir_property).toString();
		String root_dir_itr_tmp = prop.getProperty(root_dir_itr_property).toString();

		if(root_dir == null)
		{
			StringBuffer rec = new StringBuffer(root_dir_tmp);
			rec.append(getReport_year());
			rec.append("\\");
			rec.append(recibidas_dir);
			rec.append("\\");

			root_dir = rec.toString();
		}

		if(root_dir_itr == null)
		{
			StringBuffer emi = new StringBuffer(root_dir_itr_tmp);
			emi.append(getReport_year());
			emi.append("\\");
			emi.append(emitidas_dir);
			emi.append("\\");

			root_dir_itr = emi.toString();
		}
	}
	
	public static void read_XML_FILES(final int year)
	{

		JAXBSAT jaxbsat = new JAXBSAT(false, year);
		jaxbsat.setOrganizeDir(true);

		final File folder = new File(jaxbsat.getRoot_dir());

		processFolder(jaxbsat, folder);
	}

	public static void read_ITR_XML_FILES(final int year)
	{

		JAXBSAT jaxbsat = new JAXBSAT(false, year);
		jaxbsat.setOrganizeDir(true);

		final File folder = new File(jaxbsat.getRoot_dir_itr());

		processFolder(jaxbsat, folder);
		
		JAXBSAT.stopExecutorService();
	}
	
	public static void read_XML_FILES(boolean printDetail, boolean printFile, final int year)
	{

		JAXBSAT jaxbsat = new JAXBSAT(printDetail, year);

		final File folder = new File(jaxbsat.getRoot_dir());

		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO de XML_FILES !!!");
			System.exit(0);
		}
		
		if(printFile)
		{
			processFolder(jaxbsat, folder, printDetail ? facturasFileName : facturasFileName.concat("_no_detail"));
		}else
		{
			processFolder(jaxbsat, folder);
		}
	}

	public static void read_ITR_XML_FILES(boolean printDetail, boolean printFile, final int year)
	{

		JAXBSAT jaxbsat = new JAXBSAT(printDetail, year);

		final File folder = new File(jaxbsat.getRoot_dir_itr());
		
		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO de ITR_XML_FILES !!!");
			System.exit(0);
		}

		if(printFile)
		{
			processFolder(jaxbsat, folder, printDetail ? facturasItrFileName : facturasItrFileName.concat("_no_detail"));
		}else
		{
			processFolder(jaxbsat, folder);
		}
	}

	private static void processFolder(JAXBSAT jaxbsat, final File folder, String fileName) 
	{
		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO (SAT) !!!");
			System.exit(0);
		}

		Factura_Total ft = null;
		try{
			ft = Factura_Total.getYearInvoice(fileName, jaxbsat.printDetail()/*jaxbsat.printDetail*/);
			ft.setYearNo(jaxbsat.getReport_year());

			int cnt = jaxbsat.read_XML_FILES(folder,ft);

			//old version: Factura_Total r = ft.getMonth(getMonth());
			//old version: printMonthYearResume(r);
			for(int mIndex = Factura_Total.ENE01; mIndex <= Factura_Total.DIC12; mIndex++)
			{
				Factura_Total r = ft.getMonth(mIndex);
				jaxbsat.printMonthYearResume(r);
			}
			
			traceMsg(" # total facturas: "+ cnt);
			//traceMsg("\n"+ft.toString());
			traceMsg(ft.toStringResMen());
			
			//in finally ft.closePDF();			
		
		} catch (SAT_ITR_WRONG_MONTH_Exception w)
		{
			ft.printPDFErrorMsg(w.getMessage());
			w.printStackTrace();
		} catch (FileNotFoundException e)
		{
			traceMsg(" no pdf folder!! "+ e.getMessage());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(ft != null)
			{
				ft.closePDF();
			}
		}
	}
	
	private static void processFolder(JAXBSAT jaxbsat, final File folder) {
		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO (SAT) !!!");
			System.exit(0);
		}

		Factura_Total ft = Factura_Total.getYearInvoice();
		ft.setYearNo(jaxbsat.getReport_year());

		int cnt = 0;
		try {
			cnt = jaxbsat.read_XML_FILES(folder,ft);
		} catch (SAT_ITR_WRONG_MONTH_Exception e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		traceMsg(" # total facturas: "+ cnt);
		//traceMsg("\n"+ft.toString());
		traceMsg(ft.toStringResMen());
	}

	protected int read_XML_FILES(final File folder, final Factura_Total ft) throws SAT_ITR_WRONG_MONTH_Exception, JAXBException 
	{

		//ArrayList<String> files = null;
		String fileName = "";

		boolean isMonth = false/*,isXMLfolder = false*/;

		itr.sat.cfdi.v33.Comprobante comprobante32 = null;
		// acuse.Acuse cancelacion = null;
		//itr.sat.cfdi.v33.Comprobante comprobante22 = null;
		int xsdVer = JAXBXMLHandler.V32;
		int cntMs = 0;
		int cnt = 0;
		
		for (final File fileEntry : folder.listFiles()) 
		{
			if (fileEntry.isDirectory())
			{
				isMonth = months.contains(fileEntry.getName());

				if( isMonth )
				{
					// create new filename filter
					FilenameFilter fileNameFilter = new FilenameFilter() 
					{
						@Override
						public boolean accept(File dir, String name) 
						{
							if(name.equalsIgnoreCase(xml_FILEDIR))
							{
								return true;
							}
							return false;
						}
					};

					File [] xmlFiles = fileEntry.listFiles(fileNameFilter);

					if(xmlFiles.length != 1)
					{
						traceMsg(getMonth()+" no contiene xml folder!");
						System.exit(7);
					}

					setMonth(fileEntry.getName());

					traceMsg("Reading xml files under the folder "+fileEntry.getAbsolutePath());
					cntMs = read_XML_FILES(xmlFiles[0],ft);
					cnt += cntMs;
					
					//old version: Factura_Total r = ft.getMonth(getMonth());
					//old version: printMonthYearResume(r);
				}
				//else if(isXMLfolder){}
				else
				{
					continue;
				}
			} else 	if (fileEntry.isFile()) 
			{
				//StringBuffer s = new StringBuffer();
				fileName = fileEntry.getName();
				if (( fileName.substring( 
						fileName.lastIndexOf('.') + 1
						,  fileName.length()).toLowerCase()).equals(xml_FILEPREFIX))
				{
					cnt++;
					//logMsg("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
					//if(USE_OLD_VERSION)
					{
						System.out.println(cnt+" File Name = " + fileEntry.getName());
					}

					Object xsdVerObj = JAXBXMLHandler.getVersion(fileEntry);
					if(xsdVerObj == null)
					{
						--cnt;
						System.out.println(cnt+"	ver33 " + fileEntry.getName());
						continue;
					}

					if(organizeDir())
					{
						checkFileLocation(xsdVerObj, fileEntry);
						continue;
					}

					try {
						if(xsdVerObj instanceof itr.sat.cfdi.v33.Comprobante)
						{
							ft.addComponent33((itr.sat.cfdi.v33.Comprobante)xsdVerObj, getMonth(), fileEntry.getName());
						}
						/*else if(xsdVerObj instanceof sat.cfdv32.Comprobante)
						{
							ft.addComponent32((sat.cfdv32.Comprobante)xsdVerObj, getMonth(), fileEntry.getName());
						}else if(xsdVerObj instanceof acuse.Acuse)
						{
							toPrintCAN((acuse.Acuse)xsdVerObj);

							ft.addCancelada((acuse.Acuse)xsdVerObj,getMonth(),fileEntry.getName());

							System.out.println("factura cancelada!!");
						}*/
					}catch(SAT_ITR_UUID_REPEATED_Exception e)
					{
						e.printStackTrace();
						continue;
					}
				}
				else
				{
					continue;
				}
			}
		}

		return cnt;	
	}

	public void checkFileLocation(Object xsdVerObj, File fileEntry) throws SAT_ITR_WRONG_MONTH_Exception
	{
		int mesIndex = -1;
		if(xsdVerObj instanceof itr.sat.cfdi.v33.Comprobante )
		{
			itr.sat.cfdi.v33.Comprobante c33 = (itr.sat.cfdi.v33.Comprobante) xsdVerObj;
			mesIndex = c33.getFecha().getMonth();
		}
		/*else if (xsdVerObj instanceof sat.cfdv22.Comprobante)
		{
			sat.cfdv22.Comprobante c22 = (sat.cfdv22.Comprobante) xsdVerObj;
			mesIndex = c22.getFecha().getMonth();
		}else if (xsdVerObj instanceof acuse.Acuse)
		{
			acuse.Acuse can = (acuse.Acuse) xsdVerObj;
			mesIndex = can.getFecha().getMonth();
		}*/
		
		if(getMonth() != mesIndex)
		{
			String rightFolder = getMonthDir(mesIndex);

			StringBuffer msg = new StringBuffer();
			msg.append("Factura mal ubicada : "+ fileEntry.getName());
			msg.append("\n month: " + month);
			msg.append("\n OK fo: " + rightFolder);
			System.out.println(msg.toString());

			File xmlFolder = fileEntry.getParentFile();
			File monthFolder = xmlFolder.getParentFile();
			File baseFolder = monthFolder.getParentFile();
			//System.out.println(" xmlFolder  > " +xmlFolder);
			//System.out.println(" monthFolder> " +monthFolder);
			//System.out.println(" baseFolder > " +baseFolder);

			StringBuffer newLoc = new StringBuffer(baseFolder.toString());
			newLoc.append("\\");
			newLoc.append(rightFolder);
			newLoc.append("\\");
			newLoc.append("xml");
			newLoc.append("\\");
			newLoc.append(fileEntry.getName());
			System.out.println(" newLoc > " + newLoc);

			Path sourcePath      = Paths.get(fileEntry.getPath());
			Path destinationPath = Paths.get(newLoc.toString());
			try {
			    Files.copy(sourcePath, destinationPath,
			            StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
			    //moving file failed.
			    e.printStackTrace();
			    throw new SAT_ITR_WRONG_MONTH_Exception("moving file failed!");
			}

			deleteFile(fileEntry);

			/*try {
			    Files.delete(sourcePath);
			} catch (IOException e) {
			    //delete file failed.
			    e.printStackTrace();
			    throw new ITR_SAT_WRONG_MONTH_Exception("deleting file failed!");
			}*/
		}

		return;
	}

	protected void printMonthYearResume(Factura_Total r) {
		//AQUI VA EL BUNCH DE CADA MES
		//if(!USE_OLD_VERSION)
		{
			if(printDetail())
			{
				System.out.println(r.toStringCompGRs());
			}
		}
		//RESUMEN MENSUAL
		traceMsg(r.toStringResMen(printDetail()));
		//System.out.println(r);
	}

	/*public static void toPrintCAN(acuse.Acuse cancelacion) 
	{
		System.out.println(padRight(" Fecha: ")+ cancelacion.getFecha());
		System.out.println(" getFolios().getUUID(): "+ cancelacion.getFolios().get(0).getUUID());
		System.out.println(" getFolios().getEstatusUUID(): "+ cancelacion.getFolios().get(0).getEstatusUUID());
	}*/

	public static void toPrint33(itr.sat.cfdi.v33.Comprobante comprobante) 
	{
		System.out.println("--------");

		System.out.println(padRight(" Folio: ")+comprobante.getFolio());
		System.out.println(padRight(" Tipo: ")+comprobante.getTipoDeComprobante());
		System.out.println(padRight(" FormaDePago : ")+ comprobante.getFormaPago());
		System.out.println(padRight(" MetodoDePago : ")+ comprobante.getMetodoPago());
		System.out.println(padRight(" Fecha : ")+ comprobante.getFecha());
		System.out.println(padRight(" Emisor : ")+ comprobante.getEmisor().getNombre());
		System.out.println(padRight(" Receptor : ")+ comprobante.getReceptor().getNombre());

		ArrayList<Concepto> conceptos = (ArrayList<Concepto>) comprobante.getConceptos().getConcepto();
		Iterator<Concepto> it = conceptos.iterator();
		Concepto c = null;
		StringBuffer sb = new StringBuffer();
		while(it.hasNext())
		{
			c=it.next();
			sb.append(padRight("  Desc: "));
			sb.append(c.getDescripcion());
			sb.append(", cant: ");
			sb.append(c.getCantidad());
			sb.append(", precio-unit: ");
			sb.append(c.getValorUnitario());
			sb.append(", importe: ");
			sb.append(c.getImporte());
			sb.trimToSize();
			System.out.println(padRight(sb.toString()));
			sb.setLength(0);
		}

		System.out.println(padRight(" SubTotal : ")+ comprobante.getSubTotal());

		if(comprobante.getImpuestos().getTotalImpuestosRetenidos() != null)
		{
			System.out.println(padRight(" TotalImpuestosRetenidos : ")+ comprobante.getImpuestos().getTotalImpuestosRetenidos());
		}
		if(comprobante.getImpuestos().getTotalImpuestosTrasladados() != null)
		{
			System.out.println(padRight(" TotalImpuestosTrasladados : ")+ comprobante.getImpuestos().getTotalImpuestosTrasladados());
		}
		System.out.println(padRight(" Total : ")+ comprobante.getTotal());

		System.out.println("--------");
		//System.out.println(" comprobante.: "+ comprobante.);
	}

	/*public static void toPrint22(sat.cfdv22.Comprobante comprobante) 
	{
		System.out.println("--------");

		System.out.println(padRight(" Folio: ")+comprobante.getFolio());
		System.out.println(padRight(" FormaDePago : ")+ comprobante.getFormaDePago());
		System.out.println(padRight(" MetodoDePago : ")+ comprobante.getMetodoDePago());
		System.out.println(padRight(" Fecha : ")+ comprobante.getFecha());
		System.out.println(padRight(" Emisor : ")+ comprobante.getEmisor().getNombre());
		System.out.println(padRight(" Receptor : ")+ comprobante.getReceptor().getNombre());

		sat.cfdv22.Comprobante.Conceptos conceptos = comprobante.getConceptos();
		ArrayList<sat.cfdv22.Comprobante.Conceptos.Concepto> listaconceptos = (ArrayList<sat.cfdv22.Comprobante.Conceptos.Concepto>) conceptos.getConcepto();
		Iterator<sat.cfdv22.Comprobante.Conceptos.Concepto> it = listaconceptos.iterator();
		sat.cfdv22.Comprobante.Conceptos.Concepto c = null;
		StringBuffer sb = new StringBuffer();
		while(it.hasNext())
		{
			c=it.next();
			sb.append(padRight("  Desc: "));
			sb.append(c.getDescripcion());
			sb.append(", cant: ");
			sb.append(c.getCantidad());
			sb.append(", precio-unit: ");
			sb.append(c.getValorUnitario());
			sb.append(", importe: ");
			sb.append(c.getImporte());
			sb.trimToSize();
			System.out.println(padRight(sb.toString()));
			sb.setLength(0);
		}

		System.out.println(padRight(" SubTotal : ")+ comprobante.getSubTotal());

		if(comprobante.getImpuestos().getTotalImpuestosRetenidos() != null)
		{
			System.out.println(padRight(" TotalImpuestosRetenidos : ")+ comprobante.getImpuestos().getTotalImpuestosRetenidos());
		}
		if(comprobante.getImpuestos().getTotalImpuestosTrasladados() != null)
		{
			System.out.println(padRight(" TotalImpuestosTrasladados : ")+ comprobante.getImpuestos().getTotalImpuestosTrasladados());
		}
		System.out.println(padRight(" Total : ")+ comprobante.getTotal());

		System.out.println("--------");
		//System.out.println(" comprobante.: "+ comprobante.);
	}*/

	public static void traceMsg(String msg)
	{
		if(DEBUG)
		{
			System.out.println("debug: "+msg);
		}
		else
		{
			System.out.println("trace: "+msg);
		}
	}

	public static String padRight(String s) 
	{
		return padRight(s, 30);
	}

	public static String padLeft(String s) 
	{
		return padLeft(s, 30);
	}
	
	public static String padRight(String s, int n) 
	{
		return String.format("%1$-" + n + "s", s);
	}

	// pad with " " to the left to the given length (n)
	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}
	
	public boolean printDetail() {
		return printDetail;
	}

	public void setPrintDetail(boolean printDetail) {
		this.printDetail = printDetail;
	}

	private static final ExecutorService DELETE_SERVICE = 
			//Executors.newSingleThreadExecutor()
			Executors.newFixedThreadPool(10);
			;

	private static void deleteFile(final File file) {
	    if (file != null) {
	        DELETE_SERVICE.submit(new Runnable() {
	            @Override
	            public void run() {
	            	try 
	            	{
	            		boolean deleted = file.delete();
	            		System.out.println(" deleting --> " + file.getName() + ", deleted: " + deleted );
	            	} catch(SecurityException e)
	            	{
	            		e.printStackTrace();
	            	}
	            }
	        });
	    }
	}

	public static void stopExecutorService()
	{
		if(DELETE_SERVICE != null)
		{
			DELETE_SERVICE.shutdown();
			try {
				if (!DELETE_SERVICE.awaitTermination(800, TimeUnit.MILLISECONDS)) {
					List<Runnable> notExecutedTasks = DELETE_SERVICE.shutdownNow();
					System.out.println("notExecutedTasks.size(): " + notExecutedTasks.size());
				} 
			} catch (InterruptedException e) {
				DELETE_SERVICE.shutdownNow();
			}
		}
		System.out.println("executorservice stopped!!!");
	}
	
	//IMPORTANT
	private static boolean DEBUG = false;
	public static int getYearFromDate(Date date) {
	    int result = -1;
	    if (date != null) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        result = cal.get(Calendar.YEAR);
	    }
	    return result;
	}
}
