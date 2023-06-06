package utkucuval.hospitalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utkucuval.hospitalproject.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
