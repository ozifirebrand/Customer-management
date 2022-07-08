package ozi.app.customer.data.dtos;

public class BillingDetailsRequestDto {
    private String accountNumber;
    private Double tariff;

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
