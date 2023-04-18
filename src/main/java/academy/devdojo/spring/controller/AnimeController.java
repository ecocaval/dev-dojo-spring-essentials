package academy.devdojo.spring.controller;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.request.AnimePostRequestBody;
import academy.devdojo.spring.request.AnimePutRequestBody;
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
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> findAll(@RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok(animeService.findAll(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        Anime anime = animeService.findById(id);
        return ResponseEntity.ok(anime);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(id, animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}