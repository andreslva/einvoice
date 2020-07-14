package itr;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import itr.algo.ComprobanteGrFactory;
/*import itr.sat.cfdvxx.ComplementoInterface;
import itr.sat.cfdvxx.ComprobanteImpl;
import itr.sat.cfdvxx.ComprobanteInterface;
import itr.sat.cfdvxx.ConceptosInterface;
import itr.sat.cfdvxx.EmisorInterface;
import itr.sat.cfdvxx.ReceptorInterface;
import itr.sat.cfdvxx.RegimenFiscalInterface;
import itr.sat.cfdvxx.RetencionInterface;
import itr.sat.cfdvxx.RetencionesInterface;
import itr.sat.cfdvxx.TrasladoInterface;
import itr.sat.cfdvxx.TrasladosInterface;*/
/*import sat.cfdv22.Comprobante.Complemento;
import sat.cfdv22.Comprobante.Emisor.RegimenFiscal;
import sat.cfdv22.TUbicacion;
import sat.cfdv22.TUbicacionFiscal;
import sat.cfdv32.Comprobante;
import sat.cfdv32.Comprobante.Conceptos;
import sat.cfdv32.Comprobante.Conceptos.Concepto;
import sat.cfdv32.Comprobante.Emisor;
import sat.cfdv32.Comprobante.Receptor;
import sat.cfdv32.ObjectFactory;
import sat.cfdv32.TimbreFiscalDigital;*/

public class ComprobanteGR implements Comparable<ComprobanteGR>{

	public static final int VER33 = 33;
	// public static final int VER32 = 32;
	// public static final int VER22 = 22;

	public static final String ISR_Ret		= "ISR";
	public static final String IVA_Ret		= "IVA";
	public static final String IEPS_Tras	= "IEPS";
	public static final String IVA_Tras		= "IVA";

	// Getting a default locale object
	//private static final Locale locale = Locale.getDefault();

	public static final BigDecimal DEF_BIGDECIMAL = new BigDecimal(0);

	private itr.sat.cfdi.v33.ObjectFactory cfdv33ObjFactory;
	private itr.sat.cfdi.v33.Comprobante comprobante;
	private int ver;
	private String xmlFile;
	private boolean isCancelled;

	private BigDecimal isrRet;
	private BigDecimal ivaRet;
	private BigDecimal iepsTras;
	private BigDecimal ivaTras;

	public ComprobanteGR() {
		super();
	}

	//v32
	public ComprobanteGR(itr.sat.cfdi.v33.Comprobante comprobante, String xmlFile) {
		this();
		this.comprobante = comprobante;
		this.ver = VER33;
		this.xmlFile = xmlFile;
		setRetenciones(comprobante.getImpuestos());
		setTraslados(comprobante.getImpuestos());
	}

	//private void setRetenciones(sat.cfdv32.Comprobante.Impuestos is32) {
	private void setRetenciones(itr.sat.cfdi.v33.Comprobante.Impuestos is33) {

		boolean calculate = is33.getRetenciones() != null;
		if(!calculate)
		{
			setIsrRet(DEF_BIGDECIMAL);
			setIvaRet(DEF_BIGDECIMAL);
			return;
		}

		itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones ret = is33.getRetenciones();
		List<itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion> listRet = ret.getRetencion();
		Iterator<itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion> itRet = listRet.iterator();
		while(itRet.hasNext())
		{
			itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion retencion = itRet.next();
			BigDecimal importe = retencion.getImporte();
			String impuesto = retencion.getImpuesto();
			if(impuesto.equals(ISR_Ret))
			{
				setIsrRet(importe);
			} else if (impuesto.equals(IVA_Ret))
			{
				setIvaRet(importe);
			}
		}
		return;
	}

