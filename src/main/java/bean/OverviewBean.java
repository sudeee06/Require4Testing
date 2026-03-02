package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import dao.OverviewDAO;
import model.TestRun;

import java.util.List;

@Named
@RequestScoped
public class OverviewBean {

    private OverviewDAO overviewDAO = new OverviewDAO();

    /**
     * Gibt die Liste der Testläufe an die Weboberfläche zurück.
     * Die Bean selbst enthält keinen Datenbankcode mehr.
     */
    public List<TestRun> getAllTestRuns() {
        return overviewDAO.findAllWithTestCases();
    }
}