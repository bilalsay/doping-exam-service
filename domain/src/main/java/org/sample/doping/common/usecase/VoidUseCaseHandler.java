package org.sample.doping.common.usecase;

import org.sample.doping.common.model.UseCase;

public interface VoidUseCaseHandler<T extends UseCase>{
    void handle(T useCase);
}
