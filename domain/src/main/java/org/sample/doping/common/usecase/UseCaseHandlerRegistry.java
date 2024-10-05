package org.sample.doping.common.usecase;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.sample.doping.common.model.UseCase;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class UseCaseHandlerRegistry {

    private final Map<Class<? extends UseCase>, UseCaseHandler<?, ? extends UseCase>> registryForUseCaseHandler;

    private final Map<Class<? extends UseCase>, VoidUseCaseHandler<? extends UseCase>> registryForVoidUseCaseHandler;

    public static final UseCaseHandlerRegistry INSTANCE = new UseCaseHandlerRegistry();

    private UseCaseHandlerRegistry() {
        this.registryForUseCaseHandler = new HashMap<>();
        this.registryForVoidUseCaseHandler = new HashMap<>();
    }

    public <R, T extends UseCase> void register(Class<T> useCaseClass, UseCaseHandler<R, T> useCaseHandler) {
        log.info("Use case {} registered by handler {}", useCaseClass.getSimpleName(), useCaseHandler.getClass().getSimpleName());
        registryForUseCaseHandler.put(useCaseClass, useCaseHandler);
    }

    public <T extends UseCase> void register(Class<T> useCaseClass, VoidUseCaseHandler<T> voidUseCaseHandler) {
        log.info("Use case {} registered by handler {}", useCaseClass.getSimpleName(), voidUseCaseHandler.getClass().getSimpleName());
        registryForVoidUseCaseHandler.put(useCaseClass, voidUseCaseHandler);
    }

    public UseCaseHandler<?, ? extends UseCase> detectUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return registryForUseCaseHandler.get(useCaseClass);
    }

    public VoidUseCaseHandler<? extends UseCase> detectVoidUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return registryForVoidUseCaseHandler.get(useCaseClass);
    }
}
