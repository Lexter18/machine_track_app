package com.machine_track_app.repositories;

import com.machine_track_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.machine_track_app.enums.StateApp.ACTIVE;
import static com.machine_track_app.enums.StateApp.DELETED;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    long countByUserNameAndStateIdStateIsNot(String username, Integer excludedUserStateId);

    long countByUserNameAndIdUserIsNotAndStateIdStateIsNot(String username, Long idUser, Integer excludedUserStateId);

    Optional<User> findByUserNameAndStateIdState(String userName, Integer stateId);

    @Query("""
            SELECT u FROM User u
            WHERE u.employee.owner.idOwner = :ownerId
              AND u.employee.state.idState <> :excludedStateId
              AND u.state.idState <> :excludedUserStateId
              AND u.role.idRole IN :roleIds
            """)
    List<User> findActiveUsersByOwnerExcludingStatesAndRoles(
            @Param("ownerId") Long ownerId,
            @Param("excludedStateId") Integer excludedStateId,
            @Param("excludedUserStateId") Integer excludedUserStateId,
            @Param("roleIds") List<Integer> roleIds
    );

    List<User> findAllByRoleIdRole(Integer idRole);

    Optional<User> findByIdUser(Long idUser);

    Optional<User> findByIdUserAndEmployee_Owner_IdOwner(Long idUser, Long idOwner);


    default long countByUserName(String userName) {
        return countByUserNameAndStateIdStateIsNot(userName,
                DELETED.getState());
    }

    default long countByUserNameIdUserNot(Long idUser, String userName) {
        return countByUserNameAndIdUserIsNotAndStateIdStateIsNot(userName,
                idUser,
                DELETED.getState());
    }

    default Optional<User> findByUserName(String userName) {
        return findByUserNameAndStateIdState(userName,
                ACTIVE.getState());
    }

}
