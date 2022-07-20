package com.example.app1.app1.controller;

import com.example.app1.app1.model.Customer;
import com.example.app1.app1.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    // injected repository
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String home(){
        return "HomePage";
    }

    @GetMapping("/list")
    public List<Customer> list(){
        return this.customerRepository.findAll();
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer){
        return this.customerRepository.save(customer);
    }

    @PutMapping("/update/id/{id}")
    public void update(@RequestBody Customer customerInput, @PathVariable Integer id){
        // create temporary customer object to hold input firstname, lastname - then save it to
        // this customer's repository
        Customer customer = this.customerRepository.findById(id).get();
        // how do you set customer's firstname and lastname to customer input's firstname and lastname?
        customer.setFirstName(customerInput.getFirstName());
        customer.setLastName(customerInput.getLastName());
        // save this object with new firstname, lastname to customerRepository
        this.customerRepository.save(customer);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Customer customer){
        this.customerRepository.delete(customer);
    }
}

