package com.eonedu.domain.counseltype.application;

import com.eonedu.domain.counseltype.dao.CounselTypeRepository;
import com.eonedu.domain.counseltype.domain.CounselType;
import com.eonedu.domain.counseltype.dto.request.CounselTypeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselTypeService {
    private final CounselTypeRepository counselTypeRepository;

    @Transactional
    public CounselType createCounselType(CounselTypeCreateRequest request) {
        CounselType counselType = CounselType.from(request.getName());

        return counselTypeRepository.save(counselType);
    }

    @Transactional(readOnly = true)
    public List<CounselType> findAll(){
        return counselTypeRepository.findAll();
    }
}