	//private void setTraslados(sat.cfdv32.Comprobante.Impuestos is32) {
	private void setTraslados(itr.sat.cfdi.v33.Comprobante.Impuestos is33) {

		boolean calculate = is33.getTraslados() != null;
		if(!calculate)
		{
			setIepsTras(DEF_BIGDECIMAL);
			setIvaTras(DEF_BIGDECIMAL);
			return;
		}

		itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados tras = is33.getTraslados();
		List<itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado> listTras = tras.getTraslado();
		Iterator<itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado> itTras = listTras.iterator();
		while(itTras.hasNext())
		{
			itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado traslado = itTras.next();
			BigDecimal importe = traslado.getImporte();
			String impuesto = traslado.getImpuesto();
			BigDecimal tasa = traslado.getTasaOCuota();
			if(impuesto.equals(IEPS_Tras))
			{
				setIepsTras(importe);
			} else if (impuesto.equals(IVA_Tras))
			{
				setIvaTras(importe);
			}
		}
		return;
	}

	//v22
	/*public ComprobanteGR(itr.sat.cfdi.v33.Comprobante c, String xmlFile) {
		super();
		this.ver = VER33;
		this.xmlFile = xmlFile;
		
		setCfdv32ObjFactory(new ObjectFactory());
		this.comprobante = (ComprobanteImpl)getCfdv32ObjFactory().createComprobante();//new sat.cfdv32.Comprobante();

		this.comprobante.setVersion(c.getVersion());
		this.comprobante.setSerie(c.getSerie());;
		this.comprobante.setFolio(c.getFolio());
		this.comprobante.setFecha(c.getFecha());
		this.comprobante.setSello(c.getSello());
		this.comprobante.setFormaDePago(c.getFormaDePago());
		this.comprobante.setNoCertificado(c.getNoCertificado());
		this.comprobante.setCertificado(c.getCertificado());
		this.comprobante.setCondicionesDePago(c.getCondicionesDePago());
		this.comprobante.setSubTotal(c.getSubTotal());
		this.comprobante.setDescuento(c.getDescuento());
		this.comprobante.setMotivoDescuento(c.getMotivoDescuento());
		this.comprobante.setTipoCambioStr(c.getTipoCambio());
		this.comprobante.setMonedaStr(c.getMoneda());
		this.comprobante.setTotal(c.getTotal());
		this.comprobante.setTipoDeComprobanteStr(c.getTipoDeComprobante());
		this.comprobante.setMetodoDePago(c.getMetodoDePago());
		this.comprobante.setLugarExpedicion(c.getLugarExpedicion());
		this.comprobante.setNumCtaPago(c.getNumCtaPago());
		this.comprobante.setFolioFiscalOrig(c.getFolioFiscalOrig());
		this.comprobante.setFechaFolioFiscalOrig(c.getFechaFolioFiscalOrig());
		this.comprobante.setMontoFolioFiscalOrig(c.getMontoFolioFiscalOrig());
		
		sat.cfdv32.Comprobante.Emisor e = getCfdv32ObjFactory().createComprobanteEmisor();
		//new sat.cfdv32.Comprobante.Emisor();
		copyEmisor22to32(c.getEmisor(), e, getCfdv32ObjFactory());
		//e.setNombre(c.getEmisor().getNombre());
		//e.setRfc(c.getEmisor().getRfc());
		this.comprobante.setEmisor(e);
		
		sat.cfdv32.Comprobante.Receptor r = getCfdv32ObjFactory().createComprobanteReceptor();
		//new sat.cfdv32.Comprobante.Receptor();
		copyReceptor22to32(c.getReceptor(), r, getCfdv32ObjFactory());
		//r.setNombre(c.getReceptor().getNombre());
		//r.setRfc(c.getReceptor().getRfc());
		this.comprobante.setReceptor(r);
		
		sat.cfdv32.Comprobante.Conceptos css32 = copyConceptos(c.getConceptos());
		this.comprobante.setConceptos(css32);
		
		sat.cfdv22.Comprobante.Impuestos is22 = c.getImpuestos();
		// Impuestos is32 = new Impuestos();
		// is32.setTotalImpuestosRetenidos(is22.getTotalImpuestosRetenidos());
		// is32.setTotalImpuestosTrasladados(is22.getTotalImpuestosTrasladados());
		sat.cfdv32.Comprobante.Impuestos is32 = copyImpuestos(is22);
		this.comprobante.setImpuestos(is32);

		Comprobante.Complemento complemento = copyComplemento(c.getComplemento());
		//orig: this.comprobante.setComplemento(complemento);
		ArrayList<ComplementoInterface> complLst = new ArrayList<ComplementoInterface>();
		complLst.add(complemento);
		this.comprobante.setComplementoList(complLst);
	}*/

