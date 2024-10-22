package vn.iotstar.Controllers;

import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.iotstar.Entity.Category;
import vn.iotstar.Services.CategoryService;
import vn.iotstar.model.CategoryModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("")
    public String findAllCategory(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setIsEdit(false);
        model.addAttribute("category", categoryModel);
        return "admin/category/add";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCategory(ModelMap model, @PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        CategoryModel categoryModel = new CategoryModel();
        if (category.isPresent()) {
            Category category1 = category.get();
//            Copy tu entity sang categoryModel
            BeanUtils.copyProperties(category1, categoryModel);
            categoryModel.setIsEdit(true);
            model.addAttribute("category", categoryModel);
            return new ModelAndView("admin/category/add");
        }
        model.addAttribute("message", "Category not found");
        return new ModelAndView("forward:/admin/categories",model);
    }

    @PostMapping("/insert")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/category/add");
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryModel, category);
        categoryService.save(category);
        String message="";
        if (categoryModel.getIsEdit() == true){
            message = "Category updated successfully";
        }
        else {
            message = "Category is save";
        }
        model.addAttribute("message", message);
        return new ModelAndView("forward:/admin/categories",model);
    }
}
