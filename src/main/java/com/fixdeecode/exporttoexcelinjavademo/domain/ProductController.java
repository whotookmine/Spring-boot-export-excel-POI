package com.fixdeecode.exporttoexcelinjavademo.domain;

import com.fixdeecode.exporttoexcelinjavademo.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "product/")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        productService.exportProductTableToExcel(response);
    }
}
