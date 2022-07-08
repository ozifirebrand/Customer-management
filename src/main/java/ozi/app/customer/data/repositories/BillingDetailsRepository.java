package ozi.app.customer.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ozi.app.customer.data.models.BillingDetails;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, String> {
}
