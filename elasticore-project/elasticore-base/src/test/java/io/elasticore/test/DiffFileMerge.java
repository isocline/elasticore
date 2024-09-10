package io.elasticore.test;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.DeltaType;
import com.github.difflib.patch.Patch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiffFileMerge {
    public static void main(String[] args) throws Exception {
        // 파일 경로 설정
        String A1 = "C:\\workspace\\Isocline\\elasticore\\data\\A1.txt";
        String B1 = "C:\\workspace\\Isocline\\elasticore\\data\\B1.txt";

        // 원본 파일(A1)과 수정된 파일(B1)을 읽기
        List<String> originalFile = Files.readAllLines(Paths.get(A1));
        List<String> modifiedFile = Files.readAllLines(Paths.get(B1));

        // 두 파일의 차이를 계산 (A1과 B1 비교)
        Patch<String> patch = DiffUtils.diff(originalFile, modifiedFile);

        System.err.println("source ");
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.err.println("source >> " + delta.getSource().toString());
        }

        System.err.println("target ");
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.err.println("target >> " + delta.getTarget().toString());
        }

        // B1 파일을 기준으로 A1의 변경 사항을 반영 (B1이 기준)
        List<String> mergedFile = mergeUsingBaseFile(originalFile, modifiedFile, patch);

        // 병합 결과 출력 (병합된 내용을 콘솔에 출력하거나 파일로 저장 가능)
        System.out.println("Merged Result:");
        for (String line : mergedFile) {
            System.out.println(line);
        }

        // 병합된 파일을 새로운 파일로 저장 (선택 사항)
        Files.write(Paths.get("C:\\workspace\\Isocline\\elasticore\\data\\merged.txt"), mergedFile);
    }

    // B1 파일을 기준으로 A1의 델타 적용하여 병합하는 메서드
    private static List<String> mergeUsingBaseFile(List<String> baseFile, List<String> modifiedFile, Patch<String> patch) throws Exception {
        List<String> resultFile = new ArrayList<>(baseFile);

        // Iterate over the deltas to find inserted or changed methods
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            if (delta.getType() == DeltaType.INSERT || delta.getType() == DeltaType.CHANGE) {
                // Extract the method block(s) from B1 (modifiedFile)
                List<List<String>> methodBlocks = extractMultipleMethods(modifiedFile, delta.getTarget().getPosition(), delta.getTarget().size());

                // Insert all valid method blocks
                for (List<String> methodBlock : methodBlocks) {
                    if (isValidMethod(methodBlock)) {
                        // Insert before the last closing brace in A1 (baseFile)
                        int insertPosition = findLastClosingBracePosition(resultFile);
                        resultFile.addAll(insertPosition, methodBlock);
                        resultFile.add(insertPosition + methodBlock.size(), "");
                    }
                }
            }
        }
        return resultFile;
    }

    // Extract multiple method blocks from the given file, starting from the given position
    private static List<List<String>> extractMultipleMethods(List<String> file, int startPosition, int deltaSize) {
        List<List<String>> methodBlocks = new ArrayList<>();
        List<String> currentMethod = new ArrayList<>();
        int braceCount = 0;
        boolean methodStarted = false;

        // Loop through the delta block to extract methods
        for (int i = startPosition; i < startPosition + deltaSize; i++) {
            String line = file.get(i);

            // Track method block
            if (line.contains("{")) {
                braceCount++;
                methodStarted = true;
            }
            if (methodStarted) {
                currentMethod.add(line);
            }
            if (line.contains("}")) {
                braceCount--;
                if (braceCount == 0 && methodStarted) {
                    methodBlocks.add(new ArrayList<>(currentMethod)); // Add the complete method
                    currentMethod.clear(); // Reset for next method
                    methodStarted = false;
                }
            }
        }

        return methodBlocks;
    }

    private static final String METHOD_SIGNATURE_REGEX =
            "(public|protected|private|static|final|abstract|synchronized)?\\s*" + // Optional access modifiers
                    "(<.*>)?\\s*" +                                                      // Optional generic types
                    "[\\w\\[\\]]+\\s+" +                                                  // Return type (primitive, object, array)
                    "\\w+\\s*" +                                                          // Method name
                    "\\(.*\\)\\s*" +                                                      // Parameter list
                    "(throws\\s+\\w+(,\\s*\\w+)*)?\\s*\\{";                               // Optional 'throws' clause

    public static boolean isValidMethod(List<String> codeLines) {
        if (codeLines == null || codeLines.isEmpty()) {
            return false;
        }

        StringBuilder fullMethod = new StringBuilder();

        // Concatenate all the lines to form a complete method block as one string
        for (String line : codeLines) {
            fullMethod.append(line.trim()).append(" ");
        }

        // Regular expression pattern for method signature
        Pattern signaturePattern = Pattern.compile(METHOD_SIGNATURE_REGEX);
        Matcher signatureMatcher = signaturePattern.matcher(fullMethod.toString());

        if (!signatureMatcher.find()) {
            return false; // No method signature found
        }

        // Ensure that the method body contains balanced braces
        return hasBalancedBraces(fullMethod.toString());
    }
    // Helper method to check if the method body has balanced curly braces
    private static boolean hasBalancedBraces(String method) {
        int openBraces = 0;

        for (char c : method.toCharArray()) {
            if (c == '{') {
                openBraces++;
            } else if (c == '}') {
                openBraces--;
                if (openBraces < 0) {
                    return false; // Found a closing brace without a matching opening brace
                }
            }
        }

        return openBraces == 0; // All braces should be balanced
    }
    // Check if the extracted block is a valid Java method
    private static boolean isValidMethod2(List<String> methodBlock) {
        for (String line : methodBlock) {
            // Improved regex to match method signatures with or without access modifiers, including generics and parameter lists
            if (line.trim().matches("^\\s*(public|private|protected)?\\s*\\w+(<.*>)?\\s+\\w+\\s*\\(.*\\)\\s*\\{")) {
                return true;  // Valid method detected
            }
            // Handle cases with no access modifier like "List<String> listTest() {"
            if (line.trim().matches("^\\s*\\w+(<.*>)?\\s+\\w+\\s*\\(.*\\)\\s*\\{")) {
                return true;  // Valid method without access modifier
            }
        }
        return false;
    }


    // Find the position of the last closing brace in the base file (before which new methods should be inserted)
    private static int findLastClosingBracePosition(List<String> file) {
        for (int i = file.size() - 1; i >= 0; i--) {
            if (file.get(i).trim().equals("}")) {
                return i;
            }
        }
        return file.size(); // Default to the end of the file
    }
}
