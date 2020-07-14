
package itr.sat.cfdi.v33;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para representar información adicional de conceptos para Factoraje
 * 
 * <p>Clase Java para TFactoraje complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TFactoraje"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="deudorProveedor" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="plazo" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="valorNominal" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="aforo" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="precioBase" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="tasaDescuento" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="precioFactoraje" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *       &lt;attribute name="importeDescuento" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TDecimalFactoraje" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFactoraje", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
public class TFactoraje {

    @XmlAttribute(name = "deudorProveedor")
    protected String deudorProveedor;
    @XmlAttribute(name = "tipoDocumento")
    protected String tipoDocumento;
    @XmlAttribute(name = "numeroDocumento")
    protected String numeroDocumento;
    @XmlAttribute(name = "fechaVencimiento")
    protected String fechaVencimiento;
    @XmlAttribute(name = "plazo")
    protected BigDecimal plazo;
    @XmlAttribute(name = "valorNominal")
    protected BigDecimal valorNominal;
    @XmlAttribute(name = "aforo")
    protected BigDecimal aforo;
    @XmlAttribute(name = "precioBase")
    protected BigDecimal precioBase;
    @XmlAttribute(name = "tasaDescuento")
    protected BigDecimal tasaDescuento;
    @XmlAttribute(name = "precioFactoraje")
    protected BigDecimal precioFactoraje;
    @XmlAttribute(name = "importeDescuento")
    protected BigDecimal importeDescuento;

    /**
     * Obtiene el valor de la propiedad deudorProveedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeudorProveedor() {
        return deudorProveedor;
    }

    /**
     * Define el valor de la propiedad deudorProveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeudorProveedor(String value) {
        this.deudorProveedor = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Define el valor de la propiedad tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Define el valor de la propiedad numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad plazo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPlazo() {
        return plazo;
    }

    /**
     * Define el valor de la propiedad plazo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPlazo(BigDecimal value) {
        this.plazo = value;
    }

    /**
     * Obtiene el valor de la propiedad valorNominal.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorNominal() {
        return valorNominal;
    }

    /**
     * Define el valor de la propiedad valorNominal.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorNominal(BigDecimal value) {
        this.valorNominal = value;
    }

    /**
     * Obtiene el valor de la propiedad aforo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAforo() {
        return aforo;
    }

    /**
     * Define el valor de la propiedad aforo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAforo(BigDecimal value) {
        this.aforo = value;
    }

    /**
     * Obtiene el valor de la propiedad precioBase.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    /**
     * Define el valor de la propiedad precioBase.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecioBase(BigDecimal value) {
        this.precioBase = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaDescuento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTasaDescuento() {
        return tasaDescuento;
    }

    /**
     * Define el valor de la propiedad tasaDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTasaDescuento(BigDecimal value) {
        this.tasaDescuento = value;
    }

    /**
     * Obtiene el valor de la propiedad precioFactoraje.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecioFactoraje() {
        return precioFactoraje;
    }

    /**
     * Define el valor de la propiedad precioFactoraje.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecioFactoraje(BigDecimal value) {
        this.precioFactoraje = value;
    }

    /**
     * Obtiene el valor de la propiedad importeDescuento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteDescuento() {
        return importeDescuento;
    }

    /**
     * Define el valor de la propiedad importeDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteDescuento(BigDecimal value) {
        this.importeDescuento = value;
    }

}
