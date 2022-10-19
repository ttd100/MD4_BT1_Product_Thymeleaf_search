package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Product;
import rikkei.academy.service.IProductService;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listProduct",productService.findAll());
        return "index";
    }
    @GetMapping("/showCreate")
    public String showCreate(Model model){
        Product student =new Product();
        model.addAttribute("showCreate",student);
        return "create";
    }
    @PostMapping("/create")
    public String create(Product student){
//        int lastIndex =studentService.findAll().size()-1;
//        int lastId = studentService.findAll().get(lastIndex).getId();
//        student.setId(lastId+1);
        productService.save(student);
        return "redirect:/";
    }
    @GetMapping("/showEdit")
    public String showEdit(@RequestParam int id,Model model ){
        productService.findById(id);
        Product product = productService.findById(id);
        model.addAttribute("editProduct",product);
        return "edit";
    }
    @PostMapping("/edit")
    public String edit(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/";
    }
    @GetMapping("/showDelete")
    public String showDelete(@RequestParam int id,Model model) {
        productService.findById(id);
        Product student = productService.findById(id);
        model.addAttribute("delete",student);
        return "delete";
    }
    @PostMapping("/delete")
    public String delete(Product product) {
        productService.delete(product.getId());
        return "redirect:/";
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam String nameProduct){
        ModelAndView modelAndView = new ModelAndView("/index");
        if (nameProduct==null||nameProduct.equals("")) {
            modelAndView.addObject("listProduct",productService.findAll());
        }else {
            modelAndView.addObject("listProduct",productService.findByName(nameProduct));
        }
        return modelAndView;
    }
}
