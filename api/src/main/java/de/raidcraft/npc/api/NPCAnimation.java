package de.raidcraft.npc.api;

/**
 * Animation types that can be displayed on an npc.
 *
 * @author lenis0012
 */
public enum NPCAnimation {
    /**
     * Makes npc swing his arm.
     */
    SWING_ARM(0),
    /**
     * Highlights the npc in red to mark that it has been damaged.
     */
    DAMAGE(1),
    /**
     * Makes an npc lay down or stand up.
     */
    LEAVE_BED(2),
    /**
     * Moves npc's arm towards his mouth to eat food.
     */
    EAT_FOOD(3),
    /**
     * Displays criticial hit
     */
    CRITICAL_HIT(4),
    /**
     * Display magic critical hit
     */
    MAGIC_CRITICAL_HIT(5),
    /**
     * Makes npc crouch.
     */
    CROUCH(104),
    /**
     * Stops npc from crouching.
     */
    UNCROUCH(105);

    private final int id;

    private NPCAnimation(int id) {

        this.id = id;
    }

    public int getId() {

        return id;
    }
}