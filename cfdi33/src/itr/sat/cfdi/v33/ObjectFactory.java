
package itr.sat.cfdi.v33;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the itr.sat.cfdi.v33 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddendaSantanderV1_QNAME = new QName("http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1", "AddendaSantanderV1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: itr.sat.cfdi.v33
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Comprobante }
     * 
     */
    public Comprobante createComprobante() {
        return new Comprobante();
    }

    /**
     * Create an instance of {@link AddendaECB }
     * 
     */
    public AddendaECB createAddendaECB() {
        return new AddendaECB();
    }

    /**
     * Create an instance of {@link AddendaECB.EstadoDeCuentaBancario }
     * 
     */
    public AddendaECB.EstadoDeCuentaBancario createAddendaECBEstadoDeCuentaBancario() {
        return new AddendaECB.EstadoDeCuentaBancario();
    }

    /**
     * Create an instance of {@link AddendaECB.EstadoDeCuentaBancario.Movimientos }
     * 
     */
    public AddendaECB.EstadoDeCuentaBancario.Movimientos createAddendaECBEstadoDeCuentaBancarioMovimientos() {
        return new AddendaECB.EstadoDeCuentaBancario.Movimientos();
    }

    /**
     * Create an instance of {@link Comprobante.Impuestos }
     * 
     */
    public Comprobante.Impuestos createComprobanteImpuestos() {
        return new Comprobante.Impuestos();
    }

    /**
     * Create an instance of {@link Comprobante.Impuestos.Traslados }
     * 
     */
    public Comprobante.Impuestos.Traslados createComprobanteImpuestosTraslados() {
        return new Comprobante.Impuestos.Traslados();
    }

    /**
     * Create an instance of {@link Comprobante.Impuestos.Retenciones }
     * 
     */
    public Comprobante.Impuestos.Retenciones createComprobanteImpuestosRetenciones() {
        return new Comprobante.Impuestos.Retenciones();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos }
     * 
     */
    public Comprobante.Conceptos createComprobanteConceptos() {
        return new Comprobante.Conceptos();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto }
     * 
     */
    public Comprobante.Conceptos.Concepto createComprobanteConceptosConcepto() {
        return new Comprobante.Conceptos.Concepto();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Parte }
     * 
     */
    public Comprobante.Conceptos.Concepto.Parte createComprobanteConceptosConceptoParte() {
        return new Comprobante.Conceptos.Concepto.Parte();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Impuestos }
     * 
     */
    public Comprobante.Conceptos.Concepto.Impuestos createComprobanteConceptosConceptoImpuestos() {
        return new Comprobante.Conceptos.Concepto.Impuestos();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Impuestos.Retenciones }
     * 
     */
    public Comprobante.Conceptos.Concepto.Impuestos.Retenciones createComprobanteConceptosConceptoImpuestosRetenciones() {
        return new Comprobante.Conceptos.Concepto.Impuestos.Retenciones();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Impuestos.Traslados }
     * 
     */
    public Comprobante.Conceptos.Concepto.Impuestos.Traslados createComprobanteConceptosConceptoImpuestosTraslados() {
        return new Comprobante.Conceptos.Concepto.Impuestos.Traslados();
    }

    /**
     * Create an instance of {@link Comprobante.CfdiRelacionados }
     * 
     */
    public Comprobante.CfdiRelacionados createComprobanteCfdiRelacionados() {
        return new Comprobante.CfdiRelacionados();
    }

    /**
     * Create an instance of {@link Comprobante.Emisor }
     * 
     */
    public Comprobante.Emisor createComprobanteEmisor() {
        return new Comprobante.Emisor();
    }

    /**
     * Create an instance of {@link Comprobante.Receptor }
     * 
     */
    public Comprobante.Receptor createComprobanteReceptor() {
        return new Comprobante.Receptor();
    }

    /**
     * Create an instance of {@link Comprobante.Complemento }
     * 
     */
    public Comprobante.Complemento createComprobanteComplemento() {
        return new Comprobante.Complemento();
    }

    /**
     * Create an instance of {@link Comprobante.Addenda }
     * 
     */
    public Comprobante.Addenda createComprobanteAddenda() {
        return new Comprobante.Addenda();
    }

    /**
     * Create an instance of {@link TimbreFiscalDigital }
     * 
     */
    public TimbreFiscalDigital createTimbreFiscalDigital() {
        return new TimbreFiscalDigital();
    }

    /**
     * Create an instance of {@link TAddendaSantanderV1 }
     * 
     */
    public TAddendaSantanderV1 createTAddendaSantanderV1() {
        return new TAddendaSantanderV1();
    }

    /**
     * Create an instance of {@link TCampoAdicional }
     * 
     */
    public TCampoAdicional createTCampoAdicional() {
        return new TCampoAdicional();
    }

    /**
     * Create an instance of {@link TInformacionPago }
     * 
     */
    public TInformacionPago createTInformacionPago() {
        return new TInformacionPago();
    }

    /**
     * Create an instance of {@link TInformacionEmision }
     * 
     */
    public TInformacionEmision createTInformacionEmision() {
        return new TInformacionEmision();
    }

    /**
     * Create an instance of {@link TFactoraje }
     * 
     */
    public TFactoraje createTFactoraje() {
        return new TFactoraje();
    }

    /**
     * Create an instance of {@link TInmuebles }
     * 
     */
    public TInmuebles createTInmuebles() {
        return new TInmuebles();
    }

    /**
     * Create an instance of {@link TBasilea }
     * 
     */
    public TBasilea createTBasilea() {
        return new TBasilea();
    }

    /**
     * Create an instance of {@link AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB }
     * 
     */
    public AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB createAddendaECBEstadoDeCuentaBancarioMovimientosMovimientoECB() {
        return new AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB();
    }

    /**
     * Create an instance of {@link Comprobante.Impuestos.Traslados.Traslado }
     * 
     */
    public Comprobante.Impuestos.Traslados.Traslado createComprobanteImpuestosTrasladosTraslado() {
        return new Comprobante.Impuestos.Traslados.Traslado();
    }

    /**
     * Create an instance of {@link Comprobante.Impuestos.Retenciones.Retencion }
     * 
     */
    public Comprobante.Impuestos.Retenciones.Retencion createComprobanteImpuestosRetencionesRetencion() {
        return new Comprobante.Impuestos.Retenciones.Retencion();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.InformacionAduanera }
     * 
     */
    public Comprobante.Conceptos.Concepto.InformacionAduanera createComprobanteConceptosConceptoInformacionAduanera() {
        return new Comprobante.Conceptos.Concepto.InformacionAduanera();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.CuentaPredial }
     * 
     */
    public Comprobante.Conceptos.Concepto.CuentaPredial createComprobanteConceptosConceptoCuentaPredial() {
        return new Comprobante.Conceptos.Concepto.CuentaPredial();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.ComplementoConcepto }
     * 
     */
    public Comprobante.Conceptos.Concepto.ComplementoConcepto createComprobanteConceptosConceptoComplementoConcepto() {
        return new Comprobante.Conceptos.Concepto.ComplementoConcepto();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Parte.InformacionAduanera }
     * 
     */
    public Comprobante.Conceptos.Concepto.Parte.InformacionAduanera createComprobanteConceptosConceptoParteInformacionAduanera() {
        return new Comprobante.Conceptos.Concepto.Parte.InformacionAduanera();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion }
     * 
     */
    public Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion createComprobanteConceptosConceptoImpuestosRetencionesRetencion() {
        return new Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion();
    }

    /**
     * Create an instance of {@link Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado }
     * 
     */
    public Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado createComprobanteConceptosConceptoImpuestosTrasladosTraslado() {
        return new Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado();
    }

    /**
     * Create an instance of {@link Comprobante.CfdiRelacionados.CfdiRelacionado }
     * 
     */
    public Comprobante.CfdiRelacionados.CfdiRelacionado createComprobanteCfdiRelacionadosCfdiRelacionado() {
        return new Comprobante.CfdiRelacionados.CfdiRelacionado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TAddendaSantanderV1 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TAddendaSantanderV1 }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1", name = "AddendaSantanderV1")
    public JAXBElement<TAddendaSantanderV1> createAddendaSantanderV1(TAddendaSantanderV1 value) {
        return new JAXBElement<TAddendaSantanderV1>(_AddendaSantanderV1_QNAME, TAddendaSantanderV1 .class, null, value);
    }

}
