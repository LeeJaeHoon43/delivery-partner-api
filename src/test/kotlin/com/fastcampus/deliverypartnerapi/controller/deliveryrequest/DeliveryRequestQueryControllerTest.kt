package com.fastcampus.deliverypartnerapi.controller.deliveryrequest

import com.fastcampus.deliverypartnerapi.controller.deliveryrequest.dto.DeliveryRequestResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryRequestQueryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var context: WebApplicationContext

    @DisplayName("라이더는 배달 요청 목록을 볼 수 있다.")
    @Test
    fun findDeliveryRequests(){
        // given
        val deliveryPartnerId = 1L
        val url = "http://localhost:30001/apis/delivery-requests?deliveryPartnerId=$deliveryPartnerId"

        // when
        val mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn()

        // then
        val deliveryRequestResponse = objectMapper.readValue(mvcResult.response.contentAsString, DeliveryRequestResponse::class.java)
        assertThat(deliveryRequestResponse).isNotNull
        assertThat(deliveryRequestResponse.deliveryRequests.size).isEqualTo(1)
    }
}