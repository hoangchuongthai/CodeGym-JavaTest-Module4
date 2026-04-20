package com.codegym.app6.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PromotionDateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPromotionDateRange {
    String message() default "Thời gian kết thúc phải lớn hơn thời gian bắt đầu ít nhất 1 ngày";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
