package academy.devdojo.spring.repository;

import academy.devdojo.spring.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    boolean existsByName(String name);

}
