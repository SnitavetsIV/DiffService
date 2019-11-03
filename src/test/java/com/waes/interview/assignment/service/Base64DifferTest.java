package com.waes.interview.assignment.service;

import com.waes.interview.assignment.domain.DiffResult;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Base64DifferTest {

  @Test
  void calculateDiff_nullNull_notConfigured() {
    // Given
    Base64Differ differ = new Base64Differ();

    // When
    DiffResult diffResult = differ.calculateDiff(null, null);

    // Then
    assertFalse(diffResult.isConfigured());
  }

  @Test
  void calculateDiff_nullNotNull_notConfigured() {
    // Given
    Base64Differ differ = new Base64Differ();
    String left = "QmFzZTY0RGlmZmVyVGVzdCBsZWZ0IFN0cmluZw=="; // "Base64DifferTest left String"

    // When
    DiffResult diffResult = differ.calculateDiff(left, null);

    // Then
    assertFalse(diffResult.isConfigured());
  }

  @Test
  void calculateDiff_notNullNull_notConfigured() {
    // Given
    Base64Differ differ = new Base64Differ();
    String right = "QmFzZTY0RGlmZmVyVGVzdCByaWdodCBTdHJpbmc="; // "Base64DifferTest right String"

    // When
    DiffResult diffResult = differ.calculateDiff(null, right);

    // Then
    assertFalse(diffResult.isConfigured());
  }

  @Test
  void calculateDiff_notBase64_notConfigured() {
    // Given
    Base64Differ differ = new Base64Differ();
    String right = "Base64DifferTest right String"; // "Base64DifferTest right String"
    String left = "QmFzZTY0RGlmZmVyQUFBQSBhYSBiYiB0dCBmZiA="; // "Base64DifferAAAA aa bb tt ff "

    // When
    DiffResult diffResult = differ.calculateDiff(left, right);

    // Then
    assertFalse(diffResult.isConfigured());
  }

  @Test
  void calculateDiff_diffLength_notEqualLength() {
    // Given
    Base64Differ differ = new Base64Differ();
    String right =
        "QmFzZTY0RGlmZmVyVGVzdCByaWdodCBiaWcgU3RyaW5n"; // "Base64DifferTest right big String"
    String left = "QmFzZTY0RGlmZmVyVGVzdCBsZWZ0IFN0cmluZw=="; // "Base64DifferTest left String"

    // When
    DiffResult diffResult = differ.calculateDiff(left, right);

    // Then
    assertFalse(diffResult.getLengthEqual());
  }

  @Test
  void calculateDiff_partsEqual_equal() {
    // Given
    Base64Differ differ = new Base64Differ();
    String right = "QmFzZTY0RGlmZmVyVGVzdCBTdHJpbmc="; // "Base64DifferTest String"
    String left = "QmFzZTY0RGlmZmVyVGVzdCBTdHJpbmc="; // "Base64DifferTest String"

    // When
    DiffResult diffResult = differ.calculateDiff(left, right);

    // Then
    assertTrue(diffResult.getEqual());
  }

  @Test
  void calculateDiff_partsNotEqual_diffNotEmpty() {
    // Given
    Base64Differ differ = new Base64Differ();
    String right = "QmFzZTY0RGlmZmVyVGVzdCBhYSBiYiBmZiB0dA=="; // "Base64DifferTest aa bb ff tt"
    String left = "QmFzZTY0RGlmZmVyQUFBQSBhYSBiYiB0dCBmZg=="; // "Base64DifferAAAA aa bb tt ff"

    // When
    DiffResult diffResult = differ.calculateDiff(left, right);

    // Then
    assertFalse(diffResult.getDiffs().isEmpty());
  }
}
