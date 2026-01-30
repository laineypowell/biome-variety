package com.laineypowell.biomevariety.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public enum LogRoot implements StringRepresentable {
    LEFT("left"),
    RIGHT("right"),
    TOP_LEFT("top_left"),
    TOP_RIGHT("top_right");

    public static final EnumProperty<LogRoot> LOG_ROOT = EnumProperty.create("log_root", LogRoot.class);

    private final String blockStateName;

    LogRoot(String blockStateName) {
        this.blockStateName = blockStateName;
    }

    @Override
    public String getSerializedName() {
        return blockStateName;
    }

}
