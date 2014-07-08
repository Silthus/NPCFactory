package de.raidcraft.npc.nms.v1_7_R3;

import net.minecraft.server.v1_7_R3.EntityPlayer;
import net.minecraft.server.v1_7_R3.NetworkManager;
import net.minecraft.server.v1_7_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R3.CraftServer;

public class NPCPlayerConnection extends PlayerConnection {

    public NPCPlayerConnection(NetworkManager networkmanager, EntityPlayer entityplayer) {

        super(((CraftServer) Bukkit.getServer()).getServer(), networkmanager, entityplayer);
    }
}