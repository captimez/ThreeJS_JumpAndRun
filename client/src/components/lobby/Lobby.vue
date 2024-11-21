<template>
    <div class="lobby-container">
        <h1 class="lobby-title">Game Lobby</h1>
        <ul class="player-list">
            <li v-for="player in players" 
                class="player-item"> 
                {{ player.playerName }}
            </li>
        </ul>
    </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
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
/* Container für die Lobby */
.lobby-container {
    text-align: center;
    padding: 20px;
    color: white;
}

/* Titel der Lobby */
.lobby-title {
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 20px;
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
}

/* Liste der Spieler */
.player-list {
    list-style: none;
    padding: 0;
    margin: 0 auto;
    width: 50%;
}

/* Einzelner Spieler-Eintrag */
.player-item {
    background-color: rgba(255, 255, 255, 0.1); /* Halbtransparenter Hintergrund */
    padding: 15px;
    margin: 10px 0;
    border-radius: 10px; /* Abgerundete Ecken */
    font-size: 1.2rem;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Schatten für Tiefe */
    transition: transform 0.2s, background-color 0.2s; /* Übergangseffekte */
    text-align: center;
}

/* Hover-Effekt für Spieler-Einträge */
.player-item:hover {
    transform: translateY(-5px); /* Eintrag hebt sich leicht an */
    background-color: rgba(255, 255, 255, 0.2); /* Hellerer Hintergrund bei Hover */
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Stärkerer Schatten bei Hover */
}
</style>
