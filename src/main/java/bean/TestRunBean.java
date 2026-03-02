package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.TestRun;
import model.TestCase;
import dao.TestRunDAO;
import dao.TestCaseDAO;

import java.util.List;

@Named
@RequestScoped
public class TestRunBean {

    private TestRun testRun = new TestRun();
    private List<Long> selectedTestCaseIds;
    private TestRunDAO testRunDAO = new TestRunDAO();
    private TestCaseDAO testCaseDAO = new TestCaseDAO();


    public TestRun getTestRun() {
        return testRun;
    }

    public List<Long> getSelectedTestCaseIds() {
        return selectedTestCaseIds;
    }

    public List<TestRun> getAllTestRuns() {
        return testRunDAO.findAll();
    }

    public void setSelectedTestCaseIds(List<Long> selectedTestCaseIds) {
        this.selectedTestCaseIds = selectedTestCaseIds;
    }

    public List<TestCase> getAllTestCases() {
        return testCaseDAO.findAll();
    }

    /**
     * Speichert den Testlauf und leitet den Nutzer weiter.
     * Das Attribut faces-redirect=true verhindert doppeltes Absenden
     */
    public String save() {
        testRunDAO.save(testRun, selectedTestCaseIds);
        testRun = new TestRun();
        selectedTestCaseIds = null;

        return "testrun.xhtml?faces-redirect=true";
    }
}