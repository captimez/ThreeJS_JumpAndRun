<template>
    <div class="background">
        <Lobby class ="center"></Lobby>
    </div>
    
</template>
<script setup>
import Lobby from '../components/lobby/Lobby.vue';

</script>
<style scopedd>
.background {
    width: 100vw;
    height: 100vh;
    display: flex; /* Aktiviert Flexbox */
    justify-content: center; /* Horizontale Zentrierung */
    align-items: center; /* Vertikale Zentrierung */
    margin: 0;
    background-image: url('../assets/login-background.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    background-color: rgba(0, 0, 0, 0.4); /* Schwarzer halbtransparenter Overlay */
    background-blend-mode: overlay; /* Überlagerung für Transparenz */
}

.center {
    width: 100%; /* Breite der Lobby-Komponente */
    max-width: 800px; /* Maximale Breite */
    background-color: rgba(32, 32, 32, 0.5); /* Optionaler halbtransparenter Hintergrund */
    padding: 20px;
    border-radius: 10px; /* Abgerundete Ecken */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Schatteneffekt */
}
</style>