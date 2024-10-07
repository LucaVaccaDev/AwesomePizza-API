# AwesomePizza-API
Ho realizzato delle API per gestire una pizzeria il contesto è il seguente : 

La pizzeria "Awesome Pizza" vuole creare un nuovo portale per gestire gli ordini dei miei clienti. 
Il portale non richiede la registrazione dell'utente per poter ordinare le sue pizze. 
Il pizzaiolo vede la coda degli ordini e li può prendere in carico uno alla volta. 
Quando la pizza è pronta, il pizzaiolo passa all'ordine successivo. 
L'utente riceve il suo codice d'ordine e può seguire lo stato dell'ordine fino all'evasione.

Immaginiamo lo scenario dove si entra in questa pizzeria altamente tecnologica piena di schermi, tipo McDonald's.
Il cliente effettua l'ordine e riceve il codice ordine, che gli servirà per seguire il cambiamento di stato dell'ordine sugli schermi.
Nello stesso momento, la pizzeria, salverà nei suoi database, l'ordine insieme al nome e la mail del clinete, questo perchè in ottica futura, la pizzeria avrà a disposizione i dati per effettuare campagne di fidelizzazione e analisi sulle pizze più richieste, al fine di organizzare le risorse e mitigare gli sprechi.

L'ordine finisce nella lista degli ordini in carico al pizzaiolo, con lo stato "Ordine ricevuto.
Nel momento in cui il pizzaiolo prendere in carico l'ordine, lo stato passa "In lavorazione" e una volta terminate tutte le pizze diventa "Ordine completato".

# Requisiti
Creare un database in locale dal nome awesome-pizza e inserire "root" come username e password, chiaramente in caso di configurazioni differenti, cambiare i parametri presenti nel application.properties

<img width="703" alt="Screenshot 2024-10-07 alle 18 36 52" src="https://github.com/user-attachments/assets/17033375-4f1b-4179-b899-15c48b479f92">

# API Test
Nella cartella APITest, è presente la postman collection per effettuare tutti i test del caso.


