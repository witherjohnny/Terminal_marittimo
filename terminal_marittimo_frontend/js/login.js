function handleLogin(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the username and password values from the form
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Perform basic validation
    if (!username || !password) {
        alert('Please enter both username and password.');
        return;
    }

    // Example: Send login data to the server
    const loginData = {
        username: username,
        password: password
    };

    fetch(`http://localhost:8080/utenti/login?username=${encodeURIComponent(loginData.username)}&password=${encodeURIComponent(loginData.password)}`, {
        method: 'POST'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Login failed');
        }
        return response.json();
    }).then(data => {
        // Handle successful login
        alert('Login successful!');
        console.log('User data:', data);
        // Redirect or perform other actions
    }).catch(error => {
        // Handle login error
        alert('Error: ' + error.message);
    });
}