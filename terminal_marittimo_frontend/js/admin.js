function Load(){
    loadNaviOptions();
    loadPortoOptions()
}

async function loadNaviOptions() {
    try {
        const response = await fetch('http://localhost:8080/admin/getAllNavi?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('nave'); // Replace with your select element's ID
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(navi => {
            const option = document.createElement('option');
            option.value = navi.id;
            option.textContent = navi.nome; 
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
async function loadPortoOptions() {
    try {
        const response = await fetch('http://localhost:8080/admin/getAllPorti?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('porto'); // Replace with your select element's ID
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(porto => {
            const option = document.createElement('option');
            option.value = porto.id;
            option.textContent = porto.nome; 
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
async function loadFornitoresOptions() {
    try {
        const response = await fetch('http://localhost:8080/admin/getAllFornitori?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('fornitore'); 
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(fornitore => {
            const option = document.createElement('option');
            option.value = fornitore.id;
            option.textContent = fornitore.nome; 
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
function registraPartenzaViaggio(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the username and password values from the form
    const nave = document.getElementById('nave').value;
    const data_partenza = document.getElementById('data_partenza').value;
    const porto = document.getElementById('porto').value;

    // Perform basic validation
    if (!nave || !data_partenza || !porto) {
        alert('Per favore, inserisci sia il nome della nave che la data di partenza e il porto.');
        return;
    }

    fetch(`http://localhost:8080/admin/registraViaggio?token=${encodeURIComponent(localStorage.getItem('authToken'))}&nave=${encodeURIComponent(nave)}&data_partenza=${encodeURIComponent(data_partenza)}&porto=${encodeURIComponent(porto)}`, {
        method: 'GET',
    }).then(response => {
        if (!response.ok) {
            throw new Error('Errore in inserimento');
        }
        return response.json();
    }).then(data => {
        
        console.log(data);

    }).catch(error => {
        // Handle login error
        alert('Error: ' + error.message);
    });
}


