package com.zhpeng.Utils;

import com.zhpeng.Armors.ArmorBase;
import com.zhpeng.Armors.Armors;
import com.zhpeng.Champions.AbilityBase;
import com.zhpeng.Champions.ChampionBase;
import com.zhpeng.Champions.Champions;
import com.zhpeng.Items.AbilityRefresh;
import com.zhpeng.Items.HealthPotion;
import com.zhpeng.Items.Items;
import com.zhpeng.Items.ItemsBase;
import com.zhpeng.Shields.ShieldBase;
import com.zhpeng.Shields.Shields;
import com.zhpeng.SummonerSpells.SpellBase;
import com.zhpeng.SummonerSpells.SummonerSpells;
import com.zhpeng.Swords.SwordBase;
import com.zhpeng.Swords.Swords;

import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, Constants.MODID);
	
	public static void init() {
		registerAll();
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static void registerAll() {
		registerArmor();
		registerWeapon();
		registerShield();
		registerItem();
		registerChampions();
		registerSpells();
	}
	
	private static void registerArmor() {
		for (Armors armor : Armors.values()) {
			ITEMS.register(armor.getName(), () -> new ArmorBase(armor));
		}
	}
	
	private static void registerWeapon() {
		for (Swords sword : Swords.values()) {
			ITEMS.register(sword.getName(), () -> new SwordBase(sword));
		}
	}
	
	private static void registerShield() {
		for (Shields shield : Shields.values()) {
			ITEMS.register(shield.getName(), () -> new ShieldBase());
		}
	}
	
	private static void registerItem() {
		for (Items item : Items.values()) {
			ITEMS.register(item.getName(), () -> new ItemsBase());
		}
		ITEMS.register("health_potion", () -> new HealthPotion());
		ITEMS.register("ability_refresh", () -> new AbilityRefresh());
	}
	
	private static void registerChampions() {
		Champions.addAllChampions();
		for (ChampionBase champion : Champions.CHAMPIONS) {
			ITEMS.register(champion.getChampionName(), () -> champion);
			for (AbilityBase ability : champion.ABILITIES) {
				ITEMS.register(ability.getAbilityName(), () -> ability);
			}
		}
	}
	
	private static void registerSpells() {
		SummonerSpells.addAllSpells();
		for (SpellBase spell : SummonerSpells.SPELLS) {
			ITEMS.register(spell.getSpellName(), () -> spell);
		}
	}

}
