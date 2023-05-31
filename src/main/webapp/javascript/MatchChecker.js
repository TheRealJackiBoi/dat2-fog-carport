// This function checks whether the password and repeatpassword match and colours it green/red respectively
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

// This function checks whether the email and repeatemail match and colours it green/red respectively
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

// This function saves the variables of the form and checks whether they are empty (no input)
function signupFinalCheck() {
    let email = document.forms["signup"]["email"].value;
    let repeatemail = document.forms["signup"]["repeatemail"].value;
    let password = document.forms["signup"]["password"].value;
    let repeatpassword = document.forms["signup"]["repeatpassword"].value;
    let name = document.forms["signup"]["name"].value;
    let address = document.forms["signup"]["address"].value;
    let city = document.forms["signup"]["city"].value;
    let zip = document.forms["signup"]["zip"].value;

    if (email == "") {
        alert("Indtast venligst en email");
        return false;
    }
    if (repeatemail == "") {
        alert("Venligst tjek at email felter stemmer overens");
        return false;
    }
    if (password == "") {
        alert("Indtast venligst et kodeord");
        return false;
    }
    if (repeatpassword == "") {
        alert("Venligst tjek at kodeord felter stemmer overens");
        return false;
    }
    if (name == "") {
        alert("Indtast venligst et navn");
        return false;
    }
    if (address == "") {
        alert("Indtast venligst en adresse");
        return false;
    }
    if (city == "") {
        alert("Indtast venligst en by");
        return false;
    }
    if (zip == "") {
        alert("Indtast venligst et postnummer");
        return false;
    }
    // Finally we check whether the email and password match their repeat values
    if (document.getElementById('email').value ===
        document.getElementById('repeatemail').value &&
        (document.getElementById('password').value ===
            document.getElementById('repeatpassword').value)) {
        return true;
    } else {
        alert("Venligst tjek at email og kodeord felter stemmer overens")
        return false;
    }
}