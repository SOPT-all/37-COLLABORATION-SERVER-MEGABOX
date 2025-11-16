package org.collaboration.megabox.domain.repository;

import java.util.List;
import org.collaboration.megabox.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    List<Movie> findAllByOrderByMovieIdAsc();
}