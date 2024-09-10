package io.elasticore.test;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Chunk;
import com.github.difflib.patch.DeltaType;
import com.github.difflib.patch.Patch;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DiffFileTest {
    public static void main(String[] args) throws Exception {
        // 파일 경로 설정
        String A1 = "C:\\workspace\\Isocline\\elasticore\\data\\A1.txt";
        String B1 = "C:\\workspace\\Isocline\\elasticore\\data\\B1.txt";

        // 원본 파일(A1)과 수정된 파일(B1)을 읽기
        List<String> originalFile = Files.readAllLines(Paths.get(A1));
        List<String> modifiedFile = Files.readAllLines(Paths.get(B1));

        // 두 파일의 차이를 계산 (A1과 B1 비교)
        Patch<String> patch = DiffUtils.diff(originalFile, modifiedFile);

        // B1 파일을 기준으로 A1의 변경 사항을 반영 (B1이 기준)
        List<String> mergedFile = mergeUsingBaseFile(modifiedFile, patch);

        // 병합 결과 출력 (병합된 내용을 콘솔에 출력하거나 파일로 저장 가능)
        System.out.println("Merged Result:");
        for (String line : mergedFile) {
            System.out.println(line);
        }

        // 병합된 파일을 새로운 파일로 저장 (선택 사항)
        Files.write(Paths.get("C:\\workspace\\Isocline\\elasticore\\data\\merged.txt"), mergedFile);
    }

    // B1 파일을 기준으로 A1의 델타 적용하여 병합하는 메서드
    private static List<String> mergeUsingBaseFile(List<String> baseFile, Patch<String> patch) throws Exception {
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            // Delta의 타입에 따라 처리 (CHANGE, DELETE, INSERT)
            if (delta.getType() == DeltaType.CHANGE) {
                System.out.println("Change detected: " + delta);
                // baseFile의 내용을 수정된 내용으로 덮어씌움
                applyDelta(baseFile, delta);
            } else if (delta.getType() == DeltaType.DELETE) {
                System.out.println("Delete detected: " + delta);
                // baseFile에서 삭제된 부분을 처리
                applyDelta(baseFile, delta);
            } else if (delta.getType() == DeltaType.INSERT) {
                System.out.println("Insert detected: " + delta);
                // baseFile에 추가된 부분을 처리
                applyDelta(baseFile, delta);
            }
        }
        return baseFile;
    }

    // Delta 적용 메서드 (B1 파일을 기준으로 A1의 변경 사항 반영)
    private static void applyDelta(List<String> baseFile, AbstractDelta<String> delta) {
        Chunk<String> sourceChunk = delta.getSource();
        Chunk<String> targetChunk = delta.getTarget();

        // Delta를 처리하여 baseFile(B1)에 반영
        int position = sourceChunk.getPosition();

        // 현재 baseFile(B1)의 크기가 delta가 적용될 위치보다 작으면 그 위치까지 빈 라인을 채움
        while (baseFile.size() < position) {
            baseFile.add(""); // 빈 라인을 추가하여 크기를 맞춤
        }

        // DELETE 또는 CHANGE일 경우 해당 위치에서 제거
        if (delta.getType() == DeltaType.CHANGE || delta.getType() == DeltaType.DELETE) {
            for (int i = 0; i < sourceChunk.size(); i++) {
                if (position < baseFile.size()) {
                    baseFile.remove(position); // baseFile에서 삭제
                }
            }
        }

        // CHANGE 또는 INSERT일 경우 해당 위치에 추가
        if (delta.getType() == DeltaType.CHANGE || delta.getType() == DeltaType.INSERT) {
            baseFile.addAll(position, targetChunk.getLines()); // 수정된 내용을 추가
        }
    }
}
