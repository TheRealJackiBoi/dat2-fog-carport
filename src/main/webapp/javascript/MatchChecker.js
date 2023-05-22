document.getElementById('signupbtn').disabled = true;

function checkPass() {
    if (document.getElementById('password').value ===
        document.getElementById('repeatpassword').value) {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Kodeord felter stemmer overens';
    } else {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Kodeord felter stemmer ikke overens';
    }
}
function checkEmail() {
    if (document.getElementById('email').value ===
        document.getElementById('repeatemail').value) {
        document.getElementById('Emessage').style.color = 'green';
        document.getElementById('Emessage').innerHTML = 'Email felter stemmer overens';
    } else {
        document.getElementById('Emessage').style.color = 'red';
        document.getElementById('Emessage').innerHTML = 'Email felter stemmer ikke overens';
    }
}
function finalCheck() {
    let email = document.forms["signup"]["email"].value;
    let repeatemail = document.forms["signup"]["repeatemail"].value;
    let password = document.forms["signup"]["password"].value;
    let repeatpassword = document.forms["signup"]["repeatpassword"].value;
    let name = document.forms["signup"]["name"].value;
    let address = document.forms["signup"]["address"].value;
    let city = document.forms["signup"]["city"].value;
    let zip = document.forms["signup"]["zip"].value;

    if (email == "") {
        alert("Please enter an email");
        return false;
    }
    if (repeatemail == "") {
        alert("Please enter a matching email");
        return false;
    }
    if (password == "") {
        alert("Please enter a password");
        return false;
    }
    if (repeatpassword == "") {
        alert("Please enter a matching password");
        return false;
    }
    if (name == "") {
        alert("Please enter a name");
        return false;
    }
    if (address == "") {
        alert("Please enter an address");
        return false;
    }
    if (city == "") {
        alert("Please enter a city");
        return false;
    }
    if (zip == "") {
        alert("Please enter a zip");
        return false;
    }
    if (document.getElementById('email').value ===
        document.getElementById('repeatemail').value &&
        (document.getElementById('password').value ===
            document.getElementById('repeatpassword').value)) {
        return true;
    } else {
        alert("Please make sure email and password are matching")
        return false;
    }
}