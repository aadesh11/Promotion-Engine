package net.engine.web.utils;

import net.engine.web.model.CodeIdentifier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Common utility methods.
 */
public final class ConverterUtils {

    //Convert list to map
    public static <T extends CodeIdentifier> Map<String, T> convert(List<T> codes) {
        return codes.stream()
                .collect(Collectors.toMap(CodeIdentifier::code, Function.identity(), (r1, r2) -> r2));
    }
}
