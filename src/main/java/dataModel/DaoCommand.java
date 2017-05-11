package dataModel;

/**
 * Created by Sasha on 07.05.2017.
 */
public interface DaoCommand {
    public Object execute(DaoFactory factory);
}
