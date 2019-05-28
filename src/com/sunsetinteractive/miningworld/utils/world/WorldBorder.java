package com.sunsetinteractive.miningworld.utils.world;

import com.sunsetinteractive.miningworld.utils.NMSUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class WorldBorder {

	private static Class<?> packetPlayOutWorldBorder, packetPlayOutWorldBorderEnumClass, worldBorderClass,
			craftWorldClass;
	private static Constructor<?> packetPlayOutWorldBorderConstructor;

	static {

		try {
			packetPlayOutWorldBorder = NMSUtils.getNMSClass("PacketPlayOutWorldBorder");

			if (NMSUtils.getVersionNumber() > 10) {
				packetPlayOutWorldBorderEnumClass = packetPlayOutWorldBorder.getDeclaredClasses()[0];
			} else {
				packetPlayOutWorldBorderEnumClass = packetPlayOutWorldBorder.getDeclaredClasses()[1];
			}

			worldBorderClass = NMSUtils.getNMSClass("WorldBorder");
			craftWorldClass = NMSUtils.getCraftClass("CraftWorld");

			packetPlayOutWorldBorderConstructor = packetPlayOutWorldBorder.getConstructor(worldBorderClass,
					packetPlayOutWorldBorderEnumClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(Player player, double size, Location centerLocation) {

		try {
			// Adjust border size to fit around whole-blocks, odd numbers only!
			size += size % 2 == 0 ? 1 : 0;

			centerLocation = centerLocation.clone();
			centerLocation.add(.5, 0, .5);
			Object worldBorder = worldBorderClass.getConstructor().newInstance();

			if (NMSUtils.getVersionNumber() < 9) {
				Field borderSize = worldBorder.getClass().getDeclaredField("d");
				borderSize.setAccessible(true);
				borderSize.set(worldBorder, size);
			} else {
				Object craftWorld = craftWorldClass.cast(centerLocation.getWorld());
				Method getHandleMethod = craftWorld.getClass().getMethod("getHandle", new Class<?>[0]);
				Object worldServer = getHandleMethod.invoke(craftWorld, new Object[0]);
				NMSUtils.setField(worldBorder, "world", worldServer, false);
			}

			Method setCenter = worldBorder.getClass().getMethod("setCenter", double.class, double.class);
			setCenter.invoke(worldBorder, centerLocation.getX(), centerLocation.getZ());

			Method setSize = worldBorder.getClass().getMethod("setSize", double.class);
			setSize.invoke(worldBorder, size);

			Method setWarningTime = worldBorder.getClass().getMethod("setWarningTime", int.class);
			setWarningTime.invoke(worldBorder, 0);

			Method transitionSizeBetween = worldBorder.getClass().getMethod("transitionSizeBetween", double.class,
					double.class, long.class);

			@SuppressWarnings({ "unchecked", "rawtypes" })
			Object packet = packetPlayOutWorldBorderConstructor.newInstance(worldBorder,
					Enum.valueOf((Class<Enum>) packetPlayOutWorldBorderEnumClass, "INITIALIZE"));
			NMSUtils.sendPacket(player, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
