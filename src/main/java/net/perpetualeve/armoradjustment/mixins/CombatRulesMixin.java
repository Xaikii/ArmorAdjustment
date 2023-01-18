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
		double sqrt = Math.sqrt(damage);
		double div = (sqrt+Math.sqrt(armor+((toughness/ArmorAdjustment.toughness)*sqrt)));
		ci.setReturnValue((float) (damage*(sqrt/div)));
	}
}
