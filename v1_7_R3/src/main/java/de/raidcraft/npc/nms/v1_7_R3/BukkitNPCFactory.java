package de.raidcraft.npc.nms.v1_7_R3;

import de.raidcraft.npc.api.NPC;
import de.raidcraft.npc.api.NPCFactory;
import de.raidcraft.npc.api.NPCProfile;
import net.minecraft.server.v1_7_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BukkitNPCFactory main class, intializes and creates npcs.
 *
 * @author lenis0012
 */
public class BukkitNPCFactory implements NPCFactory, Listener {

    private final Plugin plugin;
    private final NPCNetworkManager networkManager;

    public BukkitNPCFactory(Plugin plugin) {

        this.plugin = plugin;
        this.networkManager = new NPCNetworkManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public NPC spawnHumanNPC(Location location, NPCProfile profile) {

        World world = location.getWorld();
        WorldServer worldServer = ((CraftWorld) world).getHandle();
        NPCEntity entity = new NPCEntity(world, profile, networkManager);
        entity.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        worldServer.addEntity(entity);
        worldServer.players.remove(entity);
        entity.getBukkitEntity().setMetadata("NPC", new FixedMetadataValue(plugin, true));
        return entity;
    }

    @Override
    public NPC getNPC(Entity entity) {

        if (!isNPC(entity)) {
            return null;
        }

        return (NPCEntity) ((CraftEntity) entity).getHandle();
    }

    @Override
    public List<NPC> getNPCs() {

        List<NPC> npcList = new ArrayList<NPC>();
        for (World world : Bukkit.getWorlds()) {
            npcList.addAll(getNPCs(world));
        }

        return npcList;
    }

    @Override
    public List<NPC> getNPCs(World world) {

        return world.getEntities().stream()
                .filter(this::isNPC)
                .map(this::getNPC)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isNPC(Entity entity) {

        return entity.hasMetadata("NPC");
    }

    @Override
    public void despawnAll() {

        Bukkit.getWorlds().forEach(this::despawnAll);
    }

    @Override
    public void despawnAll(World world) {

        world.getEntities().stream()
                .filter(entity -> entity.hasMetadata("NPC"))
                .forEach(Entity::remove);
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {

        if (event.getPlugin().equals(plugin)) {
            despawnAll();
        }
    }
}
