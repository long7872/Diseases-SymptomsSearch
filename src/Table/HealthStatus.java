package Table;

public class HealthStatus {

	private int idHealthIssues;
	private int idSymptom;
	
	public HealthStatus() {
	
	}

	public HealthStatus(int idHealthIssues, int idSymptom) {
		this.idHealthIssues = idHealthIssues;
		this.idSymptom = idSymptom;
	}
	
	public int getIdHealthIssues() {
		return idHealthIssues;
	}

	public void setIdHealthIssues(int idHealthIssues) {
		this.idHealthIssues = idHealthIssues;
	}

	public int getIdSymptom() {
		return idSymptom;
	}

	public void setIdSymptom(int idSymptom) {
		this.idSymptom = idSymptom;
	}

}
