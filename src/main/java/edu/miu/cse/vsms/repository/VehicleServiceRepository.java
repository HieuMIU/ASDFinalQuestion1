package edu.miu.cse.vsms.repository;

import edu.miu.cse.vsms.model.VService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleServiceRepository extends JpaRepository<VService,Long> {
}
