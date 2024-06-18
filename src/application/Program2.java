package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args)  {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Scanner sc = new Scanner(System.in);

       //INSERT
/*        Department dep1 = new Department(10, "Inserted!");
        departmentDao.insert(dep1);
        System.out.println("Inserted!");*/

/*
        //Update
        dep1.setName("Fashion1");
        dep1.setId(10);
        departmentDao.update(dep1);
*/


/*      //Delete
        System.out.println("Digite uma id para ser deletada.");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
*/

/*        //findById
        System.out.println("Digite uma id para ser localizada.");
        Department dep = departmentDao.findById(1);
        System.out.println(dep);
*/

        //findAll
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }

    }
}
