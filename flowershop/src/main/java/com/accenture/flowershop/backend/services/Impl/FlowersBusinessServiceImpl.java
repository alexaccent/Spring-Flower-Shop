package com.accenture.flowershop.backend.services.Impl;

import com.accenture.flowershop.backend.dao.FlowerDao;
import com.accenture.flowershop.backend.entity.Flower;
import com.accenture.flowershop.backend.services.FlowersBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Flower> searchFlowers(String name) {

        // Fixed
        return flowerDao.searchOnName(name);
    }

    public List<Flower> searchMinAndMaxPrice(String min, String max) {

        BigDecimal minbigDecimal = new BigDecimal(min);
        BigDecimal maxbigDecimal = new BigDecimal(max);
        return flowerDao.searchMinAndMax(minbigDecimal, maxbigDecimal);
    }
}
