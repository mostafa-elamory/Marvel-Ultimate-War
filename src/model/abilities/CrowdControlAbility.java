package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}
	public String toString() {
		return "Name:"+this.getName()+"\n"+"\n"+"Type: Crowdcontrol"+"\n"+"\n"+"area of effect: "+this.getCastArea()+"\n"+"\n"+"cast range: "
	+this.getCastRange()+"\n"+"\n"+"mana cost: "+this.getManaCost()+"\n"+"\n"+"action points: "+this.getRequiredActionPoints()+"\n"+"\n"+
				"cooldown: "+this.getCurrentCooldown()+"/"+this.getBaseCooldown()+"\n"+"\n"+"effect: "+this.getEffect().getName();
	}

}
