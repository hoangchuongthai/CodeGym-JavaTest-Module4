package com.codegym.app6.dto;

import com.codegym.app6.validation.ValidPromotionDateRange;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@ValidPromotionDateRange
public class PromotionForm {

    private Long id;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 255, message = "Tiêu đề tối đa 255 ký tự")
    private String title;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Future(message = "Thời gian bắt đầu phải lớn hơn thời gian hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Mức giảm giá không được để trống")
    @DecimalMin(value = "10000.01", message = "Mức giảm giá phải lớn hơn 10.000 VNĐ")
    @Digits(integer = 13, fraction = 2, message = "Mức giảm giá không hợp lệ")
    private BigDecimal discountAmount;

    @NotBlank(message = "Chi tiết không được để trống")
    @Size(max = 1000, message = "Chi tiết tối đa 1000 ký tự")
    private String details;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
