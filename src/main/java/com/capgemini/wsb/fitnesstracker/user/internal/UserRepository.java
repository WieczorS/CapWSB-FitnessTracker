package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Query searching users by full name. Not case sensitive.
     *
     * @param firstName first name of searched user
     * @param lastName  last name of searched user
     * @return list of users witch mathing full name
     */
    default List<User> findByFullNameIgnoreCase(String firstName, String lastName) {
        return findAll().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName)
                        && user.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    /**
     * Query searching users older than specified date
     *
     * @param maxDate maximum scope of user age - youngest acceptable age
     * @return list of users, that are younger than maxDate
     */
    default List<User> findUsersYoungerThan(LocalDate maxDate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(maxDate))
                .toList();
    }

    /**
     * Query searching user by id / first and last name / date of birth / email
     *
     * @param id          user id
     * @param firstName   first name
     * @param lastName    user last name
     * @param dateOfBirth user date of birth
     * @param email       user email
     * @return list of users, that are younger than maxDate
     */
    default List<User> findUsersByParameter(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email) {
        return findAll().stream()
                .filter(user ->
                        (id != null && user.getId().equals(id)) ||
                                (firstName != null && lastName != null &&
                                        user.getFirstName().equalsIgnoreCase(firstName) &&
                                        user.getLastName().equalsIgnoreCase(lastName)) ||
                                (dateOfBirth != null && user.getBirthdate().equals(dateOfBirth)) ||
                                (email != null && user.getEmail().equalsIgnoreCase(email))
                )
                .toList();
    }


    /**
     * Finds users by email ignoring case and searches by containing
     *
     * @param email email or it's part
     * @return list of users that fulfil requirement
     */
    List<User> findByEmailContainingIgnoreCase(String email);


}
