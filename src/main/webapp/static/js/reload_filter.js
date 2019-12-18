function  main() {
    handleFilterCheckbox();
}

function handleFilterCheckbox() {
    let filterForm = document.querySelector('#filter');
    let checkboxes = filterForm.querySelectorAll("input");
    for (let checkbox of checkboxes) {
        checkbox.addEventListener('CheckboxStateChange', function () {
                console.log("FFFFFFFF")
            }
        )
    }
}


main();