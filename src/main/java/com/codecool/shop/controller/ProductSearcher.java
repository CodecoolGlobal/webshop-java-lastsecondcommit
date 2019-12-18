package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/search"})
public class ProductSearcher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String[] selectedCategoryIds = req.getParameterValues("categoryId") != null ? req.getParameterValues("categoryId") : new String[0];
        String[] selectedSupplierIds = req.getParameterValues("supplierId") != null ? req.getParameterValues("supplierId") : new String[0];

        List<ProductCategory> selectedCategories = new ArrayList<>();
        List<Supplier> selectedSuppliers = new ArrayList<>();
        List<ProductCategory> allCategory = productCategoryDataStore.getAll();
        List<Supplier> allSupplier = supplierDataStore.getAll();
        List<Product> selectedProducts = new ArrayList<>();

        for (String id : selectedSupplierIds) {
            int supplierId = Integer.valueOf(id);
            Supplier supplier = supplierDataStore.find(supplierId);
            selectedSuppliers.add(supplier);
        }

        for (String id : selectedCategoryIds) {
            int categoryId = Integer.valueOf(id);
            ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
            selectedCategories.add(selectedCategory);
            productDataStore.getBy(selectedCategory).stream().filter(x -> selectedSuppliers.contains(x.getSupplier())).forEach(selectedProducts::add);
        }

         Map<String, Object> params = new HashMap<>();
         params.put("allCategories", allCategory);
         params.put("allSuppliers", allSupplier);
         params.put("products", selectedProducts);
         params.put("selectedCategories", selectedCategories);
         params.put("selectedSuppliers", selectedSuppliers);
         context.setVariables(params);

        engine.process("product/index.html", context, resp.getWriter());
    }

}
