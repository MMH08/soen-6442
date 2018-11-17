package com.soen.risk.entity;

import java.util.List;

public interface ReinforceStrategy {
    void execute(List<Country> countries);
}
