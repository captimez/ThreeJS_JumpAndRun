import { defineStore } from 'pinia';

export const useLobbyStore = defineStore('lobby', {
    state: () => ({
        players: [], // Spieler-Liste
        lobbyId: null,
    }),
    actions: {
        setPlayers(players) {
            this.players = players; // Spieler-Liste aktualisieren
        },
        addPlayer(player) {
            this.players.push(player); // Neuen Spieler hinzufügen
        },
        removePlayer(playerId) {
            this.players = this.players.filter(p => p.id !== playerId); // Spieler entfernen
        },
        setLobbyId(lobbyid){
          this.lobbyId = lobbyid;
        }
    },
});
