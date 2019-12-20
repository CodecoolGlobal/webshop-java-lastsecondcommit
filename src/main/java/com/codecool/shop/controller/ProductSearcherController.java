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

        String[] selectedCategoryIds = getSelectedFilterTypeIds("categoryId", req);
        String[] selectedSupplierIds = getSelectedFilterTypeIds("supplierId", req);

        selectedSuppliers = new ArrayList<>();
        selectedProducts = new ArrayList<>();
        selectedCategories = new ArrayList<>();

        fillInSelectedSuppliers(selectedSupplierIds);
        fillInSelectedCategories(selectedCategoryIds);
        fillInSelectedProducts();
    }

    private void fillInSelectedSuppliers(String[] selectedSupplierIds) {
        for (String id : selectedSupplierIds) {
            int supplierId = Integer.parseInt(id);
            Supplier supplier = supplierDataStore.find(supplierId);
            selectedSuppliers.add(supplier);
        }
    }

    private void fillInSelectedCategories(String[] selectedCategoryIds) {
        for (String id : selectedCategoryIds) {
            int categoryId = Integer.parseInt(id);
            ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
            selectedCategories.add(selectedCategory);
        }
    }

    private void fillInSelectedProducts() {
        for (ProductCategory selectedCategory : selectedCategories) {
            productDataStore.getBy(selectedCategory).stream().filter(x -> selectedSuppliers.
                    contains(x.getSupplier())).forEach(selectedProducts::add);
        }
    }

    private String[] getSelectedFilterTypeIds(String filterTypeIdName, HttpServletRequest req) {
        return Optional.ofNullable(req.getParameterValues(filterTypeIdName)).orElseGet(() -> new String[0]);
    }



}
