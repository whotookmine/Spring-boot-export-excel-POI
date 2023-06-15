package com.fixdeecode.exporttoexcelinjavademo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class SeedData {
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public SeedData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private List<Customer> customers = Arrays.asList(
            new Customer("Sam", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Annie", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("John", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Jessica", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Anita", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Jacobs", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("White", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Black", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Forrest", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address")),
            new Customer("Sally", "Jefferson", "sam@email.com", new Address("Japan", "some state", " some city","The street address"))
            );

    private List<ProductModel> products = Arrays.asList(
            new ProductModel("Product 1", new BigDecimal("10.99"), new Date()),
            new ProductModel("Product 2", new BigDecimal("19.99"), new Date()),
            new ProductModel("Product 3", new BigDecimal("14.99"), new Date()),
            new ProductModel("Product 4", new BigDecimal("8.99"), new Date()),
            new ProductModel("Product 5", new BigDecimal("24.99"), new Date()),
            new ProductModel("Product 6", new BigDecimal("12.99"), new Date()),
            new ProductModel("Product 7", new BigDecimal("17.99"), new Date()),
            new ProductModel("Product 8", new BigDecimal("9.99"), new Date()),
            new ProductModel("Product 9", new BigDecimal("22.99"), new Date()),
            new ProductModel("Product 10", new BigDecimal("15.99"), new Date())
    );


    @PostConstruct
    public void saveCustomers(){
        customerRepository.saveAll(customers);
        productRepository.saveAll(products);
    }
}
