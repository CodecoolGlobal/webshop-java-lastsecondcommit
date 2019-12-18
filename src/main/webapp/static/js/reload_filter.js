function  main() {
    handleFilterCheckbox();
}

function handleFilterCheckbox() {
    let filterForm = document.querySelector('#filter');
    let checkboxes = filterForm.querySelectorAll("input");
    for (let checkbox of checkboxes) {
        checkbox.addEventListener('change', function () {
                filterForm.submit();
            }
        )
    }
}


main();