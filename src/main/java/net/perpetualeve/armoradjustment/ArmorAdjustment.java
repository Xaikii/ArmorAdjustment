package net.perpetualeve.armoradjustment;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod(ArmorAdjustment.MODID)
public class ArmorAdjustment {
	public static final String MODID = "armoradjustment";
	
	public static float toughness;
	DoubleValue ToughnessDividor;
	public static ForgeConfigSpec CONFIG;	
	
	public ArmorAdjustment() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		builder.push("general");
		ToughnessDividor = builder.defineInRange("toughness_dividor", 12D, 1D, Double.MAX_VALUE);
		builder.pop();
		CONFIG = builder.build();
		ModLoadingContext.get().registerConfig(Type.COMMON, CONFIG, "ArmorAdjustment.toml");
	}
	
	public void onLoad(ModConfigEvent.Loading configEvent) 
    {
    	toughness = ToughnessDividor.get().floatValue();
    }

    public void onFileChange(ModConfigEvent.Reloading configEvent) 
    {
    	toughness = ToughnessDividor.get().floatValue();
    }
}
