package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Flower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import java.math.BigDecimal;
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
        entityManager.flush();
    }

    @Transactional
    public void update(Flower flower) {
        entityManager.merge(flower);
        entityManager.flush();
    }

    public List<Flower> searchOnName(String name) {
        TypedQuery<Flower> q = entityManager.createQuery("select u from Flower u where u.name like :name ", Flower.class);
        q.setParameter("name", name + "%");
        return q.getResultList();
    }


    public List<Flower> searchMinAndMax(BigDecimal min, BigDecimal max) {
        TypedQuery<Flower> q = entityManager.createQuery("select u from Flower u where u.price > :min and u.price < :max", Flower.class);
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }

    public List<Flower> searchOnPrice() {
        return null;
    }

}
