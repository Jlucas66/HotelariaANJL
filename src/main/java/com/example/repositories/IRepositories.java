package com.example.repositories;

public interface IRepositories {
    Object[] findAll();
    Object findById(int id);
    void save(Object entity);
    void delete(int id);
    void update(Object entity);
}
