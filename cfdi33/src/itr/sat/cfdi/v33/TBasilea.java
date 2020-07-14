
package itr.sat.cfdi.v33;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Estructura para los campos de Basilea.
 * 
 * <p>Clase Java para TBasilea complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TBasilea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="numContrato" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="OrigenGasto" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="TipoGasto" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBasilea", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
public class TBasilea {

    @XmlAttribute(name = "numContrato")
    protected String numContrato;
    @XmlAttribute(name = "OrigenGasto")
    protected String origenGasto;
    @XmlAttribute(name = "TipoGasto")
    protected String tipoGasto;

    /**
     * Obtiene el valor de la propiedad numContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumContrato() {
        return numContrato;
    }

    /**
     * Define el valor de la propiedad numContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumContrato(String value) {
        this.numContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad origenGasto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenGasto() {
        return origenGasto;
    }

    /**
     * Define el valor de la propiedad origenGasto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenGasto(String value) {
        this.origenGasto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoGasto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoGasto() {
        return tipoGasto;
    }

    /**
     * Define el valor de la propiedad tipoGasto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoGasto(String value) {
        this.tipoGasto = value;
    }

}
