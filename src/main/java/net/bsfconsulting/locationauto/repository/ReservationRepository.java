package net.bsfconsulting.locationauto.repository;


import net.bsfconsulting.locationauto.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM Reservation r
            WHERE r.car.id = :id
            AND (
                (r.dateDebut BETWEEN :dateDebut AND :dateFin)
                OR (r.dateFin BETWEEN :dateDebut AND :dateFin)
                OR (r.dateDebut <= :dateDebut AND r.dateFin >= :dateFin)
            )
            """)
    boolean existsByDateDebutBetweenAndDateFinBetween(LocalDate dateDebut, LocalDate dateFin, Long id);

    List<Reservation> findByUserId(Long userId);
}
