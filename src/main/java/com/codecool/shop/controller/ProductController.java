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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private int CART_ID = 1;
    private ProductDao productDataStore = ProductDaoMem.getInstance();
    private ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    private ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
    private ShoppingCart shoppingCart = shoppingCartDataStore.find(CART_ID);
    private List<ProductCategory> allCategory = productCategoryDataStore.getAll();
    private List<Supplier> allSupplier = supplierDataStore.getAll();
    private List<ProductCategory> selectedCategories;
    private List<Product> selectedProducts;
    private List<Supplier> selectedSuppliers;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        makeFiltering(req);

        context.setVariable("allCategories", allCategory);
        context.setVariable("allSuppliers", allSupplier);
        context.setVariable("products", selectedProducts);
        context.setVariable("selectedCategories", selectedCategories);
        context.setVariable("selectedSuppliers", selectedSuppliers);
        context.setVariable("shoppingCart", shoppingCart);

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
        ProductCategory defaultProductCategory = productCategoryDataStore.find(DEFAULT_CATEGORY_ID);

        selectedCategories.add(defaultProductCategory);
        selectedProducts = productDataStore.getBy(defaultProductCategory);
        selectedSuppliers = supplierDataStore.getAll();
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
                Supplier supplier = supplierDataStore.find(supplierId);
                selectedSuppliers.add(supplier);
            }
        }
    }

    private void fillInSelectedCategories(String[] selectedCategoryIds) {
        if (selectedCategoryIds != null) {
            for (String id : selectedCategoryIds) {
                int categoryId = Integer.parseInt(id);
                ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
                selectedCategories.add(selectedCategory);
            }
        }
    }

    private void fillInSelectedProducts() {
        for (ProductCategory selectedCategory : selectedCategories) {
            productDataStore.getBy(selectedCategory).stream().filter(x -> selectedSuppliers.
                    contains(x.getSupplier())).forEach(selectedProducts::add);
        }
    }

}

