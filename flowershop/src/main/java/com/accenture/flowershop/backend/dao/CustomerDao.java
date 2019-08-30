package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> getAll() {
        return entityManager.createQuery("select u from Customer u", Customer.class).getResultList();
    }

    public Customer getOne(String login) {
        return entityManager.find(Customer.class, login);
    }

    @Transactional
    public void add(Customer customer) {
        entityManager.persist(customer);
    }
}
