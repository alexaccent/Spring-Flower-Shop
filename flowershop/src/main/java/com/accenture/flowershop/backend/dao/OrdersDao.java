package com.accenture.flowershop.backend.dao;

import com.accenture.flowershop.backend.entity.Orders;
import com.accenture.flowershop.enums.OrderStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrdersDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Orders> getAll() {
        return entityManager.createQuery("select u from Orders u", Orders.class).getResultList();
    }

    public List<Orders> getByStatus(OrderStatus status) {
        String hql = "select u from Orders u order by u.status ASC, u.ordersDate DESC";
        Query query = entityManager.createQuery(hql);

        return query.getResultList();
    }

    public Orders getOne(Long id) {
        TypedQuery<Orders> q = entityManager.createQuery("select u from Orders u where u.id = :id", Orders.class);
        q.setParameter("id", id);

        return q.getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    public void add(Orders orders) {
        entityManager.persist(orders);
        entityManager.flush();
    }

    @Transactional
    public void update(Orders orders) {
        entityManager.merge(orders);
        entityManager.flush();
    }
}
