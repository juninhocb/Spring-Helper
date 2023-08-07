package com.example.carlosjr.hibernatedaoqueries.person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    private final EntityManagerFactory emf;

    @Override
    public Person findPersonById(Long id) {
        return getEntityManager().find(Person.class, id);
    }

    @Override
    public Person findPersonByName(String name) {
        TypedQuery<Person> query = getEntityManager()
                .createQuery("SELECT p FROM Person p WHERE p.name = :paramName", Person.class);
        query.setParameter("paramName", name);
        return query.getSingleResult();

    }

    @Override
    public List<Person> findPeople() {
        TypedQuery<Person> query = getEntityManager()
                .createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }


    //@Transactional
    @Override
    public Long createPerson(Person person) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        //em.joinTransaction();
        em.persist(person);
        //em.flush
        em.getTransaction().commit();

        return person.getId();
    }

    @Override
    public void deletePerson(Long id) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
        em.getTransaction().begin();
        em.remove(person);
        em.flush();
        em.getTransaction().commit();

    }

    @Override
    public List<Person> findAlive() {
        TypedQuery<Person> query = getEntityManager()
                .createQuery("SELECT p FROM Person p WHERE p.isAlive = true", Person.class);
        return query.getResultList();
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
