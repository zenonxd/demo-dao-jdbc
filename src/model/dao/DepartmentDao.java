package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obg);
    void update(Department obj);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
