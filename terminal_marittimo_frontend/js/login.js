function handleLogin(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the username and password values from the form
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Perform basic validation
    if (!username || !password) {
        alert('Per favore, inserisci sia il nome utente che la password.');
        return;
    }

    // Example: Send login data to the server
    const loginData = {
        username: username,
        password: CryptoJS.MD5(password)
    };

    fetch(`http://localhost:8080/utenti/login?username=${encodeURIComponent(loginData.username)}&password=${encodeURIComponent(loginData.password)}`, {
        method: 'POST'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Accesso fallito');
        }
        return response.json();
    }).then(data => {
        
        //alert('logged');
        console.log(data);

        localStorage.setItem('authToken', data.token);

        window.location.href = 'index.html'; 
    }).catch(error => {
        // Handle login error
        alert('Error: ' + error.message);
    });
}
function handleRegister(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the username and password values from the form
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const password2 = document.getElementById('password2').value;

    // Perform basic validation
    if (!username || !password || !password2) {
        alert('Per favore, inserisci sia il nome utente che la password.');
        return;
    }
    if (password !== password2) {
        alert('Le password non corrispondono.');
        return;
    }

    // Example: Send login data to the server
    const loginData = {
        username: username,
        password: CryptoJS.MD5(password)
    };

    fetch(`http://localhost:8080/utenti/register?username=${encodeURIComponent(loginData.username)}&password=${encodeURIComponent(loginData.password)}`, {
        method: 'POST'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Registrazione fallita');
        }
        return response.json();
    }).then(data => {
        console.log(data);
        
        window.location.href = 'index.html'; 
    }).catch(error => {
        // Handle login error
        alert('Error: ' + error.message);
    });
}
function checkAuthToken() {
    const token = localStorage.getItem('authToken');
    if (!token) {
        alert('Non sei autenticato. Reindirizzamento alla pagina di login.');
        window.location.href = 'login.html';
    }
}