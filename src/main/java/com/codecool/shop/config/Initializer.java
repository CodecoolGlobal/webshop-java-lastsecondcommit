package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier superFlowers = new Supplier("Super Flowers kft", "Small family business with quality plants");
        supplierDataStore.add(superFlowers);
        Supplier kingOfCactus = new Supplier("King Of Cactus kft", "Main priority is cacti, but also sell other plants");
        supplierDataStore.add(kingOfCactus);
        Supplier bigFlowerBusiness = new Supplier("Big Flower Business Zrt", "Industry leader big company");
        supplierDataStore.add(bigFlowerBusiness);

        //setting up a new product category
        ProductCategory cactus = new ProductCategory("Cactus", "Plant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        productCategoryDataStore.add(cactus);
        ProductCategory flower = new ProductCategory("Flower", "Plant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        productCategoryDataStore.add(flower);
        ProductCategory littlePlant = new ProductCategory("Little_Plant", "Plant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        productCategoryDataStore.add(littlePlant);
        ProductCategory palm = new ProductCategory("Palm", "Plant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        productCategoryDataStore.add(palm);

        //setting up products and printing it
        productDataStore.add(new Product("Bunch of smol Cacti", 100, "USD", "Stellar collection of little cacti. If you take good care of them, they protect you from robbers and bad men by throwing themselves on the floor before thus immobilizing the intruder.", cactus, kingOfCactus));
        productDataStore.add(new Product("Highfive Cactus", 20, "USD", "Big Brother cactus. He watches your daily life and comforts you when in need.", cactus, kingOfCactus));
        productDataStore.add(new Product("Spikealousless Cactus", 10, "USD", "Smol cactus", cactus, kingOfCactus));
        productDataStore.add(new Product("Spikasaurus Rex", 30, "USD", "Pack of StoneRose. They are siblings to cacti.", cactus, kingOfCactus));
        productDataStore.add(new Product("Candelabra Cactus", 5, "USD", "Piercing cactus", cactus, kingOfCactus));
        productDataStore.add(new Product("Joseph's Coat", 25, "USD", "Bonsai cactus. Bedroom size version of his successor who lives in the Atacama Desert.", cactus, kingOfCactus));
        productDataStore.add(new Product("Mandarin Cactus", 20, "USD", "Pair of mandarin cacti. They together almost make an orange.", cactus, kingOfCactus));
        productDataStore.add(new Product("Pink Mr. Lincoln", 5, "USD", "Pink rose.", flower, superFlower));
        productDataStore.add(new Product("Colourful bunch Daisies", 15, "USD", "Spring flower.", flower, superFlower));
        productDataStore.add(new Product("Red Pat Austin", 10, "USD", "Red rose. Even Zorro would be jealous.", flower, superFlowers));
        productDataStore.add(new Product("Pink Leucadendron", 10, "USD", "Purple?.", flower, superFlowers));
        productDataStore.add(new Product("Yellow Daffodil", 10, "USD", "Yellow flower!", flower, superFlowers));
        productDataStore.add(new Product("White Lily", 5, "USD", "Snow White.", flower, superFlowers));
        productDataStore.add(new Product("Pink Cinquefoil", 20, "USD", "Three head flower. If you ever try to cut one head off, it will grow back three instead! But only after it cuts your hair.", flower, superFlowers));
        productDataStore.add(new Product("Green Leaflet", 15, "USD", "The greenest of them all", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Home Plant", 15, "USD", "This is the best choice if you, like most people forgets to water the plant time to time. It can live for a month without watering!", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Elephant Grass", 20, "USD", "Very nice for a bedroom.", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Monstera", 35, "USD", "Leaves so big you could place in on a balcony and it casts shadows all over the place.", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Piercy Growling", 10, "USD", "It may seem small now but will grow big and fierce.", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("ZZ Plant", 15, "USD", "A piece of forest for your home", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Pothos", 25, "USD", "It spreads like gossip.", littlePlant, bigFlowerBusiness));
        productDataStore.add(new Product("Lady Palm", 45, "USD", "Spectacular plant.", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Parlor Palm", 40, "USD", "Best to place in spacious areas, it fills the room.", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Florida Silver Palm", 30, "USD", "Palm tree from India..", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Yucca", 20, "USD", "No big leaves but rather strong trunk.", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Dracaenat", 25, "USD", "You may recognize it from cartoon network shows. It was often used as the hair of many iconic characters", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Pygmy Date Palm", 120, "USD", "Maugli envies you if your choice fall upon thee.", palm, bigFlowerBusiness));
        productDataStore.add(new Product("Ficus", 35, "USD", "The crown of Caesar.", palm, bigFlowerBusiness));
    }
}
