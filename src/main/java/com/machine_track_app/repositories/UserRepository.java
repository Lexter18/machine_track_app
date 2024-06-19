package com.machine_track_app.repositories;

import com.machine_track_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.machine_track_app.enums.StateApp.ACTIVE;
import static com.machine_track_app.enums.StateApp.DELETED;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    long countByUserNameAndStateIdStateIsNot(String username, Integer excludedUserStateId);

    Optional<User> findByUserNameAndStateIdState(String userName, Integer stateId);

    List<User> findByEmployee_Owner_IdOwnerAndEmployee_State_IdStateNotAndState_IdStateNot(Long ownerId,
                                                                                           Integer excludedStateId,
                                                                                           Integer excludedUserStateId);

    default List<User> findByEmployee_Owner(Long ownerId) {
        return findByEmployee_Owner_IdOwnerAndEmployee_State_IdStateNotAndState_IdStateNot(ownerId,
                DELETED.getState(),
                DELETED.getState());
    }

    default long countByUserName(String userName) {
        return countByUserNameAndStateIdStateIsNot(userName,
                DELETED.getState());
    }

    default Optional<User> findByUserName(String userName) {
        return findByUserNameAndStateIdState(userName,
                ACTIVE.getState());
    }

}
