package ozi.app.customer.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ozi.app.customer.data.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String > {
}
