package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("========== TEST 1: SELLER FindById ==========");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n========== TEST 2: SELLER FindByDepartment ==========");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n========== TEST 3: SELLER FindAll ==========");
        list = sellerDao.findAll();
        for (Seller obj : list){
            System.out.println(obj);
        }

        /* System.out.println("\n========== TEST 4: SELLER Insert ==========");
        Seller newSeller = new Seller(null, "Gredi", "Gredi@gmail.com", new Date(), 4000.0, department);

        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId()); */

        System.out.println("\n========== TEST 5: SELLER Update ==========");
        seller = sellerDao.findById(1);
        seller.setName("Suxu Lima");
        sellerDao.update(seller);
        System.out.println("Seller Updated");

        /*
        System.out.println("\n========== TEST 6: SELLER Delete ==========");
        sellerDao.deleteById(8);
        System.out.println("Seller deleted!"); */

        System.out.println("\n\n\n\n========== TEST 1: DEPARTMENT FindById ==========");
        Department department1 = departmentDao.findById(1);
        System.out.println(department1);

        System.out.println("\n========== TEST 2: DEPARTMENT FindAll ==========");
        List<Department> listDep = new ArrayList<>();
        listDep = departmentDao.findAll();
        for (Department obj : listDep){
            System.out.println(obj);
        }

        System.out.println("\n========== TEST 3: DEPARTMENT Insert ==========");
        Department newDep  = new Department(null, "Food");

        departmentDao.insert(newDep);
        System.out.println("Inserted! New id = " + newDep.getId());

        System.out.println("\n========== TEST 4: DEPARTMENT Update ==========");
        department1 = departmentDao.findById(6);
        department1.setName("Phones");
        departmentDao.update(department1);
        System.out.println("Department Updated");

        /*
        System.out.println("\n========== TEST 6: DEPARTMENT Delete ==========");
        sellerDao.deleteById(8);
        System.out.println("Seller deleted!"); */
    }
}
