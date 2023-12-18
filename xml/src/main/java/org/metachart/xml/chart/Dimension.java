
package org.metachart.xml.chart;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="ratio" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "dimension")
public class Dimension
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "height")
    protected Integer height;
    @XmlAttribute(name = "width")
    protected Integer width;
    @XmlAttribute(name = "ratio")
    protected Double ratio;

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHeight(Integer value) {
        this.height = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWidth(Integer value) {
        this.width = value;
    }

    /**
     * Gets the value of the ratio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * Sets the value of the ratio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRatio(Double value) {
        this.ratio = value;
    }

}
