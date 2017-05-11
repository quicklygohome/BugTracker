package entities;

/**
 * Created by Sasha on 28.04.2017.
 */
public enum TaskPriority {
    URGENT, MAJOR, NORMAL, LOW;

    @Override
    public String toString() {
        String result = "";
        switch (this){
            case LOW: result = "Low";
            case MAJOR: result = "Major";
            case NORMAL: result = "Normal";
            case URGENT: result = "Urgent";
        }
        return result;
    }
}
