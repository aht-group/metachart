
package org.metachart.model.xml.chart;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.metachart.org/xml}axisType"/&gt;
 *         &lt;element ref="{http://www.metachart.org/xml}label"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="dateformat" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="autoRangIncludeNull" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "axisType",
    "label"
})
@XmlRootElement(name = "axis")
public class Axis
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected AxisType axisType;
    @XmlElement(required = true)
    protected Label label;
    @XmlAttribute(name = "code")
    protected String code;
    @XmlAttribute(name = "dateformat")
    protected String dateformat;
    @XmlAttribute(name = "autoRangIncludeNull")
    protected Boolean autoRangIncludeNull;

    /**
     * Gets the value of the axisType property.
     * 
     * @return
     *     possible object is
     *     {@link AxisType }
     *     
     */
    public AxisType getAxisType() {
        return axisType;
    }

    /**
     * Sets the value of the axisType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AxisType }
     *     
     */
    public void setAxisType(AxisType value) {
        this.axisType = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link Label }
     *     
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link Label }
     *     
     */
    public void setLabel(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the dateformat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateformat() {
        return dateformat;
    }

    /**
     * Sets the value of the dateformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateformat(String value) {
        this.dateformat = value;
    }

    /**
     * Gets the value of the autoRangIncludeNull property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoRangIncludeNull() {
        return autoRangIncludeNull;
    }

    /**
     * Sets the value of the autoRangIncludeNull property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoRangIncludeNull(Boolean value) {
        this.autoRangIncludeNull = value;
    }

}
