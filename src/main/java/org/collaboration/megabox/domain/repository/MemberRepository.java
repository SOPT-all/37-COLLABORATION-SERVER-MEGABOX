package org.collaboration.megabox.domain.repository;

import org.collaboration.megabox.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