	/*private Comprobante.Complemento copyComplemento(Complemento complemento22) {
		//Complemento complemento22 =  c.getComplemento();
		TimbreFiscalDigital timbreFiscal = getCfdv32ObjFactory().createTimbreFiscalDigital();
		sat.cfdv22.Comprobante.Complemento.TimbreFiscalDigital timbreFiscal22 = complemento22.getTimbreFiscalDigital();
		timbreFiscal.setNoCertificadoSAT(timbreFiscal22.getNoCertificadoSAT());
		timbreFiscal.setFechaTimbrado(timbreFiscal22.getFechaTimbrado());
		timbreFiscal.setSelloCFD(timbreFiscal22.getSelloCFD());
		timbreFiscal.setSelloSAT(timbreFiscal22.getSelloSAT());
		timbreFiscal.setUUID(timbreFiscal22.getUUID());
		timbreFiscal.setVersion(timbreFiscal22.getVersion());

		Comprobante.Complemento complemento = getCfdv32ObjFactory().createComprobanteComplemento();
		complemento.setTimbreFiscalDigital(timbreFiscal);
		return complemento;
		//this.comprobante.setComplemento(complemento);
	}

	private Conceptos copyConceptos(sat.cfdv22.Comprobante.Conceptos cs22)
	{
		Conceptos cs32 = new Conceptos();
		ArrayList<Concepto> css32 = (ArrayList<Concepto>) cs32.getConcepto();
		if(css32 != null)
		{
			css32.clear();
		}
		//css32 = new ArrayList<Concepto>();ERRORR!!!
		Concepto c32;
		////
		//sat.cfdv22.Comprobante.Conceptos cs22 = c.getConceptos();
		List<sat.cfdv22.Comprobante.Conceptos.Concepto> css22 = cs22.getConcepto();
		Iterator<sat.cfdv22.Comprobante.Conceptos.Concepto> it = css22.iterator();
		sat.cfdv22.Comprobante.Conceptos.Concepto c22;
		while(it.hasNext())
		{
			c32 = new Concepto();
			c22 = it.next();
			
			c32.setCantidad(c22.getCantidad());
			c32.setDescripcion(c22.getDescripcion());
			c32.setImporte(c22.getImporte());
			c32.setUnidad(c22.getUnidad());
			c32.setValorUnitario(c22.getValorUnitario());
			css32.add(c32);
			
		}
		return cs32;
	}

	private sat.cfdv32.Comprobante.Impuestos copyImpuestos(sat.cfdv22.Comprobante.Impuestos is22) {

		sat.cfdv32.Comprobante.Impuestos impuestos32 = getCfdv32ObjFactory().createComprobanteImpuestos();
																					 	//new Impuestos();
		
		sat.cfdv32.Comprobante.Impuestos.Retenciones retenciones32 = getCfdv32ObjFactory().createComprobanteImpuestosRetenciones();
																			  //new sat.cfdv32.Comprobante.Impuestos.Retenciones();
		List<sat.cfdv32.Comprobante.Impuestos.Retenciones.Retencion> listRet32 = retenciones32.getRetencion();

		sat.cfdv32.Comprobante.Impuestos.Traslados traslados32 = getCfdv32ObjFactory().createComprobanteImpuestosTraslados();
																		  //new sat.cfdv32.Comprobante.Impuestos.Traslados();
		List<sat.cfdv32.Comprobante.Impuestos.Traslados.Traslado> listTras32 = traslados32.getTraslado();

		// begin
		// retenciones
		sat.cfdv22.Comprobante.Impuestos.Retenciones ret = is22.getRetenciones();
		List<sat.cfdv22.Comprobante.Impuestos.Retenciones.Retencion> listRet = ret.getRetencion();
		Iterator<sat.cfdv22.Comprobante.Impuestos.Retenciones.Retencion> itRet = listRet.iterator();
		while(itRet.hasNext())
		{
			sat.cfdv22.Comprobante.Impuestos.Retenciones.Retencion retencion = itRet.next();
			BigDecimal importe = retencion.getImporte();
			String impuesto = retencion.getImpuesto();
			//32
			sat.cfdv32.Comprobante.Impuestos.Retenciones.Retencion newRet = getCfdv32ObjFactory().createComprobanteImpuestosRetencionesRetencion();//new Retencion();
			newRet.setImporte(importe);
			newRet.setImpuesto(impuesto);
			listRet32.add(newRet);
			if(impuesto.equals(ISR_Ret))
			{
				setIsrRet(importe);
			} else if (impuesto.equals(IVA_Ret))
			{
				setIvaRet(importe);
			}
		}
		BigDecimal totalRet = is22.getTotalImpuestosRetenidos();

		// traslados
		sat.cfdv22.Comprobante.Impuestos.Traslados tras = is22.getTraslados();
		List<sat.cfdv22.Comprobante.Impuestos.Traslados.Traslado> listTras = tras.getTraslado();
		Iterator<sat.cfdv22.Comprobante.Impuestos.Traslados.Traslado> itTras = listTras.iterator();
		while(itTras.hasNext())
		{
			sat.cfdv22.Comprobante.Impuestos.Traslados.Traslado traslado = itTras.next();
			BigDecimal importe = traslado.getImporte();
			String impuesto = traslado.getImpuesto();
			BigDecimal tasa = traslado.getTasa();
			//32
			sat.cfdv32.Comprobante.Impuestos.Traslados.Traslado newTras = getCfdv32ObjFactory().createComprobanteImpuestosTrasladosTraslado();//new Traslado();
			newTras.setImporte(importe);
			newTras.setImpuesto(impuesto);
			newTras.setTasa(tasa);
			listTras32.add(newTras);
			if(impuesto.equals(IEPS_Tras))
			{
				setIepsTras(importe);
			} else if (impuesto.equals(IVA_Tras))
			{
				setIvaTras(importe);
			}
		}
		BigDecimal totalTras = is22.getTotalImpuestosTrasladados();
		// end

		//32
		impuestos32.setTotalImpuestosRetenidos(totalRet);
		impuestos32.setTotalImpuestosTrasladados(totalTras);
		impuestos32.setRetenciones(retenciones32);
		impuestos32.setTraslados(traslados32);
		return impuestos32;
	}*/

