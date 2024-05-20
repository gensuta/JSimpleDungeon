enum Event {DIALOGUE_EVENT, BATTLE_EVENT, DESTROYABLE_OBSTACLE,LOCKED_DOOR,NONE}

class GameEvent{
    private Event event;
    private int id;
    private boolean isComplete;
    private boolean isRepeatable;

    public GameEvent() {
    }

    public  GameEvent(Event event, int id, boolean isRepeatable){
        this.event = event;
        this.id = id;
        this.isRepeatable = isRepeatable;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public void setRepeatable(boolean repeatable) {
        isRepeatable = repeatable;
    }
}
