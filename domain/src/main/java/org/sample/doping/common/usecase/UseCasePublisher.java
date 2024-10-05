package org.sample.doping.common.usecase;

import org.sample.doping.common.model.UseCase;

public interface UseCasePublisher {

    <R, T extends UseCase> R publish(Class<R> returnClass, T useCase);

    <T extends UseCase> void publish(T useCase);
}
