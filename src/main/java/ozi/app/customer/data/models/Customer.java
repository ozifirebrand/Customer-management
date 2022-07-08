package ozi.app.customer.data.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(strategy="uuid", name="system-uuid")
    private String id;

    @Column
    private String firstName;
    @Column
    private String lastName;


    @Column(unique = true)
    private String email;

    @OneToOne(cascade =  CascadeType.MERGE, fetch = FetchType.EAGER)
    private BillingDetails billingDetails;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}

