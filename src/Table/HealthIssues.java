package Table;

public class HealthIssues implements Comparable<HealthIssues> {

	private int idHealthIssues;
	private String nameHealthIssues;
	private String category;
	private String causesOfHealthIssues;
	private int idSeverity;
	
	public HealthIssues() {
		
	}

	public HealthIssues(String nameHealthIssues, String category, String causesOfHealthIssues, int idSeverity) {
		this.nameHealthIssues = nameHealthIssues;
		this.category = category;
		this.causesOfHealthIssues = causesOfHealthIssues;
		this.idSeverity = idSeverity;
	}
	
	public HealthIssues(int idHealthIssues, String nameHealthIssues, String category, String causesOfHealthIssues, int idSeverity) {
		this.idHealthIssues = idHealthIssues;
		this.nameHealthIssues = nameHealthIssues;
		this.category = category;
		this.causesOfHealthIssues = causesOfHealthIssues;
		this.idSeverity = idSeverity;
	}
	
	@Override
	public String toString() {
		return "HealthIssues [idHealthIssues=" + idHealthIssues + ", nameHealthIssues=" + nameHealthIssues + ", causesOfHealthIssues="
				+ causesOfHealthIssues + ", idSeverity=" + idSeverity + "]";
	}
	
	public int getIdHealthIssues() {
		return idHealthIssues;
	}

	public void setIdHealthIssues(int idHealthIssues) {
		this.idHealthIssues = idHealthIssues;
	}

	public String getNameHealthIssues() {
		return nameHealthIssues;
	}

	public void setNameHealthIssues(String nameHealthIssues) {
		this.nameHealthIssues = nameHealthIssues;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCausesOfHealthIssues() {
		return causesOfHealthIssues;
	}

	public void setCausesOfHealthIssues(String causesOfHealthIssues) {
		this.causesOfHealthIssues = causesOfHealthIssues;
	}

	public int getIdSeverity() {
		return idSeverity;
	}

	public void setIdSeverity(int idSeverity) {
		this.idSeverity = idSeverity;
	}

	@Override
	public int compareTo(HealthIssues o) {
		return this.idSeverity - o.idSeverity;
	}
	
	public int compareToL(HealthIssues o) {
		return o.getNameHealthIssues().length() - this.nameHealthIssues.length();
	}

}
