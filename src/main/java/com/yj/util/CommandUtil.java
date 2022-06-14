package com.yj.util;

import com.yj.model.ResponseData;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令行工具类
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2020/9/10 16:36
 */
@Slf4j
public class CommandUtil {

    /**
     * 执行command指令
     *
     * @param command
     * @return boolean
     * @author 邹敦宇
     * @date 2020-09-10 16:43:08
     **/
    public static ResponseData executeCommand(String command) {
        List<String> logList = new ArrayList<>();
        // Process可以控制该子进程的执行或获取该子进程的信息
        Process process = null;
        try {
            log.info("exec cmd : {}", command);
            // exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            process = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                log.info(s);
                logList.add(s);
                if (logList.size() > 10) {
                    break;
                }
            }
            while ((s = stdError.readLine()) != null) {
                log.error(s);
                logList.add(s);
                if (logList.size() > 10) {
                    break;
                }
            }

            // 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
            int exitStatus = process.waitFor();

            if (exitStatus != 0) {
                log.error("exec cmd exitStatus {}", exitStatus);
                return ResponseData.error(logList.toString());
            } else {
                log.info("exec cmd exitStatus {}", exitStatus);
                return ResponseData.success(logList);
            }
        } catch (InterruptedException e) {
            log.error("InterruptedException exec {}", command, e);
            return ResponseData.error(e.getMessage());
        } catch (IOException e) {
            log.error(" exec {} error", command, e);
            return ResponseData.error(e.getMessage());
        } finally {
            if (process != null) {
                // 销毁子进程
                process.destroy();
            }
        }
    }
}
