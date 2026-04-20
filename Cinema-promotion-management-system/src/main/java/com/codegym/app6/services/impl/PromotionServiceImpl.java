package com.codegym.app6.services.impl;

import com.codegym.app6.dto.PromotionForm;
import com.codegym.app6.dto.PromotionSearchForm;
import com.codegym.app6.entities.Promotion;
import com.codegym.app6.exception.ResourceNotFoundException;
import com.codegym.app6.repositories.PromotionRepository;
import com.codegym.app6.services.PromotionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> findAll() {
        return promotionRepository.search(null, null, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> search(PromotionSearchForm searchForm) {
        if (searchForm == null) {
            return findAll();
        }
        return promotionRepository.search(
                searchForm.getDiscountAmount(),
                searchForm.getStartDate(),
                searchForm.getEndDate()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionForm findFormById(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khuyến mãi với id = " + id));

        PromotionForm form = new PromotionForm();
        form.setId(promotion.getId());
        form.setTitle(promotion.getTitle());
        form.setStartDate(promotion.getStartDate());
        form.setEndDate(promotion.getEndDate());
        form.setDiscountAmount(promotion.getDiscountAmount());
        form.setDetails(promotion.getDetails());
        return form;
    }

    @Override
    public void create(PromotionForm form) {
        Promotion promotion = new Promotion();
        mapFormToEntity(form, promotion);
        promotionRepository.save(promotion);
    }

    @Override
    public void update(Long id, PromotionForm form) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khuyến mãi với id = " + id));
        mapFormToEntity(form, promotion);
        promotionRepository.save(promotion);
    }

    @Override
    public void deleteById(Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khuyến mãi với id = " + id));
        promotionRepository.delete(promotion);
    }

    private void mapFormToEntity(PromotionForm form, Promotion promotion) {
        promotion.setTitle(form.getTitle().trim());
        promotion.setStartDate(form.getStartDate());
        promotion.setEndDate(form.getEndDate());
        promotion.setDiscountAmount(form.getDiscountAmount());
        promotion.setDetails(form.getDetails().trim());
    }
}
