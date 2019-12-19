package com.codecool.shop.controller;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@WebServlet(urlPatterns = {"/search"})
public class ProductSearcherController extends FirstPageController {

    @Override
     protected void broadenDoGet(HttpServletRequest req) {

        String[] selectedCategoryIds = Optional.ofNullable(req.getParameterValues("categoryId")).orElseGet(() -> new String[0]);
        String[] selectedSupplierIds = Optional.ofNullable(req.getParameterValues("supplierId")).orElseGet(() -> new String[0]);

        selectedSuppliers = new ArrayList<>();
        selectedProducts = new ArrayList<>();
        selectedCategories = new ArrayList<>();

        for (String id : selectedSupplierIds) {
            int supplierId = Integer.parseInt(id);
            Supplier supplier = supplierDataStore.find(supplierId);
            selectedSuppliers.add(supplier);
        }

        for (String id : selectedCategoryIds) {
            int categoryId = Integer.parseInt(id);
            ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
            selectedCategories.add(selectedCategory);
            productDataStore.getBy(selectedCategory).stream().filter(x -> selectedSuppliers.contains(x.getSupplier())).forEach(selectedProducts::add);
        }

    }



}
