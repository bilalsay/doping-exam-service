package org.sample.doping.common.usecase;

import org.sample.doping.common.model.UseCase;

public interface UseCaseHandler <R, T extends UseCase> {
    R handle(T useCase);
}
