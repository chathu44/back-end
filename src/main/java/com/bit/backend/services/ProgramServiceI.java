package com.bit.backend.services;

import com.bit.backend.dtos.ProgramDto;

import java.util.List;

public interface ProgramServiceI {
    ProgramDto addProgram(ProgramDto programDto);

    List<ProgramDto> getAllPrograms();

    ProgramDto getProgramById(long id);

    ProgramDto updateProgram(long id, ProgramDto programDto);

    ProgramDto deleteProgram(long id);
}
