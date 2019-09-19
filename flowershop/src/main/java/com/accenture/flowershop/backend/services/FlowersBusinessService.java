package com.accenture.flowershop.backend.services;

import com.accenture.flowershop.backend.entity.Flower;

import java.util.List;

public interface FlowersBusinessService {
    /**
     * Get all flowers for Table
     * @return List<Flower>
     */
    List<Flower> getAllFlowers();

    /**
     * Add amount flowers
     * @param amount add to a flower
     */
    public void updateFlowersAmount(long amount);
}
