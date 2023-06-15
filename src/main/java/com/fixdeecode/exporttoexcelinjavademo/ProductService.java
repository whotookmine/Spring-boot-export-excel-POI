package com.fixdeecode.exporttoexcelinjavademo;

import com.fixdeecode.exporttoexcelinjavademo.domain.ProductExcelExportUtils;
import com.fixdeecode.exporttoexcelinjavademo.domain.ProductModel;
import com.fixdeecode.exporttoexcelinjavademo.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;

    public void exportProductTableToExcel(HttpServletResponse response) throws IOException {
        List<ProductModel> productModels = repo.findAll();
        ProductExcelExportUtils productExcelExportUtils = new ProductExcelExportUtils(productModels);
        productExcelExportUtils.exportToExcel(response);
    }
}
