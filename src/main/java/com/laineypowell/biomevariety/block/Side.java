package com.laineypowell.biomevariety.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum Side implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    public static final EnumProperty<Side> SIDE = EnumProperty.create("side", Side.class);

    private final String blockStateName;

    Side(String blockStateName) {
        this.blockStateName = blockStateName;
    }

    @Override
    public String getSerializedName() {
        return blockStateName;
    }

}
