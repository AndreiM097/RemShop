
// When the user scrolls down 50px from the top of the document, resize the header's font size
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
//if scrolling down , the logo will shrink and move to the left...else it will stay centered
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    document.getElementById("myLogo").style.height = "35px";
    document.getElementById("myNavBarContainer").style.borderRadius = "20px";
    document.getElementById("myNavBarContainer").style.width = "80%";
    document.getElementById("myNavBarContainer").style.left = "9%";
    document.getElementById("myNavBarContainer").style.backgroundColor = "rgba(255, 255, 255, 0.90)";
  }
  else {
      document.getElementById("myLogo").style.height = "45px";
      document.getElementById("myNavBarContainer").style.borderRadius = "0px";
      document.getElementById("myNavBarContainer").style.width = "100%";
      document.getElementById("myNavBarContainer").style.left = "0%";
      document.getElementById("myNavBarContainer").style.backgroundColor = "rgba(255, 255, 255, 1.0)";
    }
}

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}