package itr.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.xml.bind.JAXBException;

import itr.FacturaSantander;
import itr.Factura_Total;
import itr.JAXBSAT;
import itr.exception.SAT_ITR_Exception;
import itr.exception.SAT_ITR_WRONG_MONTH_Exception;
// import itr.jpa.SAT_ITR_TO_Assembler;
// import itr.jpa.SAT_MetaDataInfo;
// import itr.model.ComprobanteBanco;
// import itr.model.ComprobanteIssued;
// import itr.model.SAT_MetaData;
import utils.ITR_Utils;

public class Movement_Santander extends JAXBSAT{

	private static final String dflt_props_dir = ".\\prop\\santander.cfg";
	private static final String edoCtaFileName = "EdoCta_Santander";

	//public static final int RESULT_OK = 0;
	//public static final int RESULT_NOT_OK_WRONG_YEAR = 1;
	//public static final int RESULT_NOT_OK_WRONG_FOLDER = 2;

	private boolean withCSV;
	private File folder;
	
	/*public Movement_Santander(String props_dir) {
		super(props_dir);
		setPrintDetail(true);
	}*/

	public Movement_Santander(String props_dir, final int year) {
		super(props_dir, year);
		setPrintDetail(true);
	}
	
	/*public static void readSantander_XML_FILES(final boolean withCSV)
	{
		Movement_Santander jaxbSantander = new Movement_Santander(dflt_props_dir);
		jaxbSantander.setWithCSV(withCSV);

		//if(withCSV)
		//{
		jaxbSantander.processSantanderFolder(edoCtaFileName);
		//}else
		//{
		//	jaxbSantander.processSantanderFolder();
		//}
	}*/

	public static void readSantander_XML_FILES(final boolean withCSV, final int year)
	{
		Movement_Santander jaxbSantander = new Movement_Santander(dflt_props_dir, year);
		jaxbSantander.setWithCSV(withCSV);

		//if(withCSV)
		//{
		jaxbSantander.processSantanderFolder(edoCtaFileName);
		//}else
		//{
		//	jaxbSantander.processSantanderFolder();
		//}
	}

	/*public static void persistSantander_XML_FILES(final boolean withCSV, final int year, final int period)
	{
		SAT_ITR_TO_Assembler toa = new SAT_ITR_TO_Assembler(period);
		Movement_Santander jaxbSantander = new Movement_Santander(dflt_props_dir, year);
		jaxbSantander.setToa(toa);
		jaxbSantander.setWithCSV(withCSV);

		//if(withCSV)
		//{
		try {
			jaxbSantander.persistSantanderFolder(edoCtaFileName);
		} catch (SAT_ITR_Exception e) {
			e.printStackTrace();
		}
		//}else
		//{
		//	jaxbSantander.processSantanderFolder();
		//}
	}*/

