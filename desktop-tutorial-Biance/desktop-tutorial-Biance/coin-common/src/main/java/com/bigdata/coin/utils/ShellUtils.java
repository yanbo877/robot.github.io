package com.bigdata.coin.utils;

import com.bigdata.coin.common.CoinCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.bigdata.coin.exception.CoinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ShellUtils.class);

    /**
     * 执行shell命令.
     */
    public static String exec(String command) {
        StringBuilder returnString = new StringBuilder();
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            throw new CoinException(CoinCode.INPUT_PARAMS, "Create runtime false!");
        }
        try {
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(
                new InputStreamReader(pro.getInputStream(), Charset.forName("UTF-8")));
            PrintWriter output = new PrintWriter(
                new OutputStreamWriter(pro.getOutputStream(), Charset.forName("UTF-8")));
            String line;
            while ((line = input.readLine()) != null) {
                // returnString = returnString + line + "\n";
                returnString.append(line).append("\n");
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            throw new CoinException(CoinCode.GENERAL, ex.getMessage());
        }
        return returnString.toString();
    }

    /**
     * 执行shell命令.
     */
    public static Map<String, Object> execmd(String cmd)
        throws InterruptedException, IOException, ExecutionException {
        Map result = new HashMap<String, Object>();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(cmd);
        final String inputMsg = getRunTimeMessage(process.getInputStream());
        final String errorMsg = getRunTimeMessage(process.getErrorStream());
        int status = process.waitFor();
        process.destroy();

        result.put("status", status);
        result.put("inputMsg", inputMsg);
        result.put("errorMsg", errorMsg);

        LOGGER.info("status=" + status);
        LOGGER.info("inputMsg=" + inputMsg);
        LOGGER.info("errorMsg=" + errorMsg);

        return result;
    }

    /**
     * .
     */
    public static String getRunTimeMessage(final InputStream input)
        throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        ExecutorService exc = Executors.newCachedThreadPool();
        FutureTask<String> callableTask = (FutureTask<String>) exc.submit(new Callable<String>() {
            @Override
            public String call() throws InterruptedException, UnsupportedEncodingException {
                StringBuilder returnString = new StringBuilder();
                Reader reader = new InputStreamReader(input, "UTF-8");
                BufferedReader bf = new BufferedReader(reader);
                String line;
                try {
                    while ((line = bf.readLine()) != null) {
                        returnString.append(line).append("\n");
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                return returnString.toString();
            }
        });
        String result = callableTask.get();
        exc.shutdown();
        return result;
    }
}
