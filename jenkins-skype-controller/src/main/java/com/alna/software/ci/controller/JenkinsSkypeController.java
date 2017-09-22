package com.alna.software.ci.controller;

import com.alna.software.ci.domain.jenkins.Job;
import com.alna.software.ci.service.SkypeService;
import com.alna.software.ci.service.param.SkypeMessageParam;
import com.alna.software.ci.utils.JenkinsConvert;
import com.alna.software.ci.utils.JenkinsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JenkinsSkypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsSkypeController.class);

    @Autowired
    private SkypeService skypeService;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @RequestMapping(value = "/{groupIdBase64}/skype.json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotification(@PathVariable String groupIdBase64, @RequestBody Job job) {
        LOGGER.info("sendNotification({},{})", groupIdBase64, job);

        skypeService.sendMessate(
                new SkypeMessageParam().
                        withGroupId(JenkinsConvert.toString(groupIdBase64)).
                        withJob(job)
        );
    }

    @ExceptionHandler(Throwable.class)
    public void handleError(HttpServletResponse httpServletResponse, Throwable throwable) throws IOException {
        LOGGER.info("handleError({})", throwable);

        logError(throwable);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JenkinsMessage.getMessageBy(throwable, messageSourceAccessor));
    }

    private void logError(Throwable throwable) {
        LOGGER.error("", throwable);
    }
}
