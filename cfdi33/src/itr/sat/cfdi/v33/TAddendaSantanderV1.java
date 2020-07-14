
package itr.sat.cfdi.v33;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para elemento raíz.
 * 
 * <p>Clase Java para TAddendaSantanderV1 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TAddendaSantanderV1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InformacionPago" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TInformacionPago" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="InformacionEmision" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TInformacionEmision" minOccurs="0"/&gt;
 *         &lt;element name="Inmuebles" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TInmuebles" minOccurs="0"/&gt;
 *         &lt;element name="Basilea" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TBasilea" minOccurs="0"/&gt;
 *         &lt;element name="CampoAdicional" type="{http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1}TCampoAdicional" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAddendaSantanderV1", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1", propOrder = {
    "informacionPago",
    "informacionEmision",
    "inmuebles",
    "basilea",
    "campoAdicional"
})
public class TAddendaSantanderV1 {

    @XmlElement(name = "InformacionPago", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected List<TInformacionPago> informacionPago;
    @XmlElement(name = "InformacionEmision", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected TInformacionEmision informacionEmision;
    @XmlElement(name = "Inmuebles", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected TInmuebles inmuebles;
    @XmlElement(name = "Basilea", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected TBasilea basilea;
    @XmlElement(name = "CampoAdicional", namespace = "http://www.santander.com.mx/schemas/xsd/AddendaSantanderV1")
    protected List<TCampoAdicional> campoAdicional;

    /**
     * Gets the value of the informacionPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informacionPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformacionPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TInformacionPago }
     * 
     * 
     */
    public List<TInformacionPago> getInformacionPago() {
        if (informacionPago == null) {
            informacionPago = new ArrayList<TInformacionPago>();
        }
        return this.informacionPago;
    }

    /**
     * Obtiene el valor de la propiedad informacionEmision.
     * 
     * @return
     *     possible object is
     *     {@link TInformacionEmision }
     *     
     */
    public TInformacionEmision getInformacionEmision() {
        return informacionEmision;
    }

    /**
     * Define el valor de la propiedad informacionEmision.
     * 
     * @param value
     *     allowed object is
     *     {@link TInformacionEmision }
     *     
     */
    public void setInformacionEmision(TInformacionEmision value) {
        this.informacionEmision = value;
    }

    /**
     * Obtiene el valor de la propiedad inmuebles.
     * 
     * @return
     *     possible object is
     *     {@link TInmuebles }
     *     
     */
    public TInmuebles getInmuebles() {
        return inmuebles;
    }

    /**
     * Define el valor de la propiedad inmuebles.
     * 
     * @param value
     *     allowed object is
     *     {@link TInmuebles }
     *     
     */
    public void setInmuebles(TInmuebles value) {
        this.inmuebles = value;
    }

    /**
     * Obtiene el valor de la propiedad basilea.
     * 
     * @return
     *     possible object is
     *     {@link TBasilea }
     *     
     */
    public TBasilea getBasilea() {
        return basilea;
    }

    /**
     * Define el valor de la propiedad basilea.
     * 
     * @param value
     *     allowed object is
     *     {@link TBasilea }
     *     
     */
    public void setBasilea(TBasilea value) {
        this.basilea = value;
    }

    /**
     * Gets the value of the campoAdicional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the campoAdicional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCampoAdicional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCampoAdicional }
     * 
     * 
     */
    public List<TCampoAdicional> getCampoAdicional() {
        if (campoAdicional == null) {
            campoAdicional = new ArrayList<TCampoAdicional>();
        }
        return this.campoAdicional;
    }

}
