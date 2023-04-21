import java.time.LocalDate;
import java.time.Period;

public class MenstrualCycle {
    private LocalDate startDate;
    private LocalDate endDate;

    public MenstrualCycle(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLength() {
        return Period.between(startDate, endDate).getDays();
    }

    public LocalDate getNextCycleStartDate() {
        return endDate.plusDays(28);
    }
}