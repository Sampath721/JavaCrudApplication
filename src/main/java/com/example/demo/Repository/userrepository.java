package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Users;

@Repository
public interface userrepository extends JpaRepository<Users,Long> {

}
