package com.techno.subject.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Query("SELECT ac FROM Calendar ac WHERE FUNCTION('TO_DATE', ac.startDate, 'YYYY-MM-DD') BETWEEN FUNCTION('TO_DATE', :startDate, 'YYYY-MM-DD') AND FUNCTION('TO_DATE', :endDate || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')AND ac.nurseryClass = :nurseryClass")
    List<Calendar> findCalenderByClassAndDate(@Param("nurseryClass") String nurseryClass, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT ac FROM Calendar ac WHERE FUNCTION('TO_DATE', ac.startDate, 'YYYY-MM-DD') BETWEEN FUNCTION('TO_DATE', :startDate, 'YYYY-MM-DD') AND FUNCTION('TO_DATE', :endDate || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')AND ac.responsible = :responsible")
    List<Calendar> findCalenderByResponsibleAndDate(@Param("responsible") String responsible, @Param("startDate") String startDate, @Param("endDate") String endDate);
    @Query("SELECT ac FROM Calendar ac WHERE FUNCTION('TO_DATE', ac.startDate, 'YYYY-MM-DD') = FUNCTION('TO_DATE', :startDate, 'YYYY-MM-DD') AND FUNCTION('TO_DATE', ac.endDate, 'YYYY-MM-DD') = FUNCTION('TO_DATE', :endDate, 'YYYY-MM-DD')")
    List<Calendar> findCalenderByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}