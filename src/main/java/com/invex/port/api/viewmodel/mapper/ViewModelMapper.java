package com.invex.port.api.viewmodel.mapper;

public interface ViewModelMapper <S,D> {
    D map(S source);
}
