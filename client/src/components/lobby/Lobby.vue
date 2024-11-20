<template>
    <div>
        <h1>Lobby ID: {{ lobbyId }}</h1>
        <ul>
            <li v-for="player in players" 
                class="player-list"> 
                {{ player.playerName }}
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
    lobbyStore.initWebsocket(lobbyId);
});
</script>

<style scoped>
/* Füge hier dein CSS hinzu */
.player-list{
    color: white;
}
</style>
