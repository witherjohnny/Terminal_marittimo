function approvaBuono(approva, buonoId){

    fetch(`http://localhost:8080/admin/approvaBuono?token=${encodeURIComponent(localStorage.getItem('authToken'))}&approva=${approva}&id=${buonoId}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // Rimuovi la card del buono approvato/disapprovato dal DOM
        const card = document.getElementById(`buono-${buonoId}`);
        if (card) {
            card.remove();
        }
        alert('Buono ' + (approva ? 'approvato' : 'non approvato') + ' con successo');
    })
    .catch(error => {
        console.error('Error:', error);
    });

}
function loadBuoniDaApprovare(){
    fetch(`http://localhost:8080/admin/getAllNotApprovedBuoni?token=${encodeURIComponent(localStorage.getItem('authToken'))}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        const container = document.getElementById('buoni-container');
        container.innerHTML = '';
        data.forEach(buono => {
            const card = document.createElement('div');
            card.className = 'card mb-3';
            card.innerHTML = `
                <div class="card-body" id="buono-${buono.id}">
                    <h5 class="card-title">Buono #${buono.id}</h5>
                    <p class="card-text">Descrizione polizza: ${buono.polizza.fornitore.nome || ''} - ${buono.polizza.merce.tipologia_merce}</p>
                    <p class="card-text">Peso totale polizza: ${buono.polizza.peso || ''}</p>
                    <p class="card-text">Peso richiesto: ${buono.peso || ''}</p>
                    <p class="card-text">Cliente: ${buono.id_cliente || ''}</p>
                    <button class="btn btn-success" onclick="approvaBuono(true, ${buono.id})">Approva</button>
                    <button class="btn btn-danger" onclick="approvaBuono(false, ${buono.id})">Non approvare</button>
                </div>
            `;
            container.appendChild(card);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}