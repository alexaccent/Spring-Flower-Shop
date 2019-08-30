package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Flower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class FlowerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Flower> getAll() {
        return entityManager.createQuery("select u from Flower u", Flower.class).getResultList();
    }

    public Flower getOne(Long id) {
        return entityManager.find(Flower.class, id);
    }

    @Transactional
    public void add(Flower flower) {
        entityManager.persist(flower);
    }
}