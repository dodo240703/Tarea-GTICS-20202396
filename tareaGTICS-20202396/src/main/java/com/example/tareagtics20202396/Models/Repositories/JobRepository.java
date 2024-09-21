package com.example.tareagtics20202396.Models.Repositories;

import com.example.tareagtics20202396.Models.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
