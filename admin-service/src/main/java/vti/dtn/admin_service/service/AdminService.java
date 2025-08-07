package vti.dtn.admin_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vti.dtn.admin_service.dto.DepartmentDTO;
import vti.dtn.admin_service.feignclient.DepartmentFeignClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final DepartmentFeignClient departmentFeignClient;

    public List<DepartmentDTO> getDepartments() {
        return departmentFeignClient.getAllDepartments();
    }
}
