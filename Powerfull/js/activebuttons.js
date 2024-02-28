const yoga = document.querySelector(".yoga");
const group = document.querySelector(".group");
const solo = document.querySelector(".solo");
const stret = document.querySelector(".stret");

yoga.addEventListener("click", () => {
    document.querySelector(".title1").innerHTML = "Why Yoga?"
    document.querySelector(".parag").innerHTML = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque, at quaerat sunt temporibus dolores quibusdam deleniti nobis laborum culpa ea animi vel commodi praesentium ipsa quis tempora illo distinctio sapiente!"
    document.querySelector(".title2").innerHTML = "When Comes Yoga Your Time."
    document.querySelector(".date1").innerHTML = "Saturday-Sunday: 8:00am - 10:00am"
    document.querySelector(".date2").innerHTML = "Monday-Tuesday: 10:00am - 12:00am"
    document.querySelector(".date3").innerHTML = "Wednesday-Friday: 3:00pm - 5:00am"
    document.querySelector(".triClasses1").style.left= "36%"
    document.querySelector(".triClasses2").style.left= "1000%"
    document.querySelector(".group").style.background = "#2a578f"
    document.querySelector(".group").style.boxShadow = "none";
    document.querySelector(".solo").style.background = "#2a578f"
    document.querySelector(".solo").style.boxShadow = "none";
    document.querySelector(".stret").style.background = "#2a578f"
    document.querySelector(".stret").style.boxShadow = "none";
    document.querySelector(".yoga").style.background = "coral"
    document.querySelector(".yoga").style.boxShadow = "5px 5px 20px coral";
    document.getElementById("image").src = "images/yoga.jpg";
})

group.addEventListener("click", () => {
    document.querySelector(".title1").innerHTML = "Why Group?"
    document.querySelector(".parag").innerHTML = "Rem voluptatem ut, facere consectetur maxime incidunt iste! Repellendus, eos ullam et nostrum expedita, magni sed ad provident nemo enim sapiente, aliquid impedit perferendis quis consequuntur a maiores inventore aspernatur."
    document.querySelector(".title2").innerHTML = "When Comes Group Your Time."
    document.querySelector(".date1").innerHTML = "Saturday-Sunday: 10:00am - 12:00am"
    document.querySelector(".date2").innerHTML = "Monday-Tuesday: 8:00am - 10:00am"
    document.querySelector(".date3").innerHTML = "Wednesday-Friday: 1:00pm - 3:00pm"
    document.querySelector(".triClasses1").style.left = "165%"
    document.querySelector(".triClasses2").style.left= "1000%"
    document.querySelector(".yoga").style.background = "#2a578f"
    document.querySelector(".yoga").style.boxShadow = "none";
    document.querySelector(".solo").style.background = "#2a578f"
    document.querySelector(".solo").style.boxShadow = "none";
    document.querySelector(".stret").style.background = "#2a578f"
    document.querySelector(".stret").style.boxShadow = "none";
    document.querySelector(".group").style.background = "coral"
    document.querySelector(".group").style.boxShadow = "5px 5px 20px coral";
    document.getElementById("image").src = "images/group.webp";
})

solo.addEventListener("click", () => {
    document.querySelector(".title1").innerHTML = "Why Solo?"
    document.querySelector(".parag").innerHTML = "Fugit, excepturi aspernatur quis fuga culpa minus repellendus sit iure tenetur quaerat eligendi iusto, neque ipsum atque, veritatis quam dignissimos a explicabo! Nulla beatae maiores maxime sunt molestiae atque eum."
    document.querySelector(".title2").innerHTML = "When Comes Solo Your Time."
    document.querySelector(".date1").innerHTML = "Saturday-Sunday: 1:00pm - 3:00pm"
    document.querySelector(".date2").innerHTML = "Monday-Tuesday: 3:00pm - 5:00am"
    document.querySelector(".date3").innerHTML = "Wednesday-Friday: 10:00am - 12:00am"
    document.querySelector(".triClasses1").style.left= "1500%"
    document.querySelector(".triClasses2").style.left= "36%"
    document.querySelector(".group").style.background = "#2a578f"
    document.querySelector(".group").style.boxShadow = "none";
    document.querySelector(".yoga").style.background = "#2a578f"
    document.querySelector(".yoga").style.boxShadow = "none";
    document.querySelector(".stret").style.background = "#2a578f"
    document.querySelector(".stret").style.boxShadow = "none";
    document.querySelector(".solo").style.background = "coral"
    document.querySelector(".solo").style.boxShadow = "5px 5px 20px coral";
    document.getElementById("image").src = "images/solo.jpg";
})

stret.addEventListener("click", () => {
    document.querySelector(".title1").innerHTML = "Why Stretching?"
    document.querySelector(".parag").innerHTML = "Cupiditate libero iusto aliquid aspernatur fugit nostrum quasi reprehenderit debitis, ut esse quibusdam iure itaque nihil earum eligendi minima odit? Odit eveniet reiciendis eius voluptates quo, et accusamus sequi praesentium."
    document.querySelector(".title2").innerHTML = "When Comes Stretching Your Time."
    document.querySelector(".date1").innerHTML = "Saturday-Sunday: 3:00pm - 5:00pm"
    document.querySelector(".date2").innerHTML = "Monday-Tuesday: 1:00pm - 3:00pm"
    document.querySelector(".date3").innerHTML = "Wednesday-Friday: 8:00am - 10:00am"
    document.querySelector(".triClasses1").style.left= "1500%"
    document.querySelector(".triClasses2").style.left= "215%"
    document.querySelector(".group").style.background = "#2a578f"
    document.querySelector(".group").style.boxShadow = "none";
    document.querySelector(".solo").style.background = "#2a578f"
    document.querySelector(".solo").style.boxShadow = "none";
    document.querySelector(".yoga").style.background = "#2a578f"
    document.querySelector(".yoga").style.boxShadow = "none";
    document.querySelector(".stret").style.background = "coral"
    document.querySelector(".stret").style.boxShadow = "5px 5px 20px coral";
    document.getElementById("image").src = "images/stret.webp";
})