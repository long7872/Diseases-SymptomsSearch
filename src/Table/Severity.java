package Table;

public class Severity {

	private int idSeverity;
	private String levelSeverity;
	private String description;
	
	public Severity() {
		
	}

	public Severity(int idSeverity, String levelSeverity, String description) {
		this.idSeverity = idSeverity;
		this.levelSeverity = levelSeverity;
		this.description = description;
	}
	
	public int getIdSeverity() {
		return idSeverity;
	}

	public void setIdSeverity(int idSeverity) {
		this.idSeverity = idSeverity;
	}

	public String getLevelSeverity() {
		return levelSeverity;
	}

	public void setLevelSeverity(String levelSeverity) {
		this.levelSeverity = levelSeverity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
