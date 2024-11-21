import { defineStore } from 'pinia';
import { stompClient, subscribeToLobby, sendMessage } from '../config/stompWebSocket';
import { usePlayerStore } from './playerStore';

export const useLobbyStore = defineStore('lobby', {
    state: () => ({
        players: [], // Spieler-Liste
        lobbyId: null,
        connected: false,
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
        },
        initWebsocket(lobbyId){
            try{
                stompClient.onConnect = () => {
                    const player = JSON.parse(sessionStorage.getItem('player'));
                    console.log(player.playerId)
                    console.log('STOMP connected in Lobby.vue');
                    // Spieler-Liste abonnieren
                    if(!this.connected){
                        subscribeToLobby(lobbyId, (players) => {
                            this.players = players;
                            this.connected = true;
                            console.log(players)
    
                        });
                
                        sendMessage(`/app/lobby/${lobbyId}/join`,{
                            playerName:  player.playerName,
                            playerId: player.playerId,
                        });
                    }
                };
            
                // Verbindung starten (falls nicht aktiv)
                if (!stompClient.connected) {
                    stompClient.activate();
                }
            }catch(err){
                console.log(err);
            }
            
        }
    },
});
