package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;

import java.util.Optional;

public interface IPersonRepository {

    /**
     * Save person.
     *
     * @param person Person to save
     *
     * @return person saved
     */
    Person save(Person person) throws Exception;

    /**
     * Delete person with specified name.
     *
     * @param firstName First name of person to delete
     * @param lastName  Last name of person to delete
     */
    void deleteByName(String firstName, String lastName) throws Exception;

    /**
     * Get list of all persons.
     *
     * @return list of persons.
     */
    Iterable<Person> findAll();

    /**
     * Finds person with specified email.
     *
     * @param firstName First name of person to find
     * @param lastName  Last name of person to find
     *
     * @return Found person
     */
    Optional<Person> findByName(String firstName, String lastName);

    /**
     * Updates a person.
     *
     * @param person Person to update.
     *
     * @return updated person
     *
     * @throws Exception thrown when update failed.
     */
    Person update(Person person) throws Exception;
}