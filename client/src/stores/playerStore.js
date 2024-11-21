import { defineStore } from 'pinia';

export const usePlayerStore = defineStore('player', {
    state: () => ({
        playerId: "",
        playerName: "",
        level: 0
    }),
    actions: {
        setPlayer(playerId,playerName,level){
            this.playerId = playerId;
            this.playerName = playerName;
            this.level = level;

            sessionStorage.setItem("playerStore", JSON.stringify({ playerId,playerName,level}))
        },

        restorePlayer(){
            const player = sessionStorage.getItem('playerStore');

            if(player){
                this.playerId = player.playerId;
                this.playerName = player.playerName;
                this.level = player.level;
            }
        }
        
    },
});
