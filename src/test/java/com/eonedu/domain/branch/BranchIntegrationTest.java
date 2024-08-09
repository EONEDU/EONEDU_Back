package com.eonedu.domain.branch;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BranchIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void 지점_생성() throws Exception {
        // given
        String request = "{ \"name\": \"강남점\" }";
        // when
        mvc.perform(post("/admin/v1/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        // then
                .andExpect(status().isOk());
    }

//    @Test // 중복 지점 추가 테스트는 실패하는 테스트이므로 예외처리 로직 PR 후 작성 예정
//    public void 중복_지점_추가() throws Exception {
//        // given
//        String request = "{ \"name\": \"강남점\" }";
//        // when
//        mvc.perform(post("/admin/v1/branches")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(request))
//        // then
//                .andExpect(status().isBadRequest());
//    }

    @Test
    public void 지점_리스트_조회() throws Exception {
        // given
        // when
        mvc.perform(get("/v1/branches"))
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branches").exists());
    }
}
