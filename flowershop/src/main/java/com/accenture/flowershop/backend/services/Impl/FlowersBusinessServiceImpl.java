package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.FlowerDao;
import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.services.FlowersBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowersBusinessServiceImpl implements FlowersBusinessService {

    @Autowired
    private FlowerDao flowerDao;

    public List<Flower> flowerForTable() {
        return  flowerDao.getAll();
    }

    public void updateFlowersAmount(long amount) {

        List<Flower> flowers = flowerDao.getAll();

        for(Flower flower : flowers) {

            long newFlowerAmount = flower.getAmount() + amount;
            Flower flowerFromBd = flowerDao.getOne(flower.getId());
            flowerFromBd.setAmount(newFlowerAmount);

            flowerDao.update(flowerFromBd);
        }
    }
}
