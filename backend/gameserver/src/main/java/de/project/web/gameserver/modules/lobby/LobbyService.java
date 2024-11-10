package de.project.web.gameserver.modules.lobby;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {
    
    private static final Logger Logger = LoggerFactory.getLogger(LobbyService.class);
    private final Map<String, Lobby> lobbies = new HashMap<>();
    
    public Lobby createLobby(String hostId){
        String lobbyId ="lobby-" + System.currentTimeMillis();

        Lobby lobby = new Lobby(lobbyId, hostId);
        lobbies.put(lobbyId, lobby);
        
        return lobby;
    }

    public Lobby joinLobby(String playerId, String lobbyId){
        Lobby lobby = lobbies.get(lobbyId);
        if(lobby != null && !lobby.isGameStarted()){
            lobby.getPlayers().add(playerId);
        }
        return lobby;
    }

    public Lobby getLobby(String lobbyId){
        return lobbies.get(lobbyId);
    }

    public void startGame(String lobbyId){
        Lobby lobby = lobbies.get(lobbyId);

        if(lobby != null){
            lobby.setGameStarted(true);
        }
    }
}
