package com.alna.software.ci.service;

import com.alna.software.ci.domain.jenkins.Job;
import com.alna.software.ci.exception.JenkinsApplicationException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

@Service
class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private static final String TEMPLATE_NAME = "template";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * Create message from job
     *
     * @param job job
     * @return message
     */
    public String createMessage(final Job job) {
        LOGGER.info("createMessage({})", job);
        try {
            Template template = createTemplate(
                    messageSourceAccessor.getMessage("jenkins.message.template." + job.getBuild().getPhase() + ".path")
            );
            Writer result = new StringWriter();
            template.process(job, result);

            return result.toString();
        } catch (TemplateException | IOException e) {
            throw new JenkinsApplicationException("error.message.bad.template").withCause(e);
        }
    }

    /**
     * Sukurti sablona
     *
     * @param templatePath path to template
     * @return sablonas
     */
    private Template createTemplate(String templatePath) {
        try {
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate(TEMPLATE_NAME, FileUtils.readFileToString(new File(templatePath), Charset.defaultCharset()));

            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
            configuration.setTemplateLoader(stringTemplateLoader);
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            configuration.setLogTemplateExceptions(false);

            return configuration.getTemplate(TEMPLATE_NAME);
        } catch (IOException e) {
            throw new JenkinsApplicationException("error.message.bad.template.content").withCause(e);
        }
    }

}
