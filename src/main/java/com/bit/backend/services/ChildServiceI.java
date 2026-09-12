package com.bit.backend.services;

import com.bit.backend.dtos.ChildDto;

import java.util.List;

public interface ChildServiceI {
    ChildDto addChild(ChildDto childDto);
    List<ChildDto> getAllChildren();
    ChildDto getChildById(long id);
    ChildDto updateChild(long id, ChildDto childDto);
    ChildDto deleteChild(long id);
}
