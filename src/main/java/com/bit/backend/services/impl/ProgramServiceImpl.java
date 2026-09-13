package com.bit.backend.services.impl;

import com.bit.backend.dtos.ProgramDto;
import com.bit.backend.entities.ProgramEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ProgramMapper;
import com.bit.backend.repositories.ProgramRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.ProgramServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramServiceI {

    private final ProgramRepository programRepository;
    private final StatusRepository statusRepository;
    private final ProgramMapper programMapper;

    public ProgramServiceImpl(
            ProgramRepository programRepository,
            StatusRepository statusRepository,
            ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.statusRepository = statusRepository;
        this.programMapper = programMapper;
    }

    @Override
    @Transactional
    public ProgramDto addProgram(ProgramDto programDto) {
        StatusEntity status = resolveStatus(programDto);

        ProgramEntity entity = programMapper.toProgramEntity(programDto);

        entity.setId(null);
        entity.setStatus(status);

        ProgramEntity saved = programRepository.save(entity);

        if (saved.getProgramCode() == null
                || saved.getProgramCode().isBlank()) {
            saved.setProgramCode("PRG-" + saved.getId());
            saved = programRepository.save(saved);
        }

        return programMapper.toProgramDto(saved);
    }

    @Override
    public List<ProgramDto> getAllPrograms() {
        return programMapper.toProgramDtoList(
                programRepository.findAll()
        );
    }

    @Override
    public ProgramDto getProgramById(long id) {
        ProgramEntity entity = programRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Program not found",
                        HttpStatus.NOT_FOUND
                ));

        return programMapper.toProgramDto(entity);
    }

    @Override
    @Transactional
    public ProgramDto updateProgram(long id, ProgramDto programDto) {
        ProgramEntity existing = programRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Program not found",
                        HttpStatus.NOT_FOUND
                ));

        StatusEntity status = resolveStatus(programDto);

        existing.setProgramName(programDto.getProgramName());
        existing.setDescription(programDto.getDescription());
        existing.setSessionStart(programDto.getSessionStart());
        existing.setSessionEnd(programDto.getSessionEnd());
        existing.setMonthlyFee(programDto.getMonthlyFee());
        existing.setStatus(status);

        if (programDto.getProgramCode() != null
                && !programDto.getProgramCode().isBlank()) {
            existing.setProgramCode(programDto.getProgramCode());
        }

        return programMapper.toProgramDto(
                programRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public ProgramDto deleteProgram(long id) {
        ProgramEntity existing = programRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Program not found",
                        HttpStatus.NOT_FOUND
                ));

        ProgramDto dto = programMapper.toProgramDto(existing);

        programRepository.delete(existing);

        return dto;
    }

    private StatusEntity resolveStatus(ProgramDto programDto) {
        if (programDto.getStatus() == null
                || programDto.getStatus().getId() == null) {
            throw new AppException(
                    "Status is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return statusRepository.findById(programDto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));
    }
}
