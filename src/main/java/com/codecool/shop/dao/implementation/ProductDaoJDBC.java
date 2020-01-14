package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends JDBC implements ProductDao {

    SupplierDaoJDBC supplierDaoJDBC = new SupplierDaoJDBC();
    ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (name, description, default_price, default_currency, supplier_id, product_category_id)" +
                " VALUES (?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().toString());
            statement.setInt(5, product.getSupplier().getId());
            statement.setInt(6, product.getProductCategory().getId());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * WHERE id = ? ";
        ResultSet resultSet = null;
        Product result = null;
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategoryDao.find(resultSet.getInt("product_category_id")),
                        supplierDaoJDBC.find(resultSet.getInt("supplier_id"))
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";

        List<Product> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategoryDao.find(resultSet.getInt("product_category_id")),
                        supplierDaoJDBC.find(resultSet.getInt("supplier_id")));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        ResultSet resultSet = null;
        int supplierId = supplier.getId();
        String query = "SELECT * FROM product WHERE id = ?;";
        List<Product> resultList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategoryDao.find(resultSet.getInt("product_category_id")),
                        supplier);
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }


    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        ResultSet resultSet = null;
        int productCategoryId = productCategory.getId();
        String query = "SELECT * FROM product WHERE id = ?;";
        List<Product> resultList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productCategoryId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplierDaoJDBC.find(resultSet.getInt("supplier_id")));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}

