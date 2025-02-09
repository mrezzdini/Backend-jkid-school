package com.techno.events.model.ParentActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentActivityRepository extends JpaRepository<ParentActivity, Long> {
    List<ParentActivity> findParentActivityByParentIdAndStudentName(Integer parentId, String studentName);

    List<ParentActivity> findAllByClassesAndActivityNameAndStartDateAndDeadline(String classes, String activityName, String startDate, String deadline);

    List<ParentActivity> findParentActivityByActivityNameAndClasses(String activityName, String classes);
}
