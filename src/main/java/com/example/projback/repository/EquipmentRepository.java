package com.example.projback.repository;

import com.example.projback.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findEquipmentById(Long equipmentId);
}