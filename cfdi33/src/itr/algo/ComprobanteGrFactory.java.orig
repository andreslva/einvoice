package itr.algo;

import itr.ComprobanteGR;
//import itr.sat.cfdvxx.ComprobanteImpl;
import itr.sat.cfdi.v33.ObjectFactory;

public class ComprobanteGrFactory {

	private ObjectFactory cfdv33ObjFactory;

	public ComprobanteGrFactory() {
		super();
		setCfdv32ObjFactory(new ObjectFactory());
	}

	public ObjectFactory getCfdv33ObjFactory() {
		return cfdv33ObjFactory;
	}

	private void setCfdv32ObjFactory(ObjectFactory cfdv32ObjFactory) {
		this.cfdv33ObjFactory = cfdv32ObjFactory;
	}
	
	/*public ComprobanteGR getComprobanteGRv22(sat.cfdv22.Comprobante c, String xmlFile)
	{
		ComprobanteGrCreatorv22 creator = new ComprobanteGrCreatorv22(getCfdv32ObjFactory(), c, xmlFile);
		return creator.copyElements();
	}*/
	
	/*public ComprobanteGR getComprobanteGRv32(itr.sat.cfdi.v33.Comprobante c, String xmlFile)
	{
		ComprobanteGrCreatorv33 creator = new ComprobanteGrCreatorv33(getCfdv33ObjFactory(), (ComprobanteImpl) c, xmlFile);
		return creator.copyElements();
	}*/
	
	public ComprobanteGR getComprobanteGRv33(itr.sat.cfdi.v33.Comprobante c, String xmlFile)
	{
		ComprobanteGrCreatorv33 creator = new ComprobanteGrCreatorv33(getCfdv33ObjFactory(), c, xmlFile);
		return creator.copyElements();
	}
}
