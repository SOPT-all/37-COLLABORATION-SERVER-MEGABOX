package org.collaboration.megabox.domain.service;

import lombok.RequiredArgsConstructor;
import org.collaboration.megabox.domain.dto.response.CinemaListResponse;
import org.collaboration.megabox.domain.entity.Cinema;
import org.collaboration.megabox.domain.repository.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaListResponse getCinemasByMovies(List<Long> movieIds) {
        List<Cinema> cinemas = cinemaRepository.findAllByMovieIds(movieIds);
        return CinemaListResponse.from(cinemas);
    }
}
