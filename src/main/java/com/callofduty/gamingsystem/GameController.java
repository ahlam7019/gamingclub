package com.callofduty.gamingsystem;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    private GameRepository repo;

    // Create Game
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        game.set_id(null); // let MongoDB generate ObjectId
        if (game.getName() == null || game.getPrice() == null) {
            return ResponseEntity.badRequest().build();
        }
        Game savedGame = repo.save(game);
        return ResponseEntity.ok(savedGame);
    }

    // Get all Games
    @GetMapping
    public List<Game> findAll() {
        return repo.findAll();
    }

    // Get Game by ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Game> findById(@PathVariable String id) {
        Optional<Game> game = repo.findById(new ObjectId(id));
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Game
    @PutMapping(path = "/{id}")
    public ResponseEntity<Game> update(@PathVariable String id, @RequestBody Game game) {
        Optional<Game> optionalOldGame = repo.findById(new ObjectId(id));
        if (optionalOldGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Game oldGame = optionalOldGame.get();

        if (game.getName() != null) oldGame.setName(game.getName());
        if (game.getDescription() != null) oldGame.setDescription(game.getDescription());
        if (game.getPrice() != null) oldGame.setPrice(game.getPrice());

        Game updatedGame = repo.save(oldGame);
        return ResponseEntity.ok(updatedGame);
    }

    // Delete Game
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Game> optionalGame = repo.findById(new ObjectId(id));
        if (optionalGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(new ObjectId(id));
        return ResponseEntity.noContent().build();
    }
}
