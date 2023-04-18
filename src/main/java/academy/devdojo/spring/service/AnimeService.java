package academy.devdojo.spring.service;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.mapper.AnimeMapper;
import academy.devdojo.spring.repository.AnimeRepository;
import academy.devdojo.spring.request.AnimePostRequestBody;
import academy.devdojo.spring.request.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public Anime findById(long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void replace(long id, AnimePutRequestBody animePutRequestBody) {
        if (id != animePutRequestBody.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path variable and body id are different");
        }
        if (!animeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found");
        }
        animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutRequestBody));
    }

    public void delete(long id) {
        animeRepository.deleteById(id);
    }
}
