
package itr.sat.cfdi.v33;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EstadoDeCuentaBancario" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Movimientos" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="MovimientoECB" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;simpleContent&gt;
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                                     &lt;attribute name="descripcion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                     &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *                                     &lt;attribute name="importe" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *                                   &lt;/extension&gt;
 *                                 &lt;/simpleContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="nombreCliente" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="numeroCuenta" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *                 &lt;attribute name="periodo" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                 &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "estadoDeCuentaBancario"
})
@XmlRootElement(name = "addendaECB", namespace = "http://www.santander.com.mx/schemas/xsd/addendaECB")
public class AddendaECB {

    @XmlElement(name = "EstadoDeCuentaBancario", namespace = "http://www.santander.com.mx/schemas/xsd/addendaECB")
    protected AddendaECB.EstadoDeCuentaBancario estadoDeCuentaBancario;

    /**
     * Obtiene el valor de la propiedad estadoDeCuentaBancario.
     * 
     * @return
     *     possible object is
     *     {@link AddendaECB.EstadoDeCuentaBancario }
     *     
     */
    public AddendaECB.EstadoDeCuentaBancario getEstadoDeCuentaBancario() {
        return estadoDeCuentaBancario;
    }

    /**
     * Define el valor de la propiedad estadoDeCuentaBancario.
     * 
     * @param value
     *     allowed object is
     *     {@link AddendaECB.EstadoDeCuentaBancario }
     *     
     */
    public void setEstadoDeCuentaBancario(AddendaECB.EstadoDeCuentaBancario value) {
        this.estadoDeCuentaBancario = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Movimientos" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="MovimientoECB" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;simpleContent&gt;
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                           &lt;attribute name="descripcion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                           &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
     *                           &lt;attribute name="importe" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
     *                         &lt;/extension&gt;
     *                       &lt;/simpleContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="nombreCliente" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="numeroCuenta" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
     *       &lt;attribute name="periodo" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "movimientos"
    })
    public static class EstadoDeCuentaBancario {

        @XmlElement(name = "Movimientos", namespace = "http://www.santander.com.mx/schemas/xsd/addendaECB")
        protected AddendaECB.EstadoDeCuentaBancario.Movimientos movimientos;
        @XmlAttribute(name = "nombreCliente", required = true)
        protected String nombreCliente;
        @XmlAttribute(name = "numeroCuenta", required = true)
        protected int numeroCuenta;
        @XmlAttribute(name = "periodo", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar periodo;
        @XmlAttribute(name = "version", required = true)
        protected BigDecimal version;

        /**
         * Obtiene el valor de la propiedad movimientos.
         * 
         * @return
         *     possible object is
         *     {@link AddendaECB.EstadoDeCuentaBancario.Movimientos }
         *     
         */
        public AddendaECB.EstadoDeCuentaBancario.Movimientos getMovimientos() {
            return movimientos;
        }

        /**
         * Define el valor de la propiedad movimientos.
         * 
         * @param value
         *     allowed object is
         *     {@link AddendaECB.EstadoDeCuentaBancario.Movimientos }
         *     
         */
        public void setMovimientos(AddendaECB.EstadoDeCuentaBancario.Movimientos value) {
            this.movimientos = value;
        }

        /**
         * Obtiene el valor de la propiedad nombreCliente.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreCliente() {
            return nombreCliente;
        }

        /**
         * Define el valor de la propiedad nombreCliente.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreCliente(String value) {
            this.nombreCliente = value;
        }

        /**
         * Obtiene el valor de la propiedad numeroCuenta.
         * 
         */
        public int getNumeroCuenta() {
            return numeroCuenta;
        }

        /**
         * Define el valor de la propiedad numeroCuenta.
         * 
         */
        public void setNumeroCuenta(int value) {
            this.numeroCuenta = value;
        }

        /**
         * Obtiene el valor de la propiedad periodo.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getPeriodo() {
            return periodo;
        }

        /**
         * Define el valor de la propiedad periodo.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setPeriodo(XMLGregorianCalendar value) {
            this.periodo = value;
        }

        /**
         * Obtiene el valor de la propiedad version.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getVersion() {
            return version;
        }

        /**
         * Define el valor de la propiedad version.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setVersion(BigDecimal value) {
            this.version = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="MovimientoECB" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;simpleContent&gt;
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *                 &lt;attribute name="descripcion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *                 &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
         *                 &lt;attribute name="importe" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
         *               &lt;/extension&gt;
         *             &lt;/simpleContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "movimientoECB"
        })
        public static class Movimientos {

            @XmlElement(name = "MovimientoECB", namespace = "http://www.santander.com.mx/schemas/xsd/addendaECB", required = true)
            protected List<AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB> movimientoECB;

            /**
             * Gets the value of the movimientoECB property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the movimientoECB property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMovimientoECB().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB }
             * 
             * 
             */
            public List<AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB> getMovimientoECB() {
                if (movimientoECB == null) {
                    movimientoECB = new ArrayList<AddendaECB.EstadoDeCuentaBancario.Movimientos.MovimientoECB>();
                }
                return this.movimientoECB;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;simpleContent&gt;
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
             *       &lt;attribute name="descripcion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *       &lt;attribute name="fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
             *       &lt;attribute name="importe" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
             *     &lt;/extension&gt;
             *   &lt;/simpleContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "descripcion"
            	,"fecha"
            	,"importe"
            })
            public static class MovimientoECB {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "descripcion", required = true)
                protected String descripcion;
                @XmlAttribute(name = "fecha", required = true)
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar fecha;
                @XmlAttribute(name = "importe", required = true)
                protected BigDecimal importe;

                /**
                 * Obtiene el valor de la propiedad value.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Define el valor de la propiedad value.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Obtiene el valor de la propiedad descripcion.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescripcion() {
                    return descripcion;
                }

                /**
                 * Define el valor de la propiedad descripcion.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescripcion(String value) {
                    this.descripcion = value;
                }

                /**
                 * Obtiene el valor de la propiedad fecha.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getFecha() {
                    return fecha;
                }

                /**
                 * Define el valor de la propiedad fecha.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setFecha(XMLGregorianCalendar value) {
                    this.fecha = value;
                }

                /**
                 * Obtiene el valor de la propiedad importe.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getImporte() {
                    return importe;
                }

                /**
                 * Define el valor de la propiedad importe.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setImporte(BigDecimal value) {
                    this.importe = value;
                }

            }

        }

    }

}
