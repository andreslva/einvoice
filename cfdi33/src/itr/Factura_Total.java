package itr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConstants;

import itr.algo.ComprobanteGrFactory;
import itr.exception.SAT_ITR_UUID_REPEATED_Exception;
import itr.exception.SAT_ITR_WRONG_MONTH_Exception;
//import itr.model.ComprobanteIssued;
import itr.pdf.ITR_PDFHandler;
import itr.sat.cfdi.v33.Comprobante;
import itr.sat.cfdi.v33.Comprobante.Complemento;
import itr.sat.cfdi.v33.Comprobante.Conceptos.Concepto;
import itr.sat.cfdi.v33.TimbreFiscalDigital;

public class Factura_Total {

	public static final int ENE01 = DatatypeConstants.JANUARY;
	public static final int FEB02 = DatatypeConstants.FEBRUARY;
	public static final int MAR03 = DatatypeConstants.MARCH;
	public static final int ABR04 = DatatypeConstants.APRIL;
	public static final int MAY05 = DatatypeConstants.MAY;
	public static final int JUN06 = DatatypeConstants.JUNE;
	public static final int JUL07 = DatatypeConstants.JULY;
	public static final int AGO08 = DatatypeConstants.AUGUST;
	public static final int SEP09 = DatatypeConstants.SEPTEMBER;
	public static final int OCT10 = DatatypeConstants.OCTOBER;
	public static final int NOV11 = DatatypeConstants.NOVEMBER;
	public static final int DIC12 = DatatypeConstants.DECEMBER;

	public static final int ALLYEAR = (DatatypeConstants.DECEMBER+1);//13;
	public static final boolean SORT = true;

	//private ArrayList<acuse.Acuse> cancelaciones;
	//private ArrayList<ComprobanteGR> canceladas;
	private ArrayList<itr.sat.cfdi.v33.Comprobante> facturas33;
	//private ArrayList<sat.cfdv22.Comprobante> facturas22;
	private BigDecimal subtotal,total;
	private BigDecimal impuestosRet, impuestosTras;
	private BigDecimal isrRet, ivaRet, iepsTras, ivaTras;

	private ArrayList<ComprobanteGR> facturasGR;
	private HashMap<String,ComprobanteGR> factMap;
	
	protected int month;
	private int yearNo;

	protected HashMap<Integer, Factura_Total> allmonths;
	
	protected ITR_PDFHandler pdfH;
	static final String NA = "n/a";
	
	//cvs file
	private boolean createCVSFile;
	private boolean checkDir;
	protected File csvFile;
	private Writer out = null;
	private FileOutputStream fos = null;
	protected BufferedWriter writer = null;
	private ComprobanteGrFactory cfdvxxObjFactory;
	private static final String Encoding = "Cp1252";

	
	public ComprobanteGrFactory getCfdvxxObjFactory() {
		return cfdvxxObjFactory;
	}

	public void setCfdvxxObjFactory(ComprobanteGrFactory cfdvxxObjFactory) {
		this.cfdvxxObjFactory = cfdvxxObjFactory;
	}

	public void setPdfH(ITR_PDFHandler pdfH) {
		this.pdfH = pdfH;
	}

	public ITR_PDFHandler getPdfH() {
		return this.pdfH;
	}

	public boolean createCVSFile() {
		return createCVSFile;
	}

	public boolean isCheckDir() {
		return checkDir;
	}

