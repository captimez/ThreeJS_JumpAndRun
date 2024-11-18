<template>
    <div class="background">
        <div class="button-form">
            <button class="lobby-button" @click="createLobby()">Create Lobby</button><br>
            <button class="lobby-button">Join Lobby</button>
        </div>
    </div>
</template>
<script setup>
import axios from 'axios';
import { useLobbyStore } from '../stores/lobbyStore';
import router from '../router/router';

const lobbyStore = useLobbyStore();

async function createLobby(){
    try{
        const response = await axios.post(`http://localhost:8080/api/lobby/create?hostId=${sessionStorage.getItem('playerId')}`,{
            method: 'POST',
        })

        lobbyStore.setLobbyId(response.data.id)
        router.push(`lobby/${response.data.id}`)

        if(response){
            console.log(response.data)
        }
    }catch(err){
        console.log(err)
    }
}
async function joinLobby(){
    try{
        //open modal, enter lobby-id, then send api request to join
    }catch(err){
        console.log(err)
    }
}

</script>
<style scoped>
    .button-form{
        position: absolute; /* Positioniere relativ zur .background */
        top: 43%; /* Passe diesen Wert an, damit die Form genau unter dem Titel steht */
        left: 50%; /* Zentriere die Form horizontal */
        transform: translate(-50%, -50%); /* Perfekte Zentrierung */
        width: 30%;
        border-radius: 10px;
        padding: 20px;
        text-align: center;
    }

    .lobby-button{
        margin: 10px;
        padding:10px;
        font-weight: 500;
        color:#262626;
        background-color: rgba(217, 151, 72, 1);
        border-radius: 10px;
        border: solid 0px;
        width: 50%;
        box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
    }
    .lobby-button:hover{
    background-color: rgb(255, 170, 65);
    box-shadow: 5px 3px 3px rgba(0, 0, 0, 0.4);
}
</style>