package com.root.config;

import com.root.common.SysfileCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Configuration
public class SysAIStartConfig {

    @Bean
    public void initAI() {
        try {
//            ProcessBuilder processBuilder = new ProcessBuilder("python", resource.getURI().getPath());
            // Python脚本路径
            //String pythonScriptPath = "E:\\SJJK\\self-cognition\\api_XF.py";
            // String pythonScriptPath = "/D:/软件项目/java/tomato_git/tomato/tomato_websocket/target/classes/api_XF.py";
            System.out.println(System.getProperty("user.dir"));
            String pythonScriptPath = SysfileCommon.rootPath() + "/tomato_websocket/src/main/resources/api_XF.py";
            //          pythonScriptPath = pythonScriptPath.substring(1);
            System.out.println(pythonScriptPath);
            String pythonCommend = "python";
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("nix") || os.contains("nux") || os.contains("linux")) {
                pythonCommend = "python3";
                System.out.println("Linux操作系统");
            } else if (os.contains("win")) {
                System.out.println("Windows操作系统");
            } else {
                System.out.println("未知操作系统");
            }
            ProcessBuilder processBuilder = new ProcessBuilder(pythonCommend, pythonScriptPath);

            processBuilder.redirectErrorStream(true);


            Process process = processBuilder.start();

            // 启动异步线程来读取标准输出流
            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("Python Output: " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            outputThread.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String stringToUnicode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append("\\u").append(Integer.toHexString(c));
        }
        return sb.toString();
    }
}
