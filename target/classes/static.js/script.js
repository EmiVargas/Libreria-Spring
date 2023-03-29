var btnRegister = document.getElementById("btnRegister");
var username = document.getElementById("username");
var email = document.getElementById("email");
var password = document.getElementById("password");
var username_help = document.getElementById("username_help");
var psw_help = document.getElementById("psw_help");

/*
var email = document.forms['form']['email'];
var password = document.forms['form']['password'];
var username = document.forms['form']['username'];
var phone = document.forms['form']['phone'];
*/

var username_error = document.getElementById('username_error');
var email_error = document.getElementById('email_error');
var phone_error = document.getElementById('phone_error');
var psw_error = document.getElementById('psw_error');

username.addEventListener('textInput', verifyUsername);
email.addEventListener('textInput', verifyEmail);
password.addEventListener('textInput', verifyPsw);


btnRegister.addEventListener('click', function (event) {
    event.preventDefault();
    const expEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (username.value.length <= 4) {
        username.style.border = "2px solid red";
        username_error.style.display = "block";
        username_help.style.display ="none";
        username.focus();
        return false;
    }

    if (expEmail.test(email.value) === false) {
        email.style.border = "2px solid red";
        email_error.style.display = "block";
        email.focus();
        return false;

    }
    if (password.value.length <= 5) {
        password.style.border = "2px solid red";
        psw_error.style.display = "block";
        psw_help.style.display ="none";
        password.focus();
        return false;
    }

});

function verifyUsername() {
    if (username.value.length > 3) {
        username.style.border = "1px solid blue";
        username_error.style.display = "none";
        return true;
    }
}

function verifyEmail() {
    const expresion = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (expresion.test(email.value)) {
        email.style.border = "1px solid blue";
        email_error.style.display = "none";
        return true;
    }
}


function verifyPsw() {
    if (password.value.length > 4) {
        password.style.border = "none";
        psw_error.style.display = "none";
        return true;
    }
}
