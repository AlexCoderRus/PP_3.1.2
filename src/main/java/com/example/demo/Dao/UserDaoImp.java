package com.example.demo.Dao;

import com.example.demo.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAllUser() {
        return em.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public void updateUser(User updateUser) {
        em.merge(updateUser);
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

}
