package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    List<Review> findAllByMovie_MovieIdOrderByCreatedAtDesc(Long movieId);
}
