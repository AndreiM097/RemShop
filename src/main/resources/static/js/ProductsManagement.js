
// Get the modal
var addProductModal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == addProductModal) {
        addProductModal.style.display = "none";
    }
}