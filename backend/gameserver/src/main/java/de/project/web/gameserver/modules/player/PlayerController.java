package de.project.web.gameserver.modules.player;

import java.util.Collection;

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
@RequestMapping("/api/player")
public class PlayerController {
    
    @Autowired
    PlayerService playerService;

    @PostMapping("/create")
    public Player createPlayer(@RequestParam String name) {
        return playerService.createPlayer(name);
    }

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable String playerId) {
        return playerService.getPlayer(playerId);
    }

    @GetMapping("/all")
    public Collection<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
    
    
}
