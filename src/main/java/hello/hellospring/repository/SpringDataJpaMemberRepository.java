package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Member save(Member member);

    @Override
    Optional<Member> findById(Long aLong);

    @Override
    Optional<Member> findByName(String name);
    // JPQL select m from Member m where m.name = ?

    @Override
    List<Member> findAll();
}
