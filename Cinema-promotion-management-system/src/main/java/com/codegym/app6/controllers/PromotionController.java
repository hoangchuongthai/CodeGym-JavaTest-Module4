package com.codegym.app6.controllers;

import com.codegym.app6.dto.PromotionForm;
import com.codegym.app6.dto.PromotionSearchForm;
import com.codegym.app6.services.PromotionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @ModelAttribute("searchForm")
    public PromotionSearchForm searchForm() {
        return new PromotionSearchForm();
    }

    @GetMapping
    public String list(@ModelAttribute("searchForm") PromotionSearchForm searchForm, Model model) {
        model.addAttribute("promotions", promotionService.search(searchForm));
        model.addAttribute("totalCount", promotionService.search(searchForm).size());
        return "promotions/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("promotionForm", new PromotionForm());
        model.addAttribute("mode", "create");
        model.addAttribute("pageTitle", "Thêm mới khuyến mãi");
        return "promotions/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("promotionForm") PromotionForm promotionForm,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("pageTitle", "Thêm mới khuyến mãi");
            return "promotions/form";
        }
        promotionService.create(promotionForm);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm khuyến mãi thành công.");
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("promotionForm", promotionService.findFormById(id));
        model.addAttribute("mode", "edit");
        model.addAttribute("pageTitle", "Cập nhật khuyến mãi");
        return "promotions/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("promotionForm") PromotionForm promotionForm,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("pageTitle", "Cập nhật khuyến mãi");
            return "promotions/form";
        }
        promotionService.update(id, promotionForm);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật khuyến mãi thành công.");
        return "redirect:/promotions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        promotionService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa khuyến mãi thành công.");
        return "redirect:/promotions";
    }
}
