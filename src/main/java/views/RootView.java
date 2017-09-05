package views;

import controllers.CoreController;
import models.EventNote;


public interface RootView {
    /**
     * pass edit signal to controller
     * @param oldEvent old data
     * @param newEvent new data
     */
    void edit(EventNote oldEvent, EventNote newEvent);

    /**
     * pass deleting signal to controller
     * @param event deleted data
     */
    void delete(EventNote event);

    /**
     * pass adding signal to controller
     * @param event
     */
    void add(EventNote event);

    /**
     * set controller to view
     * @param controller controller that can receive signal
     */
    void setController(CoreController controller);
}
