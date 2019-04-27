/**
 * 26/1/2019
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.payment.zarinpal;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Authorities" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "authorities"
})
@XmlRootElement(name = "GetUnverifiedTransactionsResponse")
public class GetUnverifiedTransactionsResponse {

    @XmlElement(name = "Status")
    protected int status;
    @XmlElement(name = "Authorities", required = true)
    protected String authorities;

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the authorities property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * Sets the value of the authorities property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorities(String value) {
        this.authorities = value;
    }

}
