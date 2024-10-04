package com.example.examplemod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod("examplemod")
public class newworld {
    private static final Logger LOGGER = LogManager.getLogger();

    public newworld() {
        // Register ourselves for server and other game events we are interested in
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.register(null);
        LOGGER.info("Hello World Mod initialized!");
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            String message = "Hello world!";
            Component component = Component.literal(message);
            MinecraftServer server = player.getServer();
            if (server != null) {
                server.getPlayerList().broadcastSystemMessage(component, false);
                LOGGER.info("Player {} joined. Sent Hello World message.", player.getName().getString());
            } else {
                LOGGER.warn("Unable to send Hello World message: server is null for player {}", player.getName().getString());
            }
        }
    }
}
