package de.raidcraft.npc.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.List;

/**
 * @author mdoering
 */
public interface NPCFactory {

    /**
     * Spawn a new npc at a speciafied location.
     *
     * @param location Location to spawn npc at.
     * @param profile  NPCProfile to use for npc
     *
     * @return New npc instance.
     */
    public NPC spawnHumanNPC(Location location, NPCProfile profile);

    /**
     * Get npc from entity.
     *
     * @param entity Entity to get npc from.
     *
     * @return NPC instance, null if entity is not an npc.
     */
    public NPC getNPC(Entity entity);

    /**
     * Get all npc's from all worlds.
     *
     * @return A list of all npc's
     */
    public List<NPC> getNPCs();

    /**
     * Get all npc's from a specific world
     *
     * @param world World to get npc's from
     *
     * @return A list of all npc's in the world
     */
    public List<NPC> getNPCs(World world);

    /**
     * Check if an entity is a NPC.
     *
     * @param entity Entity to check.
     *
     * @return Entity is a npc?
     */
    public boolean isNPC(Entity entity);

    /**
     * Despawn all npc's on all worlds.
     */
    public void despawnAll();

    /**
     * Despawn all npc's on a single world.
     *
     * @param world World to despawn npc's on.
     */
    public void despawnAll(World world);
}
