
package org.metachart.model.xml.graph;

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
 *         &lt;element ref="{http://www.metachart.org/graph}nodes"/&gt;
 *         &lt;element ref="{http://www.metachart.org/graph}edges"/&gt;
 *         &lt;element ref="{http://www.metachart.org/graph}clusters"/&gt;
 *         &lt;element ref="{http://www.metachart.org/graph}dot"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="code" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nodes",
    "edges",
    "clusters",
    "dot"
})
@XmlRootElement(name = "graph")
public class Graph
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected Nodes nodes;
    @XmlElement(required = true)
    protected Edges edges;
    @XmlElement(required = true)
    protected Clusters clusters;
    @XmlElement(required = true)
    protected Dot dot;
    @XmlAttribute(name = "code")
    protected String code;

    /**
     * Gets the value of the nodes property.
     * 
     * @return
     *     possible object is
     *     {@link Nodes }
     *     
     */
    public Nodes getNodes() {
        return nodes;
    }

    /**
     * Sets the value of the nodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nodes }
     *     
     */
    public void setNodes(Nodes value) {
        this.nodes = value;
    }

    /**
     * Gets the value of the edges property.
     * 
     * @return
     *     possible object is
     *     {@link Edges }
     *     
     */
    public Edges getEdges() {
        return edges;
    }

    /**
     * Sets the value of the edges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Edges }
     *     
     */
    public void setEdges(Edges value) {
        this.edges = value;
    }

    /**
     * Gets the value of the clusters property.
     * 
     * @return
     *     possible object is
     *     {@link Clusters }
     *     
     */
    public Clusters getClusters() {
        return clusters;
    }

    /**
     * Sets the value of the clusters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Clusters }
     *     
     */
    public void setClusters(Clusters value) {
        this.clusters = value;
    }

    /**
     * Gets the value of the dot property.
     * 
     * @return
     *     possible object is
     *     {@link Dot }
     *     
     */
    public Dot getDot() {
        return dot;
    }

    /**
     * Sets the value of the dot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dot }
     *     
     */
    public void setDot(Dot value) {
        this.dot = value;
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

}
