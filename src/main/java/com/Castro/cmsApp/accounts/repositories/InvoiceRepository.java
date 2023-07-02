package com.Castro.cmsApp.accounts.repositories;

import com.Castro.cmsApp.accounts.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
