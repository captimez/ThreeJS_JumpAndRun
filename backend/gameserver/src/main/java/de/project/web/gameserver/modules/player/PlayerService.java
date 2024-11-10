package de.project.web.gameserver.modules.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.project.web.gameserver.modules.lobby.LobbyService;

@Service
public class PlayerService {
    
    private static final Logger Logger = LoggerFactory.getLogger(PlayerService.class);
    private final Map<String, Player> players = new HashMap<>();

    public Player createPlayer(String playerName){
        String playerId = UUID.randomUUID().toString();
        Player player = new Player(playerId,playerName);

        Logger.info("Player registerd NAME: "+ playerName);
        players.put(playerId, player);

        return player;
    }

    public Player getPlayer(String playerId){
        
        return players.get(playerId);    
    }

    public Collection<Player> getAllPlayers(){
        return players.values();
    }
}
