function auth() {
    const token = localStorage.getItem('authToken');
    if (!token) {
        console.log('No token found in local storage. Redirecting to login page.');
        // Redirect to login page
        window.location.href = '../login.html'; // Update with your login page path
    }
}