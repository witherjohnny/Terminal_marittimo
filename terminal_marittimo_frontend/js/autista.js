function getAssignedBuoni() {
    fetch(`http://localhost:8080/autista/getBuoni?token=${encodeURIComponent(localStorage.getItem('authToken'))}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        const container = document.getElementById('autisti-container');
        container.innerHTML = '';
        data.forEach(buono => {
            const card = document.createElement('div');
            card.className = 'card mb-3';
            card.innerHTML = `
                <div class="card-body" id="buono-${buono.id}">
                    <h5 class="card-title">Buono #${buono.id}</h5>
                    <p class="card-text">Descrizione polizza: ${buono.polizza.fornitore.nome || ''} - ${buono.polizza.merce.tipologia_merce}</p>
                    <p class="card-text">Peso richiesto: ${buono.peso || ''}</p>
                    <p class="card-text">Cliente: ${buono.id_cliente || ''}</p>
                    <div class="mb-2">
                        <label for="targa-${buono.id}" class="form-label">Targa:</label>
                        <input type="text" class="form-control" id="targa-${buono.id}" name="targa-${buono.id}" placeholder="Inserisci targa">
                    </div>
                    <button class="btn btn-success" onclick="ritira(${buono.id},${buono.id_cliente},${buono.polizza.id},${buono.peso})">Ritira</button>
                </div>
            `;
            container.appendChild(card);
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
function ritira(id_buono, peso) {
    const targaInput = document.getElementById(`targa-${id_buono}`);
    const targa = targaInput ? targaInput.value.trim() : '';
    if (!targa) {
        alert('Inserisci la targa prima di procedere con il ritiro.');
        return;
    }
    const token = localStorage.getItem('authToken');
    console.log(`http://localhost:8080/autista/richiediRitiro?token=${encodeURIComponent(token)}&id_buono=${encodeURIComponent(id_buono)}&peso=${encodeURIComponent(peso)}&targa=${encodeURIComponent(targa)}`);
    fetch(`http://localhost:8080/autista/richiediRitiro?token=${encodeURIComponent(token)}&id_buono=${encodeURIComponent(id_buono)}&peso=${encodeURIComponent(peso)}&targa=${encodeURIComponent(targa)}`, {
        method: 'GET',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Errore nella risposta del server: ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
            alert('Ritiro effettuato con successo!');
            getAssignedBuoni();
        } else {
            alert('Errore nel ritiro: ' + (data.message || 'Operazione fallita.'));
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Errore di rete durante il ritiro: ' + error.message);
    });
}