package net.perpetualeve.armoradjustment.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.CombatRules;
import net.perpetualeve.armoradjustment.ArmorAdjustment;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

	@Redirect(method = "getDamageAfterAbsorb", at = @At("Invoke"))
	private static float getDamageAfterAbsorbRed(float u1, float u2, float u3, float damage, float armor, float toughness) {
		float sqrt = Mth.fastInvSqrt(damage);
		float div = sqrt+Mth.fastInvSqrt(armor*((toughness/ArmorAdjustment.toughness)*sqrt));
		return damage*(sqrt/div);
	}
}
