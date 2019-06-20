var http = require('http');
console.log("Server Started at http://localhost:3000");

const ok = res => {
  res.writeHead(200, {'Content-Type': 'application/json'});
  res.end(JSON.stringify({status: "ok"}));
};

const err = res => {
  console.log("Not Found");
  res.writeHead(404);
  res.end(JSON.stringify({status: "not found"}));
};

http.createServer(function (req, res) {
  if(req.method === "POST" && req.url === "/post") {
    console.log("POST recieved");
    ok(res);
  } else if(req.method === "GET" && req.url === "/get") {
    console.log("GET recieved");
    const finishedProcessing = (Math.random() > 0.75); // 25% chance of data being processed

    if(finishedProcessing){
      console.log("GET successful");
      ok(res);
    } else {
      err(res);
    }
  } else {
    err(res);
  }
}).listen(3000);