package net.perpetualeve.armoradjustment.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.world.damagesource.CombatRules;
import net.perpetualeve.armoradjustment.ArmorAdjustment;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

	@Inject(method = "getDamageAfterAbsorb", at = @At("Invoke"), cancellable = true, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void getDamageAfterAbsorbRed(float damage, float armor, float toughness, CallbackInfoReturnable<Float> ci) {
		ci.setReturnValue((float) (damage * Math.pow(ArmorAdjustment.armor_base, armor/ArmorAdjustment.armor_div) * Math.pow(ArmorAdjustment.toughness_base, Math.sqrt(damage*(toughness/ArmorAdjustment.toughness_div)))));
	}
}
