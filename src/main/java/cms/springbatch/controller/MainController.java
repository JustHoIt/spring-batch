package cms.springbatch.controller;


import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@AllArgsConstructor
public class MainController {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final JobRegistry jobRegistry;

    @GetMapping("/first")
    public String firstApi(@RequestParam("value") String value) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", value)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("first"), jobParameters);

        return "OK";
    }
}
