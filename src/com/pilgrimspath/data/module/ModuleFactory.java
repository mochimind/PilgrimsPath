package com.pilgrimspath.data.module;

import com.pilgrimspath.data.Ship;

public class ModuleFactory {
	public static Module CreateModule(int id, Ship container) {
		switch (id) {
		case Module.AGRO_PODS:
			return new AgroPods(container);
		case Module.ANTIMATTER_FACILITY:
			return new AntimatterFacility(container);
		case Module.APARTMENTS:
			return new Apartments(container);
		case Module.ASSEMBLER:
			return new Assembler(container);
		case Module.ASSEMBLY:
			return new Assembly(container);
		case Module.CAFE:
			return new Cafe(container);
		case Module.COMPLEX:
			return new Complex(container);
		case Module.CREW_BARRACKS:
			return new CrewBarracks(container);
		case Module.ELECTROLYTER:
			return new Electrolyter(container);
		case Module.FACTORY:
			return new Factory(container);
		case Module.FOOD_PROCESSOR:
			return new FoodProcessor(container);
		case Module.FOOD_VAT:
			return new FoodVat(container);
		case Module.FORUM:
			return new Forum(container);
		case Module.FRACKER:
			return new Fracker(container);
		case Module.FUSION_REACTOR:
			return new FusionReactor(container);
		case Module.HEAVY_REFINERY:
			return new HeavyRefinery(container);
		case Module.HIGH_ENERGY_LAB:
			return new HighEnergyLab(container);
		case Module.HYDROPONICS:
			return new Hydroponics(container);
		case Module.INDUSTRIAL_FACILITY:
			return new IndustrialFacility(container);
		case Module.LAB:
			return new Lab(container);
		case Module.LUXURY_QUARTERS:
			return new LuxuryQuarters(container);
		case Module.MANUFACTORY:
			return new Manufactory(container);
		case Module.OBSERVATORY:
			return new Observatory(container);
		case Module.ORE_PROCESSOR:
			return new OreProcessor(container);
		case Module.POLYMER_FACTORY:
			return new PolymerFactory(container);
		case Module.POWER_PLANT:
			return new PowerPlant(container);
		case Module.POWER_STATION:
			return new PowerStation(container);
		case Module.PRINT_SHOP:
			return new PrintShop(container);
		case Module.PROTOTYPE_CENTER:
			return new PrototypeCenter(container);
		case Module.QUARTERS:
			return new Quarters(container);
		case Module.READING_ROOM:
			return new ReadingRoom(container);
		case Module.REFINERY:
			return new Refinery(container);
		case Module.SIMULATRON:
			return new Simulatron(container);
		case Module.SKUNKWORKS:
			return new Skunkworks(container);
		case Module.WORKSHOP:
			return new Workshop(container);
		default:
			return null;
		}
	}
}
