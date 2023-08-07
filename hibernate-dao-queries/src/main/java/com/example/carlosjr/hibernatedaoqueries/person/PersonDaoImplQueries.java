package com.example.carlosjr.hibernatedaoqueries.person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonDaoImplQueries implements PersonDao{
    private final EntityManagerFactory emf;
    @Override
    public Person findPersonById(Long id) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);

            ParameterExpression<Long> idParam = criteriaBuilder.parameter(Long.class);
            Predicate idPredicate = criteriaBuilder.equal(root.get("id"), idParam);
            criteriaQuery.select(root).where(criteriaBuilder.and(idPredicate));

            TypedQuery<Person> typedQuery = em.createQuery(criteriaQuery);
            typedQuery.setParameter(idParam, id);
            return typedQuery.getSingleResult();
        }finally {
            em.close();
        }
    }

    @Override
    public Person findPersonByName(String name) {

        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em
                    .createNamedQuery("find_by_name", Person.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }finally {
            em.close();
        }

    }

    @Override
    public List<Person> findPeople() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> query = em.createNamedQuery("find_all", Person.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    @Override
    public Long createPerson(Person person) {
        return null;
    }

    @Override
    public void deletePerson(Long id) {

    }

    @Override
    public List<Person> findAlive() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Person p WHERE p.isAlive = true");
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
