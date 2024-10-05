package org.sample.doping.common.usecase;

import org.sample.doping.common.model.UseCase;

public class ObservableUseCasePublisher extends BeanAwareUseCasePublisher {

    public <R, T extends UseCase> void register(Class<T> useCaseClass, UseCaseHandler<R, T> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, useCaseHandler);
    }

    public <T extends UseCase> void register(Class<T> useCaseClass, VoidUseCaseHandler<T> voidUseCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, voidUseCaseHandler);
    }
}
