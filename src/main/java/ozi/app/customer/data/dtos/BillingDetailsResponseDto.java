package ozi.app.customer.data.dtos;

public class BillingDetailsResponseDto {
    private String id;
    private String accountNumber;
    private Double tariff;

    public void setId(String id){
        this.id= id;
    }
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
