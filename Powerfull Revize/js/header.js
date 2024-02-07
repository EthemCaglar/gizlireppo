document.getElementById("body").onscroll = function() {scroller()};

function scroller(){
    if(window.scrollY ){
        document.getElementById("stick").style.position = "fixed"
        document.getElementById("stick").style.background = "#355592"
    }
}
