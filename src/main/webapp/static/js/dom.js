import { dataHandler } from "./data_handler.js";

export let dom = {

    init: function () {
        console.log("Hello");
        dom.addEventListenerToFilterCheckbox();
        dom.addEventListenerToCartButtons();
    },
    addEventListenerToCartButtons: function () {
        let addToCartButtons = document.querySelectorAll(".btn-success");


        addToCartButtons.forEach((button) => {
            let productId = button.dataset.productId;

            console.log(productId);

            button.addEventListener('click', function () {
                dataHandler.addToCart(productId, dom.increaseNumOfItemsInCart())
            });
        });
    },
    increaseNumOfItemsInCart: function () {
        console.log("in callback");
        let itemsInCart = parseInt(document.querySelector("#cart-info").innerHTML);
        console.log("itemsInCart:");
        console.log(itemsInCart);
        itemsInCart += 1;
        console.log(itemsInCart);

        document.querySelector("#cart-info").innerHTML = itemsInCart.toString();
    },
    addEventListenerToFilterCheckbox :function () {

        let filterForm = document.querySelector('#filter');
        let checkboxes = filterForm.querySelectorAll("input");

        for (let checkbox of checkboxes) {
            checkbox.addEventListener('change', function () {
                filterForm.submit();
            })
        }
    }
};