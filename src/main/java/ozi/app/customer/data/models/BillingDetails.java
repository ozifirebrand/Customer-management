package ozi.app.customer.data.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class BillingDetails {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(strategy="uuid", name="system-uuid")
    private String id;

    @Column(unique = true)
    private String accountNumber;

    @Column
    private Double tariff;

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

}
