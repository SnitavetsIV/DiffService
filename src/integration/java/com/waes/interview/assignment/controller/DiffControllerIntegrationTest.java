package com.waes.interview.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.interview.assignment.WaesAssignmentApp;
import com.waes.interview.assignment.controller.dto.DiffOperand;
import com.waes.interview.assignment.domain.DiffEntity;
import com.waes.interview.assignment.domain.DiffResult;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaesAssignmentApp.class)
@AutoConfigureMockMvc
public class DiffControllerIntegrationTest {

  private static final ObjectMapper mapper = new ObjectMapper();
  @Autowired private MockMvc mvc;

  private String toString(Object dto) throws JsonProcessingException {
    return mapper.writeValueAsString(dto);
  }

  private <T> T parseDto(MvcResult mvcResult, Class<T> type) throws IOException {
    return mapper.readValue(mvcResult.getResponse().getContentAsString(), type);
  }

  @Test
  public void leftOperand_notExistId_validEntity() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 1L;
    operand.setValue("QmFzZTY0RGlmZmVyVGVzdCBsZWZ0IFN0cmluZw=="); // "Base64DifferTest left String"

    // When
    MvcResult mvcResult =
        mvc.perform(
                post("/v1/diff/" + id + "/left")
                    .content(toString(operand))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    DiffEntity diffEntity = parseDto(mvcResult, DiffEntity.class);

    // Then
    assertEquals(diffEntity.getId(), id);
    assertEquals(diffEntity.getLeft(), operand.getValue());
  }

  @Test
  public void rightOperand_existEntity_updatedOperand() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 2L;
    operand.setValue("QmFzZTY0RGlmZmVyVGVzdCBTdHJpbmc="); // "Base64DifferTest String"

    mvc.perform(
            post("/v1/diff/" + id + "/right")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBVcGRhdGVkIFN0cmluZw=="); // "Base64DifferTest Updated String"
    MvcResult mvcResultUpdated =
        mvc.perform(
                post("/v1/diff/" + id + "/right")
                    .content(toString(operand))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    DiffEntity diffEntityUpdated = parseDto(mvcResultUpdated, DiffEntity.class);

    // Then
    assertEquals(diffEntityUpdated.getId(), id);
    assertEquals(diffEntityUpdated.getRight(), operand.getValue());
  }

  @Test
  public void fetchDiffResult_onlyLeft_notConfigured() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 3L;
    operand.setValue("QmFzZTY0RGlmZmVyVGVzdCBTdHJpbmc="); // "Base64DifferTest String"
    mvc.perform(
            post("/v1/diff/" + id + "/left")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    MvcResult mvcResultDiff =
        mvc.perform(get("/v1/diff/" + id).content(toString(operand))).andReturn();
    DiffResult diffResult = parseDto(mvcResultDiff, DiffResult.class);

    // Then
    assertFalse(diffResult.isConfigured());
  }

  @Test
  public void fetchDiffResult_differLength_notEqual() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 4L;
    operand.setValue("QmFzZTY0RGlmZmVyVGVzdCBTdHJpbmc="); // "Base64DifferTest String"
    mvc.perform(
            post("/v1/diff/" + id + "/left")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGxvbmcgU3RyaW5n"); // "Base64DifferTest long long String"
    mvc.perform(
            post("/v1/diff/" + id + "/right")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    MvcResult mvcResultDiff =
        mvc.perform(get("/v1/diff/" + id).content(toString(operand))).andReturn();
    DiffResult diffResult = parseDto(mvcResultDiff, DiffResult.class);

    // Then
    assertTrue(diffResult.isConfigured());
    assertFalse(diffResult.getEqual());
    assertFalse(diffResult.getLengthEqual());
  }

  @Test
  public void fetchDiffResult_notEqual_returnDiff() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 5L;
    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGFzZGYgU3RyQW5n"); // "Base64DifferTest long asdf StrAng"
    mvc.perform(
            post("/v1/diff/" + id + "/left")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGxvbmcgU3RyaW5n"); // "Base64DifferTest long long String"
    mvc.perform(
            post("/v1/diff/" + id + "/right")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    MvcResult mvcResultDiff =
        mvc.perform(get("/v1/diff/" + id).content(toString(operand))).andReturn();
    DiffResult diffResult = parseDto(mvcResultDiff, DiffResult.class);

    // Then
    assertTrue(diffResult.isConfigured());
    assertFalse(diffResult.getDiffs().isEmpty());
  }

  @Test
  public void fetchDiffResult_equal_returnEqual() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 6L;
    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGxvbmcgU3RyaW5n"); // "Base64DifferTest long long String"
    mvc.perform(
            post("/v1/diff/" + id + "/left")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGxvbmcgU3RyaW5n"); // "Base64DifferTest long long String"
    mvc.perform(
            post("/v1/diff/" + id + "/right")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    MvcResult mvcResultDiff =
        mvc.perform(get("/v1/diff/" + id).content(toString(operand))).andReturn();
    DiffResult diffResult = parseDto(mvcResultDiff, DiffResult.class);

    // Then
    assertTrue(diffResult.isConfigured());
    assertTrue(diffResult.getEqual());
    assertTrue(diffResult.getLengthEqual());
    assertTrue(diffResult.getDiffs().isEmpty());
  }

  @Test
  public void fetchDiffResult_notBase64_notConfigured() throws Exception {
    // Given
    DiffOperand operand = new DiffOperand();
    long id = 7L;
    operand.setValue(
        "QmFzZTY0RGlmZmVyVGVzdCBsb25nIGxvbmcgU3RyaW5n"); // "Base64DifferTest long long String"
    mvc.perform(
            post("/v1/diff/" + id + "/left")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    operand.setValue("Base64DifferTest long long String");
    mvc.perform(
            post("/v1/diff/" + id + "/right")
                .content(toString(operand))
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    // When
    MvcResult mvcResultDiff =
        mvc.perform(get("/v1/diff/" + id).content(toString(operand))).andReturn();
    DiffResult diffResult = parseDto(mvcResultDiff, DiffResult.class);

    // Then
    assertFalse(diffResult.isConfigured());
  }
}
