package Table;

public class Symptom implements Comparable<Symptom> {

	private int idSymptom;
	private String nameSymptom;
	private String description;
	
	public Symptom() {
		
	}
	
	public Symptom(String nameSymptom, String description) {
		this.nameSymptom = nameSymptom;
		this.description = description;
	}

	public Symptom(int idSymptom, String nameSymptom, String description) {
		this.idSymptom = idSymptom;
		this.nameSymptom = nameSymptom;
		this.description = description;
	}
	
	public int getIdSymptom() {
		return idSymptom;
	}

	public void setIdSymptom(int idSymptom) {
		this.idSymptom = idSymptom;
	}

	public String getNameSymptom() {
		return nameSymptom;
	}

	public void setNameSymptom(String nameSymptom) {
		this.nameSymptom = nameSymptom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Symptom o) {
		return o.getNameSymptom().length() - this.nameSymptom.length();
	}
	
}
