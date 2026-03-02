package bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.Requirement;
import dao.RequirementDAO;

import java.util.List;

@Named
@RequestScoped
public class RequirementBean {

    private Requirement requirement = new Requirement();
    private List<Requirement> requirements;
    private RequirementDAO dao = new RequirementDAO();

    @PostConstruct
    public void init() {
        requirements = dao.findAll();
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void save() {
        dao.save(requirement);
        requirements = dao.findAll();
    }
}