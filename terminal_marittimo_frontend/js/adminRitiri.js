function loadRitiriDaApprovare(){
    fetch(`http://localhost:8080/admin/getAllNotApprovedRitiri?token=${encodeURIComponent(localStorage.getItem('authToken'))}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        const container = document.getElementById('ritiri-container');
        container.innerHTML = '';
        data.forEach(ritiro => {
            const card = document.createElement('div');
            card.className = 'card mb-3';
            card.innerHTML = `
                <div class="card-body" id="ritiro-${ritiro.id}">
                    <h5 class="card-title">Ritiro #${ritiro.id}</h5>
                    <p class="card-text">Descrizione polizza: ${ritiro.buono.polizza.fornitore.nome || ''} - ${ritiro.buono.polizza.merce.tipologia_merce}</p>
                    <p class="card-text">Peso totale polizza: ${ritiro.buono.polizza.peso || ''}</p>
                    <p class="card-text">Peso richiesto: ${ritiro.buono.peso || ''}</p>
                    <p class="card-text">Cliente: ${ritiro.buono.id_cliente || ''}</p>
                    <p class="card-text">Targa: ${ritiro.guida.camion.targa || ''}</p>
                    <p class="card-text">Autista: ${ritiro.guida.autista.nome || ''} ${ritiro.guida.autista.cognome || ''}</p>
                    <button class="btn btn-success" onclick="approvaRitiro(true, ${ritiro.id})">Approva</button>
                    <button class="btn btn-danger" onclick="approvaRitiro(false, ${ritiro.id})">Non approvare</button>
                </div>
            `;
            container.appendChild(card);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
function approvaRitiro(approva, id){
    fetch(`http://localhost:8080/admin/approvaRitiro?token=${encodeURIComponent(localStorage.getItem('authToken'))}&approva=${approva}&id=${id}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // Rimuovi la card del ritiro approvato/disapprovato dal DOM
        const card = document.getElementById(`ritiro-${id}`);
        if (card) {
            card.remove();
        }
        alert('Ritiro ' + (approva ? 'approvato' : 'non approvato') + ' con successo');
    })
    .catch(error => {
        console.error('Error:', error);
    });
}