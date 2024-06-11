package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.User;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlanRepository;
import io.jmix.core.FetchPlans;
import io.jmix.data.PersistenceHints;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UsersService {

    private final FetchPlans fetchPlans;
    private final FetchPlanRepository fetchPlanRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UsersService(FetchPlans fetchPlans, FetchPlanRepository fetchPlanRepository) {
        this.fetchPlans = fetchPlans;
        this.fetchPlanRepository = fetchPlanRepository;
    }

    @Transactional
    public List<User> getUsersNotInProject(Project project, int firstResult, int maxResult) {
        return entityManager.createQuery("SELECT u FROM User u " +
                        "WHERE u.id NOT IN " +
                        "(SELECT u1.id FROM User u1 " +
                        " INNER JOIN u1.projects pul " +
                        " WHERE pul.id = ?1)", User.class)
                .setParameter(1, project.getId())
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }

    @Transactional
    public Project loadGraphOfPartialEntities(UUID orderId) {
        FetchPlan fetchPlan = fetchPlanRepository.getFetchPlan(Project.class, "project-with-tasks");

        Map<String, Object> properties = PersistenceHints.builder()
                     .withFetchPlan(fetchPlan)
                     .build();

        return entityManager.find(Project.class, orderId, properties);
    }
}