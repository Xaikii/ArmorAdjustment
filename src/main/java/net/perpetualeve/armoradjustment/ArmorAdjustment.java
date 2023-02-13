package net.perpetualeve.armoradjustment;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArmorAdjustment.MODID)
public class ArmorAdjustment {
	public static final String MODID = "armoradjustment";
	
	public static float armor_div;
	DoubleValue ArmorDividor;
	public static float toughness_div;
	DoubleValue ToughnessDividor;
	public static float armor_base;
	DoubleValue ArmorBaseCFG;
	public static float toughness_base;
	DoubleValue ToughnessBaseCFG;
	public static ForgeConfigSpec CONFIG;	
	
	public ArmorAdjustment() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		builder.push("general");
		builder.comment("the armor will be divided by this number in calculation");
		ArmorDividor = builder.defineInRange("armor_dividor", 1D, 0.01D, Double.MAX_VALUE);
		builder.comment("the toughness will be divided by this number in calculation");
		ToughnessDividor = builder.defineInRange("toughness_dividor", 1D, 0.01D, Double.MAX_VALUE);
		builder.comment("the base used for armor in calculation");
		ArmorBaseCFG = builder.defineInRange("armor_base", 0.966D, 0.0D, Double.MAX_VALUE);
		builder.comment("the base used for toughness in calculation");
		ToughnessBaseCFG = builder.defineInRange("toughness_base", 0.99D, 0.0D, Double.MAX_VALUE);
		builder.pop();
		CONFIG = builder.build();
		ModLoadingContext.get().registerConfig(Type.COMMON, CONFIG, "ArmorAdjustment.toml");
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::onLoad);
		bus.addListener(this::onFileChange);
	}
	
	public void onLoad(ModConfigEvent.Loading configEvent) 
    {
    	armor_div = ArmorDividor.get().floatValue();
    	toughness_div = ToughnessDividor.get().floatValue();
    	armor_base = ArmorBaseCFG.get().floatValue();
    	toughness_base = ToughnessBaseCFG.get().floatValue();
    }

    public void onFileChange(ModConfigEvent.Reloading configEvent) 
    {
    	armor_div = ArmorDividor.get().floatValue();
    	toughness_div = ToughnessDividor.get().floatValue();
    	armor_base = ArmorBaseCFG.get().floatValue();
    	toughness_base = ToughnessBaseCFG.get().floatValue();
    }
}
