
package itr.sat.cfdi.v33;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para Información de Pago.
 * 
 * <p>Clase Java para TInformacionPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TInformacionPago"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="numProveedor" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ordenCompra" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="posCompra" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="nombreBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="institucionReceptora" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="numeroCuenta" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TNumeroCuenta" /&gt;
 *       &lt;attribute name="cuentaContable" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TNumeroCuenta" /&gt;
 *       &lt;attribute name="claveDeposito" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="email" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="codigoISOMoneda" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInformacionPago", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
public class TInformacionPago {

    @XmlAttribute(name = "numProveedor")
    protected String numProveedor;
    @XmlAttribute(name = "ordenCompra")
    protected String ordenCompra;
    @XmlAttribute(name = "posCompra")
    protected String posCompra;
    @XmlAttribute(name = "nombreBeneficiario")
    protected String nombreBeneficiario;
    @XmlAttribute(name = "institucionReceptora")
    protected String institucionReceptora;
    @XmlAttribute(name = "numeroCuenta")
    protected String numeroCuenta;
    @XmlAttribute(name = "cuentaContable")
    protected String cuentaContable;
    @XmlAttribute(name = "claveDeposito")
    protected String claveDeposito;
    @XmlAttribute(name = "email")
    protected String email;
    @XmlAttribute(name = "codigoISOMoneda")
    protected String codigoISOMoneda;
    @XmlAttribute(name = "concepto")
    protected String concepto;

    /**
     * Obtiene el valor de la propiedad numProveedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumProveedor() {
        return numProveedor;
    }

    /**
     * Define el valor de la propiedad numProveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumProveedor(String value) {
        this.numProveedor = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdenCompra() {
        return ordenCompra;
    }

    /**
     * Define el valor de la propiedad ordenCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdenCompra(String value) {
        this.ordenCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad posCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosCompra() {
        return posCompra;
    }

    /**
     * Define el valor de la propiedad posCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosCompra(String value) {
        this.posCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * Define el valor de la propiedad nombreBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreBeneficiario(String value) {
        this.nombreBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad institucionReceptora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitucionReceptora() {
        return institucionReceptora;
    }

    /**
     * Define el valor de la propiedad institucionReceptora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitucionReceptora(String value) {
        this.institucionReceptora = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el valor de la propiedad numeroCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuenta(String value) {
        this.numeroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaContable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaContable() {
        return cuentaContable;
    }

    /**
     * Define el valor de la propiedad cuentaContable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaContable(String value) {
        this.cuentaContable = value;
    }

    /**
     * Obtiene el valor de la propiedad claveDeposito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveDeposito() {
        return claveDeposito;
    }

    /**
     * Define el valor de la propiedad claveDeposito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveDeposito(String value) {
        this.claveDeposito = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoISOMoneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoISOMoneda() {
        return codigoISOMoneda;
    }

    /**
     * Define el valor de la propiedad codigoISOMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoISOMoneda(String value) {
        this.codigoISOMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

}
