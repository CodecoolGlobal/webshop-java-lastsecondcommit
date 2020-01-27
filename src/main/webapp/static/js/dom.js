import { dataHandler } from "./data_handler.js";

export let dom = {

    init: function () {
        dom.addEventListenerToFilterCheckbox();
        dom.addEventListenerToCartButtons();
        dom.addEventListenerToMinusButtons();
        dom.addEventListenerToPlusButtons();
        dom.addEventListenerToPaymentSelector();
        dom.setDefaultPaymentTemplate();
    },

    setDefaultPaymentTemplate: function(){
        const creditCardTemplate = document.querySelector("#card-payment-template");
        const creditCardClone = document.importNode(creditCardTemplate.content, true);
        let paymentContainer = document.querySelector("#payment-container");
        paymentContainer.appendChild(creditCardClone);
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

        let price = parseInt(rowNode.querySelector(".price").dataset.productPrice);
        let quantity = parseInt(quantityNode.innerHTML);

        let newQuantity = quantity + changeValue;

        if (changeValue > 0) {
            dataHandler.addToCart(productId, dom.changeNumOfItemsInCart(changeValue));
            dom.changeTotalSum(price);
            quantityNode.innerHTML = newQuantity.toString();

        } else if (changeValue < 0) {
            dataHandler.deleteFromCart(productId, dom.changeNumOfItemsInCart(changeValue));
            dom.changeTotalSum(price*(-1));
            quantityNode.innerHTML = newQuantity.toString();
        }
        if (newQuantity < 1) {
            dataHandler.deleteFromCart(productId, dom.deleteProductRow(rowNode));
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
    },

    addEventListenerToPaymentSelector: function () {
        let paymentSelector = document.querySelector("#payment-type");
        paymentSelector.addEventListener("change", function() {
            dom.changePaymentType(paymentSelector);
        });
    },
    changePaymentType: function (paymentSelector){
        const creditCardTemplate = document.querySelector("#card-payment-template");
        const paypalTemplate = document.querySelector("#paypal-payment-template");
        const creditCardClone = document.importNode(creditCardTemplate.content, true);
        const paypalClone = document.importNode(paypalTemplate.content, true);

        let paymentContainer = document.querySelector("#payment-container");

        if (paymentSelector.value==="card"){
            let paypalTemplateContent = document.querySelector("#paypal-template-content");
            paypalTemplateContent.remove();
            paymentContainer.appendChild(creditCardClone);
        }
        else if (paymentSelector.value==="paypal"){
            let cardTemplateContent = document.querySelector("#card-template-content");
            cardTemplateContent.remove();
            paymentContainer.appendChild(paypalClone);
        }
    }
};