	public void setCheckDir(boolean checkDir) {
		this.checkDir = checkDir;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public void setCreateCVSFile(boolean createCVSFile) {
		this.createCVSFile = createCVSFile;
	}

	public static Factura_Total getYearInvoice()
	{
		Factura_Total y = new Factura_Total(ALLYEAR);
		return y;
	}
	
	public static Factura_Total getYearInvoice(String fileName, boolean createCSVFile) throws FileNotFoundException
	{
		Factura_Total y = new Factura_Total(fileName, createCSVFile);
		return y;
	}

	public static Factura_Total getYearInvoice(String fileName) throws FileNotFoundException
	{
		Factura_Total y = new Factura_Total(fileName);
		return y;
	}
	
	protected Factura_Total()
	{
		// cancelaciones = new ArrayList<acuse.Acuse>();
		//canceladas = new ArrayList<ComprobanteGR>();
		facturas33 = new ArrayList<itr.sat.cfdi.v33.Comprobante>();
		//facturas22 = new ArrayList<sat.cfdv22.Comprobante>();

		facturasGR = new ArrayList<ComprobanteGR>();
		factMap = new HashMap<String,ComprobanteGR>();
		
		subtotal = new BigDecimal(0);
		total = new BigDecimal(0);
		impuestosRet  = new BigDecimal(0);
		impuestosTras  = new BigDecimal(0);

		isrRet = new BigDecimal(0);
		ivaRet = new BigDecimal(0);
		iepsTras = new BigDecimal(0);
		ivaTras = new BigDecimal(0);

		setCheckDir(true);
		this.cfdvxxObjFactory = new ComprobanteGrFactory();
	}

	private Factura_Total(String fileName) throws FileNotFoundException
	{
		this(fileName, true);
	}

	private Factura_Total(String fileName, boolean withCSVFile) throws FileNotFoundException
	{
		this(ALLYEAR);
		//pdf
		this.pdfH = new ITR_PDFHandler(fileName);
		this.pdfH.openDocument();
		
		//cvs
		setCreateCVSFile(withCSVFile);
		if(createCVSFile())
		{
			StringBuffer cvsFullPath = new StringBuffer(this.pdfH.getRootPdfDir());
			cvsFullPath.append(fileName);
			cvsFullPath.append(".csv");
			csvFile = new File(cvsFullPath.toString());
			try
			{
				if ( csvFile.exists() )
				{
					StringBuffer cvs2 = new StringBuffer(this.pdfH.getRootPdfDir());
					cvs2.append(fileName);
					cvs2.append("_0");
					cvs2.append(".csv");
					csvFile = new File(cvs2.toString());
				}

				csvFile.createNewFile();
			}catch(IOException io)
			{
				csvFile = null;
			}

			initCsvFileVars();
		}
		
		if(this.allmonths != null)
		{
			Iterator<Factura_Total> it = this.allmonths.values().iterator();
			while(it.hasNext())
			{
				Factura_Total f = it.next();
				f.setPdfH(this.pdfH);
				f.setCreateCVSFile(withCSVFile);
				f.setWriter(this.writer);
			}
		}
	}
	
	private Factura_Total(int month)
	{
		this();
		this.month = month;

		switch(month)
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			break;
		case ALLYEAR:
			this.allmonths = new HashMap<Integer,Factura_Total>(13);
			this.allmonths.put(new Integer(ENE01), new Factura_Total(ENE01));
			this.allmonths.put(new Integer(FEB02), new Factura_Total(FEB02));
			this.allmonths.put(new Integer(MAR03), new Factura_Total(MAR03));
			this.allmonths.put(new Integer(ABR04), new Factura_Total(ABR04));
			this.allmonths.put(new Integer(MAY05), new Factura_Total(MAY05));
			this.allmonths.put(new Integer(JUN06), new Factura_Total(JUN06));
			this.allmonths.put(new Integer(JUL07), new Factura_Total(JUL07));
			this.allmonths.put(new Integer(AGO08), new Factura_Total(AGO08));
			this.allmonths.put(new Integer(SEP09), new Factura_Total(SEP09));
			this.allmonths.put(new Integer(OCT10), new Factura_Total(OCT10));
			this.allmonths.put(new Integer(NOV11), new Factura_Total(NOV11));
			this.allmonths.put(new Integer(DIC12), new Factura_Total(DIC12));
			break;
		default:
		}
		
	}

	public static final int IMP_RET_TOTAL 	= 100;
	public static final int IMP_RET_ISR 	= 200;
	public static final int IMP_RET_IVA 	= 300;
	public static final int IMP_TRAS_TOTAL 	= 400;
	public static final int IMP_TRAS_IEPS 	= 500;
	public static final int IMP_TRAS_IVA 	= 600;
	public static final int SUB_TOTAL 		= 700;
	public static final int TOTAL 			= 800;

