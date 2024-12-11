package autoutil;

public class AutoConfig {
    private final AutoSegment<?,?> getWaypointSegment, getSetpointSegment;
    public AutoConfig(AutoSegment<?,?> set, AutoSegment<?,?> way){
        this.getWaypointSegment = way; this.getSetpointSegment = set;
    }

    public AutoSegment<?,?> getWaypointSegment(){ return getWaypointSegment; }
    public AutoSegment<?, ?> getSetpointSegment(){ return getSetpointSegment; }
}
