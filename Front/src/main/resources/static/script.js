const HEADERS = {
    'Content-Type': 'application/json'
  }

async function fetchPlayer1(){
    const input = document.getElementById("player1").value;
    const form = document.getElementById("formTable")
    const btn = document.getElementById("btn")
    const formTable = document.getElementById("formTable");



       const res = await fetch('http://localhost:8081/game/start', {
            method:'POST',
            headers: HEADERS,
            body:JSON.stringify({
                name:input
            })
        })
        console.log(res.status)
        if(res.status===200){
         if(formTable.style.display === "none"){
                formTable.style.display = "block";
                if(btn.textContent==="Crear") btn.textContent = "Creado";
            }
        }

        const cont = await res.json();
        console.log(cont)
}

async function fetchPlayer2(){
    const inputPlayer2 = document.getElementById("player2").value;
    const gameId = document.getElementById("gameId").value;
    const btnJoin = document.getElementById("btnJoin")

       const res = await fetch('http://localhost:8081/game/connect', {
            method:'POST',
            headers: HEADERS,
            body:JSON.stringify({
                player2:{name:inputPlayer2},
                gameId: gameId
            })
        })
        if(res.status===200){
            if(btnJoin.textContent==="Conectar") btnJoin.textContent = "Conectado";
        }
       
        const cont = await res.json();
        console.log(cont)

}
