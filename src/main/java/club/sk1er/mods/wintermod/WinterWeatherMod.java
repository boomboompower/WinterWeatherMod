package club.sk1er.mods.wintermod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = WinterWeatherMod.MODID, version = WinterWeatherMod.VERSION)
public class WinterWeatherMod {

    public static final String MODID = "winter_weather";
    public static final String VERSION = "1.0";

    private final SnowRenderHandler snowRenderer = new SnowRenderHandler();
    private final Minecraft mc = Minecraft.getMinecraft();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        event.world.provider.setWeatherRenderer(this.snowRenderer);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !mc.isGamePaused()) {
            this.snowRenderer.incrementSnowUpdateCounter();
        }
    }
}
