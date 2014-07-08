package de.raidcraft.npc.nms.v1_7_R3;

import de.raidcraft.npc.api.NPCChannel;
import net.minecraft.server.v1_7_R3.NetworkManager;

import java.lang.reflect.Field;
import java.net.SocketAddress;

public class NPCNetworkManager extends NetworkManager {

    public NPCNetworkManager() {

        super(false);

        try {
            Field channel = NetworkManager.class.getDeclaredField("m");
            Field address = NetworkManager.class.getDeclaredField("n");

            channel.setAccessible(true);
            address.setAccessible(true);

            channel.set(this, new NPCChannel(null));
            address.set(this, new SocketAddress() {
                private static final long serialVersionUID = 6994835504305404545L;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