	public static void main(String[] args)
	{
		int year = 0;
		try {
			year = ITR_Utils.scanInt("Enter YEAR: ");

			if((args != null) && (args.length != 0))
			{
				readSantander_XML_FILES(true, year);
			}
			else
			{
				System.out.println("1 with csv");
				System.out.println("2 just pdf");
				//System.out.println("3 db and csv");

				int option = 0;
				try {
					option = ITR_Utils.scanInt("Enter option: ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}

				switch (option) {
				case 1:
					readSantander_XML_FILES(true, year);
					break;
				case 2:
					readSantander_XML_FILES(false, year);
					break;
				/*case 3:
					int period = 0;
					try {
						System.out.println("09 febjun16");
						System.out.println("10 agodic16");
						System.out.println("11 febjun17");

						period = ITR_Utils.scanInt("Enter per_dgeti_id: ");
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.exit(0);
					}
					persistSantander_XML_FILES(true, year, period);
					break;*/
				default:
					break;
				}
				//readSantander_XML_FILES(false, period);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}finally
		{
			ITR_Utils.closeScanner();
		}
	}

	/*public void processSantanderFolder() {
		
		final File folder = new File(getRoot_dir());
		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO (SANTANDER) !!!");
			return;
		}
	
		Factura_Total ft = new FacturaSantander(Factura_Total.ALLYEAR);
		ft.setCheckDir(false);
		ft.setYearNo(getReport_year());
	
		int cnt = 0;
		try {
			cnt = read_XML_FILES(folder,ft);
		} catch (ITR_SAT_WRONG_MONTH_Exception e) {
			e.printStackTrace();
		}
	
		traceMsg(" # total facturas: "+ cnt);
		traceMsg(ft.toStringResMen());
	}*/

	public void processSantanderFolder(String fileName) {
		final File folder = new File(getRoot_dir());
		if (!folder.exists())
		{
			traceMsg("ERROR EN EL DIRECTORIO (SANTANDER) !!!");
			return;
		}
	
		Factura_Total ft = null;
		try{
			ft = new FacturaSantander(fileName, isWithCSV());
			ft.setCheckDir(false);
			ft.setYearNo(getReport_year());
	
			int cnt = read_XML_FILES(folder,ft);
	
			for(int mIndex = Factura_Total.ENE01; mIndex <= Factura_Total.DIC12; mIndex++)
			{
				Factura_Total r = ft.getMonth(mIndex);
				printMonthYearResume(r);
			}
			
			traceMsg(" # total facturas: "+ cnt);
			//traceMsg(ft.toStringResMen());
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

	/*public void persistSantanderFolder(String fileName) throws SAT_ITR_Exception {
		
		checkPreconditions();

		Factura_Total ft = null;
		try{
			getToa().beginTransaction();

			ft = new FacturaSantander(fileName, isWithCSV());
			ft.setCheckDir(false);
			ft.setYearNo(getReport_year());
	
			int cnt = read_XML_FILES(getFolder(),ft);
	
			for(int mIndex = Factura_Total.ENE01; mIndex <= Factura_Total.DIC12; mIndex++)
			{
				Factura_Total r = ft.getMonth(mIndex);
				persistMonthYearResume(r);
			}
			
			traceMsg(" # total facturas: "+ cnt);

			getToa().commitTransaction();
		} catch (SAT_ITR_WRONG_MONTH_Exception w)
		{
			ft.printPDFErrorMsg(w.getMessage());
			w.printStackTrace();
			try
			{
				getToa().rollBackTransaction();
			} catch(PersistenceException pe)
			{
				pe.printStackTrace();
			}
		} catch (FileNotFoundException e)
		{
			traceMsg(" no pdf folder!! "+ e.getMessage());
			try
			{
				getToa().rollBackTransaction();
			} catch(PersistenceException pe)
			{
				pe.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
			try
			{
				getToa().rollBackTransaction();
			} catch(PersistenceException pe)
			{
				pe.printStackTrace();
			}
		}finally
		{
			if(ft != null)
			{
				ft.closePDF();
			}
		}
	}*/

	/*private void checkPreconditions() throws SAT_ITR_Exception {
		SAT_MetaData metaData = getToa().findMetaDataField(SAT_MetaDataInfo.META_DATA_YEAR);
		if(metaData.getNumeric_Field() != getReport_year())
		{
			throw new SAT_ITR_Exception("ERROR EN EL year or period: "+getToa().getPdId()+ "!!!", SAT_ITR_Exception.WRONG_PERIOD_DGETI);
			
		}

		setFolder(new File(getRoot_dir()));
		if (!getFolder().exists())
		{
			throw new SAT_ITR_Exception("ERROR EN EL DIRECTORIO (SANTANDER): " + getRoot_dir() + "!!!", SAT_ITR_Exception.WRONG_FOLDER);
		}
	}*/

	protected void printMonthYearResume(Factura_Total r) {
		//AQUI VA EL BUNCH DE CADA MES
		if(printDetail())
		{
			System.out.println(r.toStringCompGRs());
		}
		//RESUMEN MENSUAL
		//traceMsg(r.toStringResMen(printDetail()));
	}

	/*protected void persistMonthYearResume(Factura_Total r) throws Exception {
		//AQUI VA EL BUNCH DE CADA MES
		//if(printDetail())
		{
			List<ComprobanteIssued> persistList = r.persistCompGRs();
			System.out.println("persistMonthYearResume month: "+ r.getMonth() +" : # "+persistList.size());
			Iterator<ComprobanteIssued> it = persistList.iterator();
			while(it.hasNext())
			{
				ComprobanteIssued cb = it.next();
				getToa().persist((ComprobanteIssued)cb);
			}
		}
		//RESUMEN MENSUAL
		//traceMsg(r.toStringResMen(printDetail()));
	}*/

	public boolean isWithCSV() {
		return withCSV;
	}

	public void setWithCSV(boolean withCSV) {
		this.withCSV = withCSV;
	}

	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}

	protected void setUpDir(final int year) {
		String root_dir_tmp = prop.getProperty(root_dir_property).toString();
		String root_dir_itr_tmp = prop.getProperty(root_dir_itr_property).toString();

		if(root_dir == null)
		{
			StringBuffer rec = new StringBuffer(root_dir_tmp);
			rec.append(getReport_year());
			rec.append("\\");

			root_dir = rec.toString();
		}

		if(root_dir_itr == null)
		{
			StringBuffer emi = new StringBuffer(root_dir_itr_tmp);
			emi.append(getReport_year());
			emi.append("\\");

			root_dir_itr = emi.toString();
		}
	}
}
