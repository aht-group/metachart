
package org.metachart.xml.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="number"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ticker"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="date"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ticker" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="timePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                           &lt;attribute name="format" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="autoRangeTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;attribute name="majorTickTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
    "number",
    "date"
})
@XmlRootElement(name = "axisType")
public class AxisType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "", required = true)
    protected AxisType.Number number;
    @XmlElement(namespace = "", required = true)
    protected AxisType.Date date;

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link AxisType.Number }
     *     
     */
    public AxisType.Number getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link AxisType.Number }
     *     
     */
    public void setNumber(AxisType.Number value) {
        this.number = value;
    }

    public boolean isSetNumber() {
        return (this.number!= null);
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link AxisType.Date }
     *     
     */
    public AxisType.Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link AxisType.Date }
     *     
     */
    public void setDate(AxisType.Date value) {
        this.date = value;
    }

    public boolean isSetDate() {
        return (this.date!= null);
    }


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
     *         &lt;element name="ticker" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="timePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                 &lt;attribute name="format" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="autoRangeTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *       &lt;attribute name="majorTickTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ticker"
    })
    public static class Date
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(namespace = "", required = true)
        protected List<AxisType.Date.Ticker> ticker;
        @XmlAttribute(name = "autoRangeTimePeriod")
        protected String autoRangeTimePeriod;
        @XmlAttribute(name = "majorTickTimePeriod")
        protected String majorTickTimePeriod;

        /**
         * Gets the value of the ticker property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ticker property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTicker().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AxisType.Date.Ticker }
         * 
         * 
         */
        public List<AxisType.Date.Ticker> getTicker() {
            if (ticker == null) {
                ticker = new ArrayList<AxisType.Date.Ticker>();
            }
            return this.ticker;
        }

        public boolean isSetTicker() {
            return ((this.ticker!= null)&&(!this.ticker.isEmpty()));
        }

        public void unsetTicker() {
            this.ticker = null;
        }

        /**
         * Gets the value of the autoRangeTimePeriod property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAutoRangeTimePeriod() {
            return autoRangeTimePeriod;
        }

        /**
         * Sets the value of the autoRangeTimePeriod property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAutoRangeTimePeriod(String value) {
            this.autoRangeTimePeriod = value;
        }

        public boolean isSetAutoRangeTimePeriod() {
            return (this.autoRangeTimePeriod!= null);
        }

        /**
         * Gets the value of the majorTickTimePeriod property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMajorTickTimePeriod() {
            return majorTickTimePeriod;
        }

        /**
         * Sets the value of the majorTickTimePeriod property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMajorTickTimePeriod(String value) {
            this.majorTickTimePeriod = value;
        }

        public boolean isSetMajorTickTimePeriod() {
            return (this.majorTickTimePeriod!= null);
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="timePeriod" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *       &lt;attribute name="format" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Ticker
            implements Serializable
        {

            private final static long serialVersionUID = 1L;
            @XmlAttribute(name = "timePeriod")
            protected String timePeriod;
            @XmlAttribute(name = "format")
            protected String format;

            /**
             * Gets the value of the timePeriod property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTimePeriod() {
                return timePeriod;
            }

            /**
             * Sets the value of the timePeriod property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTimePeriod(String value) {
                this.timePeriod = value;
            }

            public boolean isSetTimePeriod() {
                return (this.timePeriod!= null);
            }

            /**
             * Gets the value of the format property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormat() {
                return format;
            }

            /**
             * Sets the value of the format property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormat(String value) {
                this.format = value;
            }

            public boolean isSetFormat() {
                return (this.format!= null);
            }

        }

    }


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
     *         &lt;element name="ticker"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
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
        "ticker"
    })
    public static class Number
        implements Serializable
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(namespace = "", required = true)
        protected AxisType.Number.Ticker ticker;

        /**
         * Gets the value of the ticker property.
         * 
         * @return
         *     possible object is
         *     {@link AxisType.Number.Ticker }
         *     
         */
        public AxisType.Number.Ticker getTicker() {
            return ticker;
        }

        /**
         * Sets the value of the ticker property.
         * 
         * @param value
         *     allowed object is
         *     {@link AxisType.Number.Ticker }
         *     
         */
        public void setTicker(AxisType.Number.Ticker value) {
            this.ticker = value;
        }

        public boolean isSetTicker() {
            return (this.ticker!= null);
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Ticker
            implements Serializable
        {

            private final static long serialVersionUID = 1L;
            @XmlAttribute(name = "size")
            protected Integer size;

            /**
             * Gets the value of the size property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public int getSize() {
                return size;
            }

            /**
             * Sets the value of the size property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setSize(int value) {
                this.size = value;
            }

            public boolean isSetSize() {
                return (this.size!= null);
            }

            public void unsetSize() {
                this.size = null;
            }

        }

    }

}
