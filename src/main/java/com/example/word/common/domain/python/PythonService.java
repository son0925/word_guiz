package com.example.word.common.domain.python;

import com.example.word.common.domain.statistics.model.StatisticsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PythonService {

    private final PythonInterpreter pythonInterpreter;

    private final ObjectMapper objectMapper;

    @Value("${python.script.path}")
    private String pythonScriptPath;


    public String getGraph(List<StatisticsEntity> statisticsList) throws IOException {
        // statisticsList를 JSON 형식으로 변환
        String jsonStats = objectMapper.writeValueAsString(statisticsList);

        // JSON 데이터를 파일에 저장
        File jsonFile = new File(pythonScriptPath + "statistics_data.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
            writer.write(jsonStats);
        }


        // ProcessBuilder로 Python 스크립트 실행
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // 파이썬 스크립트가 결과를 저장할 파일을 읽고 반환
        File outputFile = new File(pythonScriptPath + "output_image.txt");  // Base64 데이터를 저장할 파일
        String result = readFile(outputFile);

        return result;
    }

    // 파일 읽기 함수
    private String readFile(File file) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }

}
