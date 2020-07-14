
package itr.sat.cfdi.v33;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para Información de Inmuebles.
 * 
 * <p>Clase Java para TInmuebles complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TInmuebles"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="numContrato" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInmuebles", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
public class TInmuebles {

    @XmlAttribute(name = "fechaVencimiento")
    protected String fechaVencimiento;
    @XmlAttribute(name = "numContrato")
    protected String numContrato;

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

}
