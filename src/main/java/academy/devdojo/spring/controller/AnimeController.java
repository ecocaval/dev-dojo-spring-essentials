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

    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody Anime anime) {
        animeService.replace(id, anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}