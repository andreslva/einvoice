package itr.algo;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import itr.ComprobanteGR;
import itr.sat.cfdi.v33.Comprobante;
/*import itr.sat.cfdvxx.ComprobanteImpl;
import itr.sat.cfdvxx.RetencionInterface;
import itr.sat.cfdvxx.RetencionesInterface;
import itr.sat.cfdvxx.TrasladoInterface;
import itr.sat.cfdvxx.TrasladosInterface;*/
import itr.sat.cfdi.v33.ObjectFactory;

public class ComprobanteGrCreatorv33 extends ComprobanteGRCreator {

	public ComprobanteGrCreatorv33(ObjectFactory objFactory, Comprobante c,String xmlFile) {
		super(objFactory);
		getCompGR().setXmlFile(xmlFile);
		getCompGR().setVerOrig(ComprobanteGR.VER33);
		getCompGR().setComprobante(c);
	}

	@Override
	protected void copyData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void copyEmisor() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void copyReceptor() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void copyConceptos() {
		// TODO Auto-generated method stub

	}

	/*@Override
	protected void copyImpuestos() {
		setRetenciones(getCompGR().getImpuestos().getRetenciones());
		setTraslados(getCompGR().getImpuestos().getTraslados());
	}

	@Override
	protected void copyComplemento() {
		// TODO Auto-generated method stub

	}*/

	/*private void setRetenciones(RetencionesInterface ret) {

		boolean calculate = ret != null;
		if(!calculate)
		{
			getCompGR().setIsrRet(ComprobanteGR.DEF_BIGDECIMAL);
			getCompGR().setIvaRet(ComprobanteGR.DEF_BIGDECIMAL);
			return;
		}

		//sat.cfdv32.Comprobante.Impuestos.Retenciones ret = is32.getRetenciones();
		List<? extends RetencionInterface> listRet = ret.getRetencion();
		Iterator<? extends RetencionInterface> itRet = listRet.iterator();
		while(itRet.hasNext())
		{
			RetencionInterface retencion = itRet.next();
			BigDecimal importe = retencion.getImporte();
			String impuesto = retencion.getImpuesto();
			if(impuesto.equals(ComprobanteGR.ISR_Ret))
			{
				getCompGR().setIsrRet(importe);
			} else if (impuesto.equals(ComprobanteGR.IVA_Ret))
			{
				getCompGR().setIvaRet(importe);
			}
		}
		return;
	}

	private void setTraslados(TrasladosInterface tras) {

		boolean calculate = tras != null;
		if(!calculate)
		{
			getCompGR().setIepsTras(ComprobanteGR.DEF_BIGDECIMAL);
			getCompGR().setIvaTras(ComprobanteGR.DEF_BIGDECIMAL);
			return;
		}

		//sat.cfdv32.Comprobante.Impuestos.Traslados tras = is32.getTraslados();
		List<? extends TrasladoInterface> listTras = tras.getTraslado();
		Iterator<? extends TrasladoInterface> itTras = listTras.iterator();
		while(itTras.hasNext())
		{
			TrasladoInterface traslado = itTras.next();
			BigDecimal importe = traslado.getImporte();
			String impuesto = traslado.getImpuesto();
			BigDecimal tasa = traslado.getTasa();
			if(impuesto.equals(ComprobanteGR.IEPS_Tras))
			{
				getCompGR().setIepsTras(importe);
			} else if (impuesto.equals(ComprobanteGR.IVA_Tras))
			{
				getCompGR().setIvaTras(importe);
			}
		}
		return;
	}*/

	@Override
	protected void copyImpuestos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void copyComplemento() {
		// TODO Auto-generated method stub
		
	}

	
}
