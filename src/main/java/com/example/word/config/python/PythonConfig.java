package com.example.word.config.python;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PythonConfig {

    @Value("${python.libs.path")
    private String pythonLibsPath;

    @Bean
    public PythonInterpreter pythonInterpreter() {
        // PythonInterpreter 초기화 설정
        var props = new Properties();
        props.setProperty("python.import.site", "false"); // site 모듈 비활성화
        PythonInterpreter.initialize(System.getProperties(), props, new String[0]);

        PythonInterpreter interpreter = new PythonInterpreter();

        // sys.path 수정하여 Python 라이브러리 경로 추가
        interpreter.exec("import sys");
        interpreter.exec("sys.path.append('" + pythonLibsPath + "')");

        return interpreter;
    }

}
