import { dataHandler } from "./data_handler.js";

export let dom = {

    init: function () {
        // dom.addEventListenerToFilterCheckbox();
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

    /*addEventListenerToFilterCheckbox :function () {
        let filterForm = document.querySelector('#filters');
        let checkboxes = filterForm.querySelectorAll("input");

        for (let checkbox of checkboxes) {
            checkbox.addEventListener('change', function () {
                filterForm.submit();
            })
        }
    },*/

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
        let rowNode = button.closest(".product-row");
        let quantityNode = rowNode.querySelector("#quantity");
        let quantity = parseInt(quantityNode.innerHTML);
        quantity += changeValue;
        quantityNode.innerHTML = quantity.toString();
        dom.changeNumOfItemsInCart(changeValue)
    }
};
