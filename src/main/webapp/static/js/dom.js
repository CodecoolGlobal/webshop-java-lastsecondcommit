import { dataHandler } from "./data_handler.js";

export let dom = {

    init: function () {
        dom.addEventListenerToFilterCheckbox();
        dom.addEventListenerToCartButtons();
        dom.addEventListenerToMinusButtons();
        dom.addEventListenerToPlusButtons();
    },

    addEventListenerToCartButtons: function () {
        let addToCartButtons = document.querySelectorAll(".add-cart");

        addToCartButtons.forEach((button) => {
            let productId = button.dataset.productId;
            button.addEventListener('click', function () {
                dataHandler.addToCart(productId, dom.changeNumOfItemsInCart(1))
            });
        });
    },

    changeNumOfItemsInCart: function (changeValue) {
        let itemsInCart = parseInt(document.querySelector("#cart-info").innerHTML);
        itemsInCart += changeValue;
        document.querySelector("#cart-info").innerHTML = itemsInCart.toString();
    },

    addEventListenerToFilterCheckbox :function () {
        if (document.querySelector('#filters') != null) {
            let filterForm = document.querySelector('#filters');
            let checkboxes = filterForm.querySelectorAll("input");

            for (let checkbox of checkboxes) {
                checkbox.addEventListener('change', function () {
                    filterForm.submit();
                })
            }
        }
    },

    addEventListenerToMinusButtons: function () {
        let quantityChangeButtons = document.querySelectorAll(".btn-minus");

        quantityChangeButtons.forEach((button) => {
            button.addEventListener('click', function () {
                dom.changeQuantity(button, -1)

            });
        });
    },

    addEventListenerToPlusButtons: function () {
        let quantityChangeButtons = document.querySelectorAll(".btn-plus");

        quantityChangeButtons.forEach((button) => {
            button.addEventListener('click', function () {
                dom.changeQuantity(button, 1)

            });
        });
    },

    changeQuantity: function (button, changeValue) {
        let productId = button.dataset.productId;

        let rowNode = button.closest(".product-row");
        let quantityNode = rowNode.querySelector(".quantity");
        let priceNode = rowNode.querySelector(".price");

        let price = parseInt(rowNode.querySelector(".price").dataset.productPrice);
        let quantity = parseInt(quantityNode.innerHTML);

        let newQuantity = quantity + changeValue;
        let newSubtotalPrice =  newQuantity * price;

        if (changeValue > 0) {
            dataHandler.addToCart(productId, dom.changeNumOfItemsInCart(changeValue));
            dom.changeTotalSum(price);

        } else if (changeValue < 0) {
            dataHandler.deleteFromCart(productId, dom.changeNumOfItemsInCart(changeValue));
            dom.changeTotalSum(price*(-1));
        }

        if (newQuantity < 1) {
            dataHandler.deleteFromCart(productId, dom.deleteProductRow(rowNode));
        } else {
            quantityNode.innerHTML = newQuantity.toString();
            priceNode.innerHTML = `${price}.0 USD / ${newSubtotalPrice}.0 USD`;
        }
    },

    changeTotalSum: function(price) {
        let totalSumNode = document.querySelector("#total-sum");
        let totalSum = parseInt(totalSumNode.innerHTML);
        let newTotalSum = totalSum + price;
        totalSumNode.innerHTML = newTotalSum.toString() + '.0 USD';
    },

    deleteProductRow: function (productRow) {
        productRow.remove();
        if (document.querySelector(".cart-table .product-row") == null ) {
            document.querySelector(".cart-table tbody").remove();
        }
    }
};
