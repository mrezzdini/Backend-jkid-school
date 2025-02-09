package com.techno.events.model.ParentActivity;


import com.techno.events.model.ParentActivity.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class ParentActivityService {

    private final ParentActivityRepository Repository;
    private final StudentClient client;

    public void saveParentActivity(ParentActivity parentActivity) {
        Repository.save(parentActivity);
    }
    public List<ParentActivity> findAllParentActivity() {
        return Repository.findAll();
    }
    public List<ParentActivity> findParentActivityByParentIdAndStudentName(Integer parentId,String studentName) {
        return Repository.findParentActivityByParentIdAndStudentName(parentId,studentName);
    }
    public void deleteParentActivity(Long parentId) {
        Repository.deleteById(parentId);
    }

    public List<ParentActivity> getAllUniqueParentActivity() {
        List<ParentActivity> allParentActivity = Repository.findAll();

        // Filtrer les événements en utilisant les champs spécifiés
        List<ParentActivity> filteredParentActivity = allParentActivity.stream()
                .collect(Collectors.groupingBy(parentActivity -> parentActivity.getActivityName() + parentActivity.getStartDate()  + parentActivity.getDeadline() + parentActivity.getDetails()))
                .values()
                .stream()
                .map(events -> events.get(0)) // Prendre le premier événement de chaque groupe
                .collect(Collectors.toList());
        return filteredParentActivity;
    }
    public ResponseEntity<String> saveParentActivityByClass(ParentActivity parentActivity) {
        List<Student> students = client.findAllStudentsByClasses(parentActivity.getClasses()).getBody();

        if (students != null && !students.isEmpty()) {
            for (Student std : students) {
                    ParentActivity newParentActivity = new ParentActivity();

                    newParentActivity.setParentId(std.getParentId());
                    newParentActivity.setStudentName(std.getName());
                    newParentActivity.setClasses(parentActivity.getClasses());
                    newParentActivity.setStartDate(parentActivity.getStartDate());
                    newParentActivity.setDeadline(parentActivity.getDeadline());
                    newParentActivity.setActivityName(parentActivity.getActivityName());
                    newParentActivity.setDetails(parentActivity.getDetails());
                    newParentActivity.setStatus(parentActivity.getStatus().toLowerCase());
                    newParentActivity.setResponseType(parentActivity.getResponseType());
                    saveParentActivity(newParentActivity);

            }
            return ResponseEntity.ok("Parent Activity saved successfully for all students in class " + parentActivity.getClasses());
        }
        return ResponseEntity.ok("Either the specified class does not exist or there are no students enrolled in this class yet.");
    }

    public ResponseEntity<String> updateActivityByClass(ParentActivity parentActivity) {
        Optional<ParentActivity> oldActivity=Repository.findById(parentActivity.getId());
        if (oldActivity.isPresent()) {
            ParentActivity old = oldActivity.get();
            List<ParentActivity> requiredActivities = Repository.findAllByClassesAndActivityNameAndStartDateAndDeadline(old.getClasses(), old.getActivityName(), old.getStartDate(), old.getDeadline());
            if (requiredActivities != null && !requiredActivities.isEmpty()) {
                for (ParentActivity act : requiredActivities) {
                    act.setDeadline(parentActivity.getDeadline());
                    act.setDetails(parentActivity.getDetails());
                    act.setStartDate(parentActivity.getStartDate());
                    act.setActivityName(parentActivity.getActivityName());
                    act.setStatus(parentActivity.getStatus());
                    act.setImg(parentActivity.getImg());
                    act.setResponseType(parentActivity.getResponseType());
                    saveParentActivity(act);
                }
                return ResponseEntity.ok("Parent Activity updated successfully for all students in class " + parentActivity.getClasses());
            }
            return ResponseEntity.ok("Either the specified class does not exist or there are no students enrolled in this class yet.");
        }
        return ResponseEntity.ok("Parent Activity not found.");
    }

    public List<ParentActivity> findParentActivityByActivityNameAndClasses(String activityName, String classes) {
        return Repository.findParentActivityByActivityNameAndClasses(activityName,classes);
    }
}