package de.raidcraft.npc;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.npc.api.NPCFactory;
import de.raidcraft.util.ReflectionUtil;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author mdoering
 */
public class NPCPlugin extends BasePlugin {

    private NPCFactory factory;

    @Override
    public void enable() {


    }

    @Override
    public void disable() {


    }

    @Nullable
    public NPCFactory getNPCFactory() {

        if (factory == null) {
            try {
                Class<?> factoryClass = ReflectionUtil.getNmsClass("de.raidcraft.npc.nms", "BukkitNPCFactory");
                Constructor<?> constructor = factoryClass.getConstructor(Plugin.class);
                constructor.setAccessible(true);
                this.factory = (NPCFactory) constructor.newInstance(this);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                getLogger().warning(e.getMessage());
                e.printStackTrace();
            }
        }
        return this.factory;
    }
}
