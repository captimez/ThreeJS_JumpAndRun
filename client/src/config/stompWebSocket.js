import { Client } from '@stomp/stompjs';
import { useLobbyStore } from '../stores/lobbyStore';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const stompClient = new Client({
  brokerURL: 'ws://localhost:8080/ws',
  connectHeaders: {
    // Optional: Authentifizierungs-Header hinzufügen
  },
  onConnect: () => {
    console.log('Connected to STOMP');
    const lobbyStore = useLobbyStore();
    // Hier könnten globale Abonnements hinzugefügt werden, falls nötig
  },
  onDisconnect: () => {
    console.log('Disconnected from STOMP');
  },
  debug: (str) => console.log(str), // Optional: Debugging aktivieren
});

// Funktion zum Abonnieren eines spezifischen Channels
function subscribeToLobby(lobbyId,callback) {
  if (!lobbyId) {
    console.error('Lobby ID is required to subscribe.');
    return;
  }
    stompClient.subscribe(`/topic/lobby/${lobbyId}`, (message) => {
      switch(message.body.type){
        case "playerJoined": 
          callback(JSON.parse(message.body.data));
          break;
        
        case "gameStarted":
          router.push(`${lobbyId}/game`)
      }
       // Nachricht als JSON an den Callback übergeben
    });
}

// Funktion zum Senden von Nachrichten
function sendMessage(destination, body) {
  if (stompClient.connected) {
    stompClient.publish({
      destination,
      body: JSON.stringify(body),
    });
  } else {
    console.error('WebSocket is not connected');
  }
}

// Exportiere den STOMP-Client und die Funktionen
export { stompClient, subscribeToLobby, sendMessage };
