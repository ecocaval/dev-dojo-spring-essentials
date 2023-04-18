package academy.devdojo.spring.controller;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.service.AnimeService;
import academy.devdojo.spring.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("anime")
@Log4j2
//@AllArgsConstructor
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping()
    public ResponseEntity<List<Anime>> findAll() {
        return ResponseEntity.ok(animeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        Anime anime = animeService.findById(id);

        return ResponseEntity.ok(anime);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        boolean animeWasDeleted = animeService.delete(id);

        if (animeWasDeleted) {
            return new ResponseEntity("anime deleted sucessfully", HttpStatus.OK);
        }
        return new ResponseEntity("anime was not found", HttpStatus.NOT_FOUND);
    }
}