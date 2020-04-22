package com.neusoft.bs.demo.utils;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {

    private static final Logger log = LoggerFactory.getLogger(TimeUtils.class);

    public static void sleepMillis(final long time) {
        try {
            log.debug("sleep: {}", time);
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}