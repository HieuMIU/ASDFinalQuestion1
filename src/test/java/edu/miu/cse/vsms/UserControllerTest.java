package edu.miu.cse.vsms;

import com.google.gson.Gson;
import edu.miu.cse.vsms.controller.EmployeeController;
import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.Option;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeController.class)//from spring: It loads the application context, but not fully
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean //spring framework
    private EmployeeService employeeService;

    @Test
    void createEmployee() throws Exception {

        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("Hieu", "hieu@gmail.com", "0987612345", LocalDate.of(2024,11,25));
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(1L, "Hieu", "hieu@gmail.com", "0987612345", LocalDate.of(2024,11,25), List.of());

       mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"Hieu\",\n" +
                                "    \"email\":\"hieu@gmail.com\",\n" +
                                "    \"phone\":\"0987654321\",\n" +
                                "    \"hireDate\":\"2024-11-25\"\n" +
                                "}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

}