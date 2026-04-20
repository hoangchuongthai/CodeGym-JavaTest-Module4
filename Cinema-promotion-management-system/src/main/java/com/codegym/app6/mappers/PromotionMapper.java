package com.codegym.app6.mappers;

import com.codegym.app6.dto.PromotionForm;
import com.codegym.app6.entities.Promotion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromotionMapper {

    private final ModelMapper modelMapper;

    public PromotionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Entity -> DTO
    public PromotionForm toForm(Promotion promotion) {
        return modelMapper.map(promotion, PromotionForm.class);
    }

    // DTO -> Entity
    public Promotion toEntity(PromotionForm form) {
        return modelMapper.map(form, Promotion.class);
    }

    // Update entity (quan trọng)
    public void updateEntity(PromotionForm form, Promotion promotion) {
        promotion.setTitle(form.getTitle());
        promotion.setStartDate(form.getStartDate());
        promotion.setEndDate(form.getEndDate());
        promotion.setDiscountAmount(form.getDiscountAmount());
        promotion.setDetails(form.getDetails());
    }

    // List mapping
    public List<PromotionForm> toForms(List<Promotion> list) {
        return list.stream()
                .map(this::toForm)
                .toList();
    }
}