function Load(){
    loadPolizzeOptions();
}
async function loadPolizzeOptions() {
    try {
        console.log('http://localhost:8080/cliente/getAllPolizze?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        const response = await fetch('http://localhost:8080/cliente/getAllPolizze?token=' + encodeURIComponent(localStorage.getItem('authToken')));
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();

        const selectElement = document.getElementById('polizza'); 
        selectElement.innerHTML = ''; // Clear existing options

        data.forEach(polizza => {
            const option = document.createElement('option');
            option.value = polizza.id;
            option.textContent = polizza.fornitore.nome +" - "+ polizza.merce.tipologia_merce;
            selectElement.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
function richiediBuono(event){
    event.preventDefault();

    const polizza = document.getElementById('polizza').value;
    const peso = document.getElementById('peso').value;
    if (!polizza) {
        alert('Seleziona una polizza.');
        return;
    }
    if (!peso || isNaN(peso) || peso <= 0) {
        alert('Inserisci un peso valido.');
        return;
    }
    fetch(`http://localhost:8080/admin/richiediBuono?token=${encodeURIComponent(localStorage.getItem('authToken'))}&polizza=${encodeURIComponent(polizza)}&peso=${encodeURIComponent(peso)}`, {
        method: 'GET',
    }).then(response => {
        if (!response.ok) {
            throw new Error('Errore ');
        }
        return response.json();
    }).then(data => {
        
        console.log(data);
        alert('Buono richiesto con successo, attendere approvazione');

    }).catch(error => {
        
        console.log('Error: ' + error.message);
    });
}