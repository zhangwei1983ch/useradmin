package demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
}
