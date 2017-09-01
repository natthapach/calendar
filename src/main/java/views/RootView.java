package views;

import controllers.CoreController;
import models.EventNote;

public interface RootView {
    void edit(EventNote oldEvent, EventNote newEvent);
    void delete(EventNote event);
    void add(EventNote event);
    void setController(CoreController controller);
}
