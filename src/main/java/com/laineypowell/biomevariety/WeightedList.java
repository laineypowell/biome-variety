package com.laineypowell.biomevariety;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;

import java.util.List;

public final class WeightedList<T> {
    private final Either<T, List<Entry<T>>> either;

    private WeightedList(Either<T, List<Entry<T>>> either) {
        this.either = either;
    }

    public T get(RandomSource randomSource) {
        var left = either.left();
        if (left.isPresent()) {
            return left.get();
        }

        var right = either.right().orElseThrow();
        var i = 0;
        var j = randomSource.nextInt(right.stream().mapToInt(Entry::weight).sum());

        for (var entry : right) {
            var weight = entry.weight;
            if (j < (i += weight)) {
                return entry.t;
            }
        }

        return right.get(0).t;
    }

    public static <T> Codec<WeightedList<T>> codec(Codec<T> codec) {
        return Codec.either(codec, entryCodec(codec).listOf()).xmap(WeightedList::new, list -> list.either);
    }

    public static <T> Codec<Entry<T>> entryCodec(Codec<T> codec) {
        return RecordCodecBuilder.create(instance -> instance.group(codec.fieldOf("value").forGetter(Entry<T>::t), Codec.INT.optionalFieldOf("weight", 0).forGetter(Entry<T>::weight)).apply(instance, Entry::new));
    }

    public record Entry<T>(T t, int weight) {

    }

}