	//start local setter and getters
	public itr.sat.cfdi.v33.ObjectFactory getCfdv33ObjFactory() {
		return cfdv33ObjFactory;
	}

	public void setCfdv32ObjFactory(itr.sat.cfdi.v33.ObjectFactory cfdv32ObjFactory) {
		this.cfdv33ObjFactory = cfdv32ObjFactory;
	}

	public itr.sat.cfdi.v33.Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(itr.sat.cfdi.v33.Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public int getVerOrig() {
		return ver;
	}

	public void setVerOrig(int ver) {
		this.ver = ver;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public BigDecimal getIsrRet() {
		return isrRet == null ? DEF_BIGDECIMAL : isrRet;
	}

	public void setIsrRet(BigDecimal importe) {
		this.isrRet = importe;
	}

	public BigDecimal getIvaRet() {
		return ivaRet == null ? DEF_BIGDECIMAL : ivaRet;
	}

	public void setIvaRet(BigDecimal importe) {
		this.ivaRet = importe;
	}

	public BigDecimal getIepsTras() {
		return iepsTras == null ? DEF_BIGDECIMAL : iepsTras;
	}

	public void setIepsTras(BigDecimal importe) {
		this.iepsTras = importe;
	}

	public BigDecimal getIvaTras() {
		return ivaTras == null ? DEF_BIGDECIMAL : ivaTras;
	}

	public void setIvaTras(BigDecimal importe) {
		this.ivaTras = importe;
	}
	//end local setter and getters
	
	//start COMPROBANTE setters
	public String getFolio()
	{
		return comprobante.getFolio();
	}

	public XMLGregorianCalendar getFecha()
	{
		return comprobante.getFecha();
	}
	
	public String getFormaDePago()
	{
		return comprobante.getFormaPago();
	}

	public BigDecimal getSubTotal()
	{
		return comprobante.getSubTotal();
	}

	public BigDecimal getTotal()
	{
		return comprobante.getTotal();
	}

	public String getTipoDeComprobante()
	{
		//return comprobante.getTipoDeComprobanteStr();
		itr.sat.cfdi.v33.CTipoDeComprobante ctipoDeComprobante = comprobante.getTipoDeComprobante();
		return ctipoDeComprobante.name();
	}

	public String getMetodoDePago()
	{
		//String metPago = comprobante.getMetodoPago();
		itr.sat.cfdi.v33.CMetodoPago metPago = comprobante.getMetodoPago();
		StringBuffer newMP = new StringBuffer("\"");
		newMP.append(metPago);
		newMP.append("\"");
		return newMP.toString();
		//return comprobante.getMetodoDePago();
	}

	//comprobante, sequence Emisor, receptor, etc
	public itr.sat.cfdi.v33.Comprobante.Emisor getEmisor()
	{
		return comprobante.getEmisor();
	}

	public itr.sat.cfdi.v33.Comprobante.Receptor getReceptor()
	{
		return comprobante.getReceptor();
	}

	public itr.sat.cfdi.v33.Comprobante.Conceptos getConceptos()
	{
		return comprobante.getConceptos();
	}

	public itr.sat.cfdi.v33.Comprobante.Impuestos getImpuestos()
	{
		return comprobante.getImpuestos();
	}

	//comprobante totales
	public BigDecimal getTotalImpuestosTrasladados()
	{
		boolean calculate = comprobante.getImpuestos().getTraslados() != null;
		if(!calculate)
		{
			return new BigDecimal(0);
		}

		BigDecimal totalImpTras = comprobante.getImpuestos().getTotalImpuestosTrasladados();
		if(totalImpTras != null)
		{
			return totalImpTras;
		} else {
			totalImpTras = new BigDecimal(0);
			//Comprobante.Impuestos.Traslados trasladados = comprobante.getImpuestos().getTraslados();
			itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados trasladados = comprobante.getImpuestos().getTraslados();
			List<itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado> list = trasladados.getTraslado();
			Iterator<itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado> it = list.iterator();
			while(it.hasNext())
			{
				itr.sat.cfdi.v33.Comprobante.Impuestos.Traslados.Traslado t = it.next();
				BigDecimal importe = t.getImporte();
				totalImpTras = totalImpTras.add(importe);
			}
		}
		return totalImpTras;
	}

	public BigDecimal getTotalImpuestosRetenidos()
	{
		boolean calculate  = comprobante.getImpuestos().getRetenciones() != null;
		if(!calculate)
		{
			return new BigDecimal(0);
		}

		BigDecimal totalImpRet = comprobante.getImpuestos().getTotalImpuestosRetenidos();
		if(totalImpRet != null)
		{
			return totalImpRet;
		} else {
			totalImpRet = new BigDecimal(0);
			itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones retenciones = comprobante.getImpuestos().getRetenciones();
			List<itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion> list = retenciones.getRetencion();
			Iterator<itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion> it = list.iterator();
			while(it.hasNext())
			{
				itr.sat.cfdi.v33.Comprobante.Impuestos.Retenciones.Retencion r = it.next();
				BigDecimal importe = r.getImporte();
				totalImpRet = totalImpRet.add(importe);
			}
		}
		return totalImpRet;
	}
	//end comprobante setters

	@Override
	public int compareTo(ComprobanteGR o) {

		int result = -1;
		/*return this.fullName().compareTo(arg0.fullName());*/
		

		//pass the user's locale as an argument
		//Collator myCollator = Collator.getInstance(locale);

		//set collator to Ignore case but not accents
		//(default is Collator.TERTIARY, which is
		//case sensitive)
		//myCollator.setStrength(Collator.PRIMARY);

		result = this.comprobante.getFecha().toGregorianCalendar().compareTo(o.getFecha().toGregorianCalendar());
		
		/*return  myCollator.compare( 
				this.comprobante.getFecha().toGregorianCalendar()
				,o.comprobante.getFecha().toGregorianCalendar()
				);*/
		return result;
	}

	public static void main(String args[])
	{
		File file22 = new File("I:\\itr\\dev\\eclipse_mars_git\\sat-xml_v03\\sat-xml_v03_30dic2016\\xml\\SECFD_11_28_2013 5_55_05 PM.xml");
		File file32 = new File(".\\xml\\89BBA197-0F74-4BE4-915B-F3C52C88ADBB.xml");
		//File file33 = new File(".\\xml\\65506992374_Octubre2018_Egreso.xml");
		  File file33 = new File(".\\xml\\65506992374_Octubre2018_Ingreso.xml");
		//File file33 = new File(".\\xml\\65504514291_Enero2018_Egreso.xml");
		
		JAXBXMLHandler.checkXSDVersion33(file22);

		//isat.cfdv22.Comprobante comprobante22 = null;
		//sat.cfdv32.Comprobante comprobante32 = null;
		itr.sat.cfdi.v33.Comprobante comprobante33 = null;
		try {
			//comprobante22 = JAXBXMLHandler.unmarshal_v22(file22);
			//comprobante32 = JAXBXMLHandler.unmarshal_v32(file32);
			comprobante33 = JAXBXMLHandler.unmarshal_v33(file33);
			//System.out.println(" isComprobante22: "+ JAXBXMLHandler.isComprobante22(comprobante22));
			//System.out.println(" isComprobante32: "+ JAXBXMLHandler.isComprobante22(comprobante32));
			System.out.println(" isComprobante33: "+ JAXBXMLHandler.isComprobante33(comprobante33));
			//JAXBSAT.toPrint22(comprobante22);
			//JAXBSAT.toPrint32(comprobante32);
			JAXBSAT.toPrint33(comprobante33);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		itr.sat.cfdi.v33.Comprobante.Emisor emisor22 = comprobante33.getEmisor();
		
		itr.sat.cfdi.v33.ObjectFactory objFact = new itr.sat.cfdi.v33.ObjectFactory();
		itr.sat.cfdi.v33.Comprobante.Emisor emisor33 = 
				//comprobante32.getEmisor()
				objFact.createComprobanteEmisor()
				;
		
		ComprobanteGrFactory fact = new ComprobanteGrFactory();
		
		ComprobanteGR cgr = fact.getComprobanteGRv33(comprobante33, file22.getName()); 
				//new ComprobanteGR(comprobante22, file22.getName());
		//copyEmisor22to32(emisor22, emisor32, objFact);
		
		//copyEmisor(emisor22, emisor32 );
		ComprobanteGR cgr33 = fact.getComprobanteGRv33(comprobante33, file33.getName());
		int a = 0;
	}

	/*private static void copyEmisor22to32(sat.cfdv22.Comprobante.Emisor emisor22, Emisor emisor32, ObjectFactory objFact32) {

		TUbicacionFiscal tubicFiscal22 = emisor22.getDomicilioFiscal();
		sat.cfdv32.TUbicacionFiscal tubicFiscal32 = objFact32.createTUbicacionFiscal();
		copy(tubicFiscal22, tubicFiscal32);
		emisor32.setDomicilioFiscal(tubicFiscal32);
		
		TUbicacion tubic22 = emisor22.getExpedidoEn();
		sat.cfdv32.TUbicacion tubic32 = objFact32.createTUbicacion();
		copy(tubic22, tubic32);
		emisor32.setExpedidoEn(tubic32);
		
		List<RegimenFiscal> listRegFiscList22 = emisor22.getRegimenFiscal();
		List<RegimenFiscalInterface> regFiscList32 = emisor32.getRegimenFiscal();
		for(RegimenFiscal tmp: listRegFiscList22)
		{
			sat.cfdv32.Comprobante.Emisor.RegimenFiscal regFisc = objFact32.createComprobanteEmisorRegimenFiscal();
			copy(tmp, regFisc);
			regFiscList32.add(regFisc);
		}
		emisor32.setUpRegimenFiscal(regFiscList32);
		//copyField("regimenFiscal", regFiscList32, emisor32);

		String nomEmisor22 = emisor22.getNombre();
		emisor32.setNombre(nomEmisor22);
		String rfcEmisor22 = emisor22.getRfc();
		emisor32.setRfc(rfcEmisor22);
	}

	private static void copyReceptor22to32(sat.cfdv22.Comprobante.Receptor receptor22, Receptor receptor32, ObjectFactory objFact32) {
		
		TUbicacion domicilio22 = receptor22.getDomicilio();
		sat.cfdv32.TUbicacion domic32 = objFact32.createTUbicacion();
		copy(domicilio22, domic32);
		receptor32.setDomicilio(domic32);
		
		String nomEmisor22 = receptor22.getNombre();
		receptor32.setNombre(nomEmisor22);
		String rfcEmisor22 = receptor22.getRfc();
		receptor32.setRfc(rfcEmisor22);
	}*/

	private static int copy(Object a, Object b)
	{
		int result = 0;
		
		Class<?> classA = a.getClass();
		Class<?> classB = b.getClass();
	    
	    Field[] fields = 
	    		classB.getDeclaredFields();
	    		;
	    for(Field tmp: fields)
	    {
	    	String fieldName = tmp.getName();
	    	try {
				Field field = classA.getDeclaredField(fieldName);
				
				//boolean arePrimitive = tmp.getClass().isPrimitive() &&	field.getClass().isPrimitive();
				boolean sameType = tmp.getType().equals(field.getType());
				if(/*arePrimitive &&*/ 
						sameType)
				{
					field.setAccessible(true);
					Object value = field.get(a);
					tmp.setAccessible(true);
					tmp.set(b, value);
				}
			} catch (NoSuchFieldException e) {
				result = 1;
				e.printStackTrace();
			} catch (SecurityException e) {
				result = 2;
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				result = 3;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				result = 4;
				e.printStackTrace();
			}
	    }

	    return result;
	}

	private static int copyField(String name, Object value, Object b)
	{
		int result = 0;

		Class<?> classB = b.getClass();
		try {
			Field field = classB.getDeclaredField(name);
			field.setAccessible(true);
			field.set(b, value);
		} catch (NoSuchFieldException e) {
			result = 1;
			e.printStackTrace();
		} catch (SecurityException e) {
			result = 2;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			result = 3;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			result = 4;
			e.printStackTrace();
		}

	    return result;
	}

	/*private static void copyEmisor(sat.cfdv22.Comprobante.Emisor v22, sat.cfdv32.Comprobante.Emisor v32)
	{
	    Class<? extends sat.cfdv22.Comprobante.Emisor > c22 = v22.getClass();
	    Class<? extends sat.cfdv32.Comprobante.Emisor > c32 = v32.getClass();
	    
	    Field[] fields32 = 
	    		c32.getDeclaredFields();
	    		//c32.getFields()
	    		;
	    for(Field tmp: fields32)
	    {
	    	String fieldName = tmp.getName();
	    	try {
				Field field22 = c22.getDeclaredField(fieldName);
				if(field22.getType().equals(tmp.getType()))
				{
					Object value = field22.get(v22);
					tmp.set(v32, value);
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    }
	}*/
}
