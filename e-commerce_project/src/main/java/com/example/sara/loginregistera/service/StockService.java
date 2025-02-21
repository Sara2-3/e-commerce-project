package com.example.sara.loginregistera.service;

import com.example.sara.loginregistera.Size;
import com.example.sara.loginregistera.model.Stock;
import com.example.sara.loginregistera.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public boolean isSizeAvailable(Long productId, Size size, int quantity) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        return stock != null && stock.getQuantity() >= quantity;
    }

    public void reduceStock(Long productId, Size size, int quantity) {
        Stock stock = stockRepository.findByProductIdAndSize(productId, size);
        if (stock != null) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);
        }
    }
}