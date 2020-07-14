
package itr.sat.cfdi.v33;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para campos adicionales.
 * 
 * <p>Clase Java para TCampoAdicional complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TCampoAdicional"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="campo" use="required" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TCampoValor" /&gt;
 *       &lt;attribute name="valor" use="required" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TCampoValor" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCampoAdicional", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
public class TCampoAdicional {

    @XmlAttribute(name = "campo", required = true)
    protected String campo;
    @XmlAttribute(name = "valor", required = true)
    protected String valor;

    /**
     * Obtiene el valor de la propiedad campo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampo() {
        return campo;
    }

    /**
     * Define el valor de la propiedad campo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampo(String value) {
        this.campo = value;
    }

    /**
     * Obtiene el valor de la propiedad valor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValor() {
        return valor;
    }

    /**
     * Define el valor de la propiedad valor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValor(String value) {
        this.valor = value;
    }

}
