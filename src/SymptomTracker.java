import java.util.ArrayList;

public class SymptomTracker {
    private ArrayList<String> symptoms;

    public SymptomTracker() {
        symptoms = new ArrayList<>();
    }

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }
}