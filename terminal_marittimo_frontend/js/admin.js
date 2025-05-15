function Load(){
    loadNaviOptions();
    loadPortoOptions();
    loadViaggioOptions();
}
function LoadRegistraPolizze(){
    loadFornitoresOptions();
    loadMerceOptions();
}
async function loadMerceOptions() {
    try {
        const response = await fetch('http://localhost:8080/admin/getAllMerce?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('merce'); 
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(merce => {
            const option = document.createElement('option');
            option.value = merce.id;
            option.textContent = merce.tipologia_merce;
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
async function loadViaggioOptions() {
    try {
        const response = await fetch('http://localhost:8080/admin/getAllViaggi?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('viaggio'); 
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(viaggio => {
            const option = document.createElement('option');
            option.value = viaggio.id;
            option.textContent = viaggio.id;
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
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

        const selectElement = document.getElementById('porto'); 
        const selectElement2 = document.getElementById('porto2');
        selectElement.innerHTML = ''; // Clear existing options
        selectElement2.innerHTML = ''; 

        data.forEach(porto => {
            const option = document.createElement('option');
            option.value = porto.id;
            option.textContent = porto.nome; 
            selectElement.appendChild(option);
            selectElement2.appendChild(option.cloneNode(true));
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
async function loadFornitoresOptions() {
    try {
        console.log('http://localhost:8080/admin/getAllFornitori?token=' + encodeURIComponent(localStorage.getItem('authToken')));
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
    console.log(`http://localhost:8080/admin/registraViaggio?token=${encodeURIComponent(localStorage.getItem('authToken'))}&nave=${encodeURIComponent(nave)}&data_partenza=${encodeURIComponent(data_partenza)}&porto=${encodeURIComponent(porto)}`);
    fetch(`http://localhost:8080/admin/registraViaggio?token=${encodeURIComponent(localStorage.getItem('authToken'))}&nave=${encodeURIComponent(nave)}&data_partenza=${encodeURIComponent(data_partenza)}&porto=${encodeURIComponent(porto)}`, {
        method: 'GET',
    }).then(response => {
        if (!response.ok) {
            throw new Error('Errore ');
        }
        return response.json();
    }).then(data => {
        
        console.log(data);
        localStorage.setItem('viaggio', data.insertId);
        alert('Viaggio registrato con successo!');
        location.href = 'registraPolizze.html';

    }).catch(error => {
        // Handle login error
        console.log('Error: ' + error.message);
    });
}
function registraArrivoViaggio(event) { 
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the values from the form
    const viaggio = document.getElementById('viaggio').value;
    const data_arrivo = document.getElementById('data_arrivo').value;
    const porto2 = document.getElementById('porto2').value;

    // Perform basic validation
    if (!viaggio || !data_arrivo || !porto2) {
        alert('Per favore, inserisci sia il viaggio che la data di arrivo e il porto di arrivo.');
        return;
    }

    const url = `http://localhost:8080/admin/registraArrivo?token=${encodeURIComponent(localStorage.getItem('authToken'))}&viaggio=${encodeURIComponent(viaggio)}&data_arrivo=${encodeURIComponent(data_arrivo)}&porto=${encodeURIComponent(porto2)}`;

    console.log(url); 

    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore durante la registrazione dell\'arrivo');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert('Arrivo registrato con successo!');
        })
        .catch(error => {
            console.error('Error:', error.message);
            alert('Errore: ' + error.message);
        });
}
function registraPolizza(event) {
    event.preventDefault(); 

    // Recupera i valori dai campi del form
    const viaggio = localStorage.getItem('viaggio');
    const fornitore = document.getElementById('fornitore').value;
    const peso = document.getElementById('peso').value;
    const merce = document.getElementById('merce').value;
    const durataFranchigia = document.getElementById('durata_franchigia').value;
    const costoFranchigia = document.getElementById('costo_franchigia').value;

    // Validazione di base
    if(!viaggio){
        alert('nessun viaggio trovato, registrare prima un viaggio');
        return;
    }
    if (!fornitore || !peso || !merce || !durataFranchigia || !costoFranchigia) {
        alert('Per favore, compila tutti i campi.');
        return;
    }

    // Costruisce l'URL con i parametri
    const url = `http://localhost:8080/admin/registraPolizza?token=${encodeURIComponent(localStorage.getItem('authToken'))}&viaggio=${encodeURIComponent(viaggio)}&fornitore=${encodeURIComponent(fornitore)}&peso=${encodeURIComponent(peso)}&merce=${encodeURIComponent(merce)}&durata_franchigia=${encodeURIComponent(durataFranchigia)}&costo_franchigia=${encodeURIComponent(costoFranchigia)}`;

    console.log(url); // Per debug

    // Effettua la chiamata API
    fetch(url, {
        method: 'GET',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore durante la registrazione della polizza');
            }
            return response.json();
        })
        .then(data => {
            console.log(data); // Log dei dati di risposta
            alert('Polizza registrata con successo!');
        })
        .catch(error => {
            console.error('Error:', error.message);
            alert('Errore: ' + error.message);
        });
}


