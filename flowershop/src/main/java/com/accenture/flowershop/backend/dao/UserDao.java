package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Administrator;
import com.accenture.flowershop.backend.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public User getOne(String login) {
        return entityManager.find(User.class, login);
    }

    @Transactional
    public void add(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }
}
