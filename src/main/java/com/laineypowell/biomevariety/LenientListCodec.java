package com.laineypowell.biomevariety;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

import java.util.ArrayList;
import java.util.List;

public final class LenientListCodec<T> implements Codec<List<T>> {
    private final Codec<T> codec;

    private LenientListCodec(Codec<T> codec) {
        this.codec = codec;
    }

    @Override
    public <T1> DataResult<Pair<List<T>, T1>> decode(DynamicOps<T1> ops, T1 input) {
        var result = codec.decode(ops, input).map(pair -> Pair.of(List.of(pair.getFirst()), pair.getSecond()));
        if (result.error().isPresent()) {
            List<T> list = new ArrayList<>();
            for (var t : ops.getStream(input).result().orElseThrow().toList()) {
                codec.decode(ops, t).result().ifPresent(pair -> list.add(pair.getFirst()));
            }
            return DataResult.success(Pair.of(list, input));
        }

        return result;
    }

    @Override
    public <T1> DataResult<T1> encode(List<T> input, DynamicOps<T1> ops, T1 prefix) {
        if (input.size() == 1) {
            return codec.encode(input.get(0), ops, prefix);
        }

        var builder = ops.listBuilder();
        for (var t : input) {
            builder.add(codec.encodeStart(ops, t));
        }

        return builder.build(prefix);
    }

    public static <T> Codec<List<T>> lenientList(Codec<T> codec) {
        return new LenientListCodec<>(codec);
    }
}
