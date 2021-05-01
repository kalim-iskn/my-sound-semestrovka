package ru.kpfu.itis.iskander.mysound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.iskander.mysound.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByVkUserIdOrEmail(Long id, String email);

    boolean existsByUsernameAndIdIsNot(String username, Long id);

    List<User> findAllByOrderByIsVerifiedDesc();

}
