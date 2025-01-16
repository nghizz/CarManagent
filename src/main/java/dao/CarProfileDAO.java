package dao;

import entity.CarProfile;
import jakarta.persistence.EntityManager;

public class CarProfileDAO {
    private EntityManager entityManager;

    // Constructor nhận EntityManager
    public CarProfileDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CarProfile getCarProfileById(Long carProfileId) {
        try {
            return entityManager.find(CarProfile.class, carProfileId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
