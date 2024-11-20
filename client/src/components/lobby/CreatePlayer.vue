<template>
        <div class="form">
            <input class="name-input" v-model="playerNameInput" placeholder="Nickname" /><br>
            <button class="enter-button" @click="registerPlayer">Play</button>
        </div>
</template>
  
<script setup>
import { ref } from 'vue';
import axios, { AxiosError } from 'axios';
import router from '../../router/router';
import { usePlayerStore } from '../../stores/playerStore';
  
const playerStore = usePlayerStore();
const playerNameInput = ref('');
  
async function registerPlayer() {
    try{
        const response = await axios.post(`http://localhost:8080/api/player/create?name=${playerNameInput.value}`,{
            method: 'POST',
            withCredentials: true,
        });
        if(response){
            const { playerId, playerName, level} =  response.data;
            
            playerStore.setPlayerId(playerId),
            playerStore.setPlayerName(playerName);
            playerStore.setPlayerLevel(level);
            
            router.push('/lobby');
        }
        console.log(response.data)
    }catch(err){    
        console.log(err);
    }  
}
</script>
<style scoped>

.form {
    position: absolute; /* Positioniere relativ zur .background */
    top: 43%; /* Passe diesen Wert an, damit die Form genau unter dem Titel steht */
    left: 50%; /* Zentriere die Form horizontal */
    transform: translate(-50%, -50%); /* Perfekte Zentrierung */
    width: 30%;
    border-radius: 10px;
    padding: 20px;
    text-align: center;
}


.name-input{
    font-size:medium;
    font-weight: 550;
    padding:10px;
    border-radius: 10px;
    margin-bottom: 20px;
    border: solid 0;
    box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
    width: 40%;
}

.name-input:focus{
    outline: none;
}

.enter-button{
    background-color: rgba(217, 151, 72, 1);
    font-size:medium;
    font-weight: 550;
    color: #262626;
    padding:10px;
    border-radius: 10px;
    border: solid 0;
    box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
    width: 20%;
}

.enter-button:hover{
    background-color: rgb(255, 170, 65);
    box-shadow: 3px 2px 2px rgba(0, 0, 0, 0.4);
}
</style>
  