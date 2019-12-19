package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class FirstPageController extends HttpServlet {
    private int CART_ID = 1;
    protected ProductDao productDataStore = ProductDaoMem.getInstance();
    protected ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    protected SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    private ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
    private ShoppingCart shoppingCart = shoppingCartDataStore.find(CART_ID);
    private List<ProductCategory> allCategory = productCategoryDataStore.getAll();
    private List<Supplier> allSupplier = supplierDataStore.getAll();
    protected List<ProductCategory> selectedCategories;
    protected List<Product> selectedProducts;
    protected List<Supplier> selectedSuppliers;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        broadenDoGet(req);

        context.setVariable("allCategories", allCategory);
        context.setVariable("allSuppliers", allSupplier);
        context.setVariable("products", selectedProducts);
        context.setVariable("selectedCategories", selectedCategories);
        context.setVariable("selectedSuppliers", selectedSuppliers);
        context.setVariable("shoppingCart", shoppingCart);

        engine.process("product/index.html", context, resp.getWriter());

    }

    abstract protected void broadenDoGet(HttpServletRequest req);

}
