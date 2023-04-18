package academy.devdojo.spring.service;

import academy.devdojo.spring.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class AnimeService {

    private final static List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "Boku no hero"), new Anime(2L, "Berserk")));
    }

    public List<Anime> findAll() {
        return animes;
    }

    public Anime findById(long id) {
        return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(Anime anime) {
        anime.setId((animes.size() + 1L));
        animes.add(anime);
        return anime;
    }

    public void replace(long id, Anime anime) {

        if (id != anime.getId()) {
            Anime animeInRequestedId = null;
            try {
                animeInRequestedId = findById(anime.getId());
            } catch (Exception e) {
                // i'm sorry mom
            }
            if(animeInRequestedId != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Anime id already in use!");
            }
        }
        animes.remove(findById(id));
        animes.add(anime);
    }

    public void delete(long id) {
        animes.remove(findById(id));
    }
}