	public BigDecimal getResume(int key) {
		
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = getInstanceValue(key);
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getResume(key));
			}
			break;
		default:
		}
		
		return r;
	}

	private BigDecimal getInstanceValue(int key) {
		BigDecimal r = new BigDecimal(0);
		switch (key) {
		case IMP_RET_TOTAL:
			r = impuestosRet;
			break;
		case IMP_RET_ISR:
			r = isrRet;
			break;
		case IMP_RET_IVA:
			r = ivaRet;
			break;
		case IMP_TRAS_TOTAL:
			r = impuestosTras;
			break;				
		case IMP_TRAS_IEPS:
			r = iepsTras;
			break;
		case IMP_TRAS_IVA:
			r = ivaTras;
			break;
		case SUB_TOTAL:
			r = subtotal;
			break;				
		case TOTAL:
			r = total;
			break;			
		default:
			break;
		}
		return r;
	}

	/*public BigDecimal getSubtotal() {
		
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = subtotal;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getSubtotal());
			}
			break;
		default:
		}
		
		return r;
	}*/

	/*public int getNoCancelaciones() {
		
		int n = 0;
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			n = cancelaciones.size();
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				n = n + f.cancelaciones.size();
			}
			break;
		default:
		}
		
		return n;
	}*/

	/*public int getNoCanceladas() {
		
		int n = 0;
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			n = canceladas.size();
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				n = n + f.canceladas.size();
			}
			break;
		default:
		}
		
		return n;
	}*/

	/*public BigDecimal getTotal() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = total;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getTotal());
			}
			break;
		default:
		}
		
		return r;
	}*/

	/*public BigDecimal getImpuestosRet() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = impuestosRet;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getImpuestosRet());
			}
			break;
		default:
		}
		
		return r;
	}*/

	/*public BigDecimal getISRRet() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = isrRet;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getISRRet());
			}
			break;
		default:
		}
		
		return r;
	}*/

	/*public BigDecimal getIVARet() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = ivaRet;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getIVARet());
			}
			break;
		default:
		}

		return r;
	}*/
	
	/*public BigDecimal getIEPSTras() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = iepsTras;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getIEPSTras());
			}
			break;
		default:
		}

		return r;
	}*/

	/*public BigDecimal getIVATras() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = ivaTras;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getIVATras());
			}
			break;
		default:
		}

		return r;
	}*/

	/*public BigDecimal getImpuestosRetTotales() {
		Iterator<Factura_Total> it = months.values().iterator();
		Factura_Total ft = null;
		BigDecimal ir = new BigDecimal(0);
		while(it.hasNext())
		{
			ft = it.next();
			ir = ir.add(ft.getImpuestosRet());
		}

		return ir;
	}*/

	/*public BigDecimal getImpuestosTras() {
		BigDecimal r = new BigDecimal(0);
		
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			r = impuestosTras;
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				r = r.add(f.getImpuestosTras());
			}
			break;
		default:
		}
		
		return r;
	}*/

	/*
	public BigDecimal getImpuestosTrasTotales() {
		Iterator<Factura_Total> it = months.values().iterator();
		Factura_Total ft = null;
		BigDecimal itr = new BigDecimal(0);
		while(it.hasNext())
		{
			ft = it.next();
			itr = itr.add(ft.getImpuestosTras());
		}
		return itr;
	}*/

	public void addComponent33(itr.sat.cfdi.v33.Comprobante c, int month, String xmlFile) throws SAT_ITR_WRONG_MONTH_Exception, SAT_ITR_UUID_REPEATED_Exception
	{
		if(month == ALLYEAR)
		{
			return;
		}

		HashSet<Factura_Total> both = new HashSet<Factura_Total>(2);
		Factura_Total m = getMonth(month);
		both.add(m);
		//NOOOO! both.add(this);

		for(Factura_Total x: both)
		{
			if(isCheckDir() && !JAXBXMLHandler.isMonthOK33(c, month, getYearNo()))
			{
				StringBuffer msg = new StringBuffer();
				msg.append("Factura32 mal ubicada : "+xmlFile);
				msg.append("\n month: " + month);
				msg.append("\n m  OK: " + c.getFecha().getMonth());
				msg.append("\n year  OK: " + c.getFecha().getYear());
				msg.append("\n y  OK: " + Integer.toString(getYearNo()));
				throw new SAT_ITR_WRONG_MONTH_Exception(msg.toString());
			}
			x.facturas33.add(c);
			ComprobanteGR cg =  getCfdvxxObjFactory().getComprobanteGRv33(c, xmlFile);
								//new ComprobanteGR(c,xmlFile);
			x.facturasGR.add(cg);//FACTURASGR
			
			//List<Object> comp = c.getComplemento().getAny();
			//cTree.put(c.getComplemento().getAny(), cg);

			x.subtotal = x.subtotal.add(c.getSubTotal());
			x.total = x.total.add(c.getTotal());

			x.impuestosRet = x.impuestosRet.add(cg.getTotalImpuestosRetenidos());	

			x.impuestosTras = x.impuestosTras.add(cg.getTotalImpuestosTrasladados());	

			x.isrRet =  x.isrRet.add(cg.getIsrRet());
			x.ivaRet = x.ivaRet.add(cg.getIvaRet());
			x.iepsTras = x.iepsTras.add(cg.getIepsTras());
			x.ivaTras = x.ivaTras.add(cg.getIvaTras());

			//String uuid = c.getComplemento().getTimbreFiscalDigital().getUUID();
			List<itr.sat.cfdi.v33.Comprobante.Complemento> listCompl = c.getComplemento();
			Iterator<itr.sat.cfdi.v33.Comprobante.Complemento> itCompl = listCompl.iterator();
			TimbreFiscalDigital timbre = null;
			while(itCompl.hasNext())
			{
				Complemento compl =  itCompl.next();
				timbre = compl.getTimbreFiscalDigital();
				break;
			}
			String uuid = xmlFile;//timbre.getUUID();
			
			if(factMap.containsKey(uuid))
			{
				throw new SAT_ITR_UUID_REPEATED_Exception(MSG_UUID_REPEATED+uuid);
			}
			factMap.put(uuid, cg);
		}
	}

	/*public void addCancelada(acuse.Acuse c, int month, String xmlFile) throws SAT_ITR_WRONG_MONTH_Exception
	{
		if(month == ALLYEAR)
		{
			return;
		}

		if(!JAXBXMLHandler.isCANMonthOK(c, month))
		{
			StringBuffer msg = new StringBuffer();
			msg.append("Factura CANCELADA mal ubicada : "+xmlFile);
			msg.append("\n month: " + month);
			msg.append("\n c  OK: " + c.getFecha().getMonth());
			throw new SAT_ITR_WRONG_MONTH_Exception(msg.toString());
		}

		//cancelaciones
		Factura_Total x = getMonth(month);
		x.cancelaciones.add(c);
		
		// canceladas
		ComprobanteGR cg = null;
		List<Folios> folio = c.getFolios();
		Iterator<Folios> it = folio.iterator();
		while(it.hasNext())
		{
			Folios f = it.next();
			cg = factMap.get(f.getUUID());
			if(cg!=null)
			{
				cg.setCancelled(true);
				break;
			}
		}

		int monthCancelada = cg.getFecha().getMonth();
		x = getMonth(monthCancelada);
		x.canceladas.add(cg);
		//quitar del calculo
		x.subtotal = x.subtotal.subtract(cg.getSubTotal());
		x.total = x.total.subtract(cg.getTotal());

		x.impuestosRet = x.impuestosRet.subtract(cg.getTotalImpuestosRetenidos());	

		x.impuestosTras = x.impuestosTras.subtract(cg.getTotalImpuestosTrasladados());

		x.isrRet =  x.isrRet.subtract(cg.getIsrRet());
		x.ivaRet = x.ivaRet.subtract(cg.getIvaRet());
		x.iepsTras = x.iepsTras.subtract(cg.getIepsTras());
		x.ivaTras = x.ivaTras.subtract(cg.getIvaTras());
	}*/
	
	/*public void addComponent22(sat.cfdv22.Comprobante c, int month, String xmlFile) throws SAT_ITR_WRONG_MONTH_Exception, SAT_ITR_UUID_REPEATED_Exception
	{
		if(month == ALLYEAR)
		{
			return;
		}

		HashSet<Factura_Total> both = new HashSet<Factura_Total>(2);
		Factura_Total m = getMonth(month);
		both.add(m);
		//NOOOO! both.add(this);

		for(Factura_Total x: both)
		{
			if(isCheckDir() && !JAXBXMLHandler.isMonthOK22(c, month, getYearNo()))
			{
				StringBuffer msg = new StringBuffer();
				msg.append("Factura22 mal ubicada : "+xmlFile);
				msg.append("\n month: " + month);
				msg.append("\n m  OK: " + c.getFecha().getMonth());
				msg.append("\n year  OK: " + c.getFecha().getYear());
				msg.append("\n y  OK: " + Integer.toString(getYearNo()));
				throw new SAT_ITR_WRONG_MONTH_Exception(msg.toString());
			}
			x.facturas22.add(c);
			ComprobanteGR cg =   getCfdvxxObjFactory().getComprobanteGRv22(c, xmlFile);
							   //new ComprobanteGR(c,xmlFile);
			x.facturasGR.add(cg);//FACTURASGR

			//List<Object> comp = c.getComplemento().getAny();
			//cTree.put(c.getComplemento().getAny(), cg);

			x.subtotal = x.subtotal.add(c.getSubTotal());
			x.total = x.total.add(c.getTotal());
			if(c.getImpuestos().getTotalImpuestosRetenidos() != null)
			{
				x.impuestosRet = x.impuestosRet.add(c.getImpuestos().getTotalImpuestosRetenidos());	
			}

			if(c.getImpuestos().getTotalImpuestosTrasladados() != null)
			{
				x.impuestosTras = x.impuestosTras.add(c.getImpuestos().getTotalImpuestosTrasladados());	
			}

			x.isrRet =  x.isrRet.add(cg.getIsrRet());
			x.ivaRet = x.ivaRet.add(cg.getIvaRet());
			x.iepsTras = x.iepsTras.add(cg.getIepsTras());
			x.ivaTras = x.ivaTras.add(cg.getIvaTras());

			String uuid = c.getComplemento().getTimbreFiscalDigital().getUUID();
			if(factMap.containsKey(uuid))
			{
				throw new SAT_ITR_UUID_REPEATED_Exception(MSG_UUID_REPEATED+uuid);
			}
			factMap.put(uuid, cg);
		}
	}*/

	private static final String MSG_UUID_REPEATED = "--- ERROR!!!! Factura already exists!!!: ";
	
	/*private void addcheckCompGR(Factura_Total ft, ComprobanteGR cgr)
	{
		JAXBXMLHandler.isMonthOK32(c, month);
	}*/
	
	public Factura_Total getMonth(int month) {

		Factura_Total x = null;
		switch(month)
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			x = allmonths.get(new Integer(month));
			break;
		case ALLYEAR:
			break;
		default:
			break;
		}
		return x;
	}

	public String toStringCompGRs()
	{
		if(SORT){
			Collections.sort(this.facturasGR);
		}
		Iterator<ComprobanteGR> it = this.facturasGR.iterator();
		//itr.sat.cfdi.v33.Comprobante cgr = null;
		ComprobanteGR cgr = null;
		int cnt = 0;
		
		StringBuffer sb = new StringBuffer();
		StringBuffer csv = new StringBuffer();
		while(it.hasNext())
		{
			cnt++;
			cgr = it.next();
			sb.append(toPrintCompGR(cnt,cgr));
			csv.append(toCVSFileData(cnt, cgr, cgr.getXmlFile()));
		}
		
		if(this.pdfH != null)
		{
			this.pdfH.writeDocument(sb.toString());
		}
		
		if(createCVSFile())
		{
			try
			{
				writer.write(csv.toString());
			} catch(IOException e)
			{
				e.printStackTrace();
			}finally
			{
				try {
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

	/*public List<ComprobanteIssued> persistCompGRs()
	{
		if(SORT){
			Collections.sort(this.facturasGR);
		}
		Iterator<ComprobanteGR> it = this.facturasGR.iterator();
		ComprobanteGR cgr = null;
		int cnt = 0;
		
		StringBuffer sb = new StringBuffer();
		StringBuffer csv = new StringBuffer();
		ArrayList<ComprobanteIssued> persistList = new ArrayList<ComprobanteIssued>(this.facturasGR.size());
		while(it.hasNext())
		{
			cnt++;
			cgr = it.next();
			ComprobanteIssued cjpa = new ComprobanteIssued(cgr.getComprobante());
			persistList.add(cjpa);
			sb.append(toPrintCompGR(cnt,cgr));
			csv.append(toCVSFileData(cnt,cgr));
		}
		
		if(this.pdfH != null)
		{
			this.pdfH.writeDocument(sb.toString());
		}
		
		if(createCVSFile())
		{
			try
			{
				writer.write(csv.toString());
			} catch(IOException e)
			{
				e.printStackTrace();
			}finally
			{
				try {
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return persistList;//sb.toString();
	}*/

	protected void initCsvFileVars() {
		if(createCVSFile())
		{
			try {
				if (fos == null)
				{ 

					fos = new FileOutputStream(this.csvFile);
				}
				if(out == null)
				{
					out = new OutputStreamWriter(fos, Encoding);
				}
				if(writer == null)
				{
					writer = new BufferedWriter( out );
				}

				writer.write(toCVSFileHeader());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String toStringResMen()
	{
		return toStringResMen(false);
	}
	
	public String toStringResMen(boolean printDetail)
	{
		if(getPdfH() == null)
		{
			return "toStringResMen, getPdfH() == null";
		}

		StringBuffer sb = new StringBuffer("\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("periodo: ")+ toMonthStr(getMonth())+"\n");
		
		int no_facturas = 0;
		switch(this.getMonth())
		{
		case ENE01:
		case FEB02:
		case MAR03:
		case ABR04:
		case MAY05:
		case JUN06:
		case JUL07:
		case AGO08:
		case SEP09:
		case OCT10:
		case NOV11:
		case DIC12:
			no_facturas = (this.facturas33.size()+this.facturas33.size());
			break;
		case ALLYEAR:
			for(Factura_Total f: this.allmonths.values())
			{
				no_facturas += (f.facturas33.size()+f.facturas33.size());
			}
			break;
		default:
		}
		
		sb.append(getPdfH().getCOL00RightPaddedStr("# facturas: ")+ no_facturas+"\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("subtotal: ")	+ getResume(SUB_TOTAL) /*getSubtotal()*/			+"\n");
		//ret
		sb.append(getPdfH().getCOL00RightPaddedStr("Imp ret: ")		+ getResume(IMP_RET_TOTAL) /*getImpuestosRet()*/		+"\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("->ISR ret: ")	+ getResume(IMP_RET_ISR) /*getISRRet()*/			+"\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("->IVA ret: ")	+ getResume(IMP_RET_IVA) /*getIVARet()*/			+"\n");
		//tras
		sb.append(getPdfH().getCOL00RightPaddedStr("Imp  tras: ")	+ getResume(IMP_TRAS_TOTAL) /*getImpuestosTras()*/	+"\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("->IEPS tras: ")	+ getResume(IMP_TRAS_IEPS) /*getIEPSTras()*/			+"\n");
		sb.append(getPdfH().getCOL00RightPaddedStr("->IVA  tras: ")	+ getResume(IMP_TRAS_IVA) /*getIVATras()*/			+"\n");

		sb.append(getPdfH().getCOL00RightPaddedStr("total: ")		+ getResume(TOTAL) /*getTotal()*/			+"\n");
		
		// sb.append(getPdfH().getCOL00RightPaddedStr("canceladas: ")+ getNoCanceladas()+"\n");
		// sb.append(getPdfH().getCOL00RightPaddedStr("cancelaciones: ")+ getNoCancelaciones());
		sb.append("\n");
		
		if(this.pdfH != null)
		{
			this.pdfH.writeDocument(sb.toString());
			if(printDetail)
			{
				this.pdfH.newPage();
			}
		}
		
		return sb.toString();
	}

	public int getMonth() {
		return month;
	}

	private String toMonthStr(int month)
	{
		String s = null;
		
		switch(month)
		{
		case ENE01:
			s = "ENERO";
			break;
		case FEB02:
			s = "FEBRERO";
			break;
		case MAR03:
			s = "MARZO";
			break;
		case ABR04:
			s = "ABRIL";
			break;
		case MAY05:
			s = "MAYO";
			break;
		case JUN06:
			s = "JUNIO";
			break;
		case JUL07:
			s = "JULIO";
			break;
		case AGO08:
			s = "AGOSTO";
			break;
		case SEP09:
			s = "SEPTIEMBRE";
			break;
		case OCT10:
			s = "OCTUBRE";
			break;
		case NOV11:
			s = "NOVIEMBRE";
			break;
		case DIC12:
			s = "DICIEMBRE";
			break;
		case ALLYEAR:
			/*Calendar c = new GregorianCalendar();
			int year 		= c.get(GregorianCalendar.YEAR);*/

			s =
				Integer.toString(getYearNo());
				//Integer.toString(year)
			;
			break;
		default:
		}
		
		return s;
	}

	public void closePDF()
	{
		if(this.pdfH != null)
		{
			this.pdfH.closeDocument();
		}
		
		//csv
		if(!createCVSFile())
		{
			return;
		}

		try {
			writer.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static final String Sep				= ",";
	
	private static final String F00_Num			= "Num";
	private static final String F10_Arch 		= "Archivo_xml";
	private static final String F20_Fecha		= "Fecha";
	private static final String F30_Mes			= "Mes";
	private static final String F31_Mes_No		= "Mes_No";
	private static final String F40_Metod_Pago	= "Met_Pago";
	private static final String F50_Nom_Emisor	= "Nom_Emisor";
	private static final String F60_RFC_Emisor	= "RFC_Emisor";

	private static final String F50_Nom_Recep	= "Nom_Receptor";
	private static final String F60_RFC_Recep	= "RFC_Receptor";

	private static final String F70_SubTotal	= "SubTotal";

	//
	private static final String F80_ImpRet		= "Imp_Retenidos";
	private static final String F81_ISR_Ret		= "ISR_Retenido";
	private static final String F82_IVA_Ret		= "IVA_Retenido";

	//
	private static final String F90_ImpTras		= "Imp_Trasladados";
	private static final String F91_IEPS_Tras	= "IEPS_Trasladado";
	private static final String F92_IVA_Tras	= "IVA_Trasladado";

	private static final String F100_Total		= "Total";
	private static final String F110_Cancel		= "Cancelada";

	protected String toCVSFileHeader()
	{
		StringBuffer header = new StringBuffer();
		
		header.append(F00_Num);
		header.append(Sep);
		header.append(F10_Arch);
		header.append(Sep);
		header.append(F20_Fecha);
		header.append(Sep);
		header.append(F30_Mes);
		header.append(Sep);
		header.append(F31_Mes_No);
		header.append(Sep);
		header.append(F40_Metod_Pago);
		header.append(Sep);
		header.append(F50_Nom_Emisor);
		header.append(Sep);
		header.append(F60_RFC_Emisor);
		header.append(Sep);

		header.append(F50_Nom_Recep);
		header.append(Sep);
		header.append(F60_RFC_Recep);
		header.append(Sep);
		
		header.append(F70_SubTotal);
		header.append(Sep);

		header.append(F80_ImpRet);
		header.append(Sep);
		header.append(F81_ISR_Ret);
		header.append(Sep);
		header.append(F82_IVA_Ret);
		header.append(Sep);
		
		header.append(F90_ImpTras);
		header.append(Sep);
		header.append(F91_IEPS_Tras);
		header.append(Sep);
		header.append(F92_IVA_Tras);
		header.append(Sep);

		header.append(F100_Total);
		header.append(Sep);
		header.append(F110_Cancel);
		header.append("\n");
		
		return header.toString();
	}
	
	public String toCVSFileData(int pos, ComprobanteGR comprobante, String xmlFileName) 
	{
		StringBuffer r = new StringBuffer();
		
		r.append(pos+Sep);
		r.append(comprobante.getXmlFile()+Sep);
		
		//begin
		//String  sFolio 		= comprobante.getFolio() != null ? comprobante.getFolio().trim() : NA;
		//String  sTipo 		= comprobante.getTipoDeComprobante() != null ? comprobante.getTipoDeComprobante().trim() : NA;
		//String  sFormaPago 	= comprobante.getFormaDePago() != null ? comprobante.getFormaDePago().trim() : NA;
		String  sMetPago 	= comprobante.getMetodoDePago() != null ? comprobante.getMetodoDePago().trim() : NA;

		String  sFecha 		= comprobante.getFecha() != null ? comprobante.getFecha().toString() : NA;
		int month = comprobante.getFecha().getMonth();
		String monthStr = getCurrMonthStr(month);

		String sEmisorNom = strWithQuotes(comprobante.getEmisor().getNombre() != null ? comprobante.getEmisor().getNombre().trim() : NA);
		String  sEmisorRFC 	= comprobante.getEmisor().getRfc() != null ? comprobante.getEmisor().getRfc().trim() : NA;

		String  sRecep 		= strWithQuotes(comprobante.getReceptor().getNombre() != null ? comprobante.getReceptor().getNombre().trim() : NA);
		String  sRecepRFC	= comprobante.getReceptor().getRfc() != null ? comprobante.getReceptor().getRfc().trim() : NA;
		//end
		
		//new
		//r.append(getPdfH().getCOL00RightPaddedStr(" Folio: ")+sFolio+",");
		//r.append(getPdfH().getCOL00RightPaddedStr(" Tipo: ")+sTipo+",");
		//r.append(getPdfH().getCOL00RightPaddedStr(" FormaDePago : ")+sFormaPago+",");
		r.append(sFecha+Sep);
		r.append(monthStr+Sep);
		r.append(month+Sep);
		r.append(sMetPago+Sep);
		r.append(sEmisorNom+Sep);
		r.append(sEmisorRFC+Sep);
		r.append(sRecep+Sep);
		r.append(sRecepRFC+Sep);

		/*ArrayList<Concepto> conceptos = (ArrayList<Concepto>) comprobante.getConceptos().getConcepto();
		if(conceptos.size() != 0)
		{
			r.append(getPdfH().getCOL00RightPaddedStr("Desc: ")+"\n");
			Iterator<Concepto> it = conceptos.iterator();
			Concepto c = null;

			int i = 0;
			while(it.hasNext())
			{
				c=it.next();
				i++;
				StringBuffer sb = new StringBuffer(" ");
				sb.append(i);
				sb.append(".- ");
				sb.append(c.getDescripcion());
				sb.append(", cant: ");
				sb.append(c.getCantidad());
				sb.append(", precio-unit: ");
				sb.append(c.getValorUnitario());
				sb.append(", importe: ");
				sb.append(c.getImporte());
				sb.trimToSize();
				r.append(sb.toString()+"\n");
			}
		}*/
		
		r.append(comprobante.getSubTotal()+Sep);
	
		r.append(comprobante.getTotalImpuestosRetenidos()+Sep);
		//begin
		r.append(comprobante.getIsrRet()+Sep);
		r.append(comprobante.getIvaRet()+Sep);
		//end

		r.append(comprobante.getTotalImpuestosTrasladados()+Sep);
		//begin
		r.append(comprobante.getIepsTras()+Sep);
		r.append(comprobante.getIvaTras()+Sep);
		//end

		r.append(comprobante.getTotal()+Sep);
		
		if(comprobante.isCancelled()){
			r.append("CANCELADA");
		}

		r.append("\n");

		return r.toString();
	}	

	private static String strWithQuotes(String s)
	{
		StringBuffer n = new StringBuffer("\"");
		n.append(s);
		n.append("\"");
		return n.toString();
	}
	
	public String toPrintCompGR(int pos, ComprobanteGR comprobante) 
	{
		StringBuffer r = new StringBuffer();
		
		r.append(pos+" File Name = "+comprobante.getXmlFile()+"\n");
		if(comprobante.isCancelled()){
			r.append(" ¡¡¡¡¡¡¡¡¡¡¡  CANCELADA !!!!!!!!!!!!!!! \n");
		}
	
		//r.append("--------"+"\n");
	
		//r.append(padRight(pos+" xmlFile: ")+comprobante.getXmlFile()+"\n");
		
		//begin
		String  sFolio 		= comprobante.getFolio() != null ? comprobante.getFolio().trim() : NA;
		String  sTipo 		= comprobante.getTipoDeComprobante() != null ? comprobante.getTipoDeComprobante().trim() : NA;
		String  sFormaPago 	= comprobante.getFormaDePago() != null ? comprobante.getFormaDePago().trim() : NA;
		String  sMetPago 	= comprobante.getMetodoDePago() != null ? comprobante.getMetodoDePago().trim() : NA;
		String  sFecha 		= comprobante.getFecha() != null ? comprobante.getFecha().toString() : NA;
		String  sEmisorNom 	= comprobante.getEmisor().getNombre() != null ? comprobante.getEmisor().getNombre().trim() : NA;
		String  sEmisorRFC 	= comprobante.getEmisor().getRfc() != null ? comprobante.getEmisor().getRfc().trim() : NA;
		String  sRecep 		= comprobante.getReceptor().getNombre() != null ? comprobante.getReceptor().getNombre().trim() : NA;
		String  sRecepRFC	= comprobante.getReceptor().getRfc() != null ? comprobante.getReceptor().getRfc().trim() : NA;
		//end
		
		/*orig
		 * r.append(padRight(" Folio: ")+comprobante.getFolio().trim()+"\n");
		r.append(padRight(" Tipo: ")+comprobante.getTipoDeComprobante().trim()+"\n");
		r.append(padRight(" FormaDePago : ")+ comprobante.getFormaDePago().trim()+"\n");
		r.append(padRight(" MetodoDePago : ")+ comprobante.getMetodoDePago().trim()+"\n");
		r.append(padRight(" Fecha : ")+ comprobante.getFecha()+"\n");
		r.append(padRight(" Emisor : ")+ comprobante.getEmisor().getNombre().trim()+"\n");
		r.append(padRight(" Emisor RFC: ")+ comprobante.getEmisor().getRfc().trim()+"\n");
		r.append(padRight(" Receptor : ")+ comprobante.getReceptor().getNombre().trim()+"\n");*/
		//new
		r.append(getPdfH().getCOL00RightPaddedStr(" Folio: ")+sFolio+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Tipo: ")+sTipo+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" FormaDePago : ")+sFormaPago+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" MetodoDePago : ")+sMetPago+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Fecha : ")+sFecha+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Emisor : ")+sEmisorNom+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Emisor RFC: ")+sEmisorRFC+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Receptor : ")+sRecep+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr(" Receptor RFC : ")+sRecepRFC+"\n");

		ArrayList<Concepto> conceptos = (ArrayList<Concepto>) comprobante.getConceptos().getConcepto();
		if(conceptos.size() != 0)
		{
			r.append(getPdfH().getCOL00RightPaddedStr("Desc: ")+"\n");
			Iterator<Concepto> it = conceptos.iterator();
			Concepto c = null;

			int i = 0;
			while(it.hasNext())
			{
				c=it.next();
				i++;
				StringBuffer sb = new StringBuffer(" ");
				sb.append(i);
				sb.append(".- ");
				sb.append(c.getDescripcion());
				sb.append(", cant: ");
				sb.append(c.getCantidad());
				sb.append(", precio-unit: ");
				sb.append(c.getValorUnitario());
				sb.append(", importe: ");
				sb.append(c.getImporte());
				sb.trimToSize();
				r.append(sb.toString()+"\n");
			}
		}
		
		r.append(getPdfH().getCOL00RightPaddedStr("SubTotal : ")+ comprobante.getSubTotal()+"\n");
	
		r.append(getPdfH().getCOL00RightPaddedStr("TotalImpuestosRetenidos : ")+ comprobante.getTotalImpuestosRetenidos()+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr("ISR retenido : ")+ comprobante.getIsrRet()+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr("IVA retenido : ")+ comprobante.getIvaRet()+"\n");

		r.append(getPdfH().getCOL00RightPaddedStr("TotalImpuestosTrasladados : ")+ comprobante.getTotalImpuestosTrasladados()+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr("IEPS trasladado : ")+ comprobante.getIepsTras()+"\n");
		r.append(getPdfH().getCOL00RightPaddedStr("IVA  trasladado : ")+ comprobante.getIvaTras()+"\n");

		r.append(getPdfH().getCOL00RightPaddedStr("Total : ")+ comprobante.getTotal()+"\n");

		r.append("--------------------------------------------------------------------"+"\n");
		//System.out.println(" comprobante.: "+ comprobante.);

		return r.toString();
	}
	
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
	
	protected static String getCurrMonthStr(int month) {
		String mes = "N/A";
		switch (month) {
		case ENE01:
			mes = MES00_ENE;
			break;
		case FEB02:
			mes = MES01_FEB;
			break;
		case MAR03:
			mes = MES02_MAR;
			break;
		case ABR04:
			mes = MES03_AVR;
			break;
		case MAY05:
			mes = MES04_MAI;
			break;
		case JUN06:
			mes = MES05_JUN;
			break;
		case JUL07:
			mes = MES06_JUL;
			break;
		case AGO08:
			mes = MES07_AGO;
			break;
		case SEP09:
			mes = MES08_SEP;
			break;
		case OCT10:
			mes = MES09_OCT;
			break;
		case NOV11:
			mes = MES10_NOV;
			break;
		case DIC12:
			mes = MES11_DIC;
			break;
		default:
			break;
		}
		
		return mes;
	}

	public int getYearNo() {
		return yearNo;
	}

	public void setYearNo(int yearTitle) {
		this.yearNo = yearTitle;
	}

	public void printPDFErrorMsg(String msg)
	{
		if(this.pdfH != null)
		{
			this.pdfH.writeDocument(msg);
		}
	}
	
	private String getPDFPaddedRightStr(final String s)
	{
		String r = null;
		
		
		
		
		return r;
	}
}
