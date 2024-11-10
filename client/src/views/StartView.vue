<template>
    <div class="background">
        <CreatePlayer></CreatePlayer>
    </div>
    
</template>
<script setup>
import CreatePlayer from '../components/lobby/CreatePlayer.vue';


</script>
<style scopedd>
.background {
    width: 100vw;
    height: 100vh;
    position: relative; /* Ermöglicht absolute Positionierung der Kinder */
    margin: 0;
    background-image: url('../assets/login-background.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    background-color: rgba(0, 0, 0, 0.4);
    background-blend-mode: overlay;
}
</style>