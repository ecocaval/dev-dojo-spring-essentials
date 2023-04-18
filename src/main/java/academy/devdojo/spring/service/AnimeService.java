package academy.devdojo.spring.service;

import academy.devdojo.spring.domain.Anime;
import academy.devdojo.spring.exception.BadRequestException;
import academy.devdojo.spring.exception.ConflictException;
import academy.devdojo.spring.exception.NotFoundException;
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
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> findAll(String name) {
        if (name != null) {
            return animeRepository.findByName(name);
        }
        return animeRepository.findAll();
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        if (animeRepository.existsByName(AnimeMapper.INSTANCE.toAnime(animePostRequestBody).getName())) {
            throw new ConflictException("Anime already exists");
        }
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void replace(long id, AnimePutRequestBody animePutRequestBody) {
        if (id != animePutRequestBody.getId()) {
            throw new BadRequestException("Path variable and body id are different");
        }
        if (!animeRepository.existsById(id)) {
            throw new NotFoundException("Anime not found");
        }
        animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutRequestBody));
    }

    public void delete(long id) {
        animeRepository.deleteById(id);
    }
}
