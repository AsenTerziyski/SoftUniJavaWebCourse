const boxImgA = document.getElementById('box-a-img');
const boxImgB = document.getElementById('box-b-img');
const boxTempA = document.getElementById('box-a-temp');
const boxTempB = document.getElementById('box-b-temp');


fetch("http://api.openweathermap.org/data/2.5/weather?q=Sofia&appid=8dd1b8c6c70655b59ef4f75b4d9fb753")
    .then(date => date.json())
    .then(info => {
        console.log(info);

        boxTempA.innerText = Math.round(info.main.temp - 273.15);
        boxImgA.src = 'images/weather-icons' + info.weather[0].icon + '.png';


    })


