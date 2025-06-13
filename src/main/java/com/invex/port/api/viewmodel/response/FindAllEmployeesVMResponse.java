package com.invex.port.api.viewmodel.response;

import com.invex.port.api.viewmodel.model.EmployeeViewModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class FindAllEmployeesVMResponse {
    public List<EmployeeViewModel> employees;
}
