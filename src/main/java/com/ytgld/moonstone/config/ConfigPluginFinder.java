package com.ytgld.moonstone.config;

import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.util.*;

public class ConfigPluginFinder {

    private ConfigPluginFinder() {

    }
    public static List<RegisterItemConfig> getModPlugins() {
        return getInstances(ConfigPlugin.class, RegisterItemConfig.class);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T> List<T> getInstances(Class<?> annotationClass, Class<T> instanceClass) {
        Type annotationType = Type.getType(annotationClass);
        Set<String> pluginClassNames = getStrings(annotationType);
        List<T> instances = new ArrayList<>();
        for (String className : pluginClassNames) {
            try {
                Class<?> asmClass = Class.forName(className);
                Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
                Constructor<? extends T> constructor = asmInstanceClass.getDeclaredConstructor();
                T instance = constructor.newInstance();
                instances.add(instance);
            } catch (ReflectiveOperationException | LinkageError e) {
            }
        }
        return instances;
    }

    private static @NotNull Set<String> getStrings(Type annotationType) {
        List<ModFileScanData> allScanData = ModList.get().getAllScanData();
        Set<String> pluginClassNames = new LinkedHashSet<>();
        for (ModFileScanData scanData : allScanData) {
            Iterable<ModFileScanData.AnnotationData> annotations = scanData.getAnnotations();
            for (ModFileScanData.AnnotationData a : annotations) {
                if (Objects.equals(a.annotationType(), annotationType)) {
                    String memberName = a.memberName();
                    pluginClassNames.add(memberName);
                }
            }
        }
        return pluginClassNames;
    }
}
