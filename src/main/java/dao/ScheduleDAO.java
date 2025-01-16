package dao;

import entity.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ScheduleDAO {

    private EntityManager entityManager;

    public ScheduleDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Lấy danh sách tất cả lịch hẹn
    public List<Schedule> getAllSchedules() {
        String query = "SELECT s FROM Schedule s LEFT JOIN FETCH s.user LEFT JOIN FETCH s.car LEFT JOIN FETCH s.technician LEFT JOIN FETCH s.services";
        TypedQuery<Schedule> typedQuery = entityManager.createQuery(query, Schedule.class);
        return typedQuery.getResultList();
    }

    // Thêm lịch hẹn mới
    public boolean addSchedule(Schedule schedule) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(schedule);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật lịch hẹn
    public boolean updateSchedule(Schedule schedule) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(schedule); // Cập nhật đối tượng Schedule
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Xóa lịch hẹn
    public boolean deleteSchedule(Long scheduleId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Schedule schedule = entityManager.find(Schedule.class, scheduleId);
            if (schedule != null) {
                entityManager.remove(schedule); // Xóa đối tượng Schedule
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Lấy lịch hẹn theo ID
    public Schedule getScheduleById(Long scheduleId) {
        return entityManager.find(Schedule.class, scheduleId);
    }
}
