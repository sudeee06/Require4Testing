package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.TestCase;
import model.Requirement;
import dao.TestCaseDAO;

import java.util.List;

@Named
@RequestScoped
public class TestCaseBean {

    private TestCaseDAO dao = new TestCaseDAO();
    private TestCase testCase = new TestCase();
    private Long selectedRequirementId;

    public TestCase getTestCase() {
        return testCase;
    }

    public Long getSelectedRequirementId() {
        return selectedRequirementId;
    }

    public void setSelectedRequirementId(Long selectedRequirementId) {
        this.selectedRequirementId = selectedRequirementId;
    }

    /**
     * Speichert den Testfall und verknüpft ihn mit der gewählten Anforderung.
     * Nutzt die Seitennavigation über den Rückgabewert.
     */
    public String save() {
        dao.save(testCase, selectedRequirementId);

        //Zustand nach dem Speichern zurücksetzen
        testCase = new TestCase();
        selectedRequirementId = null;

        //Navigation zurück zur Übersicht oder Neuladen der Seite
        return "testcases.xhtml?faces-redirect=true";
    }

    //Liefert alle Anforderungen für das Dropdown-Menü in der View.
    public List<Requirement> getAllRequirements() {
        return dao.findAllRequirements();
    }

        public List<TestCase> getAllTestCases() {
            return dao.findAll();
        }
    }
