package de.project.web.gameserver.modules.lobby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/lobby")
public class LobbyController {
    
    @Autowired
    private LobbyService lobbyService;

    @PostMapping("/create")
    public Lobby createLobby(@RequestParam String hostId) {
        Lobby lobby = createLobby(hostId);
        lobby.getPlayers().add(hostId);

        return lobby;  
    }
    
    @PostMapping("/join")
    public Lobby joinLobby(@RequestParam String lobbyId, @RequestParam String playerId) {
        
        return lobbyService.joinLobby(playerId, lobbyId);
    }
    
    @GetMapping("/{lobbyId}")
    public Lobby getLobby(@PathVariable String lobbyId) {
        return lobbyService.getLobby(lobbyId);
    }

    @PostMapping("/start")
    public void startGame(@RequestParam String lobbyId) {
        lobbyService.startGame(lobbyId);
    }
    
    
}
