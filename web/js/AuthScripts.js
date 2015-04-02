    function checkPass()
{
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('InputPassFirst');
    var pass2 = document.getElementById('InputPassSecond');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if((pass1.value === pass2.value) && (pass1.value !== "")){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "\nPasswords Match!";
        document.getElementById("button").disabled = false;
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        document.getElementById("button").disabled = true;
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "\nPasswords Do Not Match!";
    }
}  