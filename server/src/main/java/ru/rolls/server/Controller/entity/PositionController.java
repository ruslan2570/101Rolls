package ru.rolls.server.controller.entity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.rolls.server.entity.Position;
import ru.rolls.server.service.entity.PositionService;

@Controller
@RequestMapping("/work/position")
@PreAuthorize("hasAuthority('ADMIN')")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public ResponseEntity<Iterable<Position>> getPositions() {
        return ResponseEntity.ok(positionService.getPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPosition(
            @PathVariable(name = "id", required = true) Long id) {
        Optional<Position> optPos = positionService.getPosition(id);
        if (optPos.isPresent()) {
            return ResponseEntity.ok(optPos.get());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Position> addPosition(
            @RequestBody Position position) {
        return ResponseEntity.ok(positionService.addOrUpdatePosition(position));
    }
}
