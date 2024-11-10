<template>
    <div>
        <input v-model="playerName" placeholder="Enter your name" />
        <button @click="registerPlayer">Submit</button>
    </div>
</template>
    
<script setup>
        import { ref} from 'vue';
        import router from '../../router/router';
    
        const playerName = ref('');
    
        async function registerPlayer() {
            const response = await fetch(`/api/player/register?name=${playerName.value}`, {
                method: 'POST',
            });
            const player = await response.json();
            console.log('Player registered:', player);
            router.push({ name: 'LobbyCreate', params: { playerId: player.id, playerName: player.name } });
        }
</script>
<style scoped>
    
</style>
      