package com.example.hazelfinalexam.web;

import com.example.hazelfinalexam.entities.Sales;
import com.example.hazelfinalexam.repositories.SalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Controller
@ControllerAdvice
public class SalesController {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @GetMapping("/index")
    public String sales(Model model) {
        List<Sales> salesList = salesRepository.findAll();
        model.addAttribute("transactions", salesList);
        return "sales";
    }

    @GetMapping("/edit-transaction/{id}")
    public String editTransaction(@PathVariable BigInteger id, Model model) {
        try {
            Optional<Sales> salesOptional = salesRepository.findById(id);
            if (salesOptional.isPresent()) {
                Sales sales = salesOptional.get();
                model.addAttribute("editedTransaction", sales);
            } else {
                throw new EntityNotFoundException("Transaction not found");
            }
        } catch (EntityNotFoundException ex) {
            return "error"; // Handle the error by displaying an error page
        }
        return "edit-transaction";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable BigInteger id) {
        salesRepository.deleteById(id);
        return "redirect:/";
    }
}
