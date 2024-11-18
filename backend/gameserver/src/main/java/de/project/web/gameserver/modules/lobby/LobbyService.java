package de.project.web.gameserver.modules.lobby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.project.web.gameserver.modules.player.Player;

@Service
public class LobbyService {
    
    private static final Logger Logger = LoggerFactory.getLogger(LobbyService.class);
    private final Map<String, Lobby> lobbies = new HashMap<>();
    
    public Lobby createLobby(String hostId){
        String lobbyId ="lobby-" + System.currentTimeMillis();

        Logger.info("Create Loby Request");

        Lobby lobby = new Lobby(lobbyId, hostId);
        lobby.setHostId(hostId);
        lobbies.put(lobbyId, lobby);

        Logger.info("created Lobby: " + lobby.getId() +" Players: " +lobby.getPlayers());
        
        return lobby;
    }

    public Lobby joinLobby(String playerId, String lobbyId){
        Lobby lobby = lobbies.get(lobbyId);
        if(lobby != null && !lobby.isGameStarted()){
            lobby.getPlayers().add(playerId);
        }

        Logger.info("Player joined! Current Lobby Players: " + lobby.getPlayers());
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

    public List<String> getPlayersInLobby(String lobbyId) {
        return lobbies.get(lobbyId).getPlayers();
    }
}
