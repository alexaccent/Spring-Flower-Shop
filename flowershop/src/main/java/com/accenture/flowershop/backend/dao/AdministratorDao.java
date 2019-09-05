package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Administrator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AdministratorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Administrator> getAll() {
        return entityManager.createQuery("select u from Administrator u", Administrator.class).getResultList();
    }

    public Administrator getOne(String login) {
        return entityManager.find(Administrator.class, login);
    }

    @Transactional
    public void add(Administrator administrator) {
        entityManager.persist(administrator);
        entityManager.flush();
    }

    @Transactional
    public void update(Administrator administrator) {
        entityManager.merge(administrator);
        entityManager.flush();
    }
}
