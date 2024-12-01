package de.project.web.gameserver.modules.lobby;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import de.project.web.gameserver.modules.player.Player;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/lobby")
public class LobbyController {
    
    @Autowired
    private LobbyService lobbyService;
    @Autowired
    private LobbyMessagingService messagingService;
    private static final Logger logger = LoggerFactory.getLogger(LobbyController.class);
    

    @PostMapping("/create")
    public Lobby createLobby(@RequestParam String hostId) {
        Lobby lobby = lobbyService.createLobby(hostId);

        return lobby;  
    }
    
    @PostMapping("/join")
    public Lobby joinLobby(@RequestParam String lobbyId, @RequestParam String playerId, @RequestParam String playerName) {
        
        return lobbyService.joinLobby(playerId, playerName, lobbyId);
    }
    
    @GetMapping("/{lobbyId}")
    public Lobby getLobby(@PathVariable String lobbyId) {
        return lobbyService.getLobby(lobbyId);
    }

    @PostMapping("/start")
    public void startGame(@RequestParam String lobbyId) {
        lobbyService.startGame(lobbyId);
    }

    //Websocket Management
    @MessageMapping("/lobby/{lobbyid}/join")
    @SendTo("topic/lobby/{lobbyid}")
    public void playerJoinLobby(Player player, @DestinationVariable String lobbyid){
        HashMap<String,Object> response = new HashMap<String,Object>();

        logger.info("Websocket request received" + player.getPlayerId());
        lobbyService.joinLobby(player.getPlayerId(), player.getPlayerName(), lobbyid);
        List<Player> players = lobbyService.getPlayersInLobby(lobbyid);

        response.put("type", "playerJoined");
        response.put("data", players);

    
        messagingService.sendPlayerList(lobbyid, response);
    }

    @MessageMapping("/lobby/{lobbyid}/start")
    @SendTo("topic/lobby/{lobbyid}")
    public void gameStart(Player player, @DestinationVariable String lobbyid){
        HashMap<String,Object> response = new HashMap<String, Object>();

        logger.info("Game: {} started", lobbyid);
        lobbyService.startGame(lobbyid);

        response.put("type", "gameStarted");
        response.put("data", true);

        messagingService.sendPlayerList(lobbyid, response);
    }
    
    
}
