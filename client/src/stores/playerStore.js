import { defineStore } from 'pinia';

export const usePlayerStore = defineStore('player', {
    state: () => ({
        playerId: "",
        playerName: "",
        level: 0
    }),
    actions: {
        setPlayerName(playerName) {
            this.playerName = playerName;
        },
        setPlayerId(playerId) {
            this.playerId =  playerId;
        },
        setPlayerLevel(level){
            this.level = level;
        },
        
    },
});
