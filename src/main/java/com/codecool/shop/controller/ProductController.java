package com.codecool.shop.controller;
import com.codecool.shop.model.ProductCategory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends FirstPageController {

    @Override
    protected void broadenDoGet(HttpServletRequest req)  {
        int DEFAULT_CATEGORY_ID = 1;

        selectedCategories = new ArrayList<>();
        ProductCategory defaultProductCategory = productCategoryDataStore.find(DEFAULT_CATEGORY_ID);

        selectedCategories.add(defaultProductCategory);
        selectedProducts = productDataStore.getBy(defaultProductCategory);
        selectedSuppliers = supplierDataStore.getAll();


    }

}

