const height = document.querySelector("#hei");
const weight = document.querySelector("#wei");
const btn = document.querySelector("#BMIbtn");

height.addEventListener("input", () =>{
    const BMI = weight.value / ((height.value/100) * (height.value/100));
    console.log(weight.value + " " + height.value + " " + BMI)
    if(BMI <= 18.5){
        btn.innerHTML = "You are too underweight";
        document.querySelector(".triBMI").style.left= "4.8"
    }else if(35 <= BMI){
        btn.innerHTML = "You are too overweight";
        document.querySelector(".triBMI").style.left= "85.4%"
    }else{
        btn.innerHTML = "Your BMI: " + BMI.toFixed(2);
        let percentage = (BMI - 16) * 4.16;
        document.querySelector(".triBMI").style.left= percentage + "%"
    }
})

weight.addEventListener("input", () =>{
    const BMI = weight.value / ((height.value/100) * (height.value/100));
    console.log(weight.value + " " + height.value + " " + BMI)
    if(BMI <= 16){
        btn.innerHTML = "You are too underweight";
        document.querySelector(".triBMI").style.left= "4.8%"
    }else if(50 <= BMI){
        btn.innerHTML = "You are too overweight";
        document.querySelector(".triBMI").style.left= "85.4%"
    }else{
        btn.innerHTML = "Your BMI: " + BMI.toFixed(2);
        let percentage = (BMI - 16) * 4.16;
        document.querySelector(".triBMI").style.left= percentage + "%"
    }
})