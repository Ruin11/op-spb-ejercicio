package com.example.opspbejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.opspbejercicio.entities.Laptop;




public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    
}
