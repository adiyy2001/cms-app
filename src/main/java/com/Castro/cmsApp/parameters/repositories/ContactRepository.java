package com.Castro.cmsApp.parameters.repositories;

import com.Castro.cmsApp.parameters.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
