package com.sda.studysystem.controllers;

import com.sda.studysystem.models.Category;
import com.sda.studysystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller to Category requests
 *
 * @author VinodJohn
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String showAllCategories(@ModelAttribute("messageType") String messageType, @ModelAttribute("message") String message,
                                    Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/category-list";
    }

    @GetMapping("/add")
    public String addCategoryForm(@ModelAttribute("category") Category category, @ModelAttribute("messageType") String messageType,
                                  @ModelAttribute("message") String message) {
        return "category/category-add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid Category category, RedirectAttributes redirectAttributes) {
        boolean createResult = categoryService.createCategory(category);

        if (createResult) {
            redirectAttributes.addFlashAttribute("message", "Category has been successfully created.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category/";
        } else {
            redirectAttributes.addFlashAttribute("category", category);
            redirectAttributes.addFlashAttribute("message", "Error in creating a category!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/category/add";
        }
    }

    @GetMapping("/update/{id}")
    public String updateCategoryForm(@PathVariable("id") Long categoryId, @RequestParam(value = "category", required = false) Category category,
                                     @ModelAttribute("messageType") String messageType,
                                     @ModelAttribute("message") String message, Model model) {
        if (category == null) {
            model.addAttribute("category", categoryService.getById(categoryId));
        }

        return "category/category-update";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long categoryId, @Valid Category category, RedirectAttributes redirectAttributes) {
        category.setId(categoryId);
        boolean updateResult = categoryService.updateCategory(category);

        if (updateResult) {
            redirectAttributes.addFlashAttribute("message", "Category #" + categoryId + " has been successfully updated.");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/category/";
        } else {
            redirectAttributes.addAttribute("id", categoryId);
            redirectAttributes.addAttribute("category", category);
            redirectAttributes.addFlashAttribute("message", "Error in updating this category #" + categoryId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/category/update/{id}";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId, RedirectAttributes redirectAttributes) {
        boolean deleteResult = categoryService.deleteCategoryById(categoryId);

        if (deleteResult) {
            redirectAttributes.addFlashAttribute("message", "Category #" + categoryId + " has been successfully deleted.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in deleting category #" + categoryId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/category/";
    }

    @GetMapping("/restore/{id}")
    public String restoreCategory(@PathVariable("id") Long categoryId, RedirectAttributes redirectAttributes) {
        boolean restoreResult = categoryService.restoreCategoryById(categoryId);

        if (restoreResult) {
            redirectAttributes.addFlashAttribute("message", "Category #" + categoryId + " has been successfully restored.");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error in restoring category #" + categoryId + "!");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/category/";
    }
}


