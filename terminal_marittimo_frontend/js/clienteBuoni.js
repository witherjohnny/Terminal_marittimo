function loadBuoni() {
    
    
    fetch(`http://localhost:8080/cliente/getBuoni?token=${encodeURIComponent(localStorage.getItem('authToken'))}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        const container = document.getElementById('buoni-container');
        container.innerHTML = '';
        data.forEach(buono => {
            //crea una select autisti
            let selectAutisti;
            fetch(`http://localhost:8080/cliente/getAutisti?token=${encodeURIComponent(localStorage.getItem('authToken'))}`)
            .then(res => res.json())
            .then(autisti => {
                const selectAutisti = document.createElement("select");
                selectAutisti.className = "form-select";
                selectAutisti.id = "autisti";
                selectAutisti.name = "autisti";
                
                autisti.forEach(autista => {
                    const option = document.createElement('option');
                    option.value = autista.id;
                    option.textContent = autista.nome;
                    selectAutisti.appendChild(option);
                });

                //crea card con la select autisti 
                const card = document.createElement('div');
                card.className = 'card mb-3';
                const cardBody = document.createElement('div');
                cardBody.className = 'card-body';
                cardBody.id = `buono-${buono.id}`;
                cardBody.innerHTML = `
                    <h5 class="card-title">Buono #${buono.id}</h5>
                    <p class="card-text">Descrizione: ${buono.polizza.fornitore.nome || ''} - ${buono.polizza.merce.tipologia_merce}</p>
                    <p class="card-text">Peso richiesto: ${buono.peso || ''}</p>
                `;
                const label = document.createElement('label');
                label.htmlFor = "autisti";
                label.textContent = "Seleziona autista:";
                cardBody.appendChild(label);
                cardBody.appendChild(selectAutisti);
                const assegnaButton = document.createElement('button');
                assegnaButton.className = 'btn btn-primary';
                assegnaButton.textContent = 'Assegna';
                assegnaButton.onclick = () => {
                    assegnaBuono(buono.id);
                };
                cardBody.appendChild(assegnaButton);
                card.appendChild(cardBody);

                container.appendChild(card);
            })
            .catch(err => {
                console.error('Errore nel caricamento degli autisti:', err);
            });
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
function assegnaBuono(buonoId) {
    const card = document.getElementById(`buono-${buonoId}`);
    const select = card.querySelector('select[name="autisti"]');
    const autistaId = select ? select.value : null;
    if (!autistaId) {
        alert('Seleziona un autista prima di assegnare il buono.');
        return;
    }

    fetch(`http://localhost:8080/cliente/assegnaBuono?token=${encodeURIComponent(localStorage.getItem('authToken'))}&id=${buonoId}&id_autista=${autistaId}`, {
        method: 'GET',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Errore nella richiesta di assegnazione del buono');
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        // Rimuovi la card del buono assegnato dal DOM
        const card = document.getElementById(`buono-${buonoId}`);
        if (card) {
            card.remove();
        }
        alert('Buono assegnato con successo');
    })
    .catch(error => {
        console.error('Error:', error);
    });
}