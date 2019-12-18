package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
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
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int DEFAULT_CATEGORY_INDEX = 1;

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<ProductCategory> allCategory = productCategoryDataStore.getAll();
        List<Supplier> allSupplier = supplierDataStore.getAll();
        ProductCategory defaultProductCategory = productCategoryDataStore.find(DEFAULT_CATEGORY_INDEX);
        List<ProductCategory> selectedCategories = new ArrayList<>();
        selectedCategories.add(defaultProductCategory);
        List<Product> selectedProducts = productDataStore.getBy(defaultProductCategory);

        context.setVariable("allCategories", allCategory);
        context.setVariable("allSuppliers", allSupplier);
        context.setVariable("products", selectedProducts);
        context.setVariable("selectedCategories", selectedCategories);
        context.setVariable("selectedSuppliers", new ArrayList<>());

        engine.process("product/index.html", context, resp.getWriter());
    }

}

