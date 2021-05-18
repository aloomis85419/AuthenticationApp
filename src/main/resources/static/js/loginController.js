
function submitCredentials(username,password){
    jQuery.post("/credentials",{"username":username,"password":password}).done(function (data){
        console.log("Credentials sent...")
        console.log("Data sent "+data)
    });
}