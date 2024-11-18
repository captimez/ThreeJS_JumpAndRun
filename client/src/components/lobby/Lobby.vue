<template>
    <div>
        <h1>Lobby ID: {{ lobbyId }}</h1>
        <ul>
            <li v-for="player in players" :key="player.id"> 
                {{ player.name }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { sendMessage, stompClient, subscribeToLobby } from '../../config/stompWebSocket';
import { useLobbyStore } from '../../stores/lobbyStore';

const route = useRoute();
const lobbyStore = useLobbyStore();
const lobbyId = route.params["id"]; // Lobby-ID aus der URL
const players = computed(() => lobbyStore.players); // Reaktive Bindung zur Spieler-Liste

onMounted(() => {
    stompClient.onConnect = () => {
        console.log('STOMP connected in Lobby.vue');
        // Spieler-Liste abonnieren
        subscribeToLobby(lobbyId, (players) => {
            console.log(players)
        });

        sendMessage(`/app/lobby/${lobbyId}/join`,{
            playerId: sessionStorage.getItem('playerId'),
        });
    };

    // Verbindung starten (falls nicht aktiv)
    if (!stompClient.connected) {
        stompClient.activate();
    }
});
</script>

<style scoped>
/* Füge hier dein CSS hinzu */
</style>
