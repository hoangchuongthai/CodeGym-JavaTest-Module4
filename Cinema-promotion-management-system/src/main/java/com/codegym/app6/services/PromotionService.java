package com.codegym.app6.services;

import com.codegym.app6.dto.PromotionForm;
import com.codegym.app6.dto.PromotionSearchForm;
import com.codegym.app6.entities.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> findAll();
    List<Promotion> search(PromotionSearchForm searchForm);
    PromotionForm findFormById(Long id);
    void create(PromotionForm form);
    void update(Long id, PromotionForm form);
    void deleteById(Long id);
}
