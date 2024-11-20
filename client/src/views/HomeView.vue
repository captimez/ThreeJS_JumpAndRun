<template>
    <div class="background">
        <div class="button-form">
            <button class="lobby-button" @click="createLobby()">Create Lobby</button><br>
            <div class="flex-child">
                <input type="text" class="lobbycode-input" v-model="lobbyId" placeholder="Enter Lobby-Code">
                <button class="join-lobby-button" @click="joinLobby()">Join Lobby</button>
            </div>
        </div>
    </div>
</template>
<script setup>
import axios from 'axios';
import { ref } from 'vue'
import { useLobbyStore } from '../stores/lobbyStore';
import router from '../router/router';

const lobbyStore = useLobbyStore();
const lobbyId = ref("");

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
        router.push(`/lobby/${lobbyId.value}`);
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
        left: 53%; /* Zentriere die Form horizontal */
        transform: translate(-50%, -50%); /* Perfekte Zentrierung */
        width: 30%;
        border-radius: 10px;
        padding: 20px;
        text-align: center;
    }
    .flex-child{
        display: flex;
        width: 100%;
        justify-content: space-between;
    }
    .lobby-button{
        padding:10px;
        margin-bottom: 10px;
        font-weight: 500;
        color:#262626;
        background-color: rgba(217, 151, 72, 1);
        border-radius: 5px;
        border: solid 0px;
        width: 100%;
        box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
    }
    .lobby-button:hover{
        background-color: rgb(255, 170, 65);
        box-shadow: 5px 3px 3px rgba(0, 0, 0, 0.4);
    }

    .join-lobby-button{
        padding:10px;
        font-weight: 500;
        color:#262626;
        background-color: rgba(217, 151, 72, 1);
        border-radius: 5px;
        border: solid 0px;
        width: 50%;
        box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
    }
    .join-lobby-button:hover{
        background-color: rgb(255, 170, 65);
        box-shadow: 5px 3px 3px rgba(0, 0, 0, 0.4);
    }



    .lobbycode-input{
        font-size:medium;
        font-weight: 550;
        padding:10px;
        border-radius: 5px;
        border: solid 0;
        box-shadow: 1px 2px 2px rgba(0, 0, 0, 0.4);
        width: 100%;
    }

    .lobbycode-input:focus{
        outline: none;
    }
</style>