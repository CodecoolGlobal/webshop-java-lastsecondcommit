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
        //let itemsInCart = parseInt(document.querySelector("nav #cart-info"));
        let itemsInCart = 0;
        itemsInCart += 1;
        console.log(itemsInCart);
        //document.querySelector("nav #cart-info").innerHTML = itemsInCart.toString();
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