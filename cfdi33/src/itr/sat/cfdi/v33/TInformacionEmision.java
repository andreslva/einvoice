
package itr.sat.cfdi.v33;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para Información de Emisión.
 * 			
 * 
 * <p>Clase Java para TInformacionEmision complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TInformacionEmision"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InformacionFactoraje" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TFactoraje" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="codigoCliente" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="contrato" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="periodo" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="centroCostos" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="folioInterno" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="claveSantander" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TInformacionEmision", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1", propOrder = {
    "informacionFactoraje"
})
public class TInformacionEmision {

    @XmlElement(name = "InformacionFactoraje", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected List<TFactoraje> informacionFactoraje;
    @XmlAttribute(name = "codigoCliente")
    protected String codigoCliente;
    @XmlAttribute(name = "contrato")
    protected String contrato;
    @XmlAttribute(name = "periodo")
    protected String periodo;
    @XmlAttribute(name = "centroCostos")
    protected String centroCostos;
    @XmlAttribute(name = "folioInterno")
    protected String folioInterno;
    @XmlAttribute(name = "claveSantander")
    protected String claveSantander;

    /**
     * Gets the value of the informacionFactoraje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informacionFactoraje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformacionFactoraje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TFactoraje }
     * 
     * 
     */
    public List<TFactoraje> getInformacionFactoraje() {
        if (informacionFactoraje == null) {
            informacionFactoraje = new ArrayList<TFactoraje>();
        }
        return this.informacionFactoraje;
    }

    /**
     * Obtiene el valor de la propiedad codigoCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Define el valor de la propiedad codigoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCliente(String value) {
        this.codigoCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad contrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Define el valor de la propiedad contrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodo(String value) {
        this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad centroCostos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroCostos() {
        return centroCostos;
    }

    /**
     * Define el valor de la propiedad centroCostos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroCostos(String value) {
        this.centroCostos = value;
    }

    /**
     * Obtiene el valor de la propiedad folioInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioInterno() {
        return folioInterno;
    }

    /**
     * Define el valor de la propiedad folioInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioInterno(String value) {
        this.folioInterno = value;
    }

    /**
     * Obtiene el valor de la propiedad claveSantander.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveSantander() {
        return claveSantander;
    }

    /**
     * Define el valor de la propiedad claveSantander.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveSantander(String value) {
        this.claveSantander = value;
    }

}
