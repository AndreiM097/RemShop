var modal = document.getElementById('modalContent');
console.log("header script loaded!")

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
 function closeModal(){
 document.getElementById('id01').style.display='none';
 }
</th:script>