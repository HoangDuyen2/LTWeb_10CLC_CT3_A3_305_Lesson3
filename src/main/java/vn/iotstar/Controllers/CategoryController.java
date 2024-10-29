package vn.iotstar.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.iotstar.Entity.Category;
import vn.iotstar.Services.CategoryService;
import vn.iotstar.model.CategoryModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/delete/{id}")
    public String deleteCategory(ModelMap model, @PathVariable Long id) {
        if(!categoryService.deleteById(id))
            model.addAttribute("message", "Category not found");
        return ("redirect:/admin/categories");
    }

    @PostMapping("/insert")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/admin/category/add");
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
        return new ModelAndView("redirect:/admin/categories",model);
    }

    @RequestMapping("/searchpaginated")
    public String search(ModelMap model, @RequestParam(name = "name",required = false) String name,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {
//        Đếm số dòng dữ liệu
        int count = (int) categoryService.count();
//        Nếu có thì trả về giá trị không thì trả về 1
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

//        Tạo ra một page request với các param có sort
        Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
        Page<Category> resultPage = null;

        if(StringUtils.hasText(name)) {
            resultPage = categoryService.findByNameContaining(name,pageable);
            model.addAttribute("name",name);
        }else {
            resultPage = categoryService.findAll(pageable);
        }
        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0) {
//            Số phân trang ở start: so sánh giữa 1 và currentPage-2 để lây số lớn hơn
            int start = Math.max(1, currentPage-2);
//            Số phân trang ở END: so sánh giữa currentPage-2  và totalPages để lây số lớn hơn
            int end = Math.min(currentPage + 2, totalPages);
            if(totalPages > count) {
                if(end == totalPages) start = end - count;
                else if (start == 1) end = start + count;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        model.addAttribute("categoryPage",resultPage);
        return "/admin/category/list-page";
    }
}
