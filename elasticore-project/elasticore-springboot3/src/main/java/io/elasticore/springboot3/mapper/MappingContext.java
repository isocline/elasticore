package io.elasticore.springboot3.mapper;

/**
 * Context information used during object mapping operations.
 * <p>
 * Holds references to source, target, field name, depth control, and optional validation logic.
 * </p>
 */
public class MappingContext {

    // Field name being mapped
    private String fieldName;

    // Parent source object
    private Object fromParent;

    // Parent target object
    private Object toParent;

    // Target child object
    private Object toChild;

    // Source object
    private Object from;

    // Target object
    private Object to;

    // Remaining depth allowed for mapping
    private int remainDepth = 1;

    // Optional guard for additional validation
    private MappingGuard checker;

    // Mapping event type (e.g., BEFORE, AFTER)
    public MappingEventType eventType = MappingEventType.BEFORE;

    /**
     * Creates a MappingContext with full event and parent information.
     *
     * @param eventType   the mapping event type
     * @param fromParent  the parent source object
     * @param from        the source object
     * @param toParent    the parent target object
     * @param to          the target object
     * @param fieldName   the name of the field being mapped
     * @param checker     the mapping guard for validation
     */
    MappingContext(MappingEventType eventType, Object fromParent, Object from, Object toParent, Object to, String fieldName, MappingGuard checker) {
        this.remainDepth = 1;
        this.eventType = eventType;
        this.from = from;;
        this.to = to;
        this.fromParent = fromParent;
        this.toParent = toParent;
        this.fieldName = fieldName;
        this.checker = checker;
    }


    /**
     * Creates a MappingContext with a specified depth, source, target, field, and guard.
     *
     * @param remainDepth remaining mapping depth
     * @param from        source object
     * @param to          target object
     * @param fieldName   field name to map
     * @param checker     guard for validation
     */
    MappingContext(int remainDepth, Object from, Object to, String fieldName, MappingGuard checker) {
        this.remainDepth = remainDepth;
        this.from = from;;
        this.to = to;
        this.fieldName = fieldName;
        this.checker = checker;
    }

    /**
     * Sets the source object.
     *
     * @param from source object
     * @return updated MappingContext
     */
    public MappingContext from(Object from) {
        this.from = from;
        return this;
    }

    /**
     * Sets the target object.
     *
     * @param to target object
     * @return updated MappingContext
     */
    public MappingContext to(Object to) {
        this.to = to;
        return this;
    }

    public MappingContext fd(String fieldName) {
        this.fieldName = fieldName;
        return this;

    }

    /**
     * Creates a new MappingContext with a guard.
     *
     * @param remainDepth remaining depth
     * @param checker     guard for validation
     * @return a new MappingContext
     */
    public static MappingContext withGuard(int remainDepth, MappingGuard checker) {
        return new MappingContext(remainDepth, null, null, null,checker);
    }

    /**
     * Creates a new MappingContext without a guard.
     *
     * @param remainDepth remaining depth
     * @return a new MappingContext
     */
    public static MappingContext withGuard(int remainDepth) {
        return withGuard(remainDepth, null);
    }


    /**
     * Checks whether the mapping operation is allowed.
     *
     * @return true if allowed, false otherwise
     */
    public boolean checkEnable() {
        if(this.remainDepth<0) return false;

        if(checker!=null) {
            return checker.check(this);
        }

        return true;
    }

    /**
     * Checks whether the mapping operation is allowed with a custom depth limit.
     *
     * @param checkLimitDepth depth threshold for checking
     * @return true if allowed, false otherwise
     */
    public boolean checkEnable(int checkLimitDepth) {
        if(this.remainDepth<checkLimitDepth) return false;

        if(checker!=null) {
            return checker.check(this);
        }

        return true;
    }

    /**
     * Gets the field name being mapped.
     *
     * @return field name
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * Gets the source object.
     *
     * @return source object
     */
    public Object getFrom() {
        return this.from;
    }

    /**
     * Gets the target object.
     *
     * @return target object
     */
    public Object getTo() {
        return this.to;
    }


    /**
     * Gets the remaining depth for mapping.
     *
     * @return remaining depth
     */
    public int getRemainDepth() {
        return this.remainDepth;
    }


    /**
     * Creates a child MappingContext with one level reduced depth.
     *
     * @return child MappingContext
     */
    public MappingContext getChild() {
        return new MappingContext(this.remainDepth-1, from,to,fieldName,checker);
    }


}
