package com.fixdeecode.exporttoexcelinjavademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer id;
    @Column(name = "prod_name")
    private String name;
    @Column(name = "prod_price")
    private BigDecimal price;
    @Column(name = "prod_in_stock_date")
    private Date inStockDate;

    public ProductModel(String name, BigDecimal price, Date inStockDate) {
        this.name = name;
        this.price = price;
        this.inStockDate = inStockDate;
    }
}
