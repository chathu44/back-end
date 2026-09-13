package com.bit.backend.services;

import com.bit.backend.dtos.ClassroomDto;

import java.util.List;

public interface ClassroomServiceI {
    ClassroomDto addClassroom(ClassroomDto classroomDto);

    List<ClassroomDto> getAllClassrooms();

    ClassroomDto getClassroomById(long id);

    ClassroomDto updateClassroom(long id, ClassroomDto classroomDto);

    ClassroomDto deleteClassroom(long id);
}
