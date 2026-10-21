
package com.bit.backend.services.impl;

import com.bit.backend.dtos.CommonDataDto;
import com.bit.backend.dtos.CommonDataListDto;
import com.bit.backend.entities.Privilege;
import com.bit.backend.entities.PrivilegeGroup;
import com.bit.backend.entities.PrivilegeGroupAuth;
import com.bit.backend.entities.PrivilegeGroupUser;
import com.bit.backend.entities.UserEntity;
import com.bit.backend.repositories.CommonDataRepository;
import com.bit.backend.repositories.PrivilegeGroupAuthRepository;
import com.bit.backend.repositories.PrivilegeGroupUserRepository;
import com.bit.backend.services.CommonDataServiceI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonDataService implements CommonDataServiceI {

    private final CommonDataRepository commonDataRepository;
    private final PrivilegeGroupAuthRepository privilegeGroupAuthRepository;
    private final PrivilegeGroupUserRepository privilegeGroupUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CommonDataService(
            CommonDataRepository commonDataRepository,
            PrivilegeGroupAuthRepository privilegeGroupAuthRepository,
            PrivilegeGroupUserRepository privilegeGroupUserRepository) {

        this.commonDataRepository = commonDataRepository;
        this.privilegeGroupAuthRepository = privilegeGroupAuthRepository;
        this.privilegeGroupUserRepository = privilegeGroupUserRepository;
    }

    @Override
    public List<CommonDataDto> getAvailablePrivilegesByGroupID(int id) {
        return mapTuples(
                commonDataRepository.getAvailablePrivilegesByGroupId(id)
        );
    }

    @Override
    public List<CommonDataDto> getAssignedPrivilegesByGroupID(int id) {
        return mapTuples(
                commonDataRepository.getAssignedPrivilegesByGroupId(id)
        );
    }

    @Override
    @Transactional
    public CommonDataListDto saveData(
            int id,
            CommonDataListDto commonDataListDto) {

        List<CommonDataDto> added =
                nullSafe(commonDataListDto.getAddedData());

        List<CommonDataDto> removed =
                nullSafe(commonDataListDto.getRemovedData());

        // Remove privileges from the group.
        for (CommonDataDto dto : removed) {
            if (dto.getId() == null) {
                continue;
            }

            privilegeGroupAuthRepository
                    .findByAuthGroupIdAndAuthId(
                            id,
                            dto.getId().intValue()
                    )
                    .ifPresent(privilegeGroupAuthRepository::delete);
        }

        // Add privileges to the group.
        for (CommonDataDto dto : added) {
            if (dto.getId() == null) {
                continue;
            }

            int authId = dto.getId().intValue();

            if (privilegeGroupAuthRepository
                    .findByAuthGroupIdAndAuthId(id, authId)
                    .isPresent()) {
                continue;
            }

            PrivilegeGroup group = entityManager.find(
                    PrivilegeGroup.class,
                    (long) id
            );

            if (group == null) {
                throw new IllegalArgumentException(
                        "Privilege group not found: " + id
                );
            }

            // The DTO ID is assumed to contain Privilege.authId.
            Privilege privilege = entityManager.createQuery(
                            "SELECT p FROM Privilege p WHERE p.authId = :authId",
                            Privilege.class
                    )
                    .setParameter("authId", authId)
                    .getResultStream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Privilege not found: " + authId
                    ));

            PrivilegeGroupAuth entity = new PrivilegeGroupAuth();
            entity.setAuthGroupId(group);
            entity.setAuthId(privilege);

            privilegeGroupAuthRepository.save(entity);
        }

        return commonDataListDto;
    }

    @Override
    public List<CommonDataDto> getAvailableUsersByGroupID(int id) {
        return mapTuples(
                commonDataRepository.getAvailableUsersByGroupID(id)
        );
    }

    @Override
    public List<CommonDataDto> getAssignedUsersByGroupID(int id) {
        return mapTuples(
                commonDataRepository.getAssignedUsersByGroupId(id)
        );
    }

    @Override
    @Transactional
    public CommonDataListDto saveGroupUserData(
            int id,
            CommonDataListDto commonDataListDto) {

        List<CommonDataDto> added =
                nullSafe(commonDataListDto.getAddedData());

        List<CommonDataDto> removed =
                nullSafe(commonDataListDto.getRemovedData());

        // Remove users from the group.
        for (CommonDataDto dto : removed) {
            if (dto.getId() == null) {
                continue;
            }

            privilegeGroupUserRepository
                    .findByAuthGroupIdAndUserId(
                            id,
                            dto.getId().intValue()
                    )
                    .ifPresent(privilegeGroupUserRepository::delete);
        }

        // Add users to the group.
        for (CommonDataDto dto : added) {
            if (dto.getId() == null) {
                continue;
            }

            int userId = dto.getId().intValue();

            if (privilegeGroupUserRepository
                    .findByAuthGroupIdAndUserId(id, userId)
                    .isPresent()) {
                continue;
            }

            PrivilegeGroup group = entityManager.find(
                    PrivilegeGroup.class,
                    (long) id
            );

            if (group == null) {
                throw new IllegalArgumentException(
                        "Privilege group not found: " + id
                );
            }

            UserEntity user = entityManager.find(
                    UserEntity.class,
                    (long) userId
            );

            if (user == null) {
                throw new IllegalArgumentException(
                        "User not found: " + userId
                );
            }

            PrivilegeGroupUser entity = new PrivilegeGroupUser();
            entity.setAuthGroupId(group);
            entity.setUserId(user);

            privilegeGroupUserRepository.save(entity);
        }

        return commonDataListDto;
    }

    private List<CommonDataDto> mapTuples(List<Tuple> tuples) {
        if (tuples == null) {
            return Collections.emptyList();
        }

        return tuples.stream()
                .map(tuple -> {
                    CommonDataDto dto = new CommonDataDto();

                    Number id = tuple.get(0, Number.class);

                    dto.setId(id != null ? id.longValue() : null);
                    dto.setDescription(tuple.get(1, String.class));

                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<CommonDataDto> nullSafe(List<CommonDataDto> list) {
        return list != null ? list : Collections.emptyList();
    }
}
