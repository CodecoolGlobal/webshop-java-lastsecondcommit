package com.codecool.shop.controller;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.SupplierDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductListController extends CartController {

    private ProductDao productDao = ProductDaoJDBC.getInstance();
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoJDBC.getInstance();
    private SupplierDao supplierDao = SupplierDaoJDBC.getInstance();

    private List<ProductCategory> allCategory = productCategoryDao.getAll();
    private List<Supplier> allSupplier = supplierDao.getAll();
    private List<ProductCategory> selectedCategories;
    private List<Product> selectedProducts;
    private List<Supplier> selectedSuppliers;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setupShoppingCart(req);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        makeFiltering(req);
        int itemNumberInShoppingCart = shoppingCart.getItemNumberInCart();

        context.setVariable("allCategories", allCategory);
        context.setVariable("allSuppliers", allSupplier);
        context.setVariable("products", selectedProducts);
        context.setVariable("selectedCategories", selectedCategories);
        context.setVariable("selectedSuppliers", selectedSuppliers);
        context.setVariable("itemNumberInShoppingCart", itemNumberInShoppingCart);

        engine.process("product/index.html", context, resp.getWriter());
    }

    private void makeFiltering(HttpServletRequest req) {
        String[] selectedCategoryIds = req.getParameterValues("categoryId");
        String[] selectedSupplierIds = req.getParameterValues("supplierId");
        if (selectedCategoryIds == null && selectedSupplierIds == null) {
            setDefaultFilter();
        } else {
            setUserFilter(selectedCategoryIds, selectedSupplierIds);
        }
    }

    private void setDefaultFilter()  {
        int DEFAULT_CATEGORY_ID = 1;

        selectedCategories = new ArrayList<>();
        ProductCategory defaultProductCategory = productCategoryDao.find(DEFAULT_CATEGORY_ID);

        selectedCategories.add(defaultProductCategory);
        selectedProducts = productDao.getBy(defaultProductCategory);
        selectedSuppliers = supplierDao.getAll();
    }

    private void setUserFilter(String[] selectedCategoryIds, String[] selectedSupplierIds) {

        selectedSuppliers = new ArrayList<>();
        selectedCategories = new ArrayList<>();
        selectedProducts = new ArrayList<>();

        fillInSelectedSuppliers(selectedSupplierIds);
        fillInSelectedCategories(selectedCategoryIds);
        fillInSelectedProducts();
    }

    private void fillInSelectedSuppliers(String[] selectedSupplierIds) {
        if (selectedSupplierIds != null) {
            for (String id : selectedSupplierIds) {
                int supplierId = Integer.parseInt(id);
                Supplier supplier = supplierDao.find(supplierId);
                selectedSuppliers.add(supplier);
            }
        }
    }

    private void fillInSelectedCategories(String[] selectedCategoryIds) {
        if (selectedCategoryIds != null) {
            for (String id : selectedCategoryIds) {
                int categoryId = Integer.parseInt(id);
                ProductCategory selectedCategory = productCategoryDao.find(categoryId);
                selectedCategories.add(selectedCategory);
            }
        }
    }

    private void fillInSelectedProducts() {
        for (ProductCategory selectedCategory : selectedCategories) {
            productDao.getBy(selectedCategory).stream().filter(x -> selectedSuppliers.
                    contains(x.getSupplier())).forEach(selectedProducts::add);
        }
    }

}

