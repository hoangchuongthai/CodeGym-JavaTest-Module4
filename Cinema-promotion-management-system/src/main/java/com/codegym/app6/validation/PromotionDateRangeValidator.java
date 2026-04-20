package com.codegym.app6.validation;

import com.codegym.app6.dto.PromotionForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PromotionDateRangeValidator implements ConstraintValidator<ValidPromotionDateRange, PromotionForm> {

    @Override
    public boolean isValid(PromotionForm value, ConstraintValidatorContext context) {
        if (value == null || value.getStartDate() == null || value.getEndDate() == null) {
            return true;
        }

        LocalDate minEndDate = value.getStartDate().plusDays(1);
        boolean valid = !value.getEndDate().isBefore(minEndDate);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Thời gian kết thúc phải lớn hơn thời gian bắt đầu ít nhất 1 ngày")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
        }
        return valid;
    }
}
